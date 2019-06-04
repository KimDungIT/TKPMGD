package com.dong.luong.tuvungtienganh.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dong.luong.tuvungtienganh.R;
import com.dong.luong.tuvungtienganh.activity.DetailSearchActivity;
import com.dong.luong.tuvungtienganh.adapter.SearchAdapter;
import com.dong.luong.tuvungtienganh.interfaces.ItemClickListener;
import com.dong.luong.tuvungtienganh.model.SearchModel;
import com.dong.luong.tuvungtienganh.utils.Const;
import com.dong.luong.tuvungtienganh.utils.DatabaseManager1;
import com.dong.luong.tuvungtienganh.utils.DividerItemDecoration;
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
public class FindFragment extends Fragment {


    @BindView(R.id.edtKeyWord)
    EditText edtKeyWord;
    @BindView(R.id.rcvSearchProduct)
    RecyclerView rcvSearchProduct;
    Unbinder unbinder;
    @BindView(R.id.adView)
    AdView adView;
    private SearchAdapter searchAdapter;
    private ArrayList<SearchModel> searchModels;
    private DatabaseManager1 databaseManager1;

    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_find, container, false);
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
    }

    private void initViews() {
        databaseManager1 = new DatabaseManager1(getContext());
        searchModels = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvSearchProduct.setLayoutManager(linearLayoutManager);
        rcvSearchProduct.addItemDecoration(
                new DividerItemDecoration(getContext(), R.drawable.divider));
        OverScrollDecoratorHelper.setUpOverScroll(rcvSearchProduct, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        edtKeyWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchModels = databaseManager1.getListSearch(s.toString());
                if (searchModels.size() > 0) {
                    searchAdapter = new SearchAdapter(getContext(), new ItemClickListener() {
                        @Override
                        public void onItemClick(int position, View view) {
                            Intent intent = new Intent(getContext(), DetailSearchActivity.class);
                            intent.putExtra(Const.KEY_CONTENT, searchModels.get(position).getMean());
                            intent.putExtra(Const.KEY_TITLE, searchModels.get(position).getWord());
                            startActivity(intent);
                        }
                    }, searchModels);
                    searchAdapter.notifyDataSetChanged();
                    rcvSearchProduct.setAdapter(searchAdapter);

                }

            }
        });

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
