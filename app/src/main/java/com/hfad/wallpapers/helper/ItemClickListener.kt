package com.hfad.wallpapers.helper

import android.view.View

interface ItemClickListener {
    fun onClick(v: View, position: Int, isLongClick: Boolean)
}