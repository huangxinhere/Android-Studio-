    package com.example.criminallintent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;

public class CrimeListFragment extends Fragment {
    private static final String TAG = "CrimeListFragment";
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private ImageView mSolvedImageView;
    private static int itemPosition;

    @Override/*使用布局并找到布局中的RecyclerView视图*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);//fragment_crime_list是RecyclerView视图

        mCrimeRecyclerView = (RecyclerView) view//这个view就是前面创建的view，它包含的文件再取出id？
                .findViewById(R.id.crime_recycle_view);
        updateUI();//有crimes，有adapter，有ViewHolder
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));//启动一个activity，就需要拿到activity对象才可以启动，而fragment对象是没有startActivity()方法的
        /*RecyclerView视图创建完成后，就立即转交给了LayoutManager对象,LM负责放置和屏幕滚动*/

        return view;
    }

    /*在onResume（）方法中刷新列表项*/
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_crime_list,menu);
    }

    /*关联Adapter和RecyclerView：创建CrimeAdapter，设置给RecyclerView*/
    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());//返回或构造crimeLab，似乎是CrimeLab类的唯一指定对象；然后才能用它的方法？public……？
        List<Crime> crimes = crimeLab.getCrimes();//获取数组列表

    if (mAdapter == null) {
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyItemChanged(itemPosition);
        }
    }


    /*Adapter:显示新创建的ViewHolder或让Crime对象和已创建的ViewHolder关联*/
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           LayoutInflater layoutInflater = LayoutInflater.from(getActivity());//菜鸟教程里有关于LayoutInflater,创建每一项的布局
           View view=layoutInflater.inflate(R.layout.list_item_crime,parent,false);
           return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);//数组里面int参数原来是索引，也就是CrimeLab类里面得到相应ID的方法
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }

    /*CrimeHolder写在CrimeAdapter里面，更容易看*/
    /*定义ViewHolder内部类：实例化并使用list_item_crime布局*/
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;

        public CrimeHolder(@NonNull View itemView){
            super(itemView);
            itemView.setOnClickListener(this);//itemView是什么？和之前的设置监听器有什么不同？

            //在构造器里实例化组件；
            mTitleTextView = itemView.findViewById(R.id.textView);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            mSolvedImageView = itemView.findViewById(R.id.crime_solved);
        }

        /*只要取到一个Crime，CrimeHolder就会刷新显示TextView标题视图和TextView日期视图;bind:捆绑，捆绑组件和数据*/
        public void bind(Crime crime){
            mCrime = crime;
            String date;
            date = (String) DateFormat.getDateInstance(DateFormat.FULL).format(mCrime.getDate());
            mTitleTextView.setText(mCrime.getTitle());//之前打错字，然后弄出默认的空的view值，一直报错
            mDateTextView.setText(date);
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);
            Log.d(TAG,"title:"+crime.getTitle()+"date:"+crime.getDate().toString());
        }

        @Override
        public void onClick(View view){
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getId());
            itemPosition = getBindingAdapterPosition();
            startActivity(intent);
        }
    }
}
