package com.example.m_news.adapters;

import com.example.m_news.ResourceTable;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.RecycleItemProvider;
import ohos.agp.components.Text;
import ohos.app.Context;
import ohos.distributedschedule.interwork.DeviceInfo;

import java.util.List;
import java.util.Optional;

/**
 * Device list adapter
 */
public class DevicesListAdapter extends RecycleItemProvider {
    private List<DeviceInfo> deviceInfoList;
    private Context context;

    public DevicesListAdapter(List<DeviceInfo> listBasicInfo, Context context) {
        this.deviceInfoList = listBasicInfo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.deviceInfoList == null ? 0 : this.deviceInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return Optional.of(this.deviceInfoList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, final Component componentP, ComponentContainer componentContainer) {
        ViewHolder viewHolder = null;
        Component component = componentP;
        if (component == null) {
            component = LayoutScatter.getInstance(this.context).parse(ResourceTable.Layout_device_list_item, null, false);
            viewHolder = new ViewHolder();
            Component componentText = component.findComponentById(ResourceTable.Id_item_chlid_textview);
            if (componentText instanceof Text) {
                viewHolder.devicesName = (Text) componentText;
            }
            component.setTag(viewHolder);
        } else {
            if (component.getTag() instanceof ViewHolder) {
                viewHolder = (ViewHolder) component.getTag();
            }
        }
        if (null != viewHolder) {
            viewHolder.devicesName.setText(this.deviceInfoList.get(position).getDeviceName());
        }
        return component;
    }

    /**
     * ViewHolder which has devicesName
     */
    private static class ViewHolder {
        private Text devicesName;
    }
}
