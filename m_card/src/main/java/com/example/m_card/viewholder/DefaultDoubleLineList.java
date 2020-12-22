package com.example.m_card.viewholder;

import com.example.m_card.ResourceTable;
import com.example.m_card.datamodel.ListItemInfo;

import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;
import ohos.agp.components.Text;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

/**
 * DoubleLineList view holder
 */
public class DefaultDoubleLineList {
    private static final int TOAST_DURATION = 1000;
    private static final int TOAST_OFFSET_X = 50;
    private static final int TOAST_OFFSET_Y = 200;
    private Text textPrimary;
    private Text textSecondary;
    private DirectionalLayout touchTargetPrimary;
    private DirectionalLayout touchTargetSecondary;
    private Image imagePrimary;
    private Image imageSecondary;

    /**
     * Default Double-Line list item view holder constructor
     *
     * @param itemComponent component
     */
    public DefaultDoubleLineList(Component itemComponent) {
        textPrimary = (Text) itemComponent.findComponentById(ResourceTable.Id_dlist_text_primary);
        textSecondary = (Text) itemComponent.findComponentById(ResourceTable.Id_dlist_text_secondary);
        touchTargetPrimary =
                (DirectionalLayout) itemComponent.findComponentById(ResourceTable.Id_dlist_right_touchTarget1);
        touchTargetSecondary =
                (DirectionalLayout) itemComponent.findComponentById(ResourceTable.Id_dlist_right_touchTarget2);
        imagePrimary = (Image) itemComponent.findComponentById(ResourceTable.Id_dlist_right_img1);
        imageSecondary = (Image) itemComponent.findComponentById(ResourceTable.Id_dlist_right_img2);
    }

    /**
     * Bind data
     *
     * @param context context
     * @param content item data container
     */
    public void setContent(Context context, ListItemInfo content) {
        textPrimary.setText(content.getPrimaryText());
        textSecondary.setText(content.getSecondaryText());
        imagePrimary.setPixelMap(content.getPrimaryImageId());
        imageSecondary.setPixelMap(content.getSecondaryImageId());
        touchTargetPrimary.setClickedListener(component -> {
            ToastDialog toastDialog = new ToastDialog(context);
            toastDialog.setContentText("You clicked the first info button")
                    .setDuration(TOAST_DURATION)
                    .setOffset(TOAST_OFFSET_X, TOAST_OFFSET_Y)
                    .show();
        });
        touchTargetSecondary.setClickedListener(component -> {
            ToastDialog toastDialog = new ToastDialog(context);
            toastDialog.setContentText("You clicked the second info button")
                    .setDuration(TOAST_DURATION)
                    .setOffset(TOAST_OFFSET_X, TOAST_OFFSET_Y)
                    .show();
        });
    }
}