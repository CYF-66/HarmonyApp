package com.example.m_card.slice;

import com.example.m_card.ResourceTable;
import com.example.m_card.datamodel.ListItemInfo;
import com.example.m_card.itemprovider.ListItemProvider;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.AttrHelper;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.Image;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.ListContainer;
import ohos.agp.components.ScrollView;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * MainAbilitySlice
 */
public class MainAbilitySlice extends AbilitySlice {
    private static final int UPPER_LIST_LEN = 5;
    private static final int LOWER_LIST_LEN = 5;
    private static final int OVERSCROLL_PERCENT = 20;
    private static final float OVERSCROLL_RATE = 1.0f;
    private static final int REMAIN_VISIBLE_PERCENT = 20;
    private static final int LIST_ITEM_HEIGHT = 65;
    private static final int TOAST_DURATION = 1000;
    private static final int TOAST_OFFSETX = 50;
    private static final int TOAST_OFFSETY = 200;
    private static final int PROFILE_SIZE = 108;
    private static final int ORIGINAL_BACKGROUND_COLOR = 211;
    private static final int BACKGROUND_COLOR = 250;
    private static final int PROFILE_TOP_MARGIN = 100;
    private static final int MARGIN_CHANGE_RATIO = 2;
    private static final int BOTTOM_TAB_BUTTON_NUM = 4;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initLists();
        initAppBarButtons();
        initScrollView();
        initBottomTab();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private void initLists() {
        List<ListItemInfo> upperListItemInfos = new ArrayList<>();
        List<ListItemInfo> lowerListItemInfos = new ArrayList<>();

        // List item data
        for (int index = 0; index < UPPER_LIST_LEN; index++) {
            upperListItemInfos.add(new ListItemInfo("Phone Number", "Mobile - ShangHai",
                    ResourceTable.Media_ic_about, ResourceTable.Media_ic_about));
        }
        for (int index = 0; index < LOWER_LIST_LEN; index++) {
            lowerListItemInfos.add(new ListItemInfo("8:50 AM", "888 888 8888",
                    ResourceTable.Media_ic_about, ResourceTable.Media_ic_about));
        }

        // Set providers to listcontainers
        ListContainer upperList = (ListContainer) findComponentById(ResourceTable.Id_contacts_detail_upperCard_list);
        if (upperList != null) {
            upperList.setItemProvider(new ListItemProvider(upperListItemInfos, this));
            upperList.setHeight(AttrHelper.vp2px(LIST_ITEM_HEIGHT, this) * UPPER_LIST_LEN);
        }
        ListContainer lowerList = (ListContainer) findComponentById(ResourceTable.Id_contacts_detail_lowerCard_list);
        if (lowerList != null) {
            lowerList.setItemProvider(new ListItemProvider(lowerListItemInfos, this));
            lowerList.setHeight(AttrHelper.vp2px(LIST_ITEM_HEIGHT, this) * LOWER_LIST_LEN);
        }
    }

    private void initAppBarButtons() {
        Image backButton = (Image) findComponentById(ResourceTable.Id_appbar_backbtn);
        if (backButton != null) {
            backButton.setClickedListener(component -> MainAbilitySlice.super.onBackPressed());
        }
        Button addButton1 = (Button) findComponentById(ResourceTable.Id_appbar_button1);
        if (addButton1 != null) {
            addButton1.setClickedListener(component -> {
                ToastDialog toastDialog = new ToastDialog(this);
                toastDialog.setContentText("You clicked the first add button")
                        .setDuration(TOAST_DURATION)
                        .setOffset(TOAST_OFFSETX, TOAST_OFFSETY)
                        .show();
            });
        }
        Button addButton2 = (Button) findComponentById(ResourceTable.Id_appbar_button2);
        if (addButton2 != null) {
            addButton2.setClickedListener(component -> {
                ToastDialog toastDialog = new ToastDialog(this);
                toastDialog.setContentText("You clicked the second add button")
                        .setDuration(TOAST_DURATION)
                        .setOffset(TOAST_OFFSETX, TOAST_OFFSETY)
                        .show();
            });
        }
        Button moreButton = (Button) findComponentById(ResourceTable.Id_appbar_button3);
        if (moreButton != null) {
            moreButton.setClickedListener(component -> {
                ToastDialog toastDialog = new ToastDialog(this);
                toastDialog.setContentText("You clicked the more button")
                        .setDuration(TOAST_DURATION)
                        .setOffset(TOAST_OFFSETX, TOAST_OFFSETY)
                        .show();
            });
        }
    }

