package com.subhasmith.assignment.infrrd.Views.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.subhasmith.assignment.infrrd.APIUtils.NetworkState;
import com.subhasmith.assignment.infrrd.Entities.Datum;
import com.subhasmith.assignment.infrrd.R;
import com.subhasmith.assignment.infrrd.Views.Adapters.ImGurImagesAdapter;
import com.subhasmith.assignment.infrrd.viewModels.ImGurImagesViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ImGurImagesViewModel mMoviesViewModel;
    private RecyclerView mRecyclerView;
    private ImGurImagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Memeful");
        setSupportActionBar(toolbar);


        adapter = new ImGurImagesAdapter(this);

        mMoviesViewModel = ViewModelProviders.of(this).get(ImGurImagesViewModel.class);
        mMoviesViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(@Nullable PagedList<Datum> movies) {
                Log.d(TAG, "onChanged: " + movies.size());
                adapter.submitList(movies);
            }
        });
        mMoviesViewModel.getNetworkStateLiveData().observe(this, new Observer<NetworkState>() {
            @Override
            public void onChanged(@Nullable NetworkState networkState) {
                Log.d(TAG, "onChanged: network state changed");
                adapter.setNetworkState(networkState);
            }
        });
        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager); // set LayoutManager to RecyclerView
        mRecyclerView.setAdapter(adapter);
    }
}
