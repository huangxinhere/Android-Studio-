package com.example.criminallintent;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class CrimeFragment extends Fragment{
    private Crime mCrime;
    private EditText mTitleField;

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
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }
}
