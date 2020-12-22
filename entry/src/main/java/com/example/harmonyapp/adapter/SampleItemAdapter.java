package com.example.harmonyapp.adapter;

import com.example.harmonyapp.ResourceTable;
import com.example.harmonyapp.bean.SampleItem;
import com.example.m_library.adapters.NewsListAdapter;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.List;

public class SampleItemAdapter extends RecycleItemProvider {

    private List<SampleItem> list;
    private AbilitySlice slice;
    public SampleItemAdapter(List<SampleItem> list, AbilitySlice slice) {
        this.list = list;
        this.slice = slice;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component convertComponent, ComponentContainer componentContainer) {
        ViewHolder viewHolder = null;
        Component component = convertComponent;
        if (component == null) {
            component = LayoutScatter.getInstance(slice).parse(ResourceTable.Layout_item_sample, null, false);
            viewHolder = new ViewHolder();
            Component componentName = component.findComponentById(ResourceTable.Id_item_name);
            if (componentName instanceof Text) {
                viewHolder.name = (Text) componentName;
            }
            component.setTag(viewHolder);
        }else{
            if (component.getTag() instanceof ViewHolder) {
                viewHolder = (ViewHolder) component.getTag();
            }
        }

        if (null != viewHolder) {
            viewHolder.name.setText(list.get(position).getName());
        }
        return component;
    }

    /**
     * ViewHolder which has title and image
     */
    private static class ViewHolder {
        Text name;
    }
}
