package com.example.harmonyapp.slice;

import com.example.harmonyapp.MainAbility;
import com.example.harmonyapp.ResourceTable;
import com.example.harmonyapp.adapter.PluginAdapter;
import com.example.harmonyapp.adapter.SampleItemAdapter;
import com.example.harmonyapp.bean.PluginEntry;
import com.example.harmonyapp.bean.SampleItem;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.components.TabList;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    private static final String TAG = "MainAbility MainAbilitySlice";
    private Button mBtn;
    private  String[] plugins={"ListContainer","TabList"};
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initView();

    }

    private void initView(){
        ListContainer listContainer = (ListContainer) findComponentById(ResourceTable.Id_list_container);
        mBtn = (Button) findComponentById(ResourceTable.Id_btn_click);
        mBtn.setClickedListener(this);

        List<PluginEntry> list = getData();
        PluginAdapter pluginAdapter = new PluginAdapter(list,this);
        listContainer.setItemProvider(pluginAdapter);
        listContainer.setItemClickedListener((container, component, position, id) -> {
            PluginEntry item = (PluginEntry) container.getItemProvider().getItem(position);

            Intent intent = new Intent();
            switch (position){
                case 0:
                    intent.setParam("content",plugins[position]);
                    switchPage(intent,"com.example.harmonyapp.ListContainerAbility");
                    break;
                case 1:
                    intent.setParam("content",plugins[position]);
                    switchPage(intent,"com.example.harmonyapp.TabListAbility");
                    break;
            }
//            new ToastDialog(this)
//                    .setText("clicked:"+item.getName())
//                    // Toast显示在界面中间
//                    .setAlignment(LayoutAlignment.BOTTOM)
//                    .show();
        });
    }

    private ArrayList<PluginEntry> getData() {
        ArrayList<PluginEntry> list = new ArrayList<>();
        for (int i = 0; i < plugins.length; i++) {
            list.add(new PluginEntry(plugins[i]));
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
            case ResourceTable.Id_btn_click:
//                AbilitySlice targetSlice = new SecondsAbilitySlice();
//                Intent intent = new Intent();
//                intent.setParam("value", 10);
//                present(targetSlice, intent);
                Intent intent = new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withBundleName(getBundleName())
                        .withAbilityName("com.example.harmonyapp.SecondsAbility")
                        .build();
                intent.setParam("content","这是传参");
                intent.setOperation(operation);
                startAbility(intent);
                break;
        }
    }

    /**
     * 跳转
     * @param intent
     * @param className
     */
    private void switchPage(Intent intent,String className){
        Operation operation = new Intent.OperationBuilder()
                .withBundleName(getBundleName())
                .withAbilityName(className)
                .build();
//        intent.setParam("content","这是传参");
        intent.setOperation(operation);
        startAbility(intent);
    }
}
