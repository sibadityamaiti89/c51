package com.example.c51.viewmodel

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainActivityViewModelTest {
    private lateinit var offerListingViewModel: MainActivityViewModel
    private var mockWebServer = MockWebServer()
    private lateinit var baseUrl: String

    @Before
    fun setup(){
        offerListingViewModel = MainActivityViewModel()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/").toString()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Throws(Exception::class)
    fun readTestAsset(): String? {
        return ClassLoader.getSystemResource("success.json").readText()
    }

    @Test
    fun test_file_exists() {
        val file: String? = readTestAsset()
        Assert.assertThat(file, CoreMatchers.notNullValue())
    }

    @Test
    fun test_offer_list_screen_response_not_null() {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(readTestAsset() as String)
        mockWebServer.enqueue(mockResponse)
        val response = offerListingViewModel.getOfferResponse()
        check(response != null)
    }
}