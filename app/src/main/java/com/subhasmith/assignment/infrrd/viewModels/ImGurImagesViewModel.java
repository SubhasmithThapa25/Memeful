package com.subhasmith.assignment.infrrd.viewModels;


import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.subhasmith.assignment.infrrd.APIUtils.NetworkState;
import com.subhasmith.assignment.infrrd.APIUtils.ServiceGenerator;
import com.subhasmith.assignment.infrrd.DataSource.ImGurImagesDataSourceFactory;
import com.subhasmith.assignment.infrrd.DataSource.ImGurImagesDataSource;
import com.subhasmith.assignment.infrrd.Entities.Datum;
import com.subhasmith.assignment.infrrd.dataModels.TMDBWebService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Subhasmith
 */

public class ImGurImagesViewModel extends ViewModel {
    private static final String TAG = "ImGurViewModel";
    private LiveData<PagedList<Datum>> pagedListLiveData;
    private LiveData<NetworkState> networkStateLiveData;
    private Executor executor;
    private LiveData<ImGurImagesDataSource> dataSource;


    public ImGurImagesViewModel() {
        Log.d(TAG, "MoviesInTheaterViewModel: ");
        executor = Executors.newFixedThreadPool(5);
        TMDBWebService webService = ServiceGenerator.createService(TMDBWebService.class);
        ImGurImagesDataSourceFactory factory = new ImGurImagesDataSourceFactory(executor, webService);
        dataSource = factory.getMutableLiveData();

        networkStateLiveData = Transformations.switchMap(factory.getMutableLiveData(), new Function<ImGurImagesDataSource, LiveData<NetworkState>>() {
            @Override
            public LiveData<NetworkState> apply(ImGurImagesDataSource source) {
                Log.d(TAG, "apply: network change");
                return source.getNetworkState();
            }
        });

        PagedList.Config pageConfig = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(100).build();

        pagedListLiveData = (new LivePagedListBuilder<Long, Datum>(factory, pageConfig))
                .setFetchExecutor(executor)
                .build();

    }

    public LiveData<PagedList<Datum>> getPagedListLiveData() {
        Log.d(TAG, "getMoviesInTheaterList: ");
        return pagedListLiveData;
    }

    public LiveData<NetworkState> getNetworkStateLiveData() {
        return networkStateLiveData;
    }
}

