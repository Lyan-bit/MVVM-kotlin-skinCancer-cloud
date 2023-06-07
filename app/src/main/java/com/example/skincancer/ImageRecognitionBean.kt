package com.example.skincancer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.skincancer.model.SkinCancer
import com.example.skincancer.viewmodel.ImageRecognitionViewModel
import com.example.skincancer.viewmodel.SkinViewModel

class ImageRecognitionBean(c: Context) {
    private var model: SkinViewModel = SkinViewModel.getInstance(c)
    private var recognition: ImageRecognitionViewModel = ImageRecognitionViewModel.getInstance(c)

    private var skincancer = ""
    private var instanceSkinCancer: SkinCancer? = null

    private var images = ""
    private var dimages: Bitmap? = null

    private var errors = java.util.ArrayList<String>()

    fun setSkinCancer(skincancerx: String) {
        skincancer = skincancerx
    }

    fun resetData() {
        skincancer = ""
    }

    fun isImageRecognitionError(): Boolean {
        errors.clear()
        instanceSkinCancer = model.getSkinCancerByPK(skincancer)
        if (instanceSkinCancer == null) {
            errors.add("skinCancer must be a valid SkinCancer id")
        }

        if (instanceSkinCancer!!.images != "") {
            val x = instanceSkinCancer!!.images
            dimages= try {
                // convert base64 to bitmap android
                val decodedString: ByteArray = Base64.decode(x, Base64.DEFAULT)
                val decodedByte = BitmapFactory.decodeByteArray(
                    decodedString,
                    0,
                    decodedString.size
                )
                decodedByte
            }
            catch (e: Exception) {
                e.message
                null
            }
        } else {
            errors.add("This is not a type of image")
        }

        return errors.isNotEmpty()
    }

    fun errors(): String {
        return errors.toString()
    }

    fun imageRecognition (): String {
        val result = recognition.imageRecognition(dimages!!)
        instanceSkinCancer?.outcome = result
        model.persistSkinCancer(instanceSkinCancer!!)
        return result
    }
}
