package com.example.harmonyapp.slice;

import com.example.harmonyapp.ResourceTable;
import com.example.harmonyapp.adapter.CommonAdapter;
import com.example.harmonyapp.adapter.SampleItemAdapter;
import com.example.harmonyapp.adapter.ViewHolder;
import com.example.harmonyapp.base.BaseAbilitySlice;
import com.example.harmonyapp.butterknife.Bind;
import com.example.harmonyapp.constant.RouteMap;
import com.example.harmonyapp.model.PluginEntry;
import com.example.harmonyapp.model.SampleEntry;
import com.example.harmonyapp.util.LogUtils;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.ListContainer;
import ohos.agp.components.Text;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;

public class ListContainerAbilitySlice extends BaseAbilitySlice implements Component.ClickedListener {
    @Bind(ResourceTable.Id_dl_left)
    private DirectionalLayout mDlBack;
    @Bind(ResourceTable.Id_tv_title)
    private Text mTvTitle;
    @Bind(ResourceTable.Id_selector_list)
    private ListContainer listContainer;

    private CommonAdapter<SampleEntry> sampleEntryAdapter;

    @Override
    public int getLayout() {
        return ResourceTable.Layout_ability_plugin_container_page;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initList();
        initListener();
    }

    @Override
    protected void initData(Intent intent) {
        super.initData(intent);
        String content = intent.getStringParam("content");
        LogUtils.info("TEST---------->","content============="+content);
        mTvTitle.setText(content);
    }

    private void initList(){
        sampleEntryAdapter = new CommonAdapter<SampleEntry>(getContext(), ResourceTable.Layout_item_sample,getData()) {
            @Override
            protected void convert(ViewHolder viewHolder, SampleEntry item, int position) {
                viewHolder.setText(ResourceTable.Id_item_name, item.getName());
//                viewHolder.setText(ResourceTable.Id_commrate_tv, String.valueOf(item.getCommentCount()));
//                viewHolder.setText(ResourceTable.Id_price_tv, String.valueOf(item.getPrice()));
//                viewHolder.setImageResource(ResourceTable.Id_image_car, ResourceTable.Media_icon_shopcar);
            }
        };
        listContainer.setItemProvider(sampleEntryAdapter);

    }

    private void initListener(){
        mDlBack.setClickedListener(this);
        listContainer.setItemClickedListener((container, component, position, id) -> {
            SampleEntry item = (SampleEntry) container.getItemProvider().getItem(position);

            new ToastDialog(this)
                    .setText("clicked:"+item.getName())
                    // Toast显示在界面中间
                    .setAlignment(LayoutAlignment.BOTTOM)
                    .show();
        });
    }

    private ArrayList<SampleEntry> getData() {
        ArrayList<SampleEntry> list = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            list.add(new SampleEntry("张三"+i));
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

    @Override
    public void onClick(Component component) {
        switch (component.getId()){
            case ResourceTable.Id_dl_left:
//                AbilitySlice targetSlice = new SecondsAbilitySlice();
//                Intent intent = new Intent();
//                intent.setParam("value", 10);
//                present(targetSlice, intent);
                terminateAbility();
                break;
        }
    }
}
