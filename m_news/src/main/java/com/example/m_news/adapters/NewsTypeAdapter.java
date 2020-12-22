package com.example.m_news.adapters;

import com.example.m_news.ResourceTable;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.RecycleItemProvider;
import ohos.agp.components.Text;
import ohos.app.Context;

import java.util.Optional;

/**
 * News type list adapter
 */
public class NewsTypeAdapter extends RecycleItemProvider {
    private String[] newsTypeList;
    private Context context;

    public NewsTypeAdapter(String[] listBasicInfo, Context context) {
        this.newsTypeList = listBasicInfo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return newsTypeList == null ? 0 : newsTypeList.length;
    }

    @Override
    public Object getItem(int position) {
        return Optional.of(this.newsTypeList[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component componentP, ComponentContainer componentContainer) {
        ViewHolder viewHolder = null;
        Component component = componentP;
        if (component == null) {
            component = LayoutScatter.getInstance(context).parse(ResourceTable.Layout_item_news_type_layout,
                    null, false);
            viewHolder = new ViewHolder();
            Component componentText = component.findComponentById(ResourceTable.Id_news_type_text);
            if (componentText instanceof Text) {
                viewHolder.title = (Text) componentText;
            }
            component.setTag(viewHolder);
        } else {
            if (component.getTag() instanceof ViewHolder) {
                viewHolder = (ViewHolder) component.getTag();
            }
        }
        if (null != viewHolder) {
            viewHolder.title.setText(newsTypeList[position]);
        }
        return component;
    }

    /**
     * ViewHolder which has title
     */
    private static class ViewHolder {
        Text title;
    }
}
