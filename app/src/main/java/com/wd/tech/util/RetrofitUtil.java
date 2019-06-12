package com.wd.tech.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wd.tech.Immerse.StatusBarUtil;
import com.wd.tech.api.Api;
import com.wd.tech.app.MyApplication;
import com.wd.tech.app.StaticClass;
import com.wd.tech.contract.Contract;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class RetrofitUtil {

    private static RetrofitUtil util;
    private Retrofit retrofit;
    private Api api;

    //构造方法私有化
    private RetrofitUtil(){
        // https://mobile.bwstudent.com/
        /*SSLContext sslContext = MyApplication.getApplicationInstance().getSSLContext(MyApplication.getApplicationInstance().getApplicationContext());
        OkHttpClient client=new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory()) //添加证书
                .build();*/
        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//添加Rx转换器
                .baseUrl("https://mobile.bwstudent.com/") //baseurl
         //       .client(client)
                .build();

        api = retrofit.create(Api.class);
    }

    //网络判断工具
    //如需单独做网络判断调用工具类此方法即可进行判断无需多写
    public  boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    //双重锁单例模式
    public static RetrofitUtil getUtil(){
        if (util == null){
            synchronized (RetrofitUtil.class){
                if(util == null){
                    util = new RetrofitUtil();
                }
            }
        }
        return util;
    }

    /*
     * 常用不带请求头 以String类型参数入参的请求方法
     */

    public void doGet(String url, HashMap<String,Object> hashMap , Observer<ResponseBody> observer){
        Observable observable = api.doGet(url,hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void doGetString(String url, Observer<ResponseBody> observer){
        Observable observable = api.doGetString(url,StaticClass.userId,StaticClass.sessionId);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void doPost(String url, HashMap<String,Object> hashMap , Observer<ResponseBody> observer){
        Observable observable = api.doPost(url,hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void doPostString(String url, HashMap<String,Object> hashMap , Observer<ResponseBody> observer){
        Observable observable = api.doPostString(url,StaticClass.userId,StaticClass.sessionId, hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void doPutString(String url, HashMap<String,Object> hashMap , Observer<ResponseBody> observer){
        Observable observable = api.doPutString(url,StaticClass.userId,StaticClass.sessionId, hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void doDeleteString(String url, HashMap<String,Object> hashMap , Observer<ResponseBody> observer){
        Observable observable = api.doDeleteString(url,StaticClass.userId,StaticClass.sessionId, hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    public void doGetStringMap(String url, HashMap<String,Object> hashMap, Observer<ResponseBody> observer){
        Observable observable = api.doGetStringMap(url,StaticClass.userId,StaticClass.sessionId,hashMap);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

}
