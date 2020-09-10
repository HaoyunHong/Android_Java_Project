package com.java.hexinyu;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.java.hexinyu.background.database.Info;
import com.java.hexinyu.background.database.InfoViewModel;
import com.java.hexinyu.ui.InfoAdapter;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private InfoViewModel mInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final InfoAdapter adapter = new InfoAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.

        mInfoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);

//        mInfoViewModel.getAllNews().observe(this, new Observer<List<Info>>() {
//            @Override
//            public void onChanged(@Nullable final List<Info> infos) {
//                // Update the cached copy of the words in the adapter.
//                adapter.setWords(infos);
//            }
//        });


//        mInfoViewModel.getAllPaper().observe(this, new Observer<List<Info>>() {
//            @Override
//            public void onChanged(@Nullable final List<Info> infos) {
//                // Update the cached copy of the words in the adapter.
//                adapter.setWords(infos);
//            }
//        });

        /**
         * 上拉获取之前新闻
         */
        mInfoViewModel.getPreviousNews().observe(this, new Observer<List<Info>>() {
            @Override
            public void onChanged(@Nullable final List<Info> infos) {
                // Update the cached copy of the words in the adapter.
                adapter.setInfo(infos);
            }
        });
        /**
         * 下拉获取最新新闻
         */
        mInfoViewModel.getLatestNews().observe(this, new Observer<List<Info>>() {
            @Override
            public void onChanged(@Nullable final List<Info> infos) {
                // Update the cached copy of the words in the adapter.
                adapter.setInfo(infos);
            }
        });

    }

}
