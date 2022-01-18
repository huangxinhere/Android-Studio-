package com.example.fsr;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.fsr.databinding.ChildMessageAcBinding;

import java.util.Calendar;

public class ChildMessageActivity extends AppCompatActivity{
    private static final String TAG = "ChildMessageAc";
    private TextView date;
    private Button boy_btn, girl_btn, finish;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChildMessageAcBinding binding = DataBindingUtil.setContentView(this,R.layout.child_message_ac);

    //gender
        boy_btn = (Button) findViewById(R.id.boy_btn);
        girl_btn = (Button) findViewById(R.id.girl_btn);
        boy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boy_btn.setBackgroundResource(R.drawable.boy_press);
                girl_btn.setBackgroundResource(R.drawable.girl);
                Log.e(TAG,"pressed!!!!");
            }
        });
        girl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boy_btn.setBackgroundResource(R.drawable.boy);
                girl_btn.setBackgroundResource(R.drawable.girl_press);
            }
        });

    //DateDialog
        date = (TextView) findViewById(R.id.editText2);
        getDate();

    //finish message
        finish = (Button) findViewById(R.id.baby_save);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChildMessageActivity.this,TwoChoicesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getDate(){
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                String days;

                if (mMonth + 1 < 10) {
                    if (mDay < 10) {
                        days = new StringBuffer().append(mYear).append("年").append("0").
                                append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                    } else {
                        days = new StringBuffer().append(mYear).append("年").append("0").
                                append(mMonth + 1).append("月").append(mDay).append("日").toString();
                    }

                } else {
                    if (mDay < 10) {
                        days = new StringBuffer().append(mYear).append("年").
                                append(mMonth + 1).append("月").append("0").append(mDay).append("日").toString();
                    } else {
                        days = new StringBuffer().append(mYear).append("年").
                                append(mMonth + 1).append("月").append(mDay).append("日").toString();
                    }
                }
                date.setText(days);
            }
        };

        date.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                new DatePickerDialog(ChildMessageActivity.this, onDateSetListener, mYear,mMonth,mDay).show();
            }
        });
    }


}
