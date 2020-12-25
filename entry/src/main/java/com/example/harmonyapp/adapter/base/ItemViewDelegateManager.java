package com.example.harmonyapp.adapter.base;

import com.example.harmonyapp.adapter.ViewHolder;

import java.util.HashMap;

/**
 * delegate manage
 *
 * @param <T> type
 */
public class ItemViewDelegateManager<T> {
    private HashMap<Integer, ItemViewDelegate<T>> delegates;

    /**
     * constructor of ItemViewDelegateManager
     */
    public ItemViewDelegateManager() {
        delegates = new HashMap<>(0);
    }

    /**
     * add delegate
     *
     * @param delegate delegate
     */
    public void addDelegate(ItemViewDelegate<T> delegate) {
        int viewType = delegates.size();
        if (delegate != null) {
            delegates.put(viewType, delegate);
        }
    }

    /**
     * constructor of convert
     *
     * @param viewHolder viewHolder
     * @param item       item
     * @param position   position
     */
    public void convert(ViewHolder viewHolder, T item, int position) {
        int delegatesCount = delegates.size();
        for (int key = delegatesCount - 1; key >= 0; key--) {
            ItemViewDelegate<T> delegate = delegates.get(key);
            if (delegate.isForViewType(item, position)) {
                delegate.convert(viewHolder, item, position);
                return;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegateManager added that matches position=" + position + " in data source");
    }

    /**
     * getItemViewDelegate
     *
     * @param item     item
     * @param position position
     * @return ItemViewDelegate
     */
    public ItemViewDelegate getItemViewDelegate(T item, int position) {
        int delegatesCount = delegates.size();
        for (int key = delegatesCount - 1; key >= 0; key--) {
            ItemViewDelegate<T> delegate = delegates.get(key);
            if (delegate.isForViewType(item, position)) {
                return delegate;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }
}
