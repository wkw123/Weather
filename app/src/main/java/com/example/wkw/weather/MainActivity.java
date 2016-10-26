package com.example.wkw.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ScrollView;

import com.example.wkw.swiperefresh.PullToRefreshBase;
import com.example.wkw.swiperefresh.PullToRefreshScrollView;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshScrollView mPullToRefreshScrollView;
    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refre_scoviews);
        mPullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });
        mScrollView = mPullToRefreshScrollView.getRefreshableView();
    }

    private void initView(){

    }

}
