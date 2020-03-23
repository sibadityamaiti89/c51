package com.example.c51.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.c51.model.OfferResponse
import com.example.c51.repository.ApiResponse
import com.example.c51.repository.IAdService
import com.example.c51.repository.RetrofitClient

class MainActivityViewModel: ViewModel() {
    fun getOfferResponse(): LiveData<ApiResponse<OfferResponse>> {
        return RetrofitClient().getRetrofit().create(IAdService::class.java).getOfferListing()
    }
}