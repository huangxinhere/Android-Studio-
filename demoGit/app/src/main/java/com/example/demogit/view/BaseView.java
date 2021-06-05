package com.example.demogit.view;

import com.example.demogit.bean.User;
import com.example.demogit.bean.User2;

import java.util.List;

public interface BaseView {

    void show_list(List<User.ItemsBean> users);

    void show_failure();
}
