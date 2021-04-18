package com.example.roomtext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private static final String TAG = "MA";
    private UserViewModel mUserViewModel;
    UserDao userDao;
    TextView textView;
    Button insert, delete, update, clear, query;
    String text;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UserListAdapter adapter = new UserListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setUsers(users);
            }
        });
        /*userDao = appDataBase.userDao();//抽象类和方法的实例化？
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
        LiveData<List<User>> list = userDao.getAll();
//LiveData:you have to observe the data so that
//when it changes, you can react.
        //逐条提取数据放置在text里
        StringBuilder text = new StringBuilder();
        for (User u:list){
            text.append(u.toString()).append("\n");
        }
        //展示
        textView.setText(text);
    }*/
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            User user = new User("11",data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mUserViewModel.insert(user);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}