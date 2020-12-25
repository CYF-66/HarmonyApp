package com.example.harmonyapp.ability;

import com.example.harmonyapp.slice.SplashScreenAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

/**
 * Splash screen ability
 */
public class SplashScreenAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(SplashScreenAbilitySlice.class.getName());
//        addActionRoute("action.system.detail", ProductDetailsSlice.class.getName());
//        addActionRoute("action.system.share", ShareAbilitySlice.class.getName());
    }
}