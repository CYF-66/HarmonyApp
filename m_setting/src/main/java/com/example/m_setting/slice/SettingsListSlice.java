package com.example.m_setting.slice;

import com.example.m_setting.ResourceTable;
import com.example.m_setting.persist.SettingsAbilityDatabaseHelper;
import com.example.m_setting.settingscategory.SettingsItemFactory;
import com.example.m_setting.views.adapter.SettingsListItemProvider;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.ListContainer;

/**
 * Settings List Ability Slice
 */
public class SettingsListSlice extends AbilitySlice {
    private static SettingsAbilityDatabaseHelper settingsAbilityDatabaseHelper;

    /**
     * Get the settings ability database helper
     *
     * @return SettingsAbilityDatabaseHelper
     */
    public static SettingsAbilityDatabaseHelper getSettingsAbilityDatabaseHelper() {
        return settingsAbilityDatabaseHelper;
    }

    private ComponentContainer createComponent() {
        Component mainComponent = LayoutScatter.getInstance(this).parse(ResourceTable.Layout_main_ability,
                null, false);
        DependentLayout dependentLayout = (DependentLayout) mainComponent.findComponentById(ResourceTable
                .Id_title_area_back_icon_hot_area);
        dependentLayout.setClickedListener(component -> this.terminate());

        // Use a sample settings data initialized from json file.
        // You can provide you own data by implement SettingsItemFactory to achieve different effects
        SettingsListItemProvider itemProvider = new SettingsListItemProvider(SettingsItemFactory.initSettings(this));

        // ListContainer item provider and listener
        ListContainer listContainer = (ListContainer) mainComponent.findComponentById(ResourceTable.Id_list_view);
        if (listContainer != null) {
            listContainer.setItemProvider(itemProvider);
            listContainer.setItemClickedListener(itemProvider);
        }

        return (ComponentContainer) mainComponent;
    }

    @Override
    public void onStart(Intent intent) {
        // Database helper
        settingsAbilityDatabaseHelper = new SettingsAbilityDatabaseHelper(this);
        setUIContent(createComponent());
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
