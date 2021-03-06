package com.subhasmith.assignment.infrrd.DataSource;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.subhasmith.assignment.infrrd.dataModels.TMDBWebService;

import java.util.concurrent.Executor;

/**
 * Created by Subhasmith
 */

public class ImGurImagesDataSourceFactory extends DataSource.Factory {
    private static final String TAG = "ImGurImagesDataSour";
    ImGurImagesDataSource imGurImagesDataSource;
    MutableLiveData<ImGurImagesDataSource> mutableLiveData;
    Executor executor;
    TMDBWebService webService;

    public ImGurImagesDataSourceFactory(Executor executor, TMDBWebService webService) {

        this.executor = executor;
        this.webService = webService;
        mutableLiveData = new MutableLiveData<>();
    }

    @Override
    public DataSource create() {
        Log.d(TAG, "create: ");
        imGurImagesDataSource = new ImGurImagesDataSource(executor, webService);
        mutableLiveData.postValue(imGurImagesDataSource);
        return imGurImagesDataSource;
    }

    public MutableLiveData<ImGurImagesDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
