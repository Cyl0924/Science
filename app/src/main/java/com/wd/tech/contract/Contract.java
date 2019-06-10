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
        public void PostModel(String url,HashMap<String,Object> hashMap, Model.ObjectCall objectCall);
        public void GetModel(String url,HashMap<String,Object> hashMap, Model.ObjectCall objectCall);
        public void PutModel(String url,HashMap<String,Object> hashMap, Model.ObjectCall objectCall);
        public void DeleteModel(String url,HashMap<String,Object> hashMap, Model.ObjectCall objectCall);

    }

    public interface PresenterInterface{
        public void RegisterPresenter(HashMap<String,Object> hashMap);
        public void LoginPresenter(HashMap<String,Object> hashMap);
    }

    public interface ViewInterface{
        public void RegisterView(MessageBean messageBean);
    }

    public interface LoginView{
        public void LoginView(LoginBean loginBean);
    }

}
