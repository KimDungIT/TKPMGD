package com.dong.luong.tuvungtienganh.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.adapter.WordDetailAdapter;
import com.dong.luong.tuvungtienganh.interfaces.ItemClickListener;
import com.dong.luong.tuvungtienganh.model.WordModel;
import com.dong.luong.tuvungtienganh.utils.Const;
import com.dong.luong.tuvungtienganh.utils.DatabaseManager;
import com.dong.luong.tuvungtienganh.utils.DividerItemDecoration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailWordActivity extends AppCompatActivity {
    @BindView(R.id.rcv_detail_vocabulary)
    RecyclerView rcvDetailVocabulary;
    @BindView(R.id.btn_tv_tuvung)
    TextView btnTvTuvung;
    @BindView(R.id.btn_tv_phatam)
    TextView btnTvPhatam;
    @BindView(R.id.btn_tv_nghia)
    TextView btnTvNghia;
    @BindView(R.id.btn_close_btm_sheet)
    Button btnCloseBtmSheet;
    @BindView(R.id.btn_sound)
    Button btnSound;
    @BindView(R.id.linear_layout_bottom_sheet)
    RelativeLayout linearLayoutBottomSheet;
    @BindView(R.id.backdrop)
    LinearLayout backdrop;
    @BindView(R.id.adView)
    AdView adView;
    private ArrayList<WordModel> wordModels;
    private WordDetailAdapter wordDetailAdapter;
    private DatabaseManager databaseManager;
    private int position = 0;
    private BottomSheetBehavior sheetBehavior;
    private MediaPlayer mediaPlayer;
    private int selectPosition = 0;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_word);
        ButterKnife.bind(this);
        initAdmob();
        initViews();
        initBottomSheet();
        initData();
    }

    private void initAdmob() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
    }

    private void initBottomSheet() {
        sheetBehavior = BottomSheetBehavior.from(linearLayoutBottomSheet);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int state) {
                switch (state) {
                    case BottomSheetBehavior.STATE_EXPANDED:
                        backdrop.setVisibility(View.VISIBLE);
                        break;
                    default:
                        backdrop.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
    }

    private void initData() {
    }

    private void initViews() {
        position = getIntent().getIntExtra(Const.KEY_POSITION, 0);
        wordModels = new ArrayList<>();
        databaseManager = new DatabaseManager(this);

        wordModels = databaseManager.getWordOrderTopic((position + 1));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailWordActivity.this);
        rcvDetailVocabulary.setLayoutManager(linearLayoutManager);
        wordDetailAdapter = new WordDetailAdapter(wordModels, this, new ItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                btnTvTuvung.setText(wordModels.get(position).getName());
                btnTvPhatam.setText(wordModels.get(position).getSpelling());
                btnTvNghia.setText(wordModels.get(position).getContain());
                selectPosition = position;
            }
        });
        rcvDetailVocabulary.addItemDecoration(
                new DividerItemDecoration(this, R.drawable.divider));
        rcvDetailVocabulary.setAdapter(wordDetailAdapter);
    }

    @OnClick({R.id.btn_close_btm_sheet, R.id.btn_sound})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_close_btm_sheet:
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.btn_sound:
                textToSpeech = new TextToSpeech(DetailWordActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            textToSpeech.setLanguage(Locale.ENGLISH);
                            textToSpeech.speak(wordModels.get(selectPosition).getName(), TextToSpeech.QUEUE_FLUSH, null);
                        } else {
                            Toast.makeText(DetailWordActivity.this, "Vui lòng kiểm tra kết nối mạng", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
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
