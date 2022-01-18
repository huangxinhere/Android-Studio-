package com.example.fsr;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LogOnActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LogOnActivity";

    private TextView title;
    private EditText account, password;
    private Button sure;
    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_ac);

        initView();
    }

    private void initView(){
        title = (TextView) findViewById(R.id.log_title_tv);
        account = (EditText) findViewById(R.id.account_edt);
        password = (EditText) findViewById(R.id.password_edt);
        sure = (Button) findViewById(R.id.account_sure);
        view = (View) findViewById(R.id.view);

        sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LogOnActivity.this, ChildMessageActivity.class);
        startActivity(intent);
    }
}
