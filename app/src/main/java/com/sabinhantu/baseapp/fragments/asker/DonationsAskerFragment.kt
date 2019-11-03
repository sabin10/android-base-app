package com.sabinhantu.baseapp.fragments.asker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.adapters.DonationAdapter
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AskerAPI
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.logErrorMessage
import com.sabinhantu.baseapp.model.Donation
import kotlinx.android.synthetic.main.fragment_donations_asker.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonationsAskerFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): DonationsAskerFragment {
            return DonationsAskerFragment()
        }
    }

    private lateinit var adapter: DonationAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_donations_asker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_donations.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        fetchDonations()
    }

    private fun fetchDonations() {

        val service =
            RetrofitClientInstance.retrofitInstance?.create<AskerAPI>(AskerAPI::class.java)
        val call = service?.getDonations()

        call?.enqueue(object : Callback<ArrayList<Donation>> {
            override fun onResponse(call: Call<ArrayList<Donation>>, response: Response<ArrayList<Donation>>) {
                if(response.isSuccessful){

                    "response=${response.body().toString()}".logErrorMessage()
                    Toast.makeText(
                        context,
                        "Get food request SUCCESSS",
                        Toast.LENGTH_SHORT
                    ).show()

                    response.body()?.let {
                        adapter = DonationAdapter(it)
                        rv_donations.adapter = adapter
                    }

                }else {
                    Toast.makeText(context,"code=${response.code()} message=${response.message()}",
                        Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<Donation>>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Something went wrong ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

}