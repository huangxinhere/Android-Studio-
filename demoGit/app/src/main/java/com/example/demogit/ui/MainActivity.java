package com.example.demogit.ui;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demogit.Contract.MainContract;
import com.example.demogit.R;
import com.example.demogit.bean.User;
import com.example.demogit.view.UserAdapter;

import java.util.List;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.UserView, SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "MainActivity";
    private RecyclerView rv;
    private ImageView pic;
    private ConstraintLayout cs_layout;
    private TextView tv;
    private Button bt;
    private UserAdapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        presenter = createPresenter();
        presenter.request(refreshLayout);

    }

    @Override
    public MainContract.Presenter createPresenter() {
        return new MainContract.Presenter(this,this);
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

    @Override
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
        presenter.request(refreshLayout);
    }

    @Override
    public void show_failure() {
        rv.setVisibility(View.GONE);
        cs_layout.setVisibility(View.VISIBLE);
        refreshLayout.setRefreshing(false);
    }

    private void initView(){
        refreshLayout = findViewById(R.id.swipe_ly);
        rv = findViewById(R.id.rv);
        cs_layout = findViewById(R.id.cs_failure);
        tv = findViewById(R.id.failure_explain);
        pic = findViewById(R.id.failure_pic);
        bt = findViewById(R.id.retry_button);
        bt.setOnClickListener(v -> presenter.request(refreshLayout));
        refresh_list();

    }

}