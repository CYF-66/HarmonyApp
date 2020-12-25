package com.example.harmonyapp.slice;

import android.net.Uri;
import com.example.harmonyapp.MyApplication;
import com.example.harmonyapp.ResourceTable;
import com.example.harmonyapp.base.BaseAbilitySlice;
import com.example.harmonyapp.butterknife.Bind;
import com.example.harmonyapp.util.LogUtils;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.ScrollView;
import ohos.agp.components.Text;

/**
 * ScrollView
 * https://developer.harmonyos.com/cn/docs/documentation/doc-guides/ui-java-component-scrollview-0000001060965602
 */
public class ScrollViewAbilitySlice extends BaseAbilitySlice {
    @Bind(ResourceTable.Id_dl_left)
    private DirectionalLayout mDlBack;
    @Bind(ResourceTable.Id_tv_title)
    private Text mTvTitle;

    @Bind(ResourceTable.Id_scrollview)
    private ScrollView mScrollView;
    @Bind(ResourceTable.Id_btn_skip)
    private Button mBtnSkip;
    @Override
    public int getLayout() {
        return  ResourceTable.Layout_ability_scrollview;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        mBtnSkip.setClickedListener(component ->{
//            android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:18217126725"));
//            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
//            MyApplication.getInstance().startActivity(intent);
            mScrollView.fluentScrollYTo(500);
        });
        mDlBack.setClickedListener(component ->{
            terminateAbility();
        });
        mScrollView.setReboundEffect(true);

        mScrollView.setClickedListener(component -> {
            mScrollView.fluentScrollByY(300);
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
