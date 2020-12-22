package com.example.m_setting.settingscategory.item;

/**
 * This is a POJO object corresponding to a switch item's json object.
 */
public class SwitchItemData {
    private String mainText;
    private String subText;
    private boolean defaultSwitch;

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public boolean isDefaultSwitch() {
        return defaultSwitch;
    }

    public void setDefaultSwitch(boolean defaultSwitch) {
        this.defaultSwitch = defaultSwitch;
    }
}