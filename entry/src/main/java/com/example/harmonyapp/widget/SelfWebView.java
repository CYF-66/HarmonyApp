package com.example.harmonyapp.widget;

import android.webkit.WebView;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.render.Canvas;
import ohos.app.Context;

public class SelfWebView extends Component {

    private WebView mWebView;

    public SelfWebView(Context context) {
        super(context);
    }

    public SelfWebView(Context context, AttrSet attrSet) {
        super(context, attrSet);
    }

    public SelfWebView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
    }

    public SelfWebView(Context context, AttrSet attrSet, int resId) {
        super(context, attrSet, resId);
    }
}
