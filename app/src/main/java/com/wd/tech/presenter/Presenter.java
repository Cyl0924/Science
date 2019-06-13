package com.wd.tech.presenter;


import android.util.Log;

import com.google.gson.Gson;
import com.wd.tech.app.StaticClass;
import com.wd.tech.bean.FaceBean;
import com.wd.tech.bean.JifenBean;
import com.wd.tech.bean.JifenBeanMingXi;
import com.wd.tech.bean.AdvertisingBean;
import com.wd.tech.bean.BannerBean;
import com.wd.tech.bean.FindByTitleBean;
import com.wd.tech.bean.InformationBean;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.bean.MessageBean;
import com.wd.tech.bean.RecommendBean;
import com.wd.tech.bean.UserMessage;
import com.wd.tech.contract.Contract;
import com.wd.tech.model.Model;

import java.util.HashMap;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class Presenter<T> implements Contract.PresenterInterface {

    Contract.ModelInterface model;
    T tt;
    Gson gson;
    //实例化Model层  也可以通过接口实例化类 方法多种
    public Presenter(T t) {
        model = new Model();
        this.tt = t;
    }
    @Override
    public void RegisterPresenter(HashMap<String, Object> hashMap) {
        model.RegisterModel(hashMap, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson = new Gson();
                MessageBean messageBean = gson.fromJson(object.toString(),MessageBean.class);
                Contract.ViewInterface viewInterface = (Contract.ViewInterface) tt;
                viewInterface.RegisterView(messageBean);
            }
        });
    }

    @Override
    public void LoginPresenter(HashMap<String, Object> hashMap) {
        model.LoginModel(hashMap, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson = new Gson();
                LoginBean loginBean = gson.fromJson(object.toString(),LoginBean.class);
                Contract.LoginView viewInterface = (Contract.LoginView) tt;
                viewInterface.LoginView(loginBean);
            }
        });
    }

    @Override
    public void getStringPresenter() {
        model.getStringModel(StaticClass.USERMESSAGE,  new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson = new Gson();
                UserMessage userMessage = gson.fromJson(object.toString(),UserMessage.class);
                Contract.ObjectView objectView = (Contract.ObjectView) tt;
                objectView.returnObject(userMessage);
            }
        });
    }

    @Override
    public void putString(HashMap<String, Object> hashMap) {
        model.PutModel(StaticClass.SINGATURE, hashMap, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson = new Gson();
                MessageBean messageBean = gson.fromJson(object.toString(),MessageBean.class);
                Contract.SignaView signaView = (Contract.SignaView) tt;
                signaView.returnAllMessage(messageBean);
            }
        });
    }

    @Override
    public void getStringUrl(String url) {
        model.getStringModel(url, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson = new Gson();
                JifenBean jifenBean = gson.fromJson(object.toString(),JifenBean.class);
                Contract.UserJifenView userJifenView = (Contract.UserJifenView) tt;
                userJifenView.returnUserJifen(jifenBean);
            }
        });
    }

    @Override
    public void getStringUrls(String url,HashMap<String,Object> hashMap) {
        model.getStringUrl(url, hashMap ,new Model.ObjectBack() {
            @Override
            public void returnBack(Object object) {
                gson = new Gson();
                JifenBeanMingXi jifenBeanMingXi = gson.fromJson(object.toString(),JifenBeanMingXi.class);
                Contract.UsrtJifenMingXi usrtJifenMingXi = (Contract.UsrtJifenMingXi) tt;
                usrtJifenMingXi.returnMingXi(jifenBeanMingXi);
            }
        });
    }

    @Override
    public void Destory() {
        model = null;
    }
    //Banner展示
    @Override
    public void bannerShow() {
        model.getStringModel(StaticClass.Banner, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson=new Gson();
                BannerBean bannerBean = gson.fromJson(object.toString(), BannerBean.class);
                Contract.BannerView bannerView= (Contract.BannerView) tt;
                bannerView.Ban(bannerBean);
            }
        });
    }
     //资讯推荐展示列表(包含单独板块列表展示)
    @Override
    public void RecommendList(HashMap<String,Object>  hashMap) {
        model.getUserModel(StaticClass.RecommendList, hashMap,new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson=new Gson();
                RecommendBean bean = gson.fromJson(object.toString(), RecommendBean.class);
                Contract.BannerView  view= (Contract.BannerView) tt;
                view.RecommendList(bean);
            }
        });
    }
    //根据标题模糊查询
    @Override
    public void findInformationByTitle(HashMap<String, Object> hashMap) {
        model.getUserModel(StaticClass.FindByTitle,hashMap, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson=new Gson();
                FindByTitleBean bean = gson.fromJson(object.toString(), FindByTitleBean.class);
                Contract.SearchView searchView= (Contract.SearchView) tt;
                searchView.findInformationByTitle(bean);
            }
        });
    }
    //根据作者名称模糊查询
    @Override
    public void findInformationBySource(HashMap<String, Object> hashMap) {
        model.getUserModel(StaticClass.FindBySource,hashMap, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson=new Gson();
                FindByTitleBean bean = gson.fromJson(object.toString(), FindByTitleBean.class);
                Contract.SearchView searchView= (Contract.SearchView) tt;
                searchView.findInformationBySource(bean);
            }
        });
    }
    //资讯广告
    @Override
    public void Advertising() {
        model.getStringModel(StaticClass.Advertising, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson=new Gson();
                AdvertisingBean bean = gson.fromJson(object.toString(), AdvertisingBean.class);
                Contract.BannerView bannerView= (Contract.BannerView) tt;
                bannerView.Advertising(bean);
            }
        });
    }

    @Override
    public void putFace(HashMap<String, Object> hashMap) {
        model.PutModel(StaticClass.BINDINGFACE, hashMap, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
               /* gson = new Gson(); //不再被调用
                FaceBean faceBean = gson.fromJson(object.toString(),FaceBean.class);
                Contract.BindFace bindFace = (Contract.BindFace) tt;
                bindFace.bindFaceId(faceBean);*/
            }
        });
    }

    @Override
    public void FaceLogin(HashMap<String, Object> hashMap) {
        model.postFace(hashMap, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson = new Gson();
                LoginBean loginBean = gson.fromJson(object.toString(),LoginBean.class);
                Log.e("tag","p"+""+loginBean.getMessage());
                Contract.LoginView viewInterface = (Contract.LoginView) tt;
                viewInterface.LoginView(loginBean);
            }
        });
    }

    @Override
    public void putStringLogin() {
        model.PutModelLogin(StaticClass.BINDINGFACE, new Model.ObjectCall() {
                    @Override
                    public void returnObject(Object object) {
                        gson = new Gson(); //不再被调用
                        FaceBean faceBean = gson.fromJson(object.toString(), FaceBean.class);
                        Contract.BindFace bindFace = (Contract.BindFace) tt;
                        bindFace.bindFaceId(faceBean);
                    }
                });
    }

    //咨询详情
    @Override
    public void InformationDetails(HashMap<String, Object> hashMap) {
        model.getUserModel(StaticClass.InformationDetails, hashMap, new Model.ObjectCall() {
            @Override
            public void returnObject(Object object) {
                gson=new Gson();
                InformationBean bean = gson.fromJson(object.toString(), InformationBean.class);
                Contract.InformationView view= (Contract.InformationView) tt;
                view.Information(bean);
            }
        });
    }
}
