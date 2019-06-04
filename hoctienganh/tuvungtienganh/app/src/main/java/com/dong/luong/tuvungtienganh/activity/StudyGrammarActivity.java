package com.dong.luong.tuvungtienganh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.model.GrammarModel;
import com.dong.luong.tuvungtienganh.utils.Const;
import com.dong.luong.tuvungtienganh.utils.DatabaseManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

public class StudyGrammarActivity extends AppCompatActivity {

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
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.scroll_grammar)
    ScrollView scrollGrammar;
    @BindView(R.id.adView)
    AdView adView;
    private int position = 0;
    DatabaseManager databaseManager;
    private ArrayList<GrammarModel> grammarModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_grammar);
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

    }

    private void initViews() {
        grammarModels = new ArrayList<>();
        databaseManager = new DatabaseManager(this);
        imgLeft.setImageResource(R.drawable.ic_back);
        position = getIntent().getIntExtra(Const.KEY_POSITION, 0);
        grammarModels = databaseManager.getListGrammar();
        tvTitle.setText(grammarModels.get(position).getTitleGrammar());
        tvContent.setText(grammarModels.get(position).getContentGrammar());
        OverScrollDecoratorHelper.setUpOverScroll(scrollGrammar);
    }

    @OnClick({R.id.img_left, R.id.ln_left, R.id.btn_start})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                finish();
                break;
            case R.id.ln_left:
                finish();
                break;
            case R.id.btn_start:
                Intent intent = new Intent(StudyGrammarActivity.this, ExamActivity.class);
                intent.putExtra(Const.KEY_TITLE, grammarModels.get(position).getTitleGrammar());
                startActivity(intent);
                break;
        }
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
