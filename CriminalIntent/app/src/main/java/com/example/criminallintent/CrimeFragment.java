package com.example.criminallintent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class CrimeFragment extends Fragment{
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle saveInstanceState){//也具有保存及获取状态的bundle
        super.onCreate(saveInstanceState);//覆盖方法的原因：Fragment生命周期方法必须是公共方法，因为托管fragment的activity要调用
        mCrime = new Crime();
    }

    /*创建和配置fragment视图是另一个Fragment生命周期方法完成的*/
    /*该方法实例化fragment视图的布局*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime,container,false);
        //调用Lay..方法,传入资源ID/视图的父视图
    /*前两者是实例化布局的必要参数//实例化的View返回给托管activity*/

        FragmentManager fm = getFragmentManager();/*getFragmentManager()所得到的是所在fragment 的父容器的管理器，getChildFragmentManager()所得到的是在fragment  里面子容器的管理器。3.0版本后可直接使用第一个*/
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);/*获得管理的fragment；容器视图资源ID：告诉FragmentManager，fragment视图应该出现在activity视图的什么位置；唯一标识FragmentManager队列中的fragment*/

        if (fragment == null){
            fragment = new CrimeFragment();
    /*创建并提交一个fragment事务：添加、移除、附加、分离或替换fragment队列中的fragment——动态组装/重新组装用户界面*/
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
        }

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate().toString());
        mDateButton.setEnabled(false);

        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {//创建实现TextWatcher监听器接口的匿名内部类？
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());//调用CharSequence（代表用户输入）的toString()方法
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//compound混合物，复合物；为什么就要选这种？
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);//可记录一下各个对mCrime的方法
            }
        });

        return v;
    }
}