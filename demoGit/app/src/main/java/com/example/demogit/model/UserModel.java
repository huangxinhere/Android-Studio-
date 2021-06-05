package com.example.demogit.model;

import com.example.demogit.bean.User;

import java.util.List;

public interface UserModel {

    //model层，用来处理业务逻辑
    List<User.ItemsBean> request();
}
