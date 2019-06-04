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
import com.dong.luong.tuvungtienganh.activity.ExamActivity;
import com.dong.luong.tuvungtienganh.adapter.GrammarAdapter;
import com.dong.luong.tuvungtienganh.interfaces.ItemClickListener;
import com.dong.luong.tuvungtienganh.interfaces.OnClickListener;
import com.dong.luong.tuvungtienganh.model.GrammarModel;
import com.dong.luong.tuvungtienganh.utils.Const;
import com.dong.luong.tuvungtienganh.utils.DatabaseManager;
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
public class TestFragment extends Fragment {


    @BindView(R.id.rcv_test)
    RecyclerView rcvTest;
    Unbinder unbinder;
    @BindView(R.id.adView)
    AdView adView;
    private ArrayList<GrammarModel> grammarModels;
    private GrammarAdapter grammarAdapter;
    private DatabaseManager databaseManager;

    public TestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
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
        rcvTest.setLayoutManager(gridLayoutManager);
        grammarAdapter = new GrammarAdapter(1, getContext(), grammarModels, new ItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                Intent intent = new Intent(getContext(), ExamActivity.class);
                intent.putExtra(Const.KEY_POSITION, position);
                startActivity(intent);
            }
        }, new OnClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), ExamActivity.class);
                intent.putExtra(Const.KEY_POSITION, position);
                startActivity(intent);
            }
        });
        int spanCount = 2; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = true;
        rcvTest.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        OverScrollDecoratorHelper.setUpOverScroll(rcvTest, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        rcvTest.setAdapter(grammarAdapter);
    }

    private void initViews() {
        grammarModels = new ArrayList<>();
        databaseManager = new DatabaseManager(getContext());
        grammarModels = databaseManager.getListGrammar();
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
