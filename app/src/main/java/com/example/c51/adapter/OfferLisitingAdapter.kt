package com.example.c51.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.c51.R
import com.example.c51.databinding.OfferListingLayoutBinding
import com.example.c51.model.Offers
import com.example.c51.util.Utils

class OfferListingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    companion object {
        //Adding 2 view type so that if we want to add pagination we can show a loader at the end of list
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
    }

    private val offerList = ArrayList<Offers?>()
    private lateinit var adItemClickListener: AdItemClickListener
    private lateinit var viewHolder : RecyclerView.ViewHolder

    interface AdItemClickListener {
        fun onOfferItemClicked(ads: Offers?)
    }

    fun setAdClickListener(listener: AdItemClickListener) {
        adItemClickListener = listener
    }

    fun setLoadingLayout() {
        if (!offerList.isNullOrEmpty() && offerList[offerList.size - 1] != null) {
            offerList.add(null)
            notifyItemInserted(offerList.size - 1)
        }
    }

    fun removeLoadingView() {
        if (!offerList.isNullOrEmpty() && offerList[offerList.size - 1] == null) {
            offerList.removeAt(offerList.size-1)
            notifyDataSetChanged()
        }
    }

    fun setOfferList(list: List<Offers>) {
        if(!offerList.isNullOrEmpty()) {
            offerList.removeAt(offerList.size-1)
        }
        offerList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM) {
            val layoutInflater = LayoutInflater.from(parent.context)
            val adItemLayoutBinding: OfferListingLayoutBinding = OfferListingLayoutBinding.inflate(layoutInflater, parent, false)
            viewHolder = AdListingViewHolder(adItemLayoutBinding)
        } else if (viewType == VIEW_TYPE_LOADING) {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.offer_list_item_loading, parent, false)
            viewHolder = LoadingViewHolder(view)
        }
        return viewHolder
    }

    override fun getItemCount(): Int{
        return offerList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (offerList[position] == null) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is LoadingViewHolder -> holder.progressBar
            is AdListingViewHolder -> holder.bind(offerList[position], adItemClickListener)
        }
    }

    private class LoadingViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val progressBar: LottieAnimationView = itemView.findViewById(R.id.offer_list_loading)
    }

    class AdListingViewHolder(val binding: OfferListingLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Offers?, adItemClickListener: AdItemClickListener) {
            binding.offers = item
            binding.util = Utils()
            itemView.setOnClickListener {
                adItemClickListener.onOfferItemClicked(item)
            }
            binding.executePendingBindings()
        }
    }


}