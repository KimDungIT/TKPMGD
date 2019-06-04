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
import com.dong.luong.tuvungtienganh.model.WordModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WordDetailAdapter extends RecyclerView.Adapter<WordDetailAdapter.MyViewHolder> {
    private ArrayList<WordModel> wordModels;
    private Context context;
    private ItemClickListener itemClickListener;

    public WordDetailAdapter(ArrayList<WordModel> wordModels, Context context, ItemClickListener itemClickListener) {
        this.wordModels = wordModels;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_detail_word, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        WordModel wordModel = wordModels.get(position);
        myViewHolder.tvWord.setText(wordModel.getName());
        myViewHolder.tvWordSpell.setText("("+wordModel.getSpelling()+")");
        myViewHolder.tvExplain.setText(wordModel.getContain());
        myViewHolder.linear.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.tv_word)
        TextView tvWord;
        @BindView(R.id.tv_word_spell)
        TextView tvWordSpell;
        @BindView(R.id.tv_explain)
        TextView tvExplain;
        @BindView(R.id.linear)
        LinearLayout linear;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
