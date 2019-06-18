package com.function.luo.collection.tools;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.function.luo.collection.R;

import java.util.List;

/**
 * Created by luo on 2019/4/25.
 */

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.MyHolder> {
    private final List<String> data;

    public SwipeAdapter(List<String> data) {
        this.data = data;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swipe, parent, false);
        MyHolder holder = new MyHolder(view);
        view.setTag(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.name.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name ;
        public MyHolder(View itemView) {
            super(itemView);

             name = itemView.findViewById(R.id.tv_list_name);
        }
    }
}
