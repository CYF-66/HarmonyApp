package com.example.m_setting.settingscategory;

import com.example.m_setting.ResourceTable;
import com.example.m_setting.datamodel.Category;
import com.example.m_setting.datamodel.CategoryItemBase;

import com.example.m_setting.settingscategory.item.GroupHeader;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

import java.util.List;

/**
 * This is a sample to implement Category interface for a simple settings page. You can declare your own Category to
 * achieve different UI designs. The items are declared in resources/rawfile/settingsPattern.json, you can modify the
 * json data to expend more settings.
 */
public class SettingsCategory implements Category {
    private static final float CORNER_RADIUS = 32;

    private static final int TOAST_DURATION = 2000;
    private final int id;
    private Context context;
    private List<CategoryItemBase> categoryItems;

    /**
     * SettingsCategory constructor
     *
     * @param context       context
     * @param categoryItems Category Items
     */
    SettingsCategory(Context context, List<CategoryItemBase> categoryItems, int id) {
        this.context = context;
        this.categoryItems = categoryItems;
        this.id = id;
    }

    private void setComponentShapeAndBackground(Component component, int index) {
        // Set corners and background
        ComponentContainer.LayoutConfig layoutConfig = component.getLayoutConfig();
        ShapeElement shapeElement = (ShapeElement) component.getBackgroundElement();

        boolean isCategoryFirstItem = hasHeader() ? (index == 1) : (index == 0);
        boolean isCategoryLastItem = index == (categoryItems.size() - 1);
        if (isCategoryFirstItem && isCategoryLastItem) {
            // only one item in category, set all corner radius and bottom margin 12vp
            shapeElement.setCornerRadiiArray(new float[]{CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS,
                    CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS});
            Component componentItemDivider = component.findComponentById(ResourceTable.Id_item_divider);
            componentItemDivider.setVisibility(Component.HIDE);
        } else if (isCategoryFirstItem) {
            // first item in category, set top two corners radius
            shapeElement.setCornerRadiiArray(new float[]{CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS,
                    0, 0, 0, 0});
        } else if (isCategoryLastItem) {
            // last Item in category, set down two corners radius and bottom margin 12vp
            shapeElement.setCornerRadiiArray(new float[]{0, 0, 0, 0, CORNER_RADIUS, CORNER_RADIUS, CORNER_RADIUS,
                    CORNER_RADIUS});
            Component componentItemDivider = component.findComponentById(ResourceTable.Id_item_divider);
            componentItemDivider.setVisibility(Component.HIDE);
        } else {
            // middle item in category, set no corners radius and show divider
            shapeElement.setCornerRadiiArray(new float[]{0, 0, 0, 0, 0, 0, 0, 0});
            Component componentItemDivider = component.findComponentById(ResourceTable.Id_item_divider);
            componentItemDivider.setVisibility(Component.VISIBLE);
        }

        component.setLayoutConfig(layoutConfig);
        component.setBackground(shapeElement);
    }

    @Override
    public Component createComponent(int index) {
        CategoryItemBase categoryItem = categoryItems.get(index);
        return categoryItem.createComponent();
    }

    @Override
    public void bindComponent(Component component, int index) {
        CategoryItemBase categoryItem = categoryItems.get(index);
        categoryItem.bindComponent(component);
        if (categoryItem.getItemType() != GroupHeader.ITEM_TYPE) {
            setComponentShapeAndBackground(component, index);
        }
    }

    /**
     * Get total category items count
     *
     * @return count of items
     */
    @Override
    public int getCategoryItemsCount() {
        return categoryItems.size();
    }

    @Override
    public int getCategoryItemType(int index) {
        return categoryItems.get(index).getItemType();
    }

    @Override
    public boolean hasHeader() {
        return categoryItems.get(0).getItemType() == GroupHeader.ITEM_TYPE;
    }

    @Override
    public void onItemClick(int position) {
        if (categoryItems.get(position).getItemType() != GroupHeader.ITEM_TYPE) {
            ToastDialog toast = new ToastDialog(context);
            toast.setText("Clicking Category " + id + " item " + position).setDuration(TOAST_DURATION).show();
        }
    }
}
