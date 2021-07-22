package com.example.demogit.ui;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demogit.Contract.MainContract2;
import com.example.demogit.R;
import com.example.demogit.bean.User;
import com.example.demogit.presenter.Presenter;
import com.example.demogit.view.UserAdapter;

import java.util.List;

public class MainActivity extends BaseActivity<Presenter> implements MainContract2.IView, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "MainActivity";
    private RecyclerView rv;
    private ImageView pic;
    private ConstraintLayout cs_layout;
    private TextView tv;
    private Button bt;
    private UserAdapter adapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public Presenter createPresenter() {
        return new Presenter();
    }

    @Override
    protected Object initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(){
        refreshLayout = findViewById(R.id.swipe_ly);
        rv = findViewById(R.id.rv);
        cs_layout = findViewById(R.id.cs_failure);
        tv = findViewById(R.id.failure_explain);
        pic = findViewById(R.id.failure_pic);
        bt = findViewById(R.id.retry_button);
        bt.setOnClickListener(v -> mPresenter.load());
        refresh_list();

    }

    @Override
    public void show_list(List<User.ItemsBean> items) {
        adapter = new UserAdapter(this,items);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //rv动画
        rv.getItemAnimator().setChangeDuration(300);
        rv.getItemAnimator().setMoveDuration(300);
        rv.setVisibility(View.VISIBLE);
        cs_layout.setVisibility(View.GONE);
    }

    @SuppressLint("ShowToast")
    @Override
    public void show_failure(String data) {
        rv.setVisibility(View.GONE);
        cs_layout.setVisibility(View.VISIBLE);
        Toast.makeText(this,data,Toast.LENGTH_SHORT);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void show_complete(String data) {
        Toast.makeText(this,data,Toast.LENGTH_SHORT);
    }

    public void refresh_list() {
        //下拉监听
        refreshLayout.setOnRefreshListener(this);
        //刷新渐变颜色
        refreshLayout.setColorSchemeResources(
                R.color.red,
                R.color.blue,
                R.color.yellow,
                R.color.green,
                R.color.white
        );
    }

    @Override
    public void onRefresh() {
        mPresenter.load();
    }


}