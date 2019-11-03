package com.sabinhantu.baseapp.activities.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.model.RequestFood

class RequestAdapter(
    val requestsList: ArrayList<RequestFood>) :
    RecyclerView.Adapter<RequestAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.tvDescription?.text = requestsList[p1].description
        p0.tvDate?.text = requestsList[p1].date
        p0.tvAddress?.text = requestsList[p1].address
        p0.tvQuantity?.text = "Still need ${requestsList[p1].quantity - requestsList[p1].alreadyTaken}/${requestsList[p1].quantity} packages of food"
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(
            R.layout.item_raises_donor,
            p0,
            false
        )

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return requestsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
        val tvAddress = itemView.findViewById<TextView>(R.id.tv_address)
        val tvQuantity = itemView.findViewById<TextView>(R.id.tv_quantity)
    }
}