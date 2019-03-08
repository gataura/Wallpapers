package com.hfad.wallpapers

import android.annotation.SuppressLint
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.hfad.wallpapers.R

class PhotoViewActivity : AppCompatActivity() {

    var res = 0
    lateinit var img: ImageView


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        res = intent.getIntExtra("pic",0)
        img = findViewById(R.id.main_image)
        img.setImageResource(res)

        var maxX = (img.width/2 - Resources.getSystem().displayMetrics.widthPixels/2)
        var maxY = (img.height/2 - Resources.getSystem().displayMetrics.heightPixels/2)
        var maxLeft = maxX * -1
        var maxRight = maxX
        var maxTop = maxY* -1
        var maxBottom = maxY

        img.setOnTouchListener(object: View.OnTouchListener{

            var downX:Float = 0.0f
            var downY:Float = 0.0f
            var totalX = 0
            var totalY = 0
            var scrollByX = 0
            var scrollByY = 0

            override fun onTouch(v: View, event: MotionEvent): Boolean {
                var currentX: Float
                var currentY:Float

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
}
