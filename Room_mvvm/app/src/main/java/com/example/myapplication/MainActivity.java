package com.example.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.room.Delete;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordViewModel viewModel;

    Button btn_insert,btn_delete,btn_clear,btn_update;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(WordViewModel.class);

        tv = findViewById(R.id.textView);
        
        //开始监听
        viewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                StringBuilder s = new StringBuilder();
                for (int i=0 ; i<words.size(); i++){
                    Word word = words.get(i);
                    s.append("id:").append(word.getId())
                            .append(",word:").append(word.getWord())
                            .append(",meaning:").append(word.getChineseMeaning())
                            .append("/n");
                }
                tv.setText(s.toString());
            }
        });
        btn_insert = findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word1 = new Word("Hello","你好");
                Word word2 = new Word("world","世界");
                viewModel.insertWord(word1,word2);
            }
        });

        btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.clearWord();
            }
        });

        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi","泥猴啊！");
                word.setId(20);
                viewModel.updateWord(word);
            }
        });

        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi","泥猴啊！");
                word.setId(17);
                viewModel.deleteWord(word);
            }
        });
    }

}