package com.sabinhantu.baseapp.fragments.donor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.adapters.RequestAdapter
import com.sabinhantu.baseapp.activities.donor.HomeDonorActivity
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AuthenticationAPI
import com.sabinhantu.baseapp.data.api.DonorAPI
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.welcome.LoginDonorFragment
import com.sabinhantu.baseapp.helper.UtilSharedPreferences
import com.sabinhantu.baseapp.helper.logErrorMessage
import com.sabinhantu.baseapp.model.Donor
import com.sabinhantu.baseapp.model.RequestFood
import kotlinx.android.synthetic.main.fragment_login_donor.*
import kotlinx.android.synthetic.main.fragment_raises_donor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RaisesDonorFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): RaisesDonorFragment {
            return RaisesDonorFragment()
        }
    }

    private lateinit var adapter: RequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_raises_donor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_requests.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        fetchRequests()
    }

    private fun fetchRequests() {

        val service =
            RetrofitClientInstance.retrofitInstance?.create<DonorAPI>(DonorAPI::class.java)
        val call = service?.getRequests()

        call?.enqueue(object : Callback<ArrayList<RequestFood>> {
            override fun onResponse(call: Call<ArrayList<RequestFood>>, response: Response<ArrayList<RequestFood>>) {
                if(response.isSuccessful){

                    "response=${response.body().toString()}".logErrorMessage()
                    Toast.makeText(
                        context,
                        "Get food request SUCCESSS",
                        Toast.LENGTH_SHORT
                    ).show()

                    response.body()?.let {
                        adapter = RequestAdapter(it)
                        rv_requests.adapter = adapter
                    }

                }else {
                    Toast.makeText(context,"code=${response.code()} message=${response.message()}",
                        Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<RequestFood>>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Something went wrong ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

}