package com.example.m_shopping.util;

import com.example.m_shopping.model.ProductList;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Media utils
 */
public class MediaUtil {
    private static final int PRODUCT = 20;
    private static final int NUMBER = 2;
    private static final int RANDOM = 100;
    private static MediaUtil mediaUtil;
    private List<ProductList> productLists = new ArrayList<>(0);

    /**
     * Instance MediaUtil
     *
     * @return MediaUtil
     */
    public static synchronized MediaUtil getInstance() {
        if (mediaUtil == null) {
            mediaUtil = new MediaUtil();
        }
        return mediaUtil;
    }

    /**
     * init ProductList
     *
     * @return productLists
     */
    public List<ProductList> initProductList() {
        productLists.clear();
        for (int key = PRODUCT; key >= 0; key--) {
            if (key % NUMBER == 0) {
                productLists
                        .add(new ProductList("Snickers peanut rolls relieve fatigue and strengthen yourself",
                                scannerRandom(), scannerRandom()));
            } else {
                productLists.add(new ProductList("Dove's chocolate love feeling, let yourself go",
                        scannerRandom(), scannerRandom()));
            }
        }
        return productLists;
    }

    /**
     * sortProduct
     *
     * @return productLists
     */
    public List<ProductList> sortProduct() {
        productLists.sort((lhs, rhs) -> {
            Double newScore = lhs.getPrice();
            Double oldScore = rhs.getPrice();
            return newScore.compareTo(oldScore);
        });
        return productLists;
    }

    /**
     * sortCount
     *
     * @return productLists
     */
    public List<ProductList> sortCount() {
        productLists.sort((lhs, rhs) -> {
            int newProductCount = lhs.getCommentCount();
            int oldProductCount = rhs.getCommentCount();
            return newProductCount - oldProductCount;
        });
        return productLists;
    }

    /**
     * search
     *
     * @param msg msg
     * @return productLists
     */
    public List<ProductList> searchProductList(String msg) {
        return productLists.stream().filter(ProductList -> ProductList.getName()
                .contains(msg))
                .collect(Collectors.toList());
    }

    /**
     * Random
     *
     * @return int
     */
    private int scannerRandom() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(RANDOM);
    }
}
