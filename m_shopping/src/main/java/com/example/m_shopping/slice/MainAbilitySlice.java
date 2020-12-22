package com.example.m_shopping.slice;

import com.example.m_shopping.ResourceTable;
import com.example.m_shopping.adapter.CommonAdapter;
import com.example.m_shopping.adapter.ViewHolder;
import com.example.m_shopping.base.BaseAbilitySlice;
import com.example.m_shopping.butterknife.Bind;
import com.example.m_shopping.model.ProductList;
import com.example.m_shopping.util.MediaUtil;

import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.ListContainer;
import ohos.agp.components.TextField;
import ohos.agp.utils.Color;

import java.util.List;

/**
 * MainAbilitySlice main class
 */
public class MainAbilitySlice extends BaseAbilitySlice implements Component.ClickedListener {
    private static final int COLOR_BLACK = 0xffff0000;
    private static final int COLOR_RED = 0xff000000;
    private static final int CHOOSE_ALL = 0;
    private static final int CHOOSE_SALE = 1;
    private static final int CHOOSE_PRICE = 2;
    private static final int CHOOSE_IN = 3;
    private int categoryId = 0;

    @Bind(ResourceTable.Id_product_lv)
    private ListContainer listContainer;
    @Bind(ResourceTable.Id_all_indicator)
    private Button allIndicator;
    @Bind(ResourceTable.Id_sale_indicator)
    private Button saleIndicator;
    @Bind(ResourceTable.Id_price_indicator)
    private Button priceIndicator;
    @Bind(ResourceTable.Id_choose_indicator)
    private Button chooseIndicator;
    @Bind(ResourceTable.Id_search)
    private Button search;
    @Bind(ResourceTable.Id_tf_search)
    private TextField tfSearch;
    private CommonAdapter<ProductList> productListCommonAdapter;

    public MainAbilitySlice() {
    }

    @Override
    public int getLayout() {
        return ResourceTable.Layout_product_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initList();
        initListener();
    }

    @Override
    protected void initData() {
        searchSuccess(MediaUtil.getInstance().initProductList());
    }

    private void searchSuccess(List<ProductList> productLists) {
        productListCommonAdapter.replace(productLists);
    }

    private void initListener() {
        listContainer.setItemClickedListener((listContainer1, component, i, l) -> {
            Intent intent = new Intent();
            Operation operation = new Intent.OperationBuilder()
                    .withBundleName(getBundleName())
                    .withAbilityName("com.example.m_shopping.MainAbility")
                    .withAction("action.system.detail")
                    .build();
            intent.setOperation(operation);
            startAbility(intent);
        });
        allIndicator.setClickedListener(this);
        saleIndicator.setClickedListener(this);
        priceIndicator.setClickedListener(this);
        chooseIndicator.setClickedListener(this);
        search.setClickedListener(this);
    }

    private void initList() {
        productListCommonAdapter = new CommonAdapter<ProductList>(getContext(), ResourceTable.Layout_product_lv_item) {
            @Override
            protected void convert(ViewHolder viewHolder, ProductList item, int position) {
                viewHolder.setText(ResourceTable.Id_name_tv, item.getName());
                viewHolder.setText(ResourceTable.Id_commrate_tv, String.valueOf(item.getCommentCount()));
                viewHolder.setText(ResourceTable.Id_price_tv, String.valueOf(item.getPrice()));
                viewHolder.setImageResource(ResourceTable.Id_image_car, ResourceTable.Media_icon_shopcar);
            }
        };
        listContainer.setItemProvider(productListCommonAdapter);
    }

    @Override
    public void onClick(Component component) {
        switch (component.getId()) {
            case ResourceTable.Id_all_indicator:
                categoryId = CHOOSE_ALL;
                searchSuccess(MediaUtil.getInstance().initProductList());
                break;
            case ResourceTable.Id_sale_indicator:
                categoryId = CHOOSE_SALE;
                searchSuccess(MediaUtil.getInstance().sortProduct());
                break;
            case ResourceTable.Id_price_indicator:
                categoryId = CHOOSE_PRICE;
                searchSuccess(MediaUtil.getInstance().sortCount());
                break;
            case ResourceTable.Id_choose_indicator:
                categoryId = CHOOSE_IN;

                break;
            case ResourceTable.Id_search:
                String searchName = tfSearch.getText();
                searchSuccess(MediaUtil.getInstance().searchProductList(searchName));
                break;
            default:
                break;
        }
        setClickColor(categoryId);
    }

    private void setClickColor(int position) {
        setAllIndicatorColor(position == CHOOSE_ALL);
        setSaleIndicatorColor(position == CHOOSE_SALE);
        setPriceIndicatorColor(position == CHOOSE_PRICE);
        setChooseIndicatorColor(position == CHOOSE_IN);
    }

    private void setAllIndicatorColor(boolean isClick) {
        allIndicator.setTextColor(new Color(isClick ? COLOR_BLACK : COLOR_RED));
    }

    private void setSaleIndicatorColor(boolean isClick) {
        saleIndicator.setTextColor(new Color(isClick ? COLOR_BLACK : COLOR_RED));
    }

    private void setPriceIndicatorColor(boolean isClick) {
        priceIndicator.setTextColor(new Color(isClick ? COLOR_BLACK : COLOR_RED));
    }

    private void setChooseIndicatorColor(boolean isClick) {
        chooseIndicator.setTextColor(new Color(isClick ? COLOR_BLACK : COLOR_RED));
    }
}
