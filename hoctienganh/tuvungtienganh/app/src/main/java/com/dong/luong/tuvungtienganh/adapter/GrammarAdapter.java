package com.dong.luong.tuvungtienganh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.interfaces.ItemClickListener;
import com.dong.luong.tuvungtienganh.interfaces.OnClickListener;
import com.dong.luong.tuvungtienganh.model.GrammarModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GrammarAdapter extends RecyclerView.Adapter<GrammarAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<GrammarModel> itemGrammars;
    private ItemClickListener itemClickListener;
    private OnClickListener onClickListener;
    private int type = 0;

    public GrammarAdapter(int type, Context context, ArrayList<GrammarModel> itemGrammars, ItemClickListener itemClickListener, OnClickListener onClickListener) {
        this.context = context;
        this.itemGrammars = itemGrammars;
        this.itemClickListener = itemClickListener;
        this.onClickListener = onClickListener;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grammar, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        GrammarModel itemGrammar = itemGrammars.get(position);
        if (type == 0) {
            myViewHolder.tvTitle.setText(itemGrammar.getTitleGrammar());
            myViewHolder.progress.setVisibility(View.GONE);
            myViewHolder.tvPercent.setVisibility(View.GONE);
            myViewHolder.btnStart.setVisibility(View.GONE);
        } else {
            myViewHolder.tvTitle.setText("Level " + itemGrammar.getIndexGrammar());
            myViewHolder.progress.setProgress(itemGrammar.getLevel());
            myViewHolder.tvPercent.setText(String.format("%d%%", itemGrammar.getLevel()));
        }
        myViewHolder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(position, v);
            }
        });
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemGrammars.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.img_icon)
        ImageView imgIcon;
        @BindView(R.id.progress)
        ProgressBar progress;
        @BindView(R.id.tv_percent)
        TextView tvPercent;
        @BindView(R.id.btn_start)
        Button btnStart;
        @BindView(R.id.cardview)
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
