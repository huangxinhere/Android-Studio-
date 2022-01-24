package com.example.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordHolder> {
    private List<Word> allWords = new ArrayList<>();

    public void setAllWords(List<Word> allWords) {
        this.allWords = allWords;
    }

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cell_normal,parent,false);
        return new WordHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordHolder holder, int position) {
        Word word = allWords.get(position);
        holder.tv_num.setText(String.valueOf(position+1));
        holder.tv_english.setText(word.getWord());
        holder.tv_chinese.setText(word.getChineseMeaning());
    }

    @Override
    public int getItemCount() {
        return allWords.size();
    }

    static class WordHolder extends RecyclerView.ViewHolder{
        TextView tv_num,tv_english,tv_chinese;
        public WordHolder(@NonNull View itemView) {
            super(itemView);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_english = itemView.findViewById(R.id.tv_english);
            tv_chinese = itemView.findViewById(R.id.tv_chinese);
        }
    }
}
