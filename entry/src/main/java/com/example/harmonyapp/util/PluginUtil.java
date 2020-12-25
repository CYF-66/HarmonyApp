package com.example.harmonyapp.util;

import com.example.harmonyapp.model.PluginEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PluginUtil {

    private static PluginUtil mPluginUtil;

    private List<PluginEntry> pluginEntryList = new ArrayList<>(0);

    public static synchronized PluginUtil getInstance(){
        if(mPluginUtil == null)
            mPluginUtil = new PluginUtil();

        return mPluginUtil;
    }

    /**
     * init initPluginList
     *
     * @return pluginEntryList
     */
    public List<PluginEntry> initPluginList() {
        pluginEntryList.clear();
        pluginEntryList.add(new PluginEntry("ListContainer","list使用"));
        pluginEntryList.add(new PluginEntry("TabList和Tab","TabList和Tab使用"));
        pluginEntryList.add(new PluginEntry("ScrollView","ScrollView使用"));
        pluginEntryList.add(new PluginEntry("ToastDialog","ToastDialog使用"));
        pluginEntryList.add(new PluginEntry("RoundProgressBar","RoundProgressBar使用"));
        pluginEntryList.add(new PluginEntry("ProgressBar","ProgressBar使用"));
        pluginEntryList.add(new PluginEntry("Checkbox","Checkbox使用"));
        pluginEntryList.add(new PluginEntry("RadioContainer","RadioContainer使用"));
        pluginEntryList.add(new PluginEntry("RadioButton","RadioButton使用"));
        pluginEntryList.add(new PluginEntry("Switch","Switch使用"));
        pluginEntryList.add(new PluginEntry("TimePicker","TimePicker使用"));
        pluginEntryList.add(new PluginEntry("DatePicker","DatePicker使用"));
        pluginEntryList.add(new PluginEntry("Picker","Picker使用"));
        pluginEntryList.add(new PluginEntry("Image","Image使用"));
        pluginEntryList.add(new PluginEntry("TextField","TextField使用"));
        pluginEntryList.add(new PluginEntry("Button","Button使用"));
        pluginEntryList.add(new PluginEntry("Text","Text使用"));
        return pluginEntryList;
    }

    /**
     * 搜索
     * @param keyword
     * @return pluginEntryList
     */
    public List<PluginEntry> search(String keyword) {

        return pluginEntryList.stream().filter(pluginEntry -> pluginEntry.getName().contains(keyword))
                .collect(Collectors.toList());
    }

}
