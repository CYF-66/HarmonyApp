package com.example.m_catagetory.custom.categorylist;

import com.example.m_catagetory.ResourceTable;
import com.example.m_catagetory.datamodel.CategoryItemBase;

import ohos.agp.components.Component;
import ohos.agp.components.LayoutScatter;
import ohos.app.Context;

/**
 * Search bar item which display a search bar in the list container.
 */
public class SearchItem implements CategoryItemBase {
    /**
     * SearchItem type
     */
    public static final int ITEM_TYPE = 1;

    private final Context context;

    SearchItem(Context context) {
        this.context = context;
    }

    /**
     * Set component content
     *
     * @param component the component to set
     */
    @Override
    public void bindComponent(Component component) {
    }

    /**
     * Create component
     *
     * @return component
     */
    @Override
    public Component createComponent() {
        return LayoutScatter.getInstance(context).parse(ResourceTable.Layout_search_item,
                null, false);
    }

    /**
     * Get the item type of this category item
     *
     * @return category item type
     */
    @Override
    public int getItemType() {
        return ITEM_TYPE;
    }
}
