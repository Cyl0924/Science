package com.wd.tech.model;

import android.util.Log;

import com.wd.tech.app.StaticClass;
import com.wd.tech.contract.Contract;
import com.wd.tech.util.RetrofitUtil;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import rx.Observer;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class Model implements Contract.ModelInterface {

    ObjectCall call;
    ObjectBack back;
    //注册方法
    @Override
    public void RegisterModel(HashMap<String, Object> hashMap, final ObjectCall objectCall) {
        //实例化CallBack
        this.call = objectCall;
        RetrofitUtil.getUtil().doPost(StaticClass.REGISTER, hashMap, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
                //成功回调
            }

            @Override
            public void onError(Throwable e) {
                //失败回调
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                //返回数据回调
                try {
                    String json = responseBody.string();
                    objectCall.returnObject(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void LoginModel(HashMap<String, Object> hashMap, final ObjectCall objectCall) {
        this.call = objectCall;
        RetrofitUtil.getUtil().doPost(StaticClass.LOGIN, hashMap, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
                    objectCall.returnObject(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getStringModel(String url, final ObjectCall objectCall) {
        this.call = objectCall;
        RetrofitUtil.getUtil().doGetString(url , new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
                    //Log.e("tag",json);
                    objectCall.returnObject(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void getUserModel(String url,HashMap<String,Object> hashMap, final ObjectCall objectCall) {
        this.call = objectCall;
          RetrofitUtil.getUtil().doGetHash(url, hashMap, new Observer<ResponseBody>() {
              @Override
              public void onCompleted() {

              }

              @Override
              public void onError(Throwable e) {

              }

              @Override
              public void onNext(ResponseBody body) {
                  try {
                      String  json = body.string();
                      //Log.e("tag",json+"----------------------- ");
                      objectCall.returnObject(json);
                  } catch (IOException e) {
                      e.printStackTrace();
                  }

              }
          });
    }
	 @Override
    public void PostModel(String url,HashMap<String, Object> hashMap, final ObjectCall objectCall) {
        this.call = objectCall;
        RetrofitUtil.getUtil().doPost(url, hashMap, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
                    objectCall.returnObject(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void GetModel(String url, HashMap<String, Object> hashMap, final ObjectCall objectCall) {
        this.call = objectCall;
        RetrofitUtil.getUtil().doGet(url, hashMap, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
                    objectCall.returnObject(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void PutModel(String url, HashMap<String, Object> hashMap,final ObjectCall objectCall) {
        this.call = objectCall;
        RetrofitUtil.getUtil().doPutString(url, hashMap, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("tag",e.getMessage());
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
                    objectCall.returnObject(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void DeleteModel(String url, HashMap<String, Object> hashMap, final ObjectCall objectCall) {
        this.call = objectCall;
        RetrofitUtil.getUtil().doDeleteString(url, hashMap, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
                    objectCall.returnObject(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getStringUrl(String url, HashMap<String,Object> hashMap, final ObjectBack objectBack) {
        this.back = objectBack;
        RetrofitUtil.getUtil().doGetStringMap(url,hashMap, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
               // Log.e("tag",e.getMessage());
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
                    //Log.e("tag",json+"++++++++++++++++++++++++");
                    objectBack.returnBack(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void PutModelLogin(String url, final ObjectCall objectCall) {
        this.call = objectCall;
        RetrofitUtil.getUtil().doPutStringLogin(url, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //Log.e("tag",e.getMessage());
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
                    //Log.e("tag",json);
                    objectCall.returnObject(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void postFace(HashMap<String, Object> hashMap, final ObjectCall objectCall) {
        this.call = objectCall;
        RetrofitUtil.getUtil().doPost(StaticClass.LOGINFACE, hashMap, new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                //Log.e("tag",e.getMessage());
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String json = responseBody.string();
                    objectCall.returnObject(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /*
     * CallBack 为避免对象引用错误关系 代码复用性高 统一采用返回值为Object到 Presenter进行强转返回View层
     */

    public interface ObjectCall{
        public void returnObject(Object object);
    }

    public interface ObjectBack{
        public void returnBack(Object object);
    }

}

