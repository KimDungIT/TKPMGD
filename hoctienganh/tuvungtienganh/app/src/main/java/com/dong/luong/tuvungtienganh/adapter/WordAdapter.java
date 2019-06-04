package com.dong.luong.tuvungtienganh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.interfaces.ItemClickListener;
import com.dong.luong.tuvungtienganh.model.WordModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<WordModel> wordModels;
    private ItemClickListener itemClickListener;

    public WordAdapter(Context context, ArrayList<WordModel> wordModels, ItemClickListener itemClickListener) {
        this.context = context;
        this.wordModels = wordModels;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_word, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int position) {
        WordModel wordModel = wordModels.get(position);
        myViewHolder.tvTitle.setText(wordModel.getKey());
        myViewHolder.image.setImageResource(wordModel.getIdImage());
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position, v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.cardview)
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
