package com.aengussong.imageloadrr

import android.widget.ImageView
import com.aengussong.imageloadrr.loader.defloader.DefaultImageLoader
import com.aengussong.imageloadrr.loader.Loader
import kotlinx.coroutines.*
import java.lang.ref.WeakReference
import kotlin.coroutines.CoroutineContext

class ImageLoadrr private constructor(private val url: String) {

    private val sJob = SupervisorJob()
    private val errorHandler = CoroutineExceptionHandler(::handleError)
    private var scope = CoroutineScope(Dispatchers.Main + sJob + errorHandler)

    companion object{
        fun test():ImageLoadrr.Companion{
            return this
        }
    }

    private var loader: Loader =
        DefaultImageLoader()

    data class Builder(
        var scope: CoroutineScope? = null,
        var loader:Loader? = null
    ) {
        fun withScope(scope: CoroutineScope): Builder {
            this.scope = scope
            return this
        }

        fun withLoader(loader:Loader):Builder{
            this.loader = loader
            return this
        }

        fun loadImage(url: String):ImageLoadrr {
            val imgLoadrr = ImageLoadrr(url)
            scope?.let { imgLoadrr.scope = it }
            loader?.let { imgLoadrr.loader = it }
            return imgLoadrr
        }
    }

    fun into(imageView: ImageView) {
        val weakImage = WeakReference(imageView)
        scope.launch {
            val bitmap = loader.loadImage(url)
            weakImage.get()?.setImageBitmap(bitmap)
        }
    }

    fun cancelCurrentLoading() = scope.coroutineContext.cancelChildren()

    private fun handleError(coroutineContext: CoroutineContext, throwable: Throwable) {
        print("Exception while trying to load image($url): ${throwable.message}")
    }


}