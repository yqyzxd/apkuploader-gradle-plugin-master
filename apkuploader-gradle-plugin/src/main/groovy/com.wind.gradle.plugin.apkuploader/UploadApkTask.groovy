package com.wind.gradle.plugin.apkupload

import com.google.gson.Gson
import com.wind.gradle.plugin.apkuploader.Retrofits
import com.wind.gradle.plugin.apkuploader.UploadApkApi
import com.wind.gradle.plugin.apkuploader.UploadConfig
import okhttp3.ResponseBody
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import retrofit2.Response

class UploadApkTask extends DefaultTask{
    public static final String TAG="UploadApkTask"
    UploadConfig mUploadConfig
    File apkFile
    void init(File apkFile,UploadConfig uploadConfig){
        mUploadConfig=uploadConfig
        this.apkFile=apkFile

        description="Upload release apk to backend"
        group="upload"
    }


    @TaskAction
    void upload(){
        try {
            println(TAG+"start uploading")
            String mediaType="multipart/form-data"
            String apkMediaTye="application/vnd.android.package-archive"
            Response<ResponseBody> response = Retrofits.create(UploadApkApi.class)
                    .uploadApk(
                            Retrofits.getFilePart(mediaType, apkFile),
                            Retrofits.getBody(mediaType,mUploadConfig.getAid().toString()),
                            Retrofits.getBody(mediaType,mUploadConfig.getContent()),
                            Retrofits.getBody(mediaType,mUploadConfig.getEnv()),
                    ).execute()
            println(TAG+"start upload end")
            println(TAG+response.toString())
        }catch(Exception e){
            println(TAG+"uploading error "+e.getMessage())
            e.printStackTrace()
        }

    }
}