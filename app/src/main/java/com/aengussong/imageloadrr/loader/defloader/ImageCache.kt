package com.aengussong.imageloadrr.loader.defloader

import android.graphics.Bitmap
import java.lang.ref.SoftReference

class ImageCache {

    private val cache = mutableMapOf<String, SoftReference<Bitmap>>()

    fun getImage(url: String) = cache[url]?.get()

    fun storeImage(url: String, bitmap: Bitmap) {
        cache[url] = SoftReference(bitmap)
    }
}