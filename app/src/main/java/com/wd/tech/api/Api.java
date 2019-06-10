package com.wd.tech.api;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public interface Api {

    @GET
    public Observable<ResponseBody> doGetString(@Header("userId") int userId , @Header("sessionId") String sessionId , @Url String url , @QueryMap HashMap<String,Object> hashMap);

    @GET
    public Observable<ResponseBody> doGet(@Url String url , @QueryMap HashMap<String,Object> hashMap);

    @GET
    public Observable<ResponseBody> doGetHeader(@Header("userId") int userId , @Header("sessionId") String sessionId , @Url String url );

    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> doPostString(@Header("userId") int userId , @Header("sessionId") String sessionId , @FieldMap HashMap<String,Object> hashMap);

    @FormUrlEncoded
    @POST
    public Observable<ResponseBody> doPost(@Url String url , @FieldMap HashMap<String,Object> hashMap);


}
