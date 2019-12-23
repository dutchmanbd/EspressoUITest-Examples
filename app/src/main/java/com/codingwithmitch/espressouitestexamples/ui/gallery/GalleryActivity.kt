package com.codingwithmitch.espressouitestexamples.ui.gallery

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.bumptech.glide.Glide
import com.codingwithmitch.espressouitestexamples.R
import kotlinx.android.synthetic.main.activity_gallery.*

const val GALLERY_REQUEST_CODE = 1234

class GalleryActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "GalleryActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        btnPickupImage.setOnClickListener {
            pickupFromGallery()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            Log.d(TAG, "RESULT_OK")
            when(requestCode){
                GALLERY_REQUEST_CODE -> {
                    Log.d(TAG, "GALLERY_REQUEST_CODE detected")
                    data?.data?.let {uri->
                        Log.d(TAG, "URI: $uri")
                        Glide.with(this)
                            .load(uri)
                            .into(ivPreviewImage)
                    }
                }
            }
        }
    }

    private fun pickupFromGallery(){

        val pickupIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickupIntent, GALLERY_REQUEST_CODE)
    }
}
