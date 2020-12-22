package com.example.m_news;

import com.example.m_news.slice.MainAbilityDetailSlice;
import com.example.m_news.slice.MainAbilityListSlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.bundle.IBundleManager;

/**
 * MainAbility
 */
public class MainAbility extends Ability {
    private static final String PERMISSION_DATASYNC = "ohos.permission.DISTRIBUTED_DATASYNC";
    private static final int MY_PERMISSION_REQUEST_CODE = 1;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilityListSlice.class.getName());
        addActionRoute("action.detail", MainAbilityDetailSlice.class.getName());

        if (verifySelfPermission(PERMISSION_DATASYNC) != IBundleManager.PERMISSION_GRANTED) {
            if (canRequestPermission(PERMISSION_DATASYNC)) {
                requestPermissionsFromUser(new String[]{PERMISSION_DATASYNC}, MY_PERMISSION_REQUEST_CODE);
            }
        }
    }
}
