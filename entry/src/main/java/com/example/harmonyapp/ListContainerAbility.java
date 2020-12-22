package com.example.harmonyapp;

import com.example.harmonyapp.slice.ListContainerAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class ListContainerAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(ListContainerAbilitySlice.class.getName());
    }
}
