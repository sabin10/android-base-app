package com.sabinhantu.baseapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.model.RequestFood

class RequestAdapter(
    val requestsList: ArrayList<RequestFood>) :
    RecyclerView.Adapter<RequestAdapter.ViewHolder>() {

    var onClickItem: ((Long?,String?, String?, String?) -> Unit)? = null

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
//        p0.tvDescription?.text = requestsList[p1].description
//        p0.tvDate?.text = requestsList[p1].date
//        p0.tvAddress?.text = requestsList[p1].address
//        p0.tvQuantity?.text = "Still need ${requestsList[p1].quantity - requestsList[p1].alreadyTaken}/${requestsList[p1].quantity} packages of food"
        p0.bind(p1)
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDescription = itemView.findViewById<TextView>(R.id.tv_description)
        val tvDate = itemView.findViewById<TextView>(R.id.tv_date)
        val tvAddress = itemView.findViewById<TextView>(R.id.tv_address)
        val tvQuantity = itemView.findViewById<TextView>(R.id.tv_quantity)

        fun bind(position: Int) {
            tvDescription?.text = requestsList[position].description
            tvDate?.text = requestsList[position].date
            tvAddress?.text = requestsList[position].address

            requestsList[position].quantity?.let { quantity ->
                requestsList[position].alreadyTaken?.let { alreadyTaken ->
                    tvQuantity?.text = "Still need ${quantity.minus(alreadyTaken)}/${quantity} packages of food"

                }

            }

            itemView.findViewById<ConstraintLayout>(R.id.cly_container_raises).setOnClickListener {
                val status = requestsList[position].quantity?.let { quantity ->
                    requestsList[position].alreadyTaken?.let { alreadyTaken ->
                        "Still need ${quantity.minus(alreadyTaken)}/${quantity} packages of food"
                    }
                }

                onClickItem?.invoke(
                    requestsList[position].id,
                    requestsList[position].description,
                    requestsList[position].address,
                    status
                )
            }
        }
    }
}