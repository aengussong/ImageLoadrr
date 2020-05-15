package com.aengussong.imageloadrr

import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ImageLoadrrTest {

    @Test
    fun loadImage_shouldLoadImage() = runBlocking {
        val url = "http://httpbin.org/image/png"
        val imageView = ImageView(InstrumentationRegistry.getInstrumentation().context)

        ImageLoadrr()
            .loadImageInto(url, imageView)
            .join()

        val result = imageView.drawable?.toBitmap()
        Assert.assertNotNull(result)
    }

    @Test
    fun loadError_shouldDoNothing(){
        Assert.fail()
    }

    @Test
    fun loadByNotImageUrl_shouldDoNothing() {
        Assert.fail()
    }

    @Test
    fun loadIntoGarbageCollectedImageView_shouldDoNoting() {
        Assert.fail()
    }

}