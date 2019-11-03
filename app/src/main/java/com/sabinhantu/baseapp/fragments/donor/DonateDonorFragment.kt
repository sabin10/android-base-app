package com.sabinhantu.baseapp.fragments.donor

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AuthenticationAPI
import com.sabinhantu.baseapp.data.api.DonorAPI
import com.sabinhantu.baseapp.data.api.OfferRequest
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.UtilSharedPreferences
import com.sabinhantu.baseapp.model.Offer
import kotlinx.android.synthetic.main.fragment_donate_donor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonateDonorFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): DonateDonorFragment {
            return DonateDonorFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_donate_donor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        btn_serve_inhouse.setOnClickListener {
            createOffer(inHouse = true)
        }

        btn_donate.setOnClickListener {
            createOffer(inHouse = false)
        }
    }

    private fun createOffer(inHouse: Boolean) {
        val service = RetrofitClientInstance.retrofitInstance?.create<DonorAPI>(
            DonorAPI::class.java)

        val call = service?.createOffer(
            OfferRequest(companyId = UtilSharedPreferences.getUserId(context!!).toLong(),
                date = et_date.text.toString(),
                quantity = et_number_packages.text.toString().toInt(),
                reserved = 0,
                inHouse = inHouse,
                description = et_description.text.toString()
            )
        )

        call?.enqueue(object : Callback<Offer> {
            override fun onFailure(call: Call<Offer>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Something went wrong ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<Offer>, response: Response<Offer>) {
                if (response.isSuccessful) {
//                    Toast.makeText(
//                        context,
//                        "Ask for food SUCCESS",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }
                showAlertDialogOnSucces()
                et_date.setText("")
                et_number_packages.setText("")
                et_description.setText("")
            }
        })
    }

    private fun showAlertDialogOnSucces() {
        val builder = AlertDialog.Builder(context)
//        builder.setTitle("")
        builder.setMessage("Thank you for donate your food instead of wasting it")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
        }
        builder.show()

        et_date.setText("")
        et_description.setText("")
        et_number_packages.setText("")
    }


}