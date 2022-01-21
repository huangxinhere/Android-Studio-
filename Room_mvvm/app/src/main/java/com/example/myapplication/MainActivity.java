package com.example.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordDao wordDao;
    WordDataBase wordDataBase;
    LiveData<List<Word>> allWords;

    Button btn_insert,btn_delete,btn_clear,btn_update;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordDataBase = Room.databaseBuilder(this,WordDataBase.class,"word_database")
                .allowMainThreadQueries()
                .build();
        wordDao = wordDataBase.getWordDao();
        //第一次获取数据
        allWords = wordDao.getAllWords();
        tv = findViewById(R.id.textView);
        
        //开始监听
        allWords.observe(this, new Observer<List<Word>>() {
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
                wordDao.insertWords(word1,word2);
            }
        });

        btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordDao.deleteAllWords();
            }
        });

        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi","泥猴啊！");
                word.setId(20);
                wordDao.updateWords(word);
            }
        });

        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi","泥猴啊！");
                word.setId(17);
                wordDao.deleteWords(word);
            }
        });
    }


}