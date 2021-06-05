package com.example.demogit.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demogit.R;
import com.example.demogit.bean.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    private final LayoutInflater mInflater;
    private List<User.ItemsBean> items;
    //未展开状态
    private int opened = -1;

    public UserAdapter(Context context, List<User.ItemsBean> items){
        mInflater = LayoutInflater.from(context);
        this.items = items;
    }

    void setList(List<User.ItemsBean> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item,parent,false);
        return new UserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        if (items != null){
            holder.bind(position,items.get(position));
        }else {
            holder.name.setText("Error..");
        }
    }

    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        else
            return 0;
    }

    class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView name,link,desc,fork_num,star_num;
        private ConstraintLayout cs_1,cs_2;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.repo_name);
            link = itemView.findViewById(R.id.repo_link);
            desc = itemView.findViewById(R.id.desc);
            fork_num = itemView.findViewById(R.id.fork_num);
            star_num = itemView.findViewById(R.id.star_num);
            cs_1 = itemView.findViewById(R.id.cs_1);
            cs_2 = itemView.findViewById(R.id.cs_2);
            cs_1.setOnClickListener(this);
        }

        void bind(int position,User.ItemsBean item){
            name.setText(item.getRepo());
            link.setText(item.getRepo_link());
            desc.setText(item.getDesc());
            fork_num.setText(item.getForks());
            star_num.setText(item.getStars());

            if (position == opened){
                cs_2.setVisibility(View.VISIBLE);
            }else {
                cs_2.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            if (opened == getAdapterPosition()){
                //当点击的item已经被展开了，就关闭
                opened = -1;
                notifyItemChanged(getAdapterPosition());
            }else {
                //还没被展开，
                int preOpened = opened;
                opened = getAdapterPosition();
                notifyItemChanged(preOpened);
                notifyItemChanged(opened);
            }
        }
    }
}
