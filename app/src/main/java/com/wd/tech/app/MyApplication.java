package com.wd.tech.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.arclibrary.builder.AcrFaceManagerBuilder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.wd.tech.database.DataBean;
import com.wd.tech.greenDao.gen.DaoMaster;
import com.wd.tech.greenDao.gen.DaoSession;
import com.wd.tech.greenDao.gen.DataBeanDao;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class MyApplication extends Application {

    /*
     * 如果要进行初始化操作 在onCreate 中写即可 其他位置不要改动
     */

    private static final String TAG = "SCApplication";
    private static MyApplication mApplication;
    public static DataBeanDao dataBean;

    public static synchronized MyApplication getApplicationInstance() {
        if (mApplication == null) {
            mApplication = new MyApplication();
        }
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;

        /*
         * 数据库进行json数据缓存 存入之前请先根据 Url 判断数据库内是否有一样的数据 有则不进行缓存
         */
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "cache");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster=new DaoMaster(database);
        DaoSession daoSession = daoMaster.newSession();
        dataBean = daoSession.getDataBeanDao();

        // 图片不进行缓存 没有网络情况下 图片显示一个占位图即可
        Fresco.initialize(this);
        initArcFace();
    }

    private void initArcFace() {
        new AcrFaceManagerBuilder().setContext(this)
                .setFreeSdkAppId(Constants.FREESDKAPPID)
                .setFdSdkKey(Constants.FDSDKKEY)
                .setFtSdkKey(Constants.FTSDKKEY)
                .setFrSdkKey(Constants.FRSDKKEY)
                .setLivenessAppId(Constants.LIVENESSAPPID)
                .setLivenessSdkKey(Constants.LIVENESSSDKKEY)
                .create();
    }

    /**
     * 获取Https的证书
     *
     * @param context Activity（fragment）的上下文
     * @return SSL的上下文对象
     */
     public static SSLContext getSSLContext(Context context) {
        SSLContext s_sSLContext;
        try {
            s_sSLContext = SSLContext.getInstance("TLS");

            //信任所有证书 （官方不推荐使用）
            s_sSLContext.init(null, new TrustManager[]{new X509TrustManager() {

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {

                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {

                }
            }}, new SecureRandom());

            return s_sSLContext;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

}
