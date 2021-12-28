package com.wind.gradle.plugin.apkuploader;

import org.codehaus.groovy.runtime.ScriptTestAdapter;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by wind on 12/30/20:10:25 AM
 */
public class Retrofits {

    public static <T> T create(Class<T> serviceClass) {

        String baseUrl = "https://app.marryu520.cn/";
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient().newBuilder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttpBuilder.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpBuilder.build())
                .build();

        return retrofit.create(serviceClass);
    }

    public static RequestBody getTextBody(String text) {
        return RequestBody.create(MediaType.parse("text/plain"), text);
    }

    public static RequestBody getBody(String mediaType, String text) {
        return RequestBody.create(MediaType.parse(mediaType), text);
    }

    public static MultipartBody.Part getFilePart(String mediaType, File file) {
        return MultipartBody.Part.createFormData("file", file.getAbsoluteFile().getName(), RequestBody.create(MediaType.parse(mediaType), file));
    }
}
