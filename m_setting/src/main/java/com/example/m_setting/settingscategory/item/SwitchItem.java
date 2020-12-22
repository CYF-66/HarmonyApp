package com.example.m_setting.settingscategory.item;

import com.example.m_setting.slice.SettingsListSlice;
import com.example.m_setting.ResourceTable;
import com.example.m_setting.datamodel.CategoryItemBase;

import ohos.agp.components.Component;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.Image;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.app.Context;
import ohos.data.preferences.Preferences;

/**
 * Double line category item with a main text, a sub text and a switch button
 */
public class SwitchItem implements CategoryItemBase {
    /**
     * Category item type of DoubleListItem
     */
    public static final int ITEM_TYPE = 2;

    private boolean switchOn;
    private String mainText;
    private String subText;
    private Context context;

    /**
     * SwitchItem constructor
     *
     * @param context Context
     * @param data    SwitchItemData in display
     */
    public SwitchItem(Context context, SwitchItemData data) {
        this.context = context;
        mainText = data.getMainText();
        subText = data.getSubText();
        Preferences preferences = SettingsListSlice.getSettingsAbilityDatabaseHelper().getPreferences();
        switchOn = preferences.getBoolean(mainText, data.isDefaultSwitch());
    }

    @Override
    public Component createComponent() {
        return LayoutScatter.getInstance(context).parse(
                ResourceTable.Layout_setting_switch, null, false);
    }

    @Override
    public void bindComponent(Component component) {
        // double line main text
        Text mainText = (Text) component.findComponentById(ResourceTable.Id_double_line_main_content);
        mainText.setText(this.mainText);

        // double line sub text
        Text subText = (Text) component.findComponentById(ResourceTable.Id_double_line_sub_content);
        subText.setText(this.subText);

        Image switchEnableImage = (Image) component.findComponentById(ResourceTable
                .Id_double_line_switch_enable);
        Image switchDisableImage = (Image) component.findComponentById(ResourceTable
                .Id_double_line_switch_disable);
        switchEnableImage.setVisibility(switchOn ? Component.VISIBLE : Component.INVISIBLE);
        switchDisableImage.setVisibility(switchOn ? Component.INVISIBLE : Component.VISIBLE);

        DependentLayout switchImageHotArea = (DependentLayout) component
                .findComponentById(ResourceTable.Id_double_line_switch_hot_area);
        switchImageHotArea.setClickedListener(component1 -> {
            switchOn = !switchOn;
            switchEnableImage.setVisibility(switchOn ? Component.VISIBLE : Component.INVISIBLE);
            switchDisableImage.setVisibility(switchOn ? Component.INVISIBLE : Component.VISIBLE);
            Preferences preferences = SettingsListSlice.getSettingsAbilityDatabaseHelper().getPreferences();
            preferences.putBoolean(this.mainText, switchOn);
            preferences.flushSync();
        });
    }

    @Override
    public int getItemType() {
        return ITEM_TYPE;
    }
}
