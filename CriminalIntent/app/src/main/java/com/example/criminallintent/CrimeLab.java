package com.example.criminallintent;

import android.content.Context;

public class CrimeLab {
    private static CrimeLab sCrimeLab;//s前缀的变量是静态变量

    /*创建单例：需创建带有私有构造方法及get（）方法的类/实例已存在，直接返回它；不存在，调用构造方法创建它*/
    public static CrimeLab get(Context context){// 私 有 构 造 方 法：其他类无法创建CrimeLab对象，除非用get——为啥这么干？
        if (sCrimeLab == null){//传入的Context对象以后14章会介绍
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){

    }
}

