package com.example.m_shopping.base;

import com.example.m_shopping.butterknife.Bind;
import com.example.m_shopping.butterknife.OnClick;
import com.example.m_shopping.util.LogUtils;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * BaseAbilitySlice base common AbilitySlice
 */
public abstract class BaseAbilitySlice extends AbilitySlice {
    private static final int CONSTANT = -1;
    private static final String TAG = BaseAbilitySlice.class.getSimpleName();

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        if (initArgs(intent)) {
            int layId = getLayout();
            setUIContent(layId);
            initBefore();
            initWidget();
            initData();
        } else {
            onBackPressed();
        }
    }

    /**
     * get layout
     *
     * @return xml
     */
    public abstract int getLayout();

    /**
     * Initialize control before starting
     */
    private void initBefore() {
    }

    /**
     * Initialize related parameters
     *
     * @param intent intent
     * @return true
     */
    private boolean initArgs(Intent intent) {
        return true;
    }

    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }

    @Override
    protected void onBackground() {
        super.onBackground();
    }

    @Override
    protected void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Initialize the control
     */
    protected void initWidget() {
        initViewAnnotationAndClick(this.getClass());
    }

    /**
     * Initialize the data
     */
    protected void initData() {
    }

    @Override
    protected void onResult(int requestCode, Intent resultIntent) {
        super.onResult(requestCode, resultIntent);
    }

    private void initViewAnnotation() {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            Bind bind = field.getAnnotation(Bind.class);
            if (bind != null) {
                if (bind.value() == CONSTANT) {
                    LogUtils.error(TAG, "bind.value is must set!");
                    return;
                }
                try {
                    field.setAccessible(true);
                    field.set(this, findComponentById(bind.value()));
                } catch (IllegalAccessException e) {
                    LogUtils.error(TAG, "IllegalAccessException :" + e.getMessage());
                }
            }
        }
    }

    private void initViewAnnotationAndClick(Class<?> cls) {
        initViewAnnotation();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            OnClick click = method.getAnnotation(OnClick.class);
            if (click != null) {
                if (click.id().length == 0) {
                    LogUtils.error(TAG, "click.id is must set!");
                    return;
                }
                for (int id : click.id()) {
                    if (id != 0) {
                        method.setAccessible(true);
                        findComponentById(id).setClickedListener(component -> {
                            try {
                                method.invoke(this, id);
                            } catch (IllegalAccessException e) {
                                LogUtils.error(TAG, "IllegalAccessException :" + e.getMessage());
                            } catch (InvocationTargetException e) {
                                LogUtils.error(TAG, "InvocationTargetException :" + e.getMessage());
                            }
                        });
                    }
                }
            }
        }
    }
}
