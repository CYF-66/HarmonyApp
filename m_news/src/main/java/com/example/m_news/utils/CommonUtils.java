package com.example.m_news.utils;

import ohos.app.Context;
import ohos.global.resource.NotExistException;
import ohos.global.resource.WrongTypeException;

import java.io.IOException;

/**
 * Common utils
 */
public class CommonUtils {
    private static final int GET_COLOR_STATE_FAILED = -1;
    private static final int MAX_LENGTH = 127;
    private static final double BUFFER = 0.5;

    /**
     * Get color method
     *
     * @param context    context resourceID res id
     * @param resourceID res id
     * @return color
     */
    public static int getColor(Context context, int resourceID) {
        try {
            return context.getResourceManager().getElement(resourceID).getColor();
        } catch (IOException | NotExistException | WrongTypeException e) {
            com.example.m_news.utils.LogUtil.info("CommonUtils", "some exception happend");
        }
        return GET_COLOR_STATE_FAILED;
    }

    /**
     * Calculate the number of characters in the shared content.
     * One Chinese character = two English letters and one Chinese punctuation = two English punctuation.
     *
     * @param str string
     * @return length
     */
    public static long calculateLength(CharSequence str) {
        double len = 0;
        for (int i = 0; i < str.length(); i++) {
            int tmp = str.charAt(i);
            if (tmp > 0 && tmp < MAX_LENGTH) {
                len += BUFFER;
            } else {
                len++;
            }
        }
        return Math.round(len);
    }

    /**
     * Obtain the content defined in string.json.
     *
     * @param context    context
     * @param resourceId resourceID
     * @return length
     */
    public static String getString(Context context, int resourceId) {
        try {
            return context.getResourceManager().getElement(resourceId).getString();
        } catch (IOException | NotExistException | WrongTypeException e) {
            LogUtil.error("CommonUtils", "some error happend");
        }
        return "";
    }
}
