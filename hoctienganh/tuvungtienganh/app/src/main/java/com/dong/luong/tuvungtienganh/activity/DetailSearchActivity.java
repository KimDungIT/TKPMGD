package com.dong.luong.tuvungtienganh.activity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.utils.Const;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailSearchActivity extends AppCompatActivity {

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
    @BindView(R.id.scroll)
    NestedScrollView scroll;
    @BindView(R.id.adView)
    AdView adView;
    private String content;
    private String title;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_search);
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
        content = getIntent().getStringExtra(Const.KEY_CONTENT);
        title = getIntent().getStringExtra(Const.KEY_TITLE);
        imgLeft.setImageResource(R.drawable.ic_back);
        imgRight.setImageResource(R.drawable.ic_volume);
        tvContent.setText(content);
        tvTitle.setText(title);
    }

    @OnClick({R.id.img_left, R.id.ln_left, R.id.img_right, R.id.ln_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_left:
                break;
            case R.id.ln_left:
                finish();
                break;
            case R.id.img_right:
                break;
            case R.id.ln_right:
                textToSpeech = new TextToSpeech(DetailSearchActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            textToSpeech.setLanguage(Locale.ENGLISH);
                            textToSpeech.speak(title, TextToSpeech.QUEUE_FLUSH, null);
                        } else {
                            Toast.makeText(DetailSearchActivity.this, "Vui lòng kiểm tra kết nối mạng", Toast.LENGTH_SHORT).show();
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
