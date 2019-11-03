package com.sabinhantu.baseapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.model.Donation

class DonationAdapter (
    val donationsList: ArrayList<Donation>) :
    RecyclerView.Adapter<DonationAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.tvDescription?.text = donationsList[p1].description
        p0.tvDate?.text = donationsList[p1].date
        p0.tvAddress?.text = donationsList[p1].address
        p0.tvQuantity?.text = "${donationsList[p1].alreadyTaken}/${donationsList[p1].quantity}"
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(
            R.layout.item_donations_asker,
            p0,
            false
        )

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return donationsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
        val tvAddress = itemView.findViewById<TextView>(R.id.tv_address)
        val tvQuantity = itemView.findViewById<TextView>(R.id.tv_quantity)
    }
}