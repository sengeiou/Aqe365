package com.likeit.aqe365.activity.people.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.likeit.aqe365.R;
import com.likeit.aqe365.network.model.CaseEntity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class GoodIndent04Adapter extends BaseQuickAdapter<CaseEntity, BaseViewHolder> {
    private List<CaseEntity> datas;
    private IndentShopListAdapter mAdapter;
    public GoodIndent04Adapter(int layoutResId, List<CaseEntity> data) {
        super(R.layout.goods_indent_items, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, CaseEntity item) {
        baseViewHolder.setText(R.id.tv_indent_number, "订单号：" + "SH20180124064250464768");
        baseViewHolder.setText(R.id.tv_indent_status, "交易完成");
        baseViewHolder.setText(R.id.tv_indent_name, "奥泉医销网");
        baseViewHolder.setText(R.id.tv_total_number, "共 " + "1" + " 个商品,合计");
        baseViewHolder.setText(R.id.tv_total_price, "¥ 69.00");
        baseViewHolder.getView(R.id.ll_indent_button).setVisibility(View.VISIBLE);
        baseViewHolder.getView(R.id.tv_del_indent).setVisibility(View.VISIBLE);
        baseViewHolder.getView(R.id.tv_appraise_indent).setVisibility(View.VISIBLE);
        baseViewHolder.getView(R.id.tv_check_wuLiu).setVisibility(View.VISIBLE);
        baseViewHolder.setText(R.id.tv_del_indent, "删除订单");
        baseViewHolder.setText(R.id.tv_appraise_indent, "评价");
        baseViewHolder.setText(R.id.tv_check_wuLiu, "查看物流");
        baseViewHolder.addOnClickListener(R.id.tv_appraise_indent);
        baseViewHolder.addOnClickListener(R.id.tv_del_indent);
        baseViewHolder.addOnClickListener(R.id.tv_check_wuLiu);
        baseViewHolder.addOnClickListener(R.id.rl_indent_details);
        datas=new ArrayList<>();
        initData();
        mAdapter = new IndentShopListAdapter(R.layout.layout_indent_shop_listitems, datas);
        RecyclerView mRecyclerView = baseViewHolder.getView(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }
    private void initData() {
        for (int i = 0; i <2; i++) {
            CaseEntity caseEntity = new CaseEntity();
            caseEntity.setUrl(i + "");
            datas.add(caseEntity);
        }
    }
}