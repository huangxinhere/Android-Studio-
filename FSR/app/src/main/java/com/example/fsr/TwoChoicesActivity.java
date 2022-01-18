package com.example.fsr;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.fsr.databinding.TwoChoicesAcBinding;

public class TwoChoicesActivity extends AppCompatActivity {
    ImageView  left, right;
    Button start;

    boolean flag = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwoChoicesAcBinding binding = DataBindingUtil.setContentView(this, R.layout.two_choices_ac);

    /*basic operation*/
        left = (ImageView) findViewById(R.id.two_left_image);
        right = (ImageView) findViewById(R.id.two_right_image);
        start = (Button) findViewById(R.id.two_start_btn);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left.setImageResource(R.drawable.two_choices_left_press);
                right.setImageResource(R.drawable.two_choices_right);
                flag = true;
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                left.setImageResource(R.drawable.two_choices_left);
                right.setImageResource(R.drawable.two_choices_press_right);
                flag = false;
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){

                }
            }
        });
    }
}
