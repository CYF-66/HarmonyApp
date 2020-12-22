package com.example.m_shopping.slice;

import com.example.m_shopping.ResourceTable;
import com.example.m_shopping.base.BaseAbilitySlice;

import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;
import ohos.agp.components.LayoutScatter;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.CommonDialog;

/**
 * ProductDetailsSlice view for goods item detail
 */
public class ProductDetailsSlice extends BaseAbilitySlice implements Component.ClickedListener {
    private Image sha;
    private CommonDialog dialog;

    @Override
    public int getLayout() {
        return ResourceTable.Layout_product_details;
    }

    @Override
    protected void initWidget() {
        initWidgets();
        initDialog();
        initListener();
    }

    private void initWidgets() {
        Component component1 = findComponentById(ResourceTable.Id_im_carousel);
        if (component1 instanceof Image) {
            Image secKill = (Image) component1;
            secKill.setPixelMap(ResourceTable.Media_sec_kill);
            secKill.setScaleMode(Image.ScaleMode.STRETCH);
        }
        Component component2 = findComponentById(ResourceTable.Id_im_share);
        if (component2 instanceof Image) {
            sha = (Image) component2;
            sha.setPixelMap(ResourceTable.Media_share_icon);
            sha.setScaleMode(Image.ScaleMode.STRETCH);
        }
    }

    private void initListener() {
        sha.setClickedListener(this);
    }

    private void initDialog() {
        dialog = new CommonDialog(this);
    }

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_im_share:
                showShareDialog();
                break;
            case ResourceTable.Id_icon_huawei:
                dialog.destroy();
                Intent intent = new Intent();
                Operation operation = new Intent.OperationBuilder()
                        .withBundleName(getBundleName())
                        .withAbilityName("com.example.m_shopping.MainAbility")
                        .withAction("action.system.share")
                        .build();
                intent.setOperation(operation);
                startAbility(intent);
                break;
            default:
                break;
        }
    }

    private void showShareDialog() {
        DirectionalLayout layout = new DirectionalLayout(getContext());
        Component component = LayoutScatter.getInstance(getContext())
                .parse(ResourceTable.Layout_share_dialog, null, false);
        layout.addComponent(component);
        Component component1 = component.findComponentById(ResourceTable.Id_icon_huawei);
        if (component1 instanceof Image) {
            Image shareHuaImage = (Image) component1;
            shareHuaImage.setPixelMap(ResourceTable.Media_share_hua);
            shareHuaImage.setScaleMode(Image.ScaleMode.INSIDE);
            shareHuaImage.setClickedListener(this);
        }
        dialog.setContentCustomComponent(layout);
        dialog.setAlignment(LayoutAlignment.BOTTOM);
        dialog.setAutoClosable(true);
        dialog.show();
    }
}
