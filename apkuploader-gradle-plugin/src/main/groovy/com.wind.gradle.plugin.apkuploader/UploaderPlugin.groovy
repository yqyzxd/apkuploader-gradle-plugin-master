package com.wind.gradle.plugin.apkuploader

import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.wind.gradle.plugin.apkupload.UploadApkTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class UploaderPlugin implements Plugin<Project>{
    @Override
    void apply(Project project) {
        UploadConfig uploadConfig= project.extensions.create("apkuploader",UploadConfig)
        project.afterEvaluate{
            AppExtension android=project.extensions.android
            android.applicationVariants.all{
                ApplicationVariant variant->
                    //println("UploaderPlugin"+variant.getBuildType().getName())
                    if (variant.getBuildType().getName().equalsIgnoreCase("release") && uploadConfig.enabled) {
                        variant.outputs.all {
                            BaseVariantOutput output ->
                                File apkFile = output.outputFile
                                UploadApkTask uploadApkTask = project.tasks.create("uploadApk${variant.baseName.capitalize()}", UploadApkTask)
                                uploadApkTask.init(apkFile, uploadConfig)

                                variant.getAssembleProvider().get().dependsOn(project.getTasks().findByName("clean"))
                                uploadApkTask.dependsOn(variant.getAssembleProvider().get())

                        }
                    }
            }
        }
    }

}