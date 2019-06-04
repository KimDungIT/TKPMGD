package com.dong.luong.tuvungtienganh.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dong.luong.tuvungtienganh.MainActivity;
import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.model.GrammarModel;
import com.dong.luong.tuvungtienganh.utils.Const;
import com.dong.luong.tuvungtienganh.utils.DatabaseManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import at.grabner.circleprogress.CircleProgressView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

import static com.dong.luong.tuvungtienganh.MainActivity.mainActivity;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.tv_end_contest)
    TextView tvEndContest;
    @BindView(R.id.circle_progress)
    CircleProgressView circleProgress;
    @BindView(R.id.tv_percent)
    TextView tvPercent;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.rate_board)
    MaterialRatingBar rateBoard;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_watch_result)
    Button btnWatchResult;
    @BindView(R.id.adView)
    AdView adView;
    private int score = 0;
    private DatabaseManager databaseManager;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
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
        rateBoard.setIsIndicator(true);
        rateBoard.setFocusable(false);
        databaseManager = new DatabaseManager(ResultActivity.this);
        score = getIntent().getIntExtra(Const.KEY_SCORE, 0);
        position = getIntent().getIntExtra(Const.KEY_POSITION, 0);
        tvScore.setText(String.format("%d/20 câu", score));
        float percen = (((float) score / 20) * 100);
        circleProgress.setValue(percen);
        rateBoard.setMax(100);
        tvPercent.setText((int) percen + "%");
        rateBoard.setProgress((int) percen);
        GrammarModel grammarModel = databaseManager.getListGrammar().get(position);
        grammarModel.setLevel((int) percen);
        databaseManager.updateLevelGrammar(grammarModel);
        MainActivity mainActivity = MainActivity.mainActivity;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.btn_back, R.id.btn_watch_result})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);

                mainActivity.bottomBarSelect(2);

                startActivity(intent);
                break;
            case R.id.btn_watch_result:
                Intent intent1 = new Intent(ResultActivity.this, WatchResultActivity.class);
                intent1.putExtra(Const.KEY_POSITION, position);
                startActivity(intent1);
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
