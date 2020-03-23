package com.example.c51.util

import org.junit.Assert
import org.junit.Before
import org.junit.Test

internal class UtilsTest {

    private lateinit var utils: Utils

    @Before
    fun setUp() {
        utils = Utils()
    }

    @Test
    fun test_empty_or_null_string() {
        val testString = ""
        check(utils.isTextNullOrEmpty(testString))
    }

    @Test
    fun test_non_empty_string() {
        val testString = "this is test string"
        check(!utils.isTextNullOrEmpty(testString))
    }

    @Test
    fun test_cashback_price() {
        val cashbackAmount = 2.0
        val expectedCashbackString = "Cashback: $2.0"
        Assert.assertEquals(expectedCashbackString, utils.getCashbackPrice(cashbackAmount))
    }
}