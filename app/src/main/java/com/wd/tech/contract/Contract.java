package com.wd.tech.contract;

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
    }

    public interface PresenterInterface{
        public void RegisterPresenter(HashMap<String,Object> hashMap);
        public void LoginPresenter(HashMap<String,Object> hashMap);
        public void getStringPresenter();
        public void Destory();
        //banner展示列表
        public void   bannerShow();
        //资讯推荐展示列表(包含单独板块列表展示)
        public void  RecommendList(HashMap<String,Object> hashMap);
        //根据标题模糊查询
        public void  findInformationByTitle(HashMap<String,Object> hashMap);
        //根据作者名字模糊查询
        public void findInformationBySource(HashMap<String,Object> hashMap);
        //资讯广告
        public void  Advertising();
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
}
