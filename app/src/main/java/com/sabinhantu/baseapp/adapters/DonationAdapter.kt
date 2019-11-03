package com.sabinhantu.baseapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.model.Donation

class DonationAdapter (
    val donationsList: ArrayList<Donation>) :
    RecyclerView.Adapter<DonationAdapter.ViewHolder>() {

    var onClickItem: ((Long?,String?, String?, String?) -> Unit)? = null

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(p1)
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
        val tvAddress = itemView.findViewById<TextView>(R.id.tv_address)
        val tvQuantity = itemView.findViewById<TextView>(R.id.tv_quantity)

        fun bind(position: Int) {

            tvDescription?.text = donationsList[position].description
            tvDate?.text = donationsList[position].date
            tvAddress?.text = donationsList[position].address
//            tvQuantity?.text = "${donationsList[position].alreadyTaken}/${donationsList[position].quantity}"

            donationsList[position].quantity?.let { quantity ->
                donationsList[position].alreadyTaken?.let { alreadyTaken ->
                    tvQuantity?.text = "Available ${quantity.minus(alreadyTaken)}/${quantity} packages of food"

                }

            }

            itemView.findViewById<ConstraintLayout>(R.id.cly_container_donation).setOnClickListener {
                val status = donationsList[position].quantity?.let { quantity ->
                    donationsList[position].alreadyTaken?.let { alreadyTaken ->
                        "Available ${quantity.minus(alreadyTaken)}/${quantity} packages of food"
                    }
                }
                onClickItem?.invoke(
                    donationsList[position].id,
                    donationsList[position].description,
                    donationsList[position].address,
                    status
                )
            }
        }
    }
}