package com.example.m_setting;

import com.example.m_setting.slice.SettingsListSlice;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

/**
 * Settings list ability
 */
public class MainAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SettingsListSlice.class.getName());
    }
}