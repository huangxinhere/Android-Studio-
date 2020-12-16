package com.example.criminallintent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.criminallintent.R;//什么时候就要导入一个包/地址？

public class SingleFragmentActivity extends AppCompatActivity {

    /*从activity_fragment.xml布局里实例化activity视图？*/
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

    /*然后在容器中查找FragmentManager里的fragment。如果找不到，就新建fragment并将其添加到容器中*/
        FragmentManager fm = creatFragment();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment =startActivityFromFragment();//
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }
    }
}
