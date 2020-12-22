package com.example.m_news.slice;

import com.example.m_news.ResourceTable;
import com.example.m_news.adapters.NewsListAdapter;
import com.example.m_news.adapters.NewsTypeAdapter;
import com.example.m_news.been.NewsInfo;
import com.example.m_news.utils.CommonUtils;
import com.example.m_news.utils.LogUtil;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * News list slice
 */
public class MainAbilityListSlice extends AbilitySlice {
    private static final float FOURCE_TEXT_SIZE = 1.2f;
    private static final float UNFOURCE_TEXT_SIZE = 1.0f;
    private static final String TAG = "MainAbility List Slice";
    private Text selectText;

    private ListContainer newsListContainer;
    private ListContainer selectorListContainer;
    private List<NewsInfo> totalNewsDatas;
    private List<NewsInfo> newsDatas;

    private NewsTypeAdapter newsTypeAdapter;
    private NewsListAdapter newsListAdapter;
    private String[] newsTypes;
    private String[] newsTitles;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_news_list_layout);
        initView();
        initData();
        initListener();
        selectorListContainer.setItemProvider(newsTypeAdapter);
        newsListContainer.setItemProvider(newsListAdapter);
        newsTypeAdapter.notifyDataChanged();
        newsListAdapter.notifyDataChanged();
    }

    @Override
    public void onActive() {
        super.onActive();
        LogUtil.info(TAG, "onActive is called," + newsListAdapter.getCount());
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
        LogUtil.info(TAG, "onForeground is called");
    }

    private void initView() {
        Component component1 = findComponentById(ResourceTable.Id_selector_list);
        Component component2 = findComponentById(ResourceTable.Id_news_container);
        if (component1 instanceof ListContainer) {
            selectorListContainer = (ListContainer) component1;
        }
        if (component2 instanceof ListContainer) {
            newsListContainer = (ListContainer) component2;
        }
    }

    private void initData() {
        newsTypes = new String[]{"All", "rec", "hot", "time", "ent", "polit", "sport"};
        newsTitles = new String[]{
                "Why are we poor?",
                "The Chinese embassy in Japan issues 3 important notices in 3 hours!",
                "The world's most expensive embassy, costing 5.1 billion to build",
                "What are the advantages of Portugal as the world's best pensioner in 2020?",
                "Saudi Arabia admires Chinese technology",
                "Table Tennis World Competition Returns CEO: Thanks China",
                "Andy Lau writes a letter: If you're sad, sing it out loud.",
                "China Consumer Association: \"Double 11\" Avoiding Seven Pits in Live TV Selling",
                "Zhang Junyu shows up at the airport, sporting style is fresh and handsome."
        };
        totalNewsDatas = new ArrayList<>();
        newsDatas = new ArrayList<>();
        initNewsData();
        newsTypeAdapter = new NewsTypeAdapter(newsTypes, this);
        newsListAdapter = new NewsListAdapter(newsDatas, this);
    }

    private void initNewsData() {
        for (int i = 0; i < newsTypes.length; i++) {
            NewsInfo newsInfo = new NewsInfo();
            newsInfo.setTitle(newsTitles[i]);
            newsInfo.setType(newsTypes[i]);
            totalNewsDatas.add(newsInfo);
        }
        newsDatas.addAll(totalNewsDatas);
    }

    /**
     * init listener of news type and news detail
     */
    private void initListener() {
        selectorListContainer.setItemClickedListener((listContainer, component, position, listener) -> {
            setCategorizationFocus(false);
            Component component1 = component.findComponentById(ResourceTable.Id_news_type_text);
            if (component1 instanceof Text) {
                selectText = (Text) component1;
            }
            setCategorizationFocus(true);
            newsDatas.clear();
            if (position == 0) {
                newsDatas.addAll(totalNewsDatas);
            } else {
                for (NewsInfo mTotalNewsData : totalNewsDatas) {
                    if (selectText.getText().equals(mTotalNewsData.getType())) {
                        newsDatas.add(mTotalNewsData);
                    }
                }
            }
            updateListView();
        });
        newsListContainer.setItemClickedListener((listContainer, component, i, l) -> {
            LogUtil.info(TAG, "onItemClicked is called");
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withBundleName(getBundleName())
                    .withAbilityName("com.example.m_news.MainAbility")
                    .withAction("action.detail")
                    .build();
            intent.setOperation(operation);
            startAbility(intent);
        });
    }

    private void setCategorizationFocus(boolean isFocus) {
        if (selectText == null) {
            return;
        }
        if (isFocus) {
            selectText.setTextColor(new Color(CommonUtils.getColor(MainAbilityListSlice.this, ResourceTable.Color_news_type_text_on)));
            selectText.setScaleX(FOURCE_TEXT_SIZE);
            selectText.setScaleY(FOURCE_TEXT_SIZE);
        } else {
            selectText.setTextColor(new Color(CommonUtils.getColor(MainAbilityListSlice.this, ResourceTable.Color_news_type_text_off)));
            selectText.setScaleX(UNFOURCE_TEXT_SIZE);
            selectText.setScaleY(UNFOURCE_TEXT_SIZE);
        }
    }

    private void updateListView() {
        newsListAdapter.notifyDataChanged();
        newsListContainer.invalidate();
        newsListContainer.scrollToCenter(0);
    }

}

