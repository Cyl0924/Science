package com.wd.tech.api;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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
    public Observable<ResponseBody> doGet(@Url String url , @QueryMap HashMap<String,Object> hashMap);

    @GET
    public Observable<ResponseBody> doGetString(@Url String url,@Header("userId") int userId , @Header("sessionId") String sessionId);

    @GET
    public Observable<ResponseBody> doGetStringMap(@Url String url,@Header("userId") int userId , @Header("sessionId") String sessionId, @QueryMap HashMap<String,Object> hashMap);

    @GET
    public Observable<ResponseBody> doGetStrings(@Url String url,@Header("userId") int userId , @Header("sessionId") String sessionId,@QueryMap HashMap<String,Object> hashMap);

    @POST
    @FormUrlEncoded
    public Observable<ResponseBody> doPost(@Url String url , @FieldMap HashMap<String,Object> hashMap);

    @POST
    @FormUrlEncoded
    public Observable<ResponseBody> doPostString(@Url String url,@Header("userId") int userId , @Header("sessionId") String sessionId , @FieldMap HashMap<String,Object> hashMap);

    @PUT
    @FormUrlEncoded
    public Observable<ResponseBody> doPutString(@Url String url,@Header("userId") int userId , @Header("sessionId") String sessionId , @FieldMap HashMap<String,Object> hashMap);

    @DELETE
    @FormUrlEncoded
    public Observable<ResponseBody> doDeleteString(@Url String url,@Header("userId") int userId , @Header("sessionId") String sessionId , @FieldMap HashMap<String,Object> hashMap);

    @PUT
    public Observable<ResponseBody> doPutLogin(@Url String url,@Header("userId") int userId , @Header("sessionId") String sessionId);

}
