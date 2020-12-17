package com.example.criminallintent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;

    @Override/*使用布局并找到布局中的RecyclerView视图*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);

        /*创建布局/的实例化？*/
        mCrimeRecyclerView = (RecyclerView) view
                .findViewById(R.id.crime_recycle_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*没有LayoutManager的支持，不仅RecyclerView无法工作，还会导致应用崩溃*/

        return view;//返回给托管的activity？
    }

    private class CrimeHolder extends Fragment{
        public CrimeHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_crime,parent,false));
        }
    }
}
