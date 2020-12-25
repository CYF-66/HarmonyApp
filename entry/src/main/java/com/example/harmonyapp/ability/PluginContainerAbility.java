package com.example.harmonyapp.ability;

import com.example.harmonyapp.constant.RouteMap;
import com.example.harmonyapp.slice.ListContainerAbilitySlice;
import com.example.harmonyapp.slice.ScrollViewAbilitySlice;
import com.example.harmonyapp.slice.TabListAbilitySlice;
import com.example.harmonyapp.slice.ToastDialogAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class PluginContainerAbility extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(ListContainerAbilitySlice.class.getName());
        addActionRoute(RouteMap.ACTION_LISTCONTAINER, ListContainerAbilitySlice.class.getName());
        addActionRoute(RouteMap.ACTION_TABLIST, TabListAbilitySlice.class.getName());
        addActionRoute(RouteMap.ACTION_SCROLLVIEW, ScrollViewAbilitySlice.class.getName());
        addActionRoute(RouteMap.ACTION_TOASTDIALOG, ToastDialogAbilitySlice.class.getName());
    }
}
