package com.example.m_shopping.slice;

import com.example.m_shopping.ResourceTable;
import com.example.m_shopping.adapter.CommonAdapter;
import com.example.m_shopping.adapter.ViewHolder;
import com.example.m_shopping.base.BaseAbilitySlice;
import com.example.m_shopping.butterknife.Bind;

import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.ListContainer;
import ohos.distributedschedule.interwork.DeviceInfo;
import ohos.distributedschedule.interwork.DeviceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * ShareAbilitySlice goods item for share
 */
public class ShareAbilitySlice extends BaseAbilitySlice {
    @Bind(ResourceTable.Id_device_list_view)
    private ListContainer listView;
    private CommonAdapter<DeviceInfo> deviceInfCommonAdapter;
    private List<DeviceInfo> devices = new ArrayList<>(0);

    @Override
    public int getLayout() {
        return ResourceTable.Layout_second_layout;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        deviceInfCommonAdapter = new CommonAdapter<DeviceInfo>(getContext(), ResourceTable.Layout_device_list_item) {
            @Override
            protected void convert(ViewHolder viewHolder, DeviceInfo item, int position) {
                viewHolder.setText(ResourceTable.Id_item_chlid_textview, " • " + item.getDeviceName());
            }
        };
        listView.setItemProvider(deviceInfCommonAdapter);
        listView.setItemClickedListener((listContainer, component, position, l) -> startAbilityFa(position));
    }

    @Override
    protected void initData() {
        super.initData();
        getDevicesId();
        deviceInfCommonAdapter.replace(devices);
    }

    private void getDevicesId() {
        List<DeviceInfo> iterators = DeviceManager.getDeviceList(DeviceInfo.FLAG_GET_ONLINE_DEVICE);
        devices.addAll(iterators);
    }

    private void startAbilityFa(int position) {
        if (devices != null) {
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId(devices.get(position).getDeviceId())
                    .withBundleName(getBundleName())
                    .withAbilityName("com.example.m_shopping.MainAbility")
                    .withFlags(Intent.FLAG_ABILITYSLICE_MULTI_DEVICE)
                    .build();
            intent.setOperation(operation);
            startAbility(intent);
        }
    }
}
