package com.sabinhantu.baseapp.fragments.donor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.DonorAPI
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.UtilSharedPreferences
import com.sabinhantu.baseapp.model.Donor
import com.sabinhantu.baseapp.model.Offer
import kotlinx.android.synthetic.main.fragment_profile_donor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileDonorFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): ProfileDonorFragment {
            return ProfileDonorFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_donor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        makeApiCall()
    }

    fun initViews() {
        makeApiCall()
    }

    fun makeApiCall() {
        val service = RetrofitClientInstance.retrofitInstance?.create<DonorAPI>(
            DonorAPI::class.java)

        context?.let {
            val call = service?.getDonorProfile(UtilSharedPreferences.getUserId(it))

            call?.enqueue(object : Callback<Donor> {
                override fun onFailure(call: Call<Donor>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Something went wrong ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()                }

                override fun onResponse(call: Call<Donor>, response: Response<Donor>) {
//                    Toast.makeText(
//                        context,
//                        "BINEEEEE",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    tv_name.setText(response.body()?.name)
                    tv_email.setText(response.body()?.email)
                    tv_phone.setText(response.body()?.phoneNumber)
                    tv_address.setText(response.body()?.address)
                }

            })

        }

    }
}