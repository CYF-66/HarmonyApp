package com.example.harmonyapp.slice;

import com.example.harmonyapp.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.TabList;
import ohos.agp.components.Text;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

public class TabListAbilitySlice extends AbilitySlice {

    private Text mTvBack;
    private Text mTvTitle;

    private TabList mTabList;

    private  String[] str={"image","Video","Audio","Other"};

    private String content;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_tab_list);
        content = intent.getStringParam("content");
        initView();
    }

    private void initView(){
        mTvBack = (Text) findComponentById(ResourceTable.Id_text_back);
        mTvTitle = (Text) findComponentById(ResourceTable.Id_text_title);
        mTvBack.setClickedListener(component -> terminateAbility());

        mTvTitle.setText(content);
        mTabList = (TabList) findComponentById(ResourceTable.Id_tab_list);
        getTab();

        mTabList.setFixedMode(true);

        mTabList.addTabSelectedListener(new TabList.TabSelectedListener() {
            @Override
            public void onSelected(TabList.Tab tab) {
                // 当某个Tab从未选中状态变为选中状态时的回调
                new ToastDialog(getContext())
                        .setText("从未选中状态变为选中状态时的回调")
                        .setAlignment(LayoutAlignment.BOTTOM)
                        .show();
            }

            @Override
            public void onUnselected(TabList.Tab tab) {
                // 当某个Tab从选中状态变为未选中状态时的回调
                new ToastDialog(getContext())
                        .setText("从选中状态变为未选中状态时的回调")
                        .setAlignment(LayoutAlignment.BOTTOM)
                        .show();
            }

            @Override
            public void onReselected(TabList.Tab tab) {
                // 当某个Tab已处于选中状态，再次被点击时的状态回调
                new ToastDialog(getContext())
                        .setText("已处于选中状态，再次被点击时的状态回调")
                        .setAlignment(LayoutAlignment.BOTTOM)
                        .show();
            }
        });
    }


    private void getTab(){
        for (int i = 0; i < str.length; i++) {
            TabList.Tab tab = mTabList.new Tab(getContext());
            tab.setText(str[i]);
            if(i == 0)
                tab.select();
            mTabList.addTab(tab);
        }
    }

    // 创建Tab并设置Tab
    private TabList.Tab createTab(String name) {
        TabList.Tab tab = mTabList.new Tab(this);
        tab.setText(name);
        tab.setName(name);
        tab.setMinWidth(64);
        tab.setPadding(12, 0, 12, 0);
        return tab;
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
