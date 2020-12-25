package com.example.harmonyapp.adapter;

import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.app.Context;

import java.util.HashMap;

/**
 * ViewHolder common view layout style
 */
public class ViewHolder {
    /**
     * position
     */
    int position;

    /**
     * get layoutId for son class
     */
    int layoutId;
    private Component component;
    private Context context;
    private HashMap<Integer, Component> views;

    /**
     * constructor of ViewHolder
     *
     * @param context  context
     * @param itemView itemView
     * @param parent   parent
     * @param position position
     */
    ViewHolder(Context context, Component itemView, ComponentContainer parent, int position) {
        this.context = context;
        this.component = itemView;
        this.position = position;
        views = new HashMap<>(0);
        component.setTag(this);
    }

    /**
     * Get the control by viewId
     *
     * @param viewId viewId
     * @param <T>    generic
     * @return view
     */
    @SuppressWarnings("unchecked")
    private <T extends Component> T getView(int viewId) {
        Component view = views.get(viewId);
        if (view == null) {
            view = component.findComponentById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * get component view
     *
     * @return Component
     */
    Component getComponentView() {
        return component;
    }

    /**
     * set text
     *
     * @param viewId viewId
     * @param text   text
     */
    public void setText(int viewId, String text) {
        Text tv = getView(viewId);
        tv.setText(text);
    }

    /**
     * set image
     *
     * @param viewId viewId
     * @param resId  ImageResource
     */
    public void setImageResource(int viewId, int resId) {
        Image image = getView(viewId);
        image.setPixelMap(resId);
        image.setScaleMode(Image.ScaleMode.STRETCH);
    }
}
