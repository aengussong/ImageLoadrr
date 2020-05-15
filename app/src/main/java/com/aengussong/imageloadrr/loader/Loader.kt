package com.aengussong.imageloadrr.loader

import android.graphics.Bitmap

interface Loader {
    suspend fun loadImage(url:String): Bitmap
}