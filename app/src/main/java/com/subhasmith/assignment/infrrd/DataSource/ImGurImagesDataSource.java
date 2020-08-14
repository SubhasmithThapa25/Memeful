package com.subhasmith.assignment.infrrd.DataSource;


import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.subhasmith.assignment.infrrd.APIUtils.JSONParser;
import com.subhasmith.assignment.infrrd.APIUtils.NetworkState;
import com.subhasmith.assignment.infrrd.APIUtils.ServiceGenerator;
import com.subhasmith.assignment.infrrd.Entities.Datum;
import com.subhasmith.assignment.infrrd.dataModels.TMDBWebService;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Subhasmith
 */

public class ImGurImagesDataSource extends PageKeyedDataSource<Long, Datum> {
    private static final String TAG = "ImGurImagesDataSource";
    private TMDBWebService tmdbWebService;
    private MutableLiveData<NetworkState> networkState;
    private MutableLiveData<NetworkState> initialLoading;
    private Executor retryExecutor;

    public ImGurImagesDataSource(Executor retryExecutor, TMDBWebService webService) {
        tmdbWebService = webService;
        networkState = new MutableLiveData<>();
        initialLoading = new MutableLiveData<>();
        this.retryExecutor = retryExecutor;
    }

    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public MutableLiveData getInitialLoading() {

        return initialLoading;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Datum> callback) {
        Log.d(TAG, "loadInitial: ");
        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);
        tmdbWebService.getImgurData(ServiceGenerator.API_DEFAULT_PAGE_KEY, true, true, true).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseString;
                List<Datum> datumList;
                if (response.isSuccessful() && response.code() == 200) {
                    try {

                        initialLoading.postValue(NetworkState.LOADING);
                        networkState.postValue(NetworkState.LOADED);
                        responseString = response.body().string();
                        datumList = JSONParser.getDataList(responseString);
                        callback.onResult(datumList, null, (long) 2);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "onResponse error " + response.message());
                    initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String errorMessage = t.getMessage();
                Log.e(TAG, "onFailure: " + errorMessage);
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Datum> callback) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {
        networkState.postValue(NetworkState.LOADING);
        tmdbWebService.getImgurData(Math.toIntExact(params.key), true, true, true).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseString;
                List<Datum> datumList;
                Long nextKey;

                if (response.isSuccessful() && response.code() == 200) {
                    try {
                        initialLoading.postValue(NetworkState.LOADING);
                        networkState.postValue(NetworkState.LOADED);

                        responseString = response.body().string();
                        datumList = JSONParser.getDataList(responseString);

                        nextKey = (params.key == datumList.size()) ? null : params.key + 1;

                        callback.onResult(datumList, nextKey);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "onResponse error " + response.message());
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                String errorMessage = t.getMessage();
                Log.e(TAG, "onFailure: " + errorMessage);
                networkState.postValue(new NetworkState(NetworkState.Status.FAILED, errorMessage));
            }
        });
    }


}

