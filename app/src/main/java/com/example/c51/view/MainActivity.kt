package com.example.c51.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c51.R
import com.example.c51.adapter.OfferListingAdapter
import com.example.c51.model.Offers
import com.example.c51.repository.ApiEmptyResponse
import com.example.c51.repository.ApiErrorResponse
import com.example.c51.repository.ApiSuccessResponse
import com.example.c51.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), OfferListingAdapter.AdItemClickListener {

    private lateinit var offerRecyclerView: RecyclerView
    private lateinit var offerErrorLayout: LinearLayout
    private lateinit var offerLoadingLayout: LinearLayout
    private lateinit var offerListAdapter: OfferListingAdapter
    private lateinit var offerLinearLayoutManager: LinearLayoutManager
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        getOfferList()
    }

    private fun initView() {
        offerListAdapter = OfferListingAdapter()
        offerRecyclerView = findViewById(R.id.offer_list)
        offerErrorLayout = findViewById(R.id.error_layout)
        offerLoadingLayout = findViewById(R.id.loading_layout)
        offerLoadingLayout.visibility = View.VISIBLE
        offerLinearLayoutManager = LinearLayoutManager(this)
        offerLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        offerRecyclerView.layoutManager = offerLinearLayoutManager
        offerRecyclerView.adapter = offerListAdapter
        offerListAdapter.setAdClickListener(this)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private fun getOfferList() {
        mainActivityViewModel.getNextOfferResponse().observe(this, Observer {
            when (it) {
                is ApiSuccessResponse -> {
                    offerListAdapter.setOfferList(it.body.offers)
                    offerRecyclerView.visibility = View.VISIBLE
                    offerErrorLayout.visibility = View.GONE
                    offerLoadingLayout.visibility = View.GONE
                }

                is ApiEmptyResponse -> {
                    offerRecyclerView.visibility = View.GONE
                    offerErrorLayout.visibility = View.VISIBLE
                    offerLoadingLayout.visibility = View.GONE
                }

                is ApiErrorResponse -> {
                    offerRecyclerView.visibility = View.GONE
                    offerErrorLayout.visibility = View.VISIBLE
                    offerLoadingLayout.visibility = View.GONE
                }
            }
        })
    }

    override fun onOfferItemClicked(offer: Offers?) {
        Toast.makeText(this, " You selected "+offer?.name, Toast.LENGTH_SHORT).show()
    }
}
