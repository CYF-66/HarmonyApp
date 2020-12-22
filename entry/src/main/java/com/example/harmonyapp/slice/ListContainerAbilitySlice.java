package com.example.harmonyapp.slice;

import com.example.harmonyapp.ResourceTable;
import com.example.harmonyapp.adapter.SampleItemAdapter;
import com.example.harmonyapp.bean.SampleItem;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.components.Text;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;

public class ListContainerAbilitySlice extends AbilitySlice {
    private Text mTvBack;
    private Text mTvTitle;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_second_page);
        mTvBack = (Text) findComponentById(ResourceTable.Id_text_back);
        mTvTitle = (Text) findComponentById(ResourceTable.Id_text_title);
        mTvBack.setClickedListener(component -> terminateAbility());
        String content = intent.getStringParam("content");
        mTvTitle.setText(content);
        ListContainer listContainer = (ListContainer) findComponentById(ResourceTable.Id_selector_list);
        List<SampleItem> list = getData();
        SampleItemAdapter sampleItemAdapter = new SampleItemAdapter(list,this);
        listContainer.setItemProvider(sampleItemAdapter);
        listContainer.setItemClickedListener((container, component, position, id) -> {
            SampleItem item = (SampleItem) container.getItemProvider().getItem(position);
            new ToastDialog(this)
                    .setText("clicked:"+item.getName())
                    // Toast显示在界面中间
                    .setAlignment(LayoutAlignment.BOTTOM)
                    .show();
        });
    }

    private ArrayList<SampleItem> getData() {
        ArrayList<SampleItem> list = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            list.add(new SampleItem("张三"+i));
        }
        return list;
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
