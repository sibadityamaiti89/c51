package com.example.c51.model

import com.squareup.moshi.Json

data class Offers (

	@field:Json(name = "offer_id") val offer_id : Int,
	@field:Json(name = "name") val name : String,
	@field:Json(name = "image_url") val image_url : String,
	@field:Json(name = "cash_back") val cash_back : Double
)