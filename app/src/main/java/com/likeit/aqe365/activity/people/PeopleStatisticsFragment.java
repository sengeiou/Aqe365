package com.likeit.aqe365.activity.people;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.king.base.BaseFragment;
import com.likeit.aqe365.R;
import com.likeit.aqe365.view.piechart.PieChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleStatisticsFragment extends BaseFragment {


    private TextView mTvTotal;
    private PieChartView mPieChart;

    public static PeopleStatisticsFragment newInstance() {
        Bundle bundle = new Bundle();
        PeopleStatisticsFragment fragment = new PeopleStatisticsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int inflaterRootView() {
        return R.layout.fragment_people_statistics;
    }

    @Override
    public void initUI() {
        /**
         * 布局文件152254235435
         */
        setBackView();
        setTitle("报表统计");
        mTvTotal = findView(R.id.tv_total);
        mPieChart = findView(R.id.pieChart);
        initView();
    }

    private void initView() {
        mTvTotal.setText(getResources().getString(R.string.app_statistics_month_total) + "¥ 888.00");
       PieChartView.PieItemBean[] items = new PieChartView.PieItemBean[]{
                new PieChartView.PieItemBean("商品一", 80),
                new PieChartView.PieItemBean("商品二", 100),
                new PieChartView.PieItemBean("商品三", 120),
                new PieChartView.PieItemBean("商品四", 90),
                new PieChartView.PieItemBean("商品五", 80),
                new PieChartView.PieItemBean("商品六", 70)
        };
        mPieChart.setPieItems(items);
    }

    @Override
    public void initData() {

    }

    @Override
    public void addListeners() {

    }
}
