package com.example.harmonyapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import ohos.aafwk.ability.AbilityPackage;

public class MyApplication extends AbilityPackage {

    private static Activity topActivity;

    private static Application instance;

    public static synchronized Application getInstance() {
        return instance;
    }

    @Override
    public void onInitialize() {
        super.onInitialize();
        instance = getApp();

        MyApplication.getInstance().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                topActivity = activity;
                Log.d("TEST","onActivityCreated====="+activity.getClass().getName());
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d("TEST","onActivityStarted====="+activity.getClass().getName());
            }

            @Override
            public void onActivityResumed(Activity activity) {
                topActivity = activity;
                Log.d("TEST","onActivityResumed====="+activity.getClass().getName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d("TEST","onActivityPaused====="+activity.getClass().getName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d("TEST","onActivityStopped====="+activity.getClass().getName());
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Log.d("TEST","onActivitySaveInstanceState====="+activity.getClass().getName());
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d("TEST","onActivityDestroyed====="+activity.getClass().getName());
            }
        });
    }

    /**
     * 利用反射获取android原生Application实例 这样可以直接使用
     * @return Application
     */
    private static Application getApp(){
        try{
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null,(Object[]) null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
