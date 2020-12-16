package com.example.criminallintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;//s前缀的变量是静态变量
    private List<Crime> mCrimes;//List<E>是一个泛型类，支持存放特定数据类型的有序列表对象，拥有获取、新增和删除列表元素的方法
    /*<>符号告诉编译器元素类型可基于Crime参数放入crime对象*/

    /*创建单例：需创建带有私有构造方法及get（）方法的类/实例已存在，直接返回它；不存在，调用构造方法创建它*/
    public static CrimeLab get(Context context){// 私 有 构 造 方 法：其他类无法创建CrimeLab对象，除非用get——为啥这么干？
        if (sCrimeLab == null){//传入的Context对象以后14章会介绍
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    /*新建List将包含用户自建的Crime*/
    private CrimeLab(Context context) {
        mCrimes = new ArrayList<>();//初始化？
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    /*返回数组列表*/
    public List<Crime> getCrimes(){
        return mCrimes;
    }

    /*返回带指定ID的Crime对象*/
    public Crime getCrime(UUID id){//问题：UUID是什么，要引入java.util.UUID;答:工具类
        for (Crime crime : mCrimes){
            if (crime.getId().equals(id)){
                return crime;
            }
        }

        return null;
    }
}

