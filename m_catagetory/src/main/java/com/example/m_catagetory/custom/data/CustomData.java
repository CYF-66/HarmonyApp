package com.example.m_catagetory.custom.data;

import com.example.m_catagetory.custom.categorylist.HeadItem;
import com.example.m_catagetory.custom.categorylist.SearchItem;
import com.example.m_catagetory.custom.categorylist.SingleListItem;
import com.example.m_catagetory.custom.categorylist.CategoryItemFactory;
import com.example.m_catagetory.custom.categorylist.TestCategory;
import com.example.m_catagetory.datamodel.Category;
import com.example.m_catagetory.datamodel.CategoryItemBase;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Some custom data to display.
 */
public class CustomData {
    private static final int[][] CATEGORY_DATA = new int[][]{
            {SearchItem.ITEM_TYPE},
            {HeadItem.ITEM_TYPE, SingleListItem.ITEM_TYPE},
            {HeadItem.ITEM_TYPE, SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE},
            {HeadItem.ITEM_TYPE, SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE},
            {HeadItem.ITEM_TYPE, SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE,
                    SingleListItem.ITEM_TYPE},
            {HeadItem.ITEM_TYPE, SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE,
                    SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE},
            {HeadItem.ITEM_TYPE, SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE,
                    SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE, SingleListItem.ITEM_TYPE},
    };

    private static final String CATEGORY_TEXT = "Category";

    private static final String SINGLE_LEFT_TEXT = "single list";

    private static final String SINGLE_RIGHT_TEXT = "right text";

    /**
     * Return some custom data to display.
     *
     * @param context current context
     * @return custom category list
     */
    public static List<Category> getCustomData(Context context) {
        ArrayList<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < CATEGORY_DATA.length; i++) {
            int[] items = CATEGORY_DATA[i];
            ArrayList<CategoryItemBase> itemList = new ArrayList<>();
            for (int j = 0; j < items.length; j++) {
                switch (items[j]) {
                    case HeadItem.ITEM_TYPE:
                        itemList.add(CategoryItemFactory.createListItem(context, HeadItem.ITEM_TYPE,
                                CATEGORY_TEXT + i));
                        break;
                    case SingleListItem.ITEM_TYPE:
                        itemList.add(CategoryItemFactory.createListItem(context, SingleListItem.ITEM_TYPE,
                                SINGLE_LEFT_TEXT + j, SINGLE_RIGHT_TEXT));
                        break;
                    case SearchItem.ITEM_TYPE:
                        itemList.add(CategoryItemFactory.createListItem(context, SearchItem.ITEM_TYPE));
                        break;
                    default:
                        break;
                }
            }
            TestCategory testCategory = new TestCategory(context, itemList, i);
            categoryList.add(testCategory);
        }
        return categoryList;
    }
}
