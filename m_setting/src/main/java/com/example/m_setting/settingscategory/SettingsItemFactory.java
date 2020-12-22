package com.example.m_setting.settingscategory;

import com.example.m_setting.settingscategory.item.GroupHeader;
import com.example.m_setting.settingscategory.item.SimpleTextItem;
import com.example.m_setting.settingscategory.item.SimpleTextItemData;
import com.example.m_setting.settingscategory.item.SwitchItem;
import com.example.m_setting.settingscategory.item.SwitchItemData;

import com.example.m_setting.datamodel.Category;
import com.example.m_setting.datamodel.CategoryItemBase;

import ohos.app.Context;
import ohos.global.resource.Resource;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.zson.ZSONArray;
import ohos.utils.zson.ZSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * A factory to produce category items.
 */
public class SettingsItemFactory {
    /**
     * The total count of settings item
     */
    public static final int TOTAL_ITEM_TYPE = 3;

    private static final HiLogLabel TAG = new HiLogLabel(HiLog.LOG_APP, 0, "SettingsItemFactory");
    private static final String SETTINGS_PATTERN_PATH = "resources/rawfile/settingsPattern.json";
    private static final int TMP_BUFFER_SIZE = 1024 * 1024;

    private static final String JSON_TAG_TYPE = "type";
    private static final String JSON_TAG_GROUPNAME = "groupName";
    private static final String JSON_TAG_DATA = "data";
    private static final String JSON_TAG_SETTINGSLIST = "settingsList";
    private static final String JSON_TAG_ITEMS = "items";

    private static CategoryItemBase createListItem(Context context, ZSONObject zsonObj) {
        CategoryItemBase item = null;
        Integer componentType = zsonObj.getInteger(JSON_TAG_TYPE);
        if (componentType == null) {
            return null;
        }
        switch (componentType) {
            case GroupHeader.ITEM_TYPE:
                String groupName = zsonObj.getString(JSON_TAG_GROUPNAME);
                if (groupName != null) {
                    item = new GroupHeader(context, groupName);
                }
                break;
            case SimpleTextItem.ITEM_TYPE:
                SimpleTextItemData simpleTextItemData = zsonObj.getObject(JSON_TAG_DATA, SimpleTextItemData.class);
                if (simpleTextItemData != null) {
                    item = new SimpleTextItem(context, simpleTextItemData);
                }
                break;
            case SwitchItem.ITEM_TYPE:
                SwitchItemData switchItemData = zsonObj.getObject(JSON_TAG_DATA, SwitchItemData.class);
                if (switchItemData != null) {
                    item = new SwitchItem(context, switchItemData);
                }
                break;
            default:
                break;
        }
        return item;
    }

    /**
     * Init settings data from resources/rawfile/settingsPattern.json
     *
     * @param context current context
     * @return category list
     */
    public static List<Category> initSettings(Context context) {
        ArrayList<Category> categoryList = new ArrayList<>();
        try {
            Resource resource = context.getResourceManager().getRawFileEntry(SETTINGS_PATTERN_PATH).openRawFile();
            byte[] tmp = new byte[TMP_BUFFER_SIZE];
            int reads = resource.read(tmp);
            if (reads != -1) {
                String jsonString = new String(tmp, 0, reads, StandardCharsets.UTF_8);
                ZSONObject zson = ZSONObject.stringToZSON(jsonString);
                ZSONArray zsonArray = zson.getZSONArray(JSON_TAG_SETTINGSLIST);
                if (zsonArray == null) {
                    return categoryList;
                }
                for (int i = 0; i < zsonArray.size(); i++) {
                    ZSONObject group = (ZSONObject) zsonArray.get(i);
                    ArrayList<CategoryItemBase> itemList = new ArrayList<>();
                    CategoryItemBase groupHeader = createListItem(context, group);
                    if (groupHeader != null) {
                        itemList.add(groupHeader);
                    }
                    ZSONArray zsonItems = group.getZSONArray(JSON_TAG_ITEMS);
                    if (zsonItems == null) {
                        continue;
                    }
                    for (int j = 0; j < zsonItems.size(); j++) {
                        CategoryItemBase item = createListItem(context, (ZSONObject) zsonItems.get(j));
                        if (item != null) {
                            itemList.add(item);
                        }
                    }
                    Category category = new SettingsCategory(context, itemList, i);
                    categoryList.add(category);
                }
            }
        } catch (IOException ex) {
            HiLog.error(TAG, "InitSettings fail");
        }
        return categoryList;
    }
}