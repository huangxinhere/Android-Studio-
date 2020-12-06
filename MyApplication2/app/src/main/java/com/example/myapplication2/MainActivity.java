package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //显示布局
        setContentView(R.layout.activity_main);
        //初始化布局
        initUI();

    }
    private void initUI(){
        //获取媒体播放对象
        final MediaPlayer mediaPlayer= MediaPlayer.create(getApplicationContext(), R.raw.happy);

        //注册点击事件
       findViewById(R.id.accelerate).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (mediaPlayer.isPlaying()){
                   mediaPlayer.pause();
               }else  {
                   mediaPlayer.start();
               }

           }
       });


    }


}