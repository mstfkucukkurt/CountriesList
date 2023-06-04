package com.example.country_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.country_list.R
import com.example.country_list.databinding.ItemCountryBinding
import com.example.country_list.model.Country
import com.example.country_list.util.downloadFromUrl
import com.example.country_list.util.placeholderProgressBar
import com.example.country_list.view.FeedFragmentDirections

class CountryAdapter(val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(var binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.binding.name.text = countryList[position].countryName
        holder.binding.region.text = countryList[position].countryRegion

        holder.binding.root.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.imageView.downloadFromUrl(countryList[position].imageUrl,
            placeholderProgressBar(holder.itemView.context)
        )
    }

    fun updateCountryList(newCountryList: List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}