package com.example.roomtext_02;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    WordDao wordDao;
    WordDataBase wordDataBase;
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

        tv = findViewById(R.id.textView);
        btn_insert = findViewById(R.id.btn_insert);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word1 = new Word("Hello","你好");
                Word word2 = new Word("world","世界");
                wordDao.insertWords(word1,word2);
                updateView();
            }
        });

        btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordDao.deleteAllWords();
                updateView();
            }
        });

        btn_update = findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi","泥猴啊！");
                word.setId(20);
                wordDao.updateWords(word);
                updateView();
            }
        });

        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("Hi","泥猴啊！");
                word.setId(17);
                wordDao.deleteWords(word);
                updateView();
            }
        });
    }
    void updateView(){
        List<Word> words = wordDao.getAllWords();
        String s = "";
        for (int i=0 ; i<words.size(); i++){
            Word word = words.get(i);
            s += "id:" + word.getId() + ",word:" + word.getWord() + ",meaning:" + word.getChineseMeaning() + "/n";
        }
        tv.setText(s);

    }
}