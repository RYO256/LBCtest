package com.example.lbctest.ui

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

fun getGlideUrl(url : String) =  GlideUrl(url, LazyHeaders.Builder()
        .addHeader("User-Agent", "your-user-agent")
        .build())