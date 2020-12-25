package com.example.harmonyapp.butterknife;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Bind component
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Bind {
    /**
     * value
     *
     * @return int
     */
    int value() default 0;
}
