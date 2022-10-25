package com.noreplypratap.random.utils

import androidx.lifecycle.LiveData

object Constants {

    val Base_URL = "https://random-data-api.com/"
    var imageURL = "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png"
    var netStatus : Boolean = false
    lateinit var livenetStatus : LiveData<Boolean>
}