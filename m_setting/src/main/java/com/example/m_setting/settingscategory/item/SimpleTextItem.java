package com.example.m_setting.settingscategory.item;

import com.example.m_setting.ResourceTable;
import com.example.m_setting.datamodel.CategoryItemBase;

import ohos.agp.components.Component;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.app.Context;

/**
 * Single line category item with a left text and right text
 */
public class SimpleTextItem implements CategoryItemBase {
    /**
     * Category type of SingleListItem
     */
    public static final int ITEM_TYPE = 1;

    private final Context context;
    private String leftText;
    private String rightText;

    /**
     * SingleTextItem constructor
     *
     * @param context Context
     * @param data    SingleTextItemData in display
     */
    public SimpleTextItem(Context context, SimpleTextItemData data) {
        this.context = context;
        leftText = data.getLeftText();
        rightText = data.getRightText();
    }

    @Override
    public Component createComponent() {
        return LayoutScatter.getInstance(context).parse(
                ResourceTable.Layout_setting_simple_text, null, false);
    }

    @Override
    public int getItemType() {
        return ITEM_TYPE;
    }

    @Override
    public void bindComponent(Component component) {
        // item context
        Text contentText = (Text) component.findComponentById(ResourceTable.Id_item_content_text);
        contentText.setText(leftText);

        // item right context
        Text rightContentText = (Text) component.findComponentById(ResourceTable.Id_item_right_content_text);
        rightContentText.setText(rightText);
    }
}
