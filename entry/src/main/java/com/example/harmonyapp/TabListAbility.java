package com.example.harmonyapp;

import com.example.harmonyapp.slice.TabListAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class TabListAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(TabListAbilitySlice.class.getName());
    }
}
