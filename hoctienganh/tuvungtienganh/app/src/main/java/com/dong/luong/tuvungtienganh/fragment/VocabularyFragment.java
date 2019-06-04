package com.dong.luong.tuvungtienganh.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.activity.DetailWordActivity;
import com.dong.luong.tuvungtienganh.adapter.WordAdapter;
import com.dong.luong.tuvungtienganh.interfaces.ItemClickListener;
import com.dong.luong.tuvungtienganh.model.WordModel;
import com.dong.luong.tuvungtienganh.utils.Const;
import com.dong.luong.tuvungtienganh.utils.GridSpacingItemDecoration;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class VocabularyFragment extends Fragment {


    @BindView(R.id.rcv_vocabulary)
    RecyclerView rcvVocabulary;
    Unbinder unbinder;
    @BindView(R.id.adView)
    AdView adView;
    private ArrayList<WordModel> wordModels;
    private WordAdapter wordAdapter;

    public VocabularyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vocabulary, container, false);
        unbinder = ButterKnife.bind(this, view);
        initAdmob();
        initViews();
        initData();
        return view;
    }

    private void initAdmob() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
    }

    private void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcvVocabulary.setLayoutManager(gridLayoutManager);
        wordAdapter = new WordAdapter(getContext(), wordModels, new ItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Intent intent = new Intent(getContext(), DetailWordActivity.class);
                intent.putExtra(Const.KEY_POSITION, position);
                startActivity(intent);
            }
        });
        int spanCount = 2; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = true;
        rcvVocabulary.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        OverScrollDecoratorHelper.setUpOverScroll(rcvVocabulary, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        rcvVocabulary.setAdapter(wordAdapter);
    }

    private void initViews() {
        wordModels = new ArrayList<>();
        wordModels.add(new WordModel("Autumn", "Mùa Thu", R.drawable.la, 1));
        wordModels.add(new WordModel("Buildings", "Tòa Nhà", R.drawable.house, 2));
        wordModels.add(new WordModel("Office,Business,Workplace", "Văn phòng,kinh doanh,và nơi làm việc", R.drawable.folders, 3));
        wordModels.add(new WordModel("Carnivals and Fairs", "Lễ hội và hội chợ", R.drawable.carousel, 4));
        wordModels.add(new WordModel("Clothes", "Quần áo", R.drawable.jacket, 5));
        wordModels.add(new WordModel("Colors", "Màu sắc", R.drawable.color, 6));
        wordModels.add(new WordModel("Shapes", "Hình dạng", R.drawable.shape, 7));
        wordModels.add(new WordModel("House", "Nhà", R.drawable.myhouse, 8));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
