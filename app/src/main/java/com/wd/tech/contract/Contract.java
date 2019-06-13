package com.wd.tech.contract;

import com.wd.tech.bean.FaceBean;
import com.wd.tech.bean.JifenBean;
import com.wd.tech.bean.JifenBeanMingXi;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.bean.MessageBean;
import com.wd.tech.model.Model;

import java.util.HashMap;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public interface Contract {

    public interface ModelInterface{
        public void RegisterModel(HashMap<String,Object> hashMap, Model.ObjectCall objectCall);
        public void LoginModel(HashMap<String,Object> hashMap, Model.ObjectCall objectCall);
        public void getStringModel(String url, Model.ObjectCall objectCall);
        public void getUserModel(String url, HashMap<String,Object> hashMap, Model.ObjectCall objectCall);
        public void PostModel(String url,HashMap<String,Object> hashMap, Model.ObjectCall objectCall);
        public void GetModel(String url,HashMap<String,Object> hashMap, Model.ObjectCall objectCall);
        public void PutModel(String url,HashMap<String,Object> hashMap, Model.ObjectCall objectCall);
        public void DeleteModel(String url,HashMap<String,Object> hashMap, Model.ObjectCall objectCall);
        public void getStringUrl(String url, HashMap<String,Object> hashMap ,Model.ObjectBack objectBack);
        public void PutModelLogin(String url,Model.ObjectCall objectCall);
        public void postFace(HashMap<String,Object> hashMap,Model.ObjectCall objectCall);
    }

    public interface PresenterInterface{
        public void RegisterPresenter(HashMap<String,Object> hashMap);
        public void LoginPresenter(HashMap<String,Object> hashMap);
        public void getStringPresenter();
        public void putString(HashMap<String,Object> hashMap);
        public void getStringUrl(String url);
        public void getStringUrls(String url , HashMap<String,Object> hashMap);
        public void   bannerShow();
        public void  RecommendList(HashMap<String,Object> hashMap);
        public void  findInformationByTitle(HashMap<String,Object> hashMap);
        //根据作者名字模糊查询
        public void findInformationBySource(HashMap<String,Object> hashMap);
        //资讯广告
        public void  Advertising();
        public void putFace(HashMap<String,Object> hashMap);
        public void FaceLogin(HashMap<String,Object> hashMap);
        public void putStringLogin();
        public void Destory();
    }

    public interface ViewInterface{
        public void RegisterView(MessageBean messageBean);
    }

    public interface LoginView{
        public void LoginView(LoginBean loginBean);
    }

    public interface ObjectView{
        public void returnObject(Object obj);
    }

    public interface SignaView{
        public void returnAllMessage(Object obj);
    }

    public interface UserJifenView{
        public void returnUserJifen(JifenBean jifenBean);
    }

    public interface UsrtJifenMingXi{
        public void returnMingXi(JifenBeanMingXi jifenBeanMingXi);
    }
    public interface BannerView{
        public void  Ban(Object obj);
        public void  RecommendList(Object obj);
        //广告
        public void  Advertising(Object obj);
    }
    public interface SearchView{
        public void findInformationByTitle(Object obj);
        public void findInformationBySource(Object obj);
    }

    public interface BindFace{
        public void bindFaceId(FaceBean faceBean);
    }

}
