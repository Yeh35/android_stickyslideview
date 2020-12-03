package com.yeh35.android.stickyslideview.utils

import android.content.res.AssetManager
import android.content.res.Configuration
import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.content.res.Resources.Theme
import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.ColorRes
import kotlin.jvm.Throws

/**
 *  Android 버전별로 리소스 가져오는 방법이 달라서 그거에 맞춘 Wrapper Class
 */
class UtilResources(
    private val resources: Resources
) {

    @Throws(NotFoundException::class)
    fun getColor(@ColorRes id: Int, theme: Theme? = null): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resources.getColor(id, theme)
        } else {
            @Suppress("DEPRECATION")
            resources.getColor(id)
        }
    }



}