package com.example.c51.repository

import androidx.lifecycle.LiveData
import com.example.c51.model.OfferResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface IAdService {

    @GET("getOffer")
    fun getOfferListing(): LiveData<ApiResponse<OfferResponse>>
}