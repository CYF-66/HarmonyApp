package com.example.m_card.itemprovider;

import java.util.List;

import com.example.m_card.ResourceTable;
import com.example.m_card.datamodel.ListItemInfo;
import com.example.m_card.viewholder.DefaultDoubleLineList;

import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.Image;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.RecycleItemProvider;
import ohos.app.AbilityContext;

/**
 * ListItemProvider
 */
public class ListItemProvider extends RecycleItemProvider {
    private List<ListItemInfo> itemList;
    private AbilityContext context;

    /**
     * Item Provider Constructor
     *
     * @param itemList List of data model
     * @param context  context
     */
    public ListItemProvider(List<ListItemInfo> itemList, AbilityContext context) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int index) {
        return itemList.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public Component getComponent(int index, Component component, ComponentContainer componentContainer) {
        Component itemComponent = component;
        DefaultDoubleLineList viewHolder;
        if (itemComponent == null) {
            itemComponent = LayoutScatter.getInstance(componentContainer.getContext())
                    .parse(ResourceTable.Layout_default_doubleline_list, componentContainer, false);
        }
        ListItemInfo content = (ListItemInfo) getItem(index);
        viewHolder = new DefaultDoubleLineList(itemComponent);
        viewHolder.setContent(context, content);

        // Make divider invisible when current item is the last one
        if (index == getCount() - 1) {
            Image mDivider = (Image) itemComponent.findComponentById(ResourceTable.Id_divider);
            if (mDivider != null) {
                mDivider.setVisibility(Component.INVISIBLE);
            }
        }
        return itemComponent;
    }
}
