package com.example.criminallintent;

import androidx.fragment.app.Fragment;

public class CrimeListActivity extends SingleFragmentActivity{

    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
