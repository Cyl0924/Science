package com.wd.tech.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.bean.MessageBean;
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
}
