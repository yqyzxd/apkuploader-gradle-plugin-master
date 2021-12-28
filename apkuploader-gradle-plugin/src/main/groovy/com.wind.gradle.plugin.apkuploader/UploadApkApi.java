package com.wind.gradle.plugin.apkuploader;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * created by wind on 12/30/20:10:26 AM
 */
public interface UploadApkApi {
    @Multipart
    @POST("pack")
    Call<ResponseBody> uploadApk(
                                  @Part MultipartBody.Part file,
                                  @Part("id") RequestBody aid,
                                  @Part("content") RequestBody content,
                                  @Part("env") RequestBody env);
}
