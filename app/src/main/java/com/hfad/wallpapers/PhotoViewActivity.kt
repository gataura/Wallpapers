package com.hfad.wallpapers

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import java.io.File
import java.io.FileOutputStream

class PhotoViewActivity : AppCompatActivity() {

    var res = 0
    lateinit var img: ImageView
    lateinit var toolbar: Toolbar
    lateinit var openButton: Button
    lateinit var fileName:String
    lateinit var sdCard:File
    lateinit var outFile:File
    var intent1 = Intent()


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)
        toolbar = findViewById(R.id.photo_view_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        openButton = findViewById(R.id.open_button)
        res = intent.getIntExtra("pic",0)
        Log.d("getId","$res")
        img = findViewById(R.id.main_image)
        img.setImageResource(res)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun saveToDevice(img: ImageView) {
        val draw: BitmapDrawable = img.drawable as BitmapDrawable
        val bitmap =  draw.bitmap
        var outStream: FileOutputStream? = null
        sdCard = Environment.getExternalStorageDirectory()
        val dir = File(sdCard.absolutePath + "/Wallpapers")
        dir.mkdirs()
        fileName = String.format("%d.jpg", System.currentTimeMillis())
        outFile = File(dir, fileName)
        outStream = FileOutputStream(outFile)
        addImageToGalery(outFile.absolutePath, this)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
        outStream.flush()
        outStream.close()
        openButton.visibility = View.VISIBLE
        intent.action = Intent.ACTION_MEDIA_SCANNER_SCAN_FILE
        intent.data = Uri.fromFile(outFile.absoluteFile)
        sendBroadcast(intent)
    }

    fun addImageToGalery(filePath: String, context: Context) {

        var values = ContentValues()

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        values.put(MediaStore.MediaColumns.DATA, filePath)

        context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

    }

    fun openImage() {
        intent1.action = Intent.ACTION_VIEW
        intent1.setDataAndType(Uri.parse(outFile.absolutePath), "image/jpg")
        startActivity(intent1)
    }


    fun getPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        } else {
            saveToDevice(img)
        }
    }

    fun onSaveButtonClick(v: View) {
        getPermissions()
        Toast.makeText(this, "Фото успешно сохранено!", Toast.LENGTH_SHORT).show()
    }

    fun onOpenButtonClick(v: View) {
        openImage()
    }
}
