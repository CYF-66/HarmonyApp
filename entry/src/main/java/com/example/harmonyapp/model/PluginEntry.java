package com.example.harmonyapp.model;

public class PluginEntry {
    private String name;

    private String desc;
    public PluginEntry(String name,String desc) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
