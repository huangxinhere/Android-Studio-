package com.example.demogit.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.BaseAdapter;

import com.example.demogit.Contract.MainContract;
import com.example.demogit.R;
import com.example.demogit.bean.User;
import com.example.demogit.presenter.Presenter;
import com.example.demogit.view.UserAdapter;
import com.example.demogit.view.BaseView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.UserView {
    private static final String TAG = "MainActivity";
    private RecyclerView rv;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = createPresenter();
        presenter.request();

    }

    @Override
    public MainContract.Presenter createPresenter() {
        return new MainContract.Presenter(this,this);
    }

    @Override
    public void show_list(List<User.ItemsBean> items) {
        rv = (RecyclerView) findViewById(R.id.rv);
        final UserAdapter adapter = new UserAdapter(this,items);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.getItemAnimator().setChangeDuration(300);
        rv.getItemAnimator().setMoveDuration(300);
    }

    @Override
    public void show_failure() {

    }

}