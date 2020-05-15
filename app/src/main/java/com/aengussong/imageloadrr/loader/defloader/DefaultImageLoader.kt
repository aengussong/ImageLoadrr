package com.aengussong.imageloadrr.loader.defloader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.aengussong.imageloadrr.loader.Loader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class DefaultImageLoader : Loader {

    private val cache = ImageCache()

    override suspend fun loadImage(url: String) = getFromCache(url) ?: loadFromNetwork(url)

    private fun getFromCache(url: String) = cache.getImage(url)

    private suspend fun loadFromNetwork(url: String) = withContext<Bitmap>(Dispatchers.IO) {
        val bitmap = URL(url).openStream().use { BitmapFactory.decodeStream(it) }
        cache.storeImage(url, bitmap)
        bitmap
    }

}