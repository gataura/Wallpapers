package com.hfad.wallpapers

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import android.support.v7.widget.Toolbar
import java.io.File
import java.io.FileOutputStream

class PhotoViewActivity : AppCompatActivity() {

    var res = 0
    lateinit var img: ImageView
    lateinit var toolbar: Toolbar


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        res = intent.getIntExtra("pic",0)
        img = findViewById(R.id.main_image)
        img.setImageResource(res)
        toolbar = findViewById(R.id.photo_view_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val maxX = (img.width/2 - Resources.getSystem().displayMetrics.widthPixels/2)
        val maxY = (img.height/2 - Resources.getSystem().displayMetrics.heightPixels/2)
        val maxLeft = maxX * -1
        val maxRight = maxX
        val maxTop = maxY* -1
        val maxBottom = maxY

        img.setOnTouchListener(object: View.OnTouchListener{

            var downX:Float = 0.0f
            var downY:Float = 0.0f
            var totalX = 0
            var totalY = 0
            var scrollByX = 0
            var scrollByY = 0

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                val currentX: Float
                val currentY:Float

                when (event.action) {

                    MotionEvent.ACTION_DOWN -> {
                        downX = event.x
                        downY = event.y
                    }

                    MotionEvent.ACTION_MOVE -> {
                        currentX = event.x
                        currentY = event.y
                        scrollByX = (downX - currentX).toInt()
                        scrollByY = (downY - currentY).toInt()

                        if (currentX > downX) {
                            if (totalX == maxLeft) {
                                scrollByX = 0
                            }
                            if (totalX > maxLeft) {
                                totalX += scrollByX
                            }
                            if (totalX < maxLeft) {
                                scrollByX = maxLeft - (totalX - scrollByX)
                                totalX = maxLeft

                            }
                        }

                        if (currentX < downX) {
                            if (totalX == maxRight)
                            {
                                scrollByX = 0
                            }
                            if (totalX < maxRight)
                            {
                                totalX += scrollByX
                            }
                            if (totalX > maxRight)
                            {
                                scrollByX = maxRight - (totalX - scrollByX)
                                totalX = maxRight
                            }
                        }

                        if (currentY > downY)
                        {
                            if (totalY == maxTop)
                            {
                                scrollByY = 0
                            }
                            if (totalY > maxTop)
                            {
                                totalY += scrollByY
                            }
                            if (totalY < maxTop)
                            {
                                scrollByY = maxTop - (totalY - scrollByY)
                                totalY = maxTop
                            }
                        }

                        if (currentY < downY)
                        {
                            if (totalY == maxBottom)
                            {
                                scrollByY = 0
                            }
                            if (totalY < maxBottom)
                            {
                                totalY += scrollByY
                            }
                            if (totalY > maxBottom)
                            {
                                scrollByY = maxBottom - (totalY - scrollByY)
                                totalY = maxBottom
                            }
                        }

                        img.scrollBy(scrollByX, scrollByY)
                        downX = currentX
                        downY = currentY

                    }
                }
                return true

            }

        })


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun saveToDevice(img: ImageView) {
        val draw: BitmapDrawable = img.drawable as BitmapDrawable
        val bitmap =  draw.bitmap
        var outStream: FileOutputStream? = null
        val sdCard:File = Environment.getExternalStorageDirectory()
        val dir = File(sdCard.absolutePath + "/Wallpapers")
        dir.mkdirs()
        val fileName = String.format("%d.jpg", System.currentTimeMillis())
        val outFile = File(dir, fileName)
        outStream = FileOutputStream(outFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
        outStream.flush()
        outStream.close()
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
}
