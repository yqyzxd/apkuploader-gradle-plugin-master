package com.wind.gradle.plugin.apkuploader


class UploadConfig{
    static final String ENV_DEBUG="Debug"
    static final String ENV_UAT="UAT"
    static final String ENV_RELEASE="Release"

    boolean enabled=true
    int aid
    String content
    String env



}