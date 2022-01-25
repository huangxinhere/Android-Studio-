package com.example.myapplication;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordViewModel viewModel;

    RecyclerView recyclerView;
    WordAdapter wordAdapter1,wordAdapter2;
    Switch switch_card;
    Button btn_insert,btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(WordViewModel.class);

        recyclerView = findViewById(R.id.rv);
        wordAdapter1 = new WordAdapter(true);
        wordAdapter2 = new WordAdapter(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(wordAdapter1);

        switch_card = findViewById(R.id.switch1);
        switch_card.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    recyclerView.setAdapter(wordAdapter2);
                }else
                    recyclerView.setAdapter(wordAdapter1);
            }
        });

        //开始监听
        viewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(@Nullable List<Word> words) {
                wordAdapter1.setAllWords(words);
                wordAdapter2.setAllWords(words);
                wordAdapter1.notifyDataSetChanged();
                wordAdapter2.notifyDataSetChanged();

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