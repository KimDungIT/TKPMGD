package com.dong.luong.tuvungtienganh;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dong.luong.tuvungtienganh.fragment.FindFragment;
import com.dong.luong.tuvungtienganh.fragment.GrammarFragment;
import com.dong.luong.tuvungtienganh.fragment.TestFragment;
import com.dong.luong.tuvungtienganh.fragment.VocabularyFragment;
import com.dong.luong.tuvungtienganh.utils.FragmentController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.fr_container)
    FrameLayout frContainer;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    private VocabularyFragment vocabularyFragment;
    private GrammarFragment grammarFragment;
    private TestFragment testFragment;
    private FindFragment findFragment;
    public static MainActivity mainActivity;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainActivity = MainActivity.this;
        initViews();
        initData();
    }

    private void initData() {
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void bottomBarSelect(final int position) {
        bottomBar.selectTabAtPosition(position);
    }

    private void initViews() {
//        if (vocabularyFragment == null) {
//            vocabularyFragment = new VocabularyFragment();
//        }
//        FragmentController.replaceFragment(MainActivity.this, vocabularyFragment, R.id.fr_container);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                if (tabId == R.id.tab_vocabulary) {
                    if (vocabularyFragment == null) {
                        vocabularyFragment = new VocabularyFragment();
                    }
                    FragmentController.replaceFragment(MainActivity.this, vocabularyFragment, R.id.fr_container);
                } else if (tabId == R.id.tab_grammar) {
                    if (grammarFragment == null) {
                        grammarFragment = new GrammarFragment();
                    }
                    FragmentController.replaceFragment(MainActivity.this, grammarFragment, R.id.fr_container);

                } else if (tabId == R.id.tab_test) {
                    if (testFragment == null) {
                        testFragment = new TestFragment();
                    }
                    FragmentController.replaceFragment(MainActivity.this, testFragment, R.id.fr_container);

                }

                if (tabId == R.id.tab_search) {
                    if (findFragment == null) {
                        findFragment = new FindFragment();
                    }
                    FragmentController.replaceFragment(MainActivity.this, findFragment, R.id.fr_container);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (count == 0) {
            Toast.makeText(mainActivity, "Chạm lần lữa để thoát", Toast.LENGTH_SHORT).show();
            count = count + 1;
        } else {
            super.onBackPressed();
        }
    }
}
