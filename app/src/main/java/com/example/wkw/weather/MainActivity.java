package com.example.wkw.weather;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wkw.swiperefresh.PullToRefreshBase;
import com.example.wkw.swiperefresh.PullToRefreshScrollView;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    private PullToRefreshScrollView mPullToRefreshScrollView;
    private ScrollView mScrollView;
    private TextView tv_city; //show current city
    private ImageView ima_city;//the button of clicking to select city
    private TextView tv_release_time;//the time of releasing
    private ImageView iv_now_weather; //current weather of image
    private TextView tv_now_weather; //currrent weather
    private TextView tv_today_tmp; //current temperature
    private TextView tv_aqi;//current Air quality index
    private TextView quality;//Air quality describe
    private TextView tv_next_three;
    private TextView tv_next_six;
    private TextView tv_next_nine;
    private TextView tv_next_twelve;
    private TextView tv_next_fifteen;
    private ImageView iv_next_three;
    private ImageView iv_next_six;
    private ImageView iv_next_nine;
    private ImageView iv_next_twelve;
    private ImageView iv_next_fifteen;


    private TextView tv_next_three_tmp;
    private TextView tv_next_six_tmp;
    private TextView tv_next_nine_tmp;
    private TextView tv_next_twelve_tmp;
    private TextView tv_next_fifteen_tmp;


    private ImageView iv_today_weather;
    private TextView tv_today_tmp_a;
    private TextView tv_today_tmp_b;

    private TextView tv_tomorrow_weather;
    private ImageView iv_tomorrow_weather;
        private TextView tv_tomorrow_tmp_a;
    private TextView tv_tomorrow_tmp_b;

    private TextView tv_thirdday;
    private ImageView iv_thirdday_weather;
    private TextView tv_thirdday_weather_a;
    private TextView tv_thirdday_weather_b;

    private TextView tv_fourthday;
    private ImageView iv_fourthday_weather;
    private TextView tv_fourthday_weather_a;
    private TextView tv_fourthday_weather_b;

    private TextView tv_surtmp;
    private TextView tv_hunidity;
    private TextView tv_wind;
    private TextView tv_uv_index;
    private TextView tv_dressing_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        getCityWeather();


    }

    private void initView(){

        tv_city = (TextView) findViewById(R.id.tv_city);
        ima_city = (ImageView) findViewById(R.id.ima_city);
        tv_release_time = (TextView) findViewById(R.id.tv_release);
        iv_now_weather = (ImageView) findViewById(R.id.iv_now_weather);
        tv_now_weather = (TextView) findViewById(R.id.tv_now_weather);
        tv_today_tmp = (TextView) findViewById(R.id.tv_today_tmp);
        tv_aqi = (TextView) findViewById(R.id.tv_aqi);
        quality = (TextView) findViewById(R.id.quality);

        tv_next_three = (TextView) findViewById(R.id.tv_next_three);
        tv_next_six = (TextView) findViewById(R.id.tv_next_six);
        tv_next_nine = (TextView) findViewById(R.id.tv_next_nine);
        tv_next_twelve = (TextView) findViewById(R.id.tv_next_twelve);
        tv_next_fifteen = (TextView) findViewById(R.id.tv_next_fifteen);

        iv_next_three = (ImageView) findViewById(R.id.iv_next_three);
        iv_next_six = (ImageView) findViewById(R.id.iv_next_six);
        iv_next_nine = (ImageView) findViewById(R.id.iv_next_nine);
        iv_next_twelve = (ImageView) findViewById(R.id.iv_next_twelve);
        iv_next_fifteen = (ImageView) findViewById(R.id.iv_next_fifteen);

        tv_next_three_tmp = (TextView) findViewById(R.id.tv_next_three_tmp);
        tv_next_six_tmp = (TextView) findViewById(R.id.tv_next_six_tmp);
        tv_next_nine_tmp = (TextView) findViewById(R.id.tv_next_nine_tmp);
        tv_next_twelve_tmp = (TextView) findViewById(R.id.tv_next_three_tmp);
        tv_next_fifteen_tmp = (TextView) findViewById(R.id.tv_next_fifteen_tmp);

        iv_today_weather = (ImageView) findViewById(R.id.iv_today_weather);
        tv_today_tmp_a = (TextView) findViewById(R.id.tv_today_tmp_a);
        tv_today_tmp_b = (TextView) findViewById(R.id.tv_today_tmp_b);

        tv_tomorrow_weather = (TextView) findViewById(R.id.tv_tomorrow);
        iv_tomorrow_weather = (ImageView) findViewById(R.id.iv_tomorrow_weather);
        tv_tomorrow_tmp_a = (TextView) findViewById(R.id.tv_tomorrow_tmp_a);
        tv_tomorrow_tmp_b = (TextView) findViewById(R.id.tv_tomorrow_tmp_b);

        tv_thirdday = (TextView) findViewById(R.id.tv_thirdday);
        iv_thirdday_weather = (ImageView) findViewById(R.id.iv_thirdday_weather);
        tv_thirdday_weather_a = (TextView) findViewById(R.id.tv_thirdday_weather_a);
        tv_thirdday_weather_b = (TextView) findViewById(R.id.tv_thirdday_weather_b);

        tv_fourthday = (TextView) findViewById(R.id.tv_fourthday);
        iv_fourthday_weather = (ImageView) findViewById(R.id.iv_fourthday_weather);
        tv_fourthday_weather_a = (TextView) findViewById(R.id.tv_fourthday_weather_a);
        tv_fourthday_weather_b = (TextView) findViewById(R.id.tv_fourthday_weather_b);

        tv_surtmp = (TextView) findViewById(R.id.tv_surtmp);
        tv_hunidity = (TextView) findViewById(R.id.tv_humidity);
        tv_wind = (TextView) findViewById(R.id.tv_wind);
        tv_dressing_index = (TextView) findViewById(R.id.tv_dressing_index);
        tv_uv_index = (TextView) findViewById(R.id.tv_uv_index);

        mPullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refre_scoviews);
        mPullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {

            }
        });
        mScrollView = mPullToRefreshScrollView.getRefreshableView();
    }


    public void getCityWeather() {

        Parameters params = new Parameters();
        params.add("cityname", "beijing");
        params.add("dtype", "beijing");
        params.add("format", 2);
        params.add("key", "c6e425dfe335a868e9bed915b63fcce2");
        /**
         * 请求的方法 参数: 第一个参数 当前请求的context;
         * 				  第二个参数 接口id;
         * 				  第三个参数 接口请求的url;
         * 				  第四个参数 接口请求的方式;
         * 				  第五个参数 接口请求的参数,键值对com.thinkland.sdk.android.Parameters类型;
         * 				  第六个参数请求的回调方法,com.thinkland.sdk.android.DataCallBack;
         *
         */
        JuheData.executeWithAPI(mContext, 39, "http://v.juhe.cn/weather/index",
                JuheData.GET, params, new DataCallBack() {
                    /**
                     * 请求成功时调用的方法 statusCode为http状态码,responseString为请求返回数据.
                     */
                    @Override
                    public void onSuccess(int statusCode, String responseString) {
                        // TODO Auto-generated method stub
//                        tv.append(responseString + "\n");
                    }

                    /**
                     * 请求完成时调用的方法,无论成功或者失败都会调用.
                     */
                    @Override
                    public void onFinish() {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(), "finish",
                                Toast.LENGTH_SHORT).show();
                    }

                    /**
                     * 请求失败时调用的方法,statusCode为http状态码,throwable为捕获到的异常
                     * statusCode:30002 没有检测到当前网络.
                     * 30003 没有进行初始化.
                     * 0 未明异常,具体查看Throwable信息.
                     * 其他异常请参照http状态码.
                     */
                    @Override
                    public void onFailure(int statusCode,
                                          String responseString, Throwable throwable) {
                        // TODO Auto-generated method stub
//                        tv.append(throwable.getMessage() + "\n");
                    }
                });
        }
}


