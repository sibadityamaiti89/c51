package com.example.c51.util

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.c51.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Utils {

    companion object {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        @JvmStatic
        @BindingAdapter("imageResource")
        fun setImageResource(imageView: ImageView, imageUrl: String) {
            var context = imageView.context
            Glide.with(context)
                .load(imageUrl)
                .placeholder(context.getDrawable(R.drawable.logo))
                .into(imageView)
        }

        fun isInternetAvailable(context: Context): Boolean {
            val cm =
                (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }

    fun isTextNullOrEmpty(inputText : CharSequence?) : Boolean {
        return inputText.isNullOrEmpty()
    }

    fun getCashbackPrice(cashback: Double): String {
        return "Cashback: $$cashback"
    }

}