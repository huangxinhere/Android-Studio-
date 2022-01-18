package com.example.textforsave;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class SaveStateActivity extends Activity {
    public static final String TAG = "SaveStateActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState!=null) {
            Log.v(TAG, savedInstanceState.getString("data"));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "onCreate");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.v(TAG, savedInstanceState.getString("data"));
        super.onRestoreInstanceState(savedInstanceState);
        Log.v(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("data", "example");
        super.onSaveInstanceState(outState);
        Log.v(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "onRestart");
    }
}

