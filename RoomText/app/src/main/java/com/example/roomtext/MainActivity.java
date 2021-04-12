package com.example.roomtext;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MA";
    UserDao userDao;
    TextView textView;
    Button insert,delete,update,clear,query;
    String text;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //把数据库操作加入到主线程中。安卓不允许把数据库操作加入到主线程中。解决——第四行
        AppDataBase appDataBase;
        appDataBase = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class,"database_happy")//name:数据库保存之后文件的文件名
                .allowMainThreadQueries()//允许在主线程中查询
                .build();//返回实体来取得Dao的实体

        userDao = appDataBase.userDao();//抽象类和方法的实例化？
    //UI
        textView = (TextView) findViewById(R.id.textView);
        insert = (Button) findViewById(R.id.insert);
        delete = (Button) findViewById(R.id.delete);
        clear = findViewById(R.id.clear);
        update = findViewById(R.id.update);
        query = findViewById(R.id.query);

        delete.setOnClickListener(v -> {
            //准备一个元组
            User user = new User("fang","yuan");
            user.setUid(10);

            userDao.delete(user);
            show();
        });

        insert.setOnClickListener(v -> {
            User user1= new User("da","ming");
            User user2 = new User("fang","yuan");

            userDao.insertUser(user1,user2);
            show();
        });

        update.setOnClickListener(v -> {
            User user3 = new User("Xin","Huang");
            user3.setUid(5);

            userDao.updateUser(user3);
            show();
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] num = {1,2,4,5};
                List<User> users = userDao.loadAllByIds(num);
                Log.e(TAG,"第一个："+users.get(0).getFirstName()+
                                "第二个："+users.get(1).getFirstName());
            }
        });


    }

    private void show() {
        //拿到数据库的所有数据
        List<User> list = userDao.getAll();

        //逐条提取数据放置在text里
        StringBuilder text = new StringBuilder();
        for (User u:list){
            text.append(u.toString()).append("\n");
        }
        //展示
        textView.setText(text);
    }

}