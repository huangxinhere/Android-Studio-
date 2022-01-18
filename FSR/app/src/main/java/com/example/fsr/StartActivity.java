package com.example.fsr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener{
    private Button log_btn;
    private TextView register_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_ac);

        initView();
    }

    private void initView(){
        log_btn = (Button) findViewById(R.id.log_bt);
        register_tv = (TextView) findViewById(R.id.register_tv);

        log_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(StartActivity.this, LogOnActivity.class);
        startActivity(intent);
    }
}