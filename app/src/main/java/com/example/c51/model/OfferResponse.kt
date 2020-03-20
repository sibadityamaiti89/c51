package com.example.c51.model

import com.squareup.moshi.Json

data class OfferResponse (

	@field:Json(name = "batch_id") val batch_id : Int,
	@field:Json(name = "offers") val offers : List<Offers>
)