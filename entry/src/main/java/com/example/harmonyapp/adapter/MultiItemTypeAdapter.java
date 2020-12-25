package com.example.harmonyapp.adapter;

import com.example.harmonyapp.adapter.base.ItemViewDelegate;
import com.example.harmonyapp.adapter.base.ItemViewDelegateManager;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.RecycleItemProvider;
import ohos.app.Context;

import java.util.List;

/**
 * MultiItemTypeAdapter
 *
 * @param <T> generic
 */
public abstract class MultiItemTypeAdapter<T> extends RecycleItemProvider {
    List<T> dataList;
    private Context context;
    private ItemViewDelegateManager mItemViewDelegateManager;

    /**
     * constructor of MultiItemTypeAdapter
     *
     * @param context  context
     * @param dataList list type
     */
    MultiItemTypeAdapter(Context context, List<T> dataList) {
        this.context = context;
        this.dataList = dataList;
        mItemViewDelegateManager = new ItemViewDelegateManager();
    }

    /**
     * constructor of MultiItemTypeAdapter
     *
     * @param itemViewDelegate item type
     */
    @SuppressWarnings("unchecked")
    void addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemViewDelegateManager.addDelegate(itemViewDelegate);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Component getComponent(int position, Component component, ComponentContainer parent) {
        ItemViewDelegate delegate = mItemViewDelegateManager.getItemViewDelegate(dataList.get(position), position);
        int layoutId = delegate.getItemViewLayoutId();
        ViewHolder viewHolder = null;
        if (component == null) {
            Component itemView = LayoutScatter.getInstance(context).parse(layoutId, parent, false);
            viewHolder = new ViewHolder(context, itemView, parent, position);
            viewHolder.layoutId = layoutId;
        } else {
            Object object = component.getTag();
            if (object instanceof ViewHolder) {
                viewHolder = (ViewHolder) object;
                viewHolder.position = position;
            }
        }
        convert(viewHolder, getItem(position), position);
        if (viewHolder != null) {
            return viewHolder.getComponentView();
        }
        return null;
    }

    /**
     * convert to a new Collection,contains clear it
     *
     * @param viewHolder new Collection
     * @param item       new style
     * @param position   pointer
     */
    @SuppressWarnings("unchecked")
    protected void convert(ViewHolder viewHolder, T item, int position) {
        mItemViewDelegateManager.convert(viewHolder, item, position);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    /**
     * return data
     *
     * @param position position
     * @return data
     */
    @Override
    public T getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
