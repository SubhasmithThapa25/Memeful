package com.subhasmith.assignment.infrrd.APIUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Subhasmith
 */

public class ServiceGenerator {
    public static final String URL_BASE_IMGUR= "https://api.imgur.com";
    public static final int API_DEFAULT_PAGE_KEY = 1;

    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL_BASE_IMGUR)
                                                            .addConverterFactory(GsonConverterFactory.create());
    static Retrofit retrofit = builder.build();

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                                                                .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Interceptor apiKeyInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            HttpUrl originalHttpUrl = originalRequest.url();

            HttpUrl url = originalHttpUrl.newBuilder().build();
            Request request = originalRequest.newBuilder().header("Authorization","Client-ID 7259273ce1c32bc").url(url).build();
            return chain.proceed(request);
        }
    };


    public static <S> S createService(Class<S> serviceClass){
        if(!httpClient.interceptors().contains(logging)){
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        if (!httpClient.interceptors().contains(apiKeyInterceptor)){
            httpClient.addInterceptor(apiKeyInterceptor);
            builder.client(httpClient.build());
            retrofit = builder.build();
        }
        return  retrofit.create(serviceClass);
    }

}
