package com.dong.luong.tuvungtienganh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.model.QuestionModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchResultAdapter extends RecyclerView.Adapter<WatchResultAdapter.MyViewHolder> {
    private ArrayList<QuestionModel> questionModels;
    private Context context;

    public WatchResultAdapter(ArrayList<QuestionModel> questionModels, Context context) {
        this.questionModels = questionModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_question, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {
        QuestionModel questionModel = questionModels.get(position);
        myViewHolder.tvNumberQuestion.setText("Câu " + (position + 1) + "/20");
        myViewHolder.tvQuestion.setText(questionModel.getTitle());
        myViewHolder.tvAnswerA.setText(questionModel.getAnswerA());
        myViewHolder.tvAnswerB.setText(questionModel.getAnswerB());
        myViewHolder.tvAnswerC.setText(questionModel.getAnswerC());
        myViewHolder.tvAnswerD.setText(questionModel.getAnswerD());
        myViewHolder.tvTrue.setText("Đáp án đúng: " + questionModel.getTrueAnswer());
        myViewHolder.tvExplain.setText("Giải thích: " + questionModel.getSubAnswer());
    }

    @Override
    public int getItemCount() {
        return questionModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_number_question)
        TextView tvNumberQuestion;
        @BindView(R.id.tv_question)
        TextView tvQuestion;
        @BindView(R.id.tv_answer_a)
        TextView tvAnswerA;
        @BindView(R.id.rl_answer_a)
        RelativeLayout rlAnswerA;
        @BindView(R.id.tv_answer_b)
        TextView tvAnswerB;
        @BindView(R.id.rl_answer_b)
        RelativeLayout rlAnswerB;
        @BindView(R.id.tv_answer_c)
        TextView tvAnswerC;
        @BindView(R.id.rl_answer_c)
        RelativeLayout rlAnswerC;
        @BindView(R.id.tv_answer_d)
        TextView tvAnswerD;
        @BindView(R.id.rl_answer_d)
        RelativeLayout rlAnswerD;
        @BindView(R.id.tv_true)
        TextView tvTrue;
        @BindView(R.id.tv_explain)
        TextView tvExplain;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
