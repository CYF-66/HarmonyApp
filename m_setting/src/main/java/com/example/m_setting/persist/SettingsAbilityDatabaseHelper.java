package com.example.m_setting.persist;

import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

/**
 * Settings database helper, persist the user config
 */
public class SettingsAbilityDatabaseHelper extends DatabaseHelper {
    private static final String CONFIGURATION_FILE_NAME = "settings";

    /**
     * Constructor of SettingsAbilityDatabaseHelper
     *
     * @param context current context
     */
    public SettingsAbilityDatabaseHelper(Context context) {
        super(context);
    }

    /**
     * Get preferences of specify config file
     *
     * @return preferences
     */
    public Preferences getPreferences() {
        return super.getPreferences(CONFIGURATION_FILE_NAME);
    }
}
