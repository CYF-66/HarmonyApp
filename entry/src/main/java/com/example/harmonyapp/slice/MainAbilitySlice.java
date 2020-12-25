package com.example.harmonyapp.slice;

import com.example.harmonyapp.ResourceTable;
import com.example.harmonyapp.adapter.CommonAdapter;
import com.example.harmonyapp.adapter.PluginAdapter;
import com.example.harmonyapp.adapter.ViewHolder;
import com.example.harmonyapp.base.BaseAbilitySlice;
import com.example.harmonyapp.model.PluginEntry;
import com.example.harmonyapp.butterknife.Bind;
import com.example.harmonyapp.constant.RouteMap;
import com.example.harmonyapp.util.LogUtils;
import com.example.harmonyapp.util.PluginUtil;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.*;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends BaseAbilitySlice implements Component.ClickedListener {
    private static final String TAG = "MainAbility MainAbilitySlice";

    @Bind(ResourceTable.Id_btn_click)
    private Button mBtn;

    @Bind(ResourceTable.Id_tf_input)
    private TextField mTf_input;

    @Bind(ResourceTable.Id_list_container)
    private ListContainer listContainer;

    private  String[] plugins={"ListContainer","TabList"};

    private CommonAdapter<PluginEntry> pluginCommonAdapter;

    @Override
    protected void initWidget() {
        super.initWidget();
        initList();
        initListener();
    }

    @Override
    protected void initData(Intent intent) {
        super.initData(intent);
        pluginCommonAdapter.update(PluginUtil.getInstance().initPluginList());
    }

    @Override
    public int getLayout() {
        return ResourceTable.Layout_ability_main;
    }

    private void initListener(){
        mBtn.setClickedListener(this);
        listContainer.setItemClickedListener((container, component, position, id) -> {
            PluginEntry item = (PluginEntry) container.getItemProvider().getItem(position);

            Intent intent = new Intent();
            String title = PluginUtil.getInstance().initPluginList().get(position).getName();
            switch (position){
                case 0:
                    intent.setParam("content",title);
                    switchPage(intent, RouteMap.PLUGIN_CONTAINER,RouteMap.ACTION_LISTCONTAINER);
                    break;
                case 1:
                    intent.setParam("content",title);
                    switchPage(intent,RouteMap.PLUGIN_CONTAINER,RouteMap.ACTION_TABLIST);
                    break;
                case 2:
                    intent.setParam("content",title);
                    switchPage(intent,RouteMap.PLUGIN_CONTAINER,RouteMap.ACTION_SCROLLVIEW);
                    break;
                case 3:
                    intent.setParam("content",title);
                    switchPage(intent,RouteMap.PLUGIN_CONTAINER,RouteMap.ACTION_TOASTDIALOG);
                    break;
            }

        });

        mTf_input.setFocusChangedListener(((component, isFocused) -> {
            LogUtils.info("TEST---------->","是否获得============="+isFocused);
            if (isFocused) {
                // 获取到焦点
            }else {
                // 失去焦点
            }
        }));

        mTf_input.addTextObserver(new Text.TextObserver() {
            @Override
            public void onTextUpdated(String s, int i, int i1, int i2) {
                pluginCommonAdapter.update(PluginUtil.getInstance().search(s));
            }
        });
    }

    private void initList(){
        pluginCommonAdapter = new CommonAdapter<PluginEntry>(getContext(), ResourceTable.Layout_item_plugin) {
            @Override
            protected void convert(ViewHolder viewHolder, PluginEntry item, int position) {
                viewHolder.setText(ResourceTable.Id_item_name, item.getName());
//                viewHolder.setText(ResourceTable.Id_commrate_tv, String.valueOf(item.getCommentCount()));
//                viewHolder.setText(ResourceTable.Id_price_tv, String.valueOf(item.getPrice()));
//                viewHolder.setImageResource(ResourceTable.Id_image_car, ResourceTable.Media_icon_shopcar);
            }
        };
        listContainer.setItemProvider(pluginCommonAdapter);

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
    private void switchPage(Intent intent,String className,String action){
        Operation operation = new Intent.OperationBuilder()
                .withBundleName(getBundleName())
                .withAbilityName(className)
                .withAction(action)
                .build();
//        intent.setParam("content","这是传参");
        intent.setOperation(operation);
        startAbility(intent);
    }
}
