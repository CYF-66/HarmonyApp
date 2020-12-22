package com.example.m_shopping.model;

/**
 * ProductList goods item
 */
public class ProductList {
    private int id;
    private String name;
    private double price;
    private String iconUrl;
    private int commentCount;
    private int falconRate;
    private int productCount;

    /**
     * constructor of ProductList
     *
     * @param name         name
     * @param price        price
     * @param commentCount commentCount
     */
    public ProductList(String name, double price, int commentCount) {
        this.name = name;
        this.price = price;
        this.commentCount = commentCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getFalconRate() {
        return falconRate;
    }

    public void setFalconRate(int falconRate) {
        this.falconRate = falconRate;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