    private void initScrollView() {
        ScrollView scrollView = (ScrollView) findComponentById(ResourceTable.Id_contacts_detail_scroll);
        int profileSizePx = AttrHelper.vp2px(PROFILE_SIZE, this);
        int profileTopMarginPx = AttrHelper.vp2px(PROFILE_TOP_MARGIN, this);
        if (scrollView != null) {
            scrollView.setReboundEffectParams(OVERSCROLL_PERCENT, OVERSCROLL_RATE, REMAIN_VISIBLE_PERCENT);
            scrollView.setReboundEffect(true);
            Text userName = (Text) findComponentById(ResourceTable.Id_appbar_username);
            Image contactProfile = (Image) findComponentById(ResourceTable.Id_contacts_detail_profile);
            DirectionalLayout upperBackGround = (DirectionalLayout) findComponentById(ResourceTable.Id_background_top);
            ShapeElement shapeElement = new ShapeElement();
            shapeElement.setShape(ShapeElement.RECTANGLE);

            // Set Scrolled listener
            scrollView.getComponentTreeObserver().addScrolledListener(() -> {
                float curY = scrollView.getScrollValue(Component.AXIS_Y);
                int fixedProfileSize = (int) (profileSizePx * (profileSizePx - curY)) / profileSizePx;

                if (fixedProfileSize > profileSizePx) {
                    curY = 0;
                    fixedProfileSize = profileSizePx;
                } else if (fixedProfileSize < 0) {
                    curY = profileSizePx;
                    fixedProfileSize = 0;
                }
                int colorChange = (int) ((BACKGROUND_COLOR - ORIGINAL_BACKGROUND_COLOR) * curY / profileSizePx);
                shapeElement.setRgbColor(new RgbColor(ORIGINAL_BACKGROUND_COLOR + colorChange,
                        ORIGINAL_BACKGROUND_COLOR + colorChange, ORIGINAL_BACKGROUND_COLOR + colorChange));
                upperBackGround.setBackground(shapeElement);

                contactProfile.setHeight(fixedProfileSize);
                contactProfile.setWidth(fixedProfileSize);
                contactProfile.setMarginTop((int) (profileTopMarginPx + curY / MARGIN_CHANGE_RATIO));
                contactProfile.setAlpha(1 * (profileSizePx - curY) / profileSizePx);

                userName.setAlpha(1 * curY / profileSizePx);
            });
        }
    }

    private void initBottomTab() {
        DirectionalLayout bottomTab = (DirectionalLayout) findComponentById(ResourceTable.Id_bottom_tabMenu);
        List<DirectionalLayout> tabList = new ArrayList<>();
        for (int count = 0; count < BOTTOM_TAB_BUTTON_NUM; count++) {
            // Use LayoutScatter to convert xml file into Component instance
            DirectionalLayout tab = (DirectionalLayout) LayoutScatter.getInstance(getContext())
                    .parse(ResourceTable.Layout_tab, bottomTab, false);
            Image buttonImage = (Image) tab.findComponentById(ResourceTable.Id_bottom_tab_button_image);
            if (buttonImage != null) {
                if (count == BOTTOM_TAB_BUTTON_NUM - 1) {
                    buttonImage.setPixelMap(ResourceTable.Media_icon_actived);
                } else {
                    buttonImage.setPixelMap(ResourceTable.Media_icon_normal);
                }
            }
            Text buttonText = (Text) tab.findComponentById(ResourceTable.Id_bottom_tab_button_text);
            if (buttonText != null) {
                buttonText.setText("Tab");
            }
            tab.setClickedListener(component -> {
                // Deselect all tabs in tab menu
                for (DirectionalLayout btn : tabList) {
                    ((Image) btn.findComponentById(ResourceTable.Id_bottom_tab_button_image))
                            .setPixelMap(ResourceTable.Media_icon_normal);
                }

                // Set seleted state on the clicked tab
                ((Image) component.findComponentById(ResourceTable.Id_bottom_tab_button_image))
                        .setPixelMap(ResourceTable.Media_icon_actived);
            });
            tabList.add(tab);
            bottomTab.addComponent(tab);
        }
    }
}