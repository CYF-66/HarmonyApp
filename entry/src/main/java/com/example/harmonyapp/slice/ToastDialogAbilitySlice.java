package com.example.harmonyapp.slice;

import com.example.harmonyapp.ResourceTable;
import com.example.harmonyapp.base.BaseAbilitySlice;
import com.example.harmonyapp.butterknife.Bind;
import com.example.harmonyapp.util.LogUtils;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;

public class ToastDialogAbilitySlice extends BaseAbilitySlice {

    @Bind(ResourceTable.Id_dl_left)
    private DirectionalLayout mDlBack;
    @Bind(ResourceTable.Id_tv_title)
    private Text mTvTitle;
    @Bind(ResourceTable.Id_btn_center)
    private Button mBtnCenter;
    @Bind(ResourceTable.Id_btn_bottom)
    private Button mBtnBottom;
    @Bind(ResourceTable.Id_btn_self)
    private Button mBtnSelf;
    @Override
    public int getLayout() {
        return ResourceTable.Layout_ability_toast_dialog;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mBtnCenter.setClickedListener(component ->{
            new ToastDialog(this)
                    .setText("This is a ToastDialog displayed in the middle")
                    .setAlignment(LayoutAlignment.CENTER)
                    .show();
        });
        mBtnBottom.setClickedListener(component ->{
            new ToastDialog(this)
                    .setText("This is a ToastDialog displayed in the bottom")
                    .setAlignment(LayoutAlignment.BOTTOM)
                    .show();
        });
        mBtnSelf.setClickedListener(component ->{
            DirectionalLayout toastLayout = (DirectionalLayout) LayoutScatter.getInstance(this)
                    .parse(ResourceTable.Layout_layout_toast, null, false);
            new ToastDialog(this)
                    .setComponent(toastLayout)
                    .setSize(DirectionalLayout.LayoutConfig.MATCH_CONTENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT)
                    .setAlignment(LayoutAlignment.BOTTOM)
                    .show();
        });
        mDlBack.setClickedListener(component ->{
            terminateAbility();
        });
    }

    @Override
    protected void initData(Intent intent) {
        super.initData(intent);
        String content = intent.getStringParam("content");
        LogUtils.info("TEST---------->","content============="+content);
        mTvTitle.setText(content);
    }
}
