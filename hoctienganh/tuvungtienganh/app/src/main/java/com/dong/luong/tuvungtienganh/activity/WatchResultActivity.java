package com.dong.luong.tuvungtienganh.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.adapter.WatchResultAdapter;
import com.dong.luong.tuvungtienganh.model.QuestionModel;
import com.dong.luong.tuvungtienganh.utils.Const;
import com.dong.luong.tuvungtienganh.utils.DatabaseManager;
import com.dong.luong.tuvungtienganh.utils.DividerItemDecoration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchResultActivity extends AppCompatActivity {

    @BindView(R.id.rcv_result)
    RecyclerView rcvResult;
    @BindView(R.id.adView)
    AdView adView;
    private ArrayList<QuestionModel> questionModels;
    private WatchResultAdapter watchResultAdapter;
    private DatabaseManager databaseManager;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_result);
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
        position = getIntent().getIntExtra(Const.KEY_POSITION, 0);
        databaseManager = new DatabaseManager(this);
        questionModels = databaseManager.getQuestion(position);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WatchResultActivity.this);
        rcvResult.setLayoutManager(linearLayoutManager);
        watchResultAdapter = new WatchResultAdapter(questionModels, this);
        rcvResult.addItemDecoration(
                new DividerItemDecoration(this, R.drawable.divider));
        rcvResult.setAdapter(watchResultAdapter);
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
