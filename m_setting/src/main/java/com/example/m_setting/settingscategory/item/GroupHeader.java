package com.example.m_setting.settingscategory.item;

import com.example.m_setting.ResourceTable;
import com.example.m_setting.datamodel.CategoryItemBase;

import ohos.agp.components.Component;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.app.Context;

/**
 * Head item display before the setting group
 */
public class GroupHeader implements CategoryItemBase {
    /**
     * Category item type of HeaderItem
     */
    public static final int ITEM_TYPE = 0;

    private final Context context;
    private String headText;

    /**
     * SettingsGroupHeader constructor
     *
     * @param context  Context
     * @param headText headText in display
     */
    public GroupHeader(Context context, String headText) {
        this.context = context;
        this.headText = headText;
    }

    @Override
    public Component createComponent() {
        return LayoutScatter.getInstance(context).parse(
                ResourceTable.Layout_setting_category_header, null, false);
    }

    @Override
    public void bindComponent(Component component) {
        Text headerText = (Text) component.findComponentById(ResourceTable.Id_header_item_content_text);
        headerText.setText(headText);
    }

    @Override
    public int getItemType() {
        return ITEM_TYPE;
    }
}
