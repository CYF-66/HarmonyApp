package com.example.harmonyapp.ability;

import android.util.Log;
import android.widget.Toast;
import com.example.harmonyapp.MyApplication;
import com.example.harmonyapp.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.bundle.IBundleManager;

public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        Log.d("TEST","Application====="+MyApplication.getInstance().getClass().getName());
        Toast.makeText(MyApplication.getInstance(),"获取Application实例",Toast.LENGTH_SHORT).show();
//        if (verifySelfPermission("ohos.permission.CAMERA") != IBundleManager.PERMISSION_GRANTED) {
//            // 应用未被授予权限
//            if (canRequestPermission("ohos.permission.CAMERA")) {
//                // 是否可以申请弹框授权(首次申请或者用户未选择禁止且不再提示)
//                requestPermissionsFromUser(
//                        new String[] { "ohos.permission.CAMERA" } , 101);
//            } else {
//                // 显示应用需要权限的理由，提示用户进入设置授权
//            }
////            // 调用者无权限，做错误处理
////                        new ToastDialog(this)
////                    .setText("无权限:")
////                    // Toast显示在界面中间
////                    .setAlignment(LayoutAlignment.BOTTOM)
////                    .show();
//        }else{
//            super.setMainRoute(MainAbilitySlice.class.getName());
//        }

    }

    @Override
    public void onRequestPermissionsFromUserResult (int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode) {
            case 101: {
                // 匹配requestPermissions的requestCode
                if (grantResults.length > 0
                        && grantResults[0] == IBundleManager.PERMISSION_GRANTED) {
                    setMainRoute(MainAbilitySlice.class.getName());
                    // 权限被授予
                    // 注意：因时间差导致接口权限检查时有无权限，所以对那些因无权限而抛异常的接口进行异常捕获处理
                } else {
                    // 权限被拒绝
                }
                return;
            }
        }
    }

    @Override
    protected void onBackground() {
        super.onBackground();
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.d("TEST","onActive====="+this.getClass().getName());
    }


    @Override
    protected void onInactive() {
        super.onInactive();
        Log.d("TEST","onInactive====="+this.getClass().getName());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("TEST","onNewIntent====="+this.getClass().getName());
    }
}
