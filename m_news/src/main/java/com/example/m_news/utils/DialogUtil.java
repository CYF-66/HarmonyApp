package com.example.m_news.utils;

import com.example.m_news.ResourceTable;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

/**
 * Dialog util
 */
public class DialogUtil {
    private static final int TEXT_SIZE = 40;
    private static final int PADDING = 20;
    private static final int HEIGHT = 100;
    private static final int CENTER = 20;
    private static final int OFF_SET_Y = 200;
    private static final int COLOR_ALPHA = 200;
    private static final int MULTIPLE = 4;

    /**
     * Toast Method
     *
     * @param context context
     * @param text    Pop-up toast content
     * @param ms      Toast display time, in ms.
     */
    public static void toast(Context context, String text, int ms) {
        Text textView = new Text(context);
        textView.setTextAlignment(TextAlignment.CENTER);
        ShapeElement background = new ShapeElement();
        background.setCornerRadius(CENTER);
        background.setRgbColor(new RgbColor(0, 0, 0, COLOR_ALPHA));
        textView.setBackground(background);
        textView.setPadding(PADDING, PADDING, PADDING, PADDING);
        textView.setMaxTextLines(1);
        textView.setTextSize(TEXT_SIZE);
        textView.setTextColor(new Color(CommonUtils.getColor(context, ResourceTable.Color_white)));
        textView.setText(text);
        textView.setWidth((int) (CommonUtils.calculateLength(text) * TEXT_SIZE + PADDING * MULTIPLE));
        textView.setHeight(HEIGHT);
        ToastDialog toastDialog = new ToastDialog(context);
        toastDialog.setContentCustomComponent(textView);
        toastDialog.setTransparent(false);
        toastDialog.setOffset(0, OFF_SET_Y);
        toastDialog.setSize((int) (CommonUtils.calculateLength(text)
                * TEXT_SIZE + PADDING * MULTIPLE), PADDING);
        toastDialog.setDuration(ms);
        toastDialog.show();
    }
}
