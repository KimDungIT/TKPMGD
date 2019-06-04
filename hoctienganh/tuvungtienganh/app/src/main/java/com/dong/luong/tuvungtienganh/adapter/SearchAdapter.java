package com.dong.luong.tuvungtienganh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.interfaces.ItemClickListener;
import com.dong.luong.tuvungtienganh.model.SearchModel;
import com.dong.luong.tuvungtienganh.model.WordModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private Context context;
    private ItemClickListener itemClickListener;
    private ArrayList<SearchModel> searchModels;

    public SearchAdapter(Context context, ItemClickListener itemClickListener, ArrayList<SearchModel> searchModels) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.searchModels = searchModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        SearchModel searchModel = searchModels.get(position);
        myViewHolder.tvWord.setText(searchModel.getWord());
        myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_word)
        TextView tvWord;
        @BindView(R.id.linear)
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
