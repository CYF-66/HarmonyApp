package com.example.m_news.slice;

import com.example.m_news.ResourceTable;
import com.example.m_news.adapters.DevicesListAdapter;
import com.example.m_news.utils.CommonUtils;
import com.example.m_news.utils.DialogUtil;
import com.example.m_news.utils.LogUtil;

import com.example.m_news.manager.INewsDemoIDL;
import com.example.m_news.manager.NewsDemoIDLStub;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.IAbilityConnection;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Text;
import ohos.agp.components.DependentLayout;
import ohos.agp.components.TextField;
import ohos.agp.components.Image;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.window.dialog.CommonDialog;
import ohos.bundle.ElementName;
import ohos.distributedschedule.interwork.DeviceInfo;
import ohos.distributedschedule.interwork.DeviceManager;
import ohos.rpc.IRemoteObject;
import ohos.rpc.RemoteException;

import java.util.ArrayList;
import java.util.List;

/**
 * News detail slice
 */
public class MainAbilityDetailSlice extends AbilitySlice {
    private static final int WAIT_TIME = 30000;
    private static final int DIALOG_SIZE_WIDTH = 900;
    private static final int DIALOG_SIZE_HEIGHT = 800;
    private DependentLayout parentLayout;
    private TextField commentTf;
    private Image iconShared;
    private CommonDialog dialog;
    private List<DeviceInfo> devices = new ArrayList<>();

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_news_detail_layout);
        initView();
        initListener();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private void initView() {
        Component componentParent = findComponentById(ResourceTable.Id_parent_layout);
        Component componentTextFile = findComponentById(ResourceTable.Id_text_file);
        Component componentButton1 = findComponentById(ResourceTable.Id_button1);
        Component componentButton2 = findComponentById(ResourceTable.Id_button2);
        Component componentButton3 = findComponentById(ResourceTable.Id_button3);
        Component componentButton4 = findComponentById(ResourceTable.Id_button4);
        Component componentTitleContent = findComponentById(ResourceTable.Id_title_content);

        if (componentParent instanceof DependentLayout) {
            parentLayout = (DependentLayout) componentParent;
        }
        if (componentTextFile instanceof TextField) {
            commentTf = (TextField) componentTextFile;
        }
        if (componentButton1 instanceof Image) {
            Image iconTalk = (Image) componentButton1;
            iconTalk.setScaleMode(Image.ScaleMode.STRETCH);
        }
        if (componentButton2 instanceof Image) {
            Image iconStar = (Image) componentButton2;
            iconStar.setScaleMode(Image.ScaleMode.STRETCH);
        }
        if (componentButton3 instanceof Image) {
            Image iconLike = (Image) componentButton3;
            iconLike.setScaleMode(Image.ScaleMode.STRETCH);
        }
        if (componentButton4 instanceof Image) {
            iconShared = (Image) componentButton4;
            iconShared.setScaleMode(Image.ScaleMode.STRETCH);
        }
        if (componentTitleContent instanceof Text) {
            Text newsText = (Text) componentTitleContent;
            newsText.setText(CommonUtils.getString(this, ResourceTable.String_news_text));
        }
    }

    private void initListener() {
        parentLayout.setTouchEventListener((component, touchEvent) -> {
            if (commentTf.hasFocus()) {
                commentTf.clearFocus();
            }
            return true;
        });
        iconShared.setClickedListener(listener -> {
            initDevices();
            showDeviceList();
        });
    }

    private void initDevices() {
        if (devices.size() > 0) {
            devices.clear();
        }
        List<ohos.distributedschedule.interwork.DeviceInfo> deviceInfos =
                DeviceManager.getDeviceList(ohos.distributedschedule.interwork.DeviceInfo.FLAG_GET_ONLINE_DEVICE);
        LogUtil.info("MainAbilityDetailSlice", "deviceInfos size is :" + deviceInfos.size());
        devices.addAll(deviceInfos);
    }

    private void showDeviceList() {
        dialog = new CommonDialog(MainAbilityDetailSlice.this);
        dialog.setAutoClosable(true);
        dialog.setTitleText("Harmony devices");
        dialog.setSize(DIALOG_SIZE_WIDTH, DIALOG_SIZE_HEIGHT);
        ListContainer devicesListContainer = new ListContainer(getContext());
        DevicesListAdapter devicesListAdapter = new DevicesListAdapter(devices, this);
        devicesListContainer.setItemProvider(devicesListAdapter);
        devicesListContainer.setItemClickedListener((listContainer, component, position, listener) -> {
            LogUtil.info("MainAbilityDetailSlice", "deviceId is :" + devices.get(position).getDeviceId());
            dialog.destroy();
            startAbilityFA(devices.get(position).getDeviceId());
        });
        devicesListAdapter.notifyDataChanged();
        dialog.setContentCustomComponent(devicesListContainer);
        dialog.show();
    }

    private void startAbilityFA(String devicesId) {
        Intent intent = new Intent();
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId(devicesId)
                .withBundleName(getBundleName())
                .withAbilityName("com.example.m_news.SharedService")
                .withFlags(Intent.FLAG_ABILITYSLICE_MULTI_DEVICE)
                .build();
        intent.setOperation(operation);
        boolean connectFlag = connectAbility(intent, new IAbilityConnection() {
            @Override
            public void onAbilityConnectDone(ElementName elementName, IRemoteObject iRemoteObject, int i) {
                INewsDemoIDL sharedManager = NewsDemoIDLStub.asInterface(iRemoteObject);
                try {
                    sharedManager.tranShare(devicesId, "url", "title", "abstract", "image");
                } catch (RemoteException e) {
                    LogUtil.info("MainAbilityDetailSlice", "connect successful,but have remote exception");
                }
            }

            @Override
            public void onAbilityDisconnectDone(ElementName elementName, int i) {
                disconnectAbility(this);
            }
        });
        if (connectFlag) {
            DialogUtil.toast(this, connectFlag ? "Sharing succeeded!"
                    : "Sharing failed. Please try again later.", WAIT_TIME);
        }
    }


}
