package com.dong.luong.tuvungtienganh.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.model.QuestionModel;
import com.dong.luong.tuvungtienganh.utils.Const;
import com.dong.luong.tuvungtienganh.utils.DatabaseManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExamActivity extends AppCompatActivity {
    @BindView(R.id.img_left)
    ImageView imgLeft;
    @BindView(R.id.ln_left)
    LinearLayout lnLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edt_search)
    EditText edtSearch;
    @BindView(R.id.img_clear)
    ImageView imgClear;
    @BindView(R.id.ln_edt_search)
    LinearLayout lnEdtSearch;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.ln_right)
    LinearLayout lnRight;
    @BindView(R.id.rlHeaderBar)
    RelativeLayout rlHeaderBar;
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
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_number_question)
    TextView tvNumberQuestion;
    @BindView(R.id.tv_question)
    TextView tvQuestion;
    @BindView(R.id.adView)
    AdView adView;
    private DatabaseManager databaseManager;
    private String title;
    private int position = 0;
    private ArrayList<QuestionModel> questionModels;
    private int countQuestion = 0;
    private int score = 0;
    private boolean isCheck = false;
    private String answerTrue;
    private boolean isSelectA = false;
    private boolean isSelectB = false;
    private boolean isSelectC = false;
    private boolean isSelectD = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
        initAdmob();
        initViews();
        initData();
    }

    private void initAdmob() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
    }

    private void initData() {
        questionModels = databaseManager.getQuestion(position);
        setColorDefaultButton();
        tvNumberQuestion.setText("Câu " + (countQuestion + 1) + "/20");
        tvQuestion.setText(questionModels.get(countQuestion).getTitle());
        tvAnswerA.setText(questionModels.get(countQuestion).getAnswerA());
        tvAnswerB.setText(questionModels.get(countQuestion).getAnswerB());
        tvAnswerC.setText(questionModels.get(countQuestion).getAnswerC());
        tvAnswerD.setText(questionModels.get(countQuestion).getAnswerD());
        answerTrue = questionModels.get(countQuestion).getTrueAnswer().trim();
    }

    private void initViews() {
        questionModels = new ArrayList<>();
        databaseManager = new DatabaseManager(this);
        imgLeft.setImageResource(R.drawable.ic_back);
        position = getIntent().getIntExtra(Const.KEY_POSITION, 0);
        tvTitle.setText("Level " + (position + 1));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.img_left, R.id.ln_left, R.id.rl_answer_a, R.id.rl_answer_b, R.id.rl_answer_c, R.id.rl_answer_d, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.ln_left:
                finish();
                break;
            case R.id.rl_answer_a:
                checkAnswerA();
                break;
            case R.id.rl_answer_b:
                checkAnswerB();
                break;
            case R.id.rl_answer_c:
                checkAnswerC();
                break;
            case R.id.rl_answer_d:
                checkAnswerD();
                break;
            case R.id.btn_next:
                countQuestion++;
                if ((isSelectA &&
                        tvAnswerA.getText().toString().substring(0, 2).trim().equals(answerTrue))
                        || ((isSelectB &&
                        tvAnswerB.getText().toString().substring(0, 2).trim().equals(answerTrue)))
                        || ((isSelectC &&
                        tvAnswerC.getText().toString().substring(0, 2).trim().equals(answerTrue)))
                        || ((isSelectD &&
                        tvAnswerD.getText().toString().substring(0, 2).trim().equals(answerTrue)))
                        ) {
                    score++;
                    Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
                }
                if (countQuestion == 20) {
                    Intent intent = new Intent(ExamActivity.this, ResultActivity.class);
                    intent.putExtra(Const.KEY_SCORE, score);
                    intent.putExtra(Const.KEY_POSITION, position);
                    startActivity(intent);
                } else {

                    setColorDefaultButton();
                    tvNumberQuestion.setText("Câu " + (countQuestion + 1) + "/20");
                    tvQuestion.setText(questionModels.get(countQuestion).getTitle());
                    tvAnswerA.setText(questionModels.get(countQuestion).getAnswerA());
                    tvAnswerB.setText(questionModels.get(countQuestion).getAnswerB());
                    tvAnswerC.setText(questionModels.get(countQuestion).getAnswerC());
                    tvAnswerD.setText(questionModels.get(countQuestion).getAnswerD());
                    answerTrue = questionModels.get(countQuestion).getTrueAnswer().trim();
                }
                break;
        }
    }

    private void checkAnswerD() {
        isSelectA = false;
        isSelectB = false;
        isSelectC = false;
        isSelectD = true;
        rlAnswerA.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerB.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerC.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerD.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_select));
    }

    private void checkAnswerC() {
        isSelectA = false;
        isSelectB = false;
        isSelectC = true;
        isSelectD = false;
        rlAnswerA.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerB.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerC.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_select));
        rlAnswerD.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));

    }

    private void checkAnswerB() {
        isSelectA = false;
        isSelectB = true;
        isSelectC = false;
        isSelectD = false;
        rlAnswerA.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerB.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_select));
        rlAnswerC.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerD.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));

    }

    private void setColorDefaultButton() {
        rlAnswerA.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerB.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerC.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerD.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        isSelectA = false;
        isSelectB = false;
        isSelectC = false;
        isSelectD = false;
    }

    private void checkAnswerA() {
        isSelectA = true;
        isSelectB = false;
        isSelectC = false;
        isSelectD = false;
        rlAnswerA.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_select));
        rlAnswerB.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerC.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));
        rlAnswerD.setBackground(ContextCompat.getDrawable(ExamActivity.this, R.drawable.state_default));


    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

}
