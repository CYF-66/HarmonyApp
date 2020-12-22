package com.example.m_card.datamodel;

/**
 * ListItemInfo
 * Data model of Double-line List item
 */
public class ListItemInfo {
    private String primaryText;
    private String secondaryText;
    private int primaryImageId;
    private int secondaryImageId;

    /**
     * Item data model Constructor
     *
     * @param primaryText      Double-Line list item primary text
     * @param secondaryText    Double-Line list item secondary text
     * @param primaryImageId   Double-Line list item primary image resource ID
     * @param secondaryImageId Double-Line list item secondary image resource ID
     */
    public ListItemInfo(String primaryText, String secondaryText, int primaryImageId, int secondaryImageId) {
        this.primaryText = primaryText;
        this.secondaryText = secondaryText;
        this.primaryImageId = primaryImageId;
        this.secondaryImageId = secondaryImageId;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public int getPrimaryImageId() {
        return primaryImageId;
    }

    public int getSecondaryImageId() {
        return secondaryImageId;
    }
}
