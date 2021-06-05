package com.example.demogit.Contract;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.demogit.api.Api;
import com.example.demogit.bean.User;
import com.example.demogit.presenter.BasePresenter;
import com.example.demogit.retrofitManage.RetrofitManager;
import com.example.demogit.view.BaseView;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/*
每写一个Activity
*都要一个Prensenter(继承自BasePrensenter)
*和一个View接口(继承自BaseView)
*所以放在一个类中
*/
public class MainContract {

    //每个activity对view接口要求不同，为演示再创show方法
    public interface UserView extends BaseView{
        void show_list(List<User.ItemsBean> users);
        void refresh_list();
    }

    public static class Presenter extends BasePresenter<UserView>{
        private static final String TAG = "MainContract_Presenter";
        private Api api;
        private Context context;

        public Presenter(UserView mView, Context context) {
            super(mView);
            this.context = context;
            api = RetrofitManager.getInstance().createApi(Api.class);
        }

        public void request(SwipeRefreshLayout refreshLayout) {
            api.pull("java","weekly")
                    .subscribeOn(Schedulers.io())//上游在子线程
                    .observeOn(AndroidSchedulers.mainThread())//下游在主线程
                    .subscribe(new Observer<User>() {//建立连接，下游
                        @Override
                        public void onSubscribe(Disposable d) {
                            //在接收状态中
                            Log.e(TAG,"onSubscribe: ");
                        }

                        @Override
                        public void onNext(User value) {
                            //执行任务
                            Log.e(TAG,"onNext: " + value.toString());
                            List<User.ItemsBean> items = value.getItems();
                            if (items!=null){
                                Log.e(TAG,items.get(0).getRepo());
                                mView.show_list(items);
                            }
                            else
                                Log.e(TAG,"items is null！！！");
                        }

                        @Override
                        public void onError(Throwable e) {
                            mView.show_failure();
                            Toast.makeText(context,"请求失败",Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {
                            refreshLayout.setRefreshing(false);
                            Toast.makeText(context,"请求成功",Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
