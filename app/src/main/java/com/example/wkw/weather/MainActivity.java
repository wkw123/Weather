package com.example.wkw.weather;

import android.content.Context;

import java.text.SimpleDateFormat;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wkw.bean.FutureWeatherBean;
import com.example.wkw.bean.HoursWeatherBean;
import com.example.wkw.bean.WeatherBean;
import com.example.wkw.swiperefresh.PullToRefreshBase;
import com.example.wkw.swiperefresh.PullToRefreshScrollView;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.wkw.constant.Constant.*;

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
    private TextView tv_now_tmp;
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

    private TextView tv_tomorrow;
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

    private SimpleDateFormat simpleDateFormat;
    private WeatherBean weatherBean;
    List<FutureWeatherBean> futureList;
    List<HoursWeatherBean> hoursWeatherBeanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        getCityWeather();
//        initDate();
        parserForecast3H();
    }

    private void initView() {

        tv_city = (TextView) findViewById(R.id.tv_city);
        ima_city = (ImageView) findViewById(R.id.ima_city);
        tv_release_time = (TextView) findViewById(R.id.tv_release);
        iv_now_weather = (ImageView) findViewById(R.id.iv_now_weather);
        tv_now_weather = (TextView) findViewById(R.id.tv_now_weather);
        tv_today_tmp = (TextView) findViewById(R.id.tv_today_tmp);
        tv_now_tmp = (TextView) findViewById(R.id.tv_now_tmp);
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

        tv_tomorrow = (TextView) findViewById(R.id.tv_tomorrow);
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

    private void initDate() {
        tv_city.setText(weatherBean.getCity());
        tv_now_weather.setText(weatherBean.getWeather_str());
        tv_release_time.setText(weatherBean.getRelease());
//        tv_today_tmp.setText(weatherBean.get);
//        8℃ ~  20℃ ↑↓
        String tmpStr[] = weatherBean.getTemp().split("~");
        String tmp_str_b = tmpStr[0].substring(0, tmpStr[0].indexOf("℃"));
        String tmp_str_a = tmpStr[1].substring(0, tmpStr[1].indexOf("℃"));
        tv_today_tmp.setText("↑ " + tmp_str_a + "°↓" + tmp_str_b + "°");
        tv_now_tmp.setText(weatherBean.getNow_temp());
        iv_today_weather.setImageResource(getResources().getIdentifier("d" + weatherBean.getWeather_id(), "drawable", "com.example.wkw.weather"));
        tv_today_tmp_a.setText(tmp_str_a + "°");
        tv_today_tmp_b.setText(tmp_str_b + "°");

//        tv_tomorrow.setText(futureList.get(0).getWeek());
        if (futureList.size() == 3) {
            setFutureViews(tv_tomorrow, iv_tomorrow_weather, tv_tomorrow_tmp_a, tv_tomorrow_tmp_b, futureList.get(0));
            setFutureViews(tv_thirdday, iv_thirdday_weather, tv_thirdday_weather_a, tv_thirdday_weather_a, futureList.get(1));
            setFutureViews(tv_fourthday, iv_fourthday_weather, tv_fourthday_weather_a, tv_fourthday_weather_a, futureList.get(2));
        }
    }

    private void setFutureViews(TextView tv_week, ImageView iv_weather, TextView tv_tmp_a, TextView tv_tmp_b, FutureWeatherBean FWB) {
        tv_week.setText(FWB.getWeek());
        iv_weather.setImageResource(getResources().getIdentifier("d" + FWB.getWeather_id(), "drawable", "com.example.wkw.weather"));
        String tmpStr[] = weatherBean.getTemp().split("~");
        String tmp_str_b = tmpStr[0].substring(0, tmpStr[0].indexOf("℃"));
        String tmp_str_a = tmpStr[1].substring(0, tmpStr[1].indexOf("℃"));
        tv_tmp_a.setText(tmp_str_a);
        tv_tmp_b.setText(tmp_str_b);
    }

    private void parserForecast3H() {
        Parameters params = new Parameters();
        params.add("cityname", "北京");
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
        JuheData.executeWithAPI(mContext, 39, "http://v.juhe.cn/weather/forecast3h",
                JuheData.GET, params, new DataCallBack() {
                    /**
                     * 请求成功时调用的方法 statusCode为http状态码,responseString为请求返回数据.
                     */
                    @Override
                    public void onSuccess(int statusCode, String responseString) {
                        // TODO Auto-generated method stub
//                        tv.append(responseString + "\n");
                        simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
                        Date currentDate = new Date(System.currentTimeMillis());
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(responseString);
                            int resultCode = jsonObject.getInt(RESULTCODE);
                            int errorCode = jsonObject.getInt(ERROR_CODE);
                            if (resultCode == 200 && errorCode == 0) {
                                hoursWeatherBeanList = new ArrayList<HoursWeatherBean>();
                                JSONArray jsonArray = jsonObject.getJSONArray(RESULT);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hourJson = jsonArray.getJSONObject(i);
                                    Log.i("mains", hourJson.toString());

                                    Date hDate = simpleDateFormat.parse(hourJson.getString(SFDATE));
                                    if(hDate.before(currentDate)){
                                        continue;
                                    }
                                    HoursWeatherBean hoursWeatherBean = new HoursWeatherBean();
                                    hoursWeatherBean.setWeather_id(hourJson.getString(WEATHERID));
                                    hoursWeatherBean.setTemp(hourJson.getString(TEMP1));
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(hDate);
                                    hoursWeatherBean.setTime(calendar.get(Calendar.HOUR_OF_DAY) + "");
                                    hoursWeatherBeanList.add(hoursWeatherBean);
                                    if(hoursWeatherBeanList.size() == 5){
                                        break;
                                    }
                                }
                                setHourViews();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


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
                     * 			  30003 没有进行初始化.
                     * 			  0 未明异常,具体查看Throwable信息.
                     * 			  其他异常请参照http状态码.
                     */
                    @Override
                    public void onFailure(int statusCode,
                                          String responseString, Throwable throwable) {
                        // TODO Auto-generated method stub
//                        tv.append(throwable.getMessage() + "\n");
                    }
                });
    }

    private void setHourViews(){
        setHourDate(tv_next_three, iv_next_three, tv_next_three_tmp, hoursWeatherBeanList.get(0));
        setHourDate(tv_next_six, iv_next_six, tv_next_six_tmp, hoursWeatherBeanList.get(1));
        setHourDate(tv_next_nine, iv_next_nine, tv_next_nine_tmp, hoursWeatherBeanList.get(2));
        setHourDate(tv_next_twelve, iv_next_twelve, tv_next_twelve_tmp, hoursWeatherBeanList.get(3));
        setHourDate(tv_next_fifteen, iv_next_fifteen, tv_next_fifteen_tmp, hoursWeatherBeanList.get(4));
    }

    public void setHourDate(TextView tv_hour, ImageView iv_weather, TextView tv_temp, HoursWeatherBean bean){
        String prefixStr = null;
        int time = Integer.parseInt(bean.getTime());
        if(time >= 6 && time <= 18){
            prefixStr = "d";
        }else {
            prefixStr = "n";
        }
//        if(time)
        tv_hour.setText(bean.getTime());
        Log.i("mains", "bean.getTime()" + bean.getTime());
        tv_temp.setText(bean.getTemp());
        iv_weather.setImageResource(getResources().getIdentifier(prefixStr + bean.getWeather_id(), "drawable", "com.example.wkw.weather"));
    }

    public void getCityWeather() {

        Parameters params = new Parameters();
        params.add("cityname", "北京");
//        params.add("dtype", "json");
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
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(int statusCode, String responseString) {
                        // TODO Auto-generated method stub
//                        tv.append(responseString + "\n");
                        try {
                            JSONObject jsonObject = new JSONObject(responseString);
                            int resultCode = jsonObject.getInt(RESULTCODE);
                            int errorCode = jsonObject.getInt(ERROR_CODE);
                            Log.i("mains", "errorCode" + errorCode + "");
                            if (resultCode == 200 && errorCode == 0) {
                                JSONObject resultJson = jsonObject.getJSONObject(RESULT);
                                weatherBean = new WeatherBean();

                                //today
                                JSONObject todayJson = resultJson.getJSONObject(TODAY);
                                Log.i("mains", "todayJson" + todayJson + "");
                                weatherBean.setCity(todayJson.getString(CITY));
                                Log.i("mains", "getString(CITY)" + todayJson.getString(CITY) + "");
                                weatherBean.setUv_index(todayJson.getString(UV_INDEX));
                                weatherBean.setTemp(todayJson.getString(TEMPERATURE));
                                weatherBean.setWeather_str(todayJson.getString(WEATHER));
                                weatherBean.setWeather_id(todayJson.getJSONObject(WEATHER_ID).getString(FA));
                                weatherBean.setDressing_index(todayJson.getString(DRESSING_INDEX));


                                //sk
                                JSONObject skJson = resultJson.getJSONObject(SK);
                                weatherBean.setWind(skJson.getString(WIND_DIRECTION) + skJson.getString(WIND_STRENGTH));
                                weatherBean.setNow_temp(skJson.getString(TEMP));
                                weatherBean.setRelease(skJson.getString(TIME));
                                Log.i("mains", "skJson.getString(TIME)" + skJson.getString(TIME) + "");
                                weatherBean.setHumidity(skJson.getString(HUMIDITY));

                                //future
                                JSONArray futureArray = resultJson.getJSONArray(FUTURE);
                                Date date = new Date(System.currentTimeMillis());
                                futureList = new ArrayList<>();

                                for (int i = 0; i < futureArray.length(); i++) {
                                    JSONObject futureJson = futureArray.getJSONObject(i);
                                    FutureWeatherBean futureWeatherBean = new FutureWeatherBean();


                                    simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

                                    try {
                                        Date datef = simpleDateFormat.parse(futureJson.getString(DATE));
                                        Log.i("mains", "datef" + datef + "");
                                        if (date.after(datef)) {
                                            continue;
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    futureWeatherBean.setWeather_id(futureJson.getJSONObject(WEATHER_ID).getString(FA));
                                    futureWeatherBean.setTemp(futureJson.getString(TEMPERATURE));
                                    futureWeatherBean.setWeek(futureJson.getString(WEEK));
                                    futureList.add(futureWeatherBean);
                                    if (futureList.size() == 3) {
                                        break;
                                    }
                                }
                                initDate();
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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


