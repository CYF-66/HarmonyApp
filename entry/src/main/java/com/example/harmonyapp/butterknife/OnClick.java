package com.example.harmonyapp.butterknife;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * OnClick event
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClick {
    /**
     * OnClick id
     *
     * @return []
     */
    int[] id();
}
