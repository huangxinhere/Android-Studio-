package com.example.criminallintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

public class MainActivity extends SingleFragmentActivity{
    /*抽象方法具体化,若有返回值，则返回类型要相同*/
    protected Fragment createFragment() {
        return new CrimeFragment();
    }
}