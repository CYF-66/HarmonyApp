package com.example.harmonyapp.adapter;

import com.example.harmonyapp.adapter.base.ItemViewDelegate;
import ohos.app.Context;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * CommonAdapter
 *
 * @param <T> type
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T> {
    /**
     * constructor of CommonAdapter
     *
     * @param context  context
     * @param layoutId id
     */
    protected CommonAdapter(Context context, final int layoutId) {
        this(context, layoutId, new ArrayList<>(0));
    }

    /**
     * constructor of CommonAdapter
     *
     * @param context  context
     * @param layoutId id
     * @param datas    list type
     */
    protected CommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder viewHolder, T data, int position) {
                CommonAdapter.this.convert(viewHolder, data, position);
            }
        });
    }

    /**
     * convert to a new Collection,contains clear it
     *
     * @param position   pointer
     * @param item       new style
     * @param viewHolder new Collection
     */
    protected abstract void convert(ViewHolder viewHolder, T item, int position);

    /**
     * convert to a new Collection,contains clear it
     *
     * @param dataList new Collection
     */
    public void update(Collection<T> dataList) {
        if (dataList == null) {
            return;
        }
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataChanged();
    }
}
