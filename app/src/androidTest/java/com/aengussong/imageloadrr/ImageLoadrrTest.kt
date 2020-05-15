package com.aengussong.imageloadrr

import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ImageLoadrrTest {

    @Test
    fun loadImage_shouldLoadImage()  = runBlocking {
        val url = "http://httpbin.org/image/png"
        val imageView = ImageView(InstrumentationRegistry.getInstrumentation().context)

        ImageLoadrr.Builder().loadImage(url).into(imageView)

        val result = imageView.drawable?.toBitmap()
        Assert.assertNotNull(result)
    }

    @Test
    fun loadError_shouldLogError(){
        Assert.fail()
    }

    @Test
    fun loadByNotImageUrl_shouldLogError(){
        Assert.fail()
    }

    @Test
    fun loadIntoGarbageCollectedImageView_shouldDoNoting(){
        Assert.fail()
    }
}