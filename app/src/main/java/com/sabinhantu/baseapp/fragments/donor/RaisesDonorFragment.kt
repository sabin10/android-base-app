package com.sabinhantu.baseapp.fragments.donor

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.details.DonationDetailsActivity
import com.sabinhantu.baseapp.activities.details.RequestDetailsActivity
import com.sabinhantu.baseapp.adapters.RequestAdapter
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.DonorAPI
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.logErrorMessage
import com.sabinhantu.baseapp.model.RequestFood
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
                        adapter.onClickItem = { companyId, description, location, status ->

                            val fragmentBundle = Bundle()
//                            "companyId=$companyId".logErrorMessage()
                            companyId?.let { it1 -> fragmentBundle.putLong("companyId", it1) }
                            description?.let { it1 -> fragmentBundle.putString("description", it1) }
                            status?.let { it1 -> fragmentBundle.putString("status", it1) }

                            val intent = Intent(activity, RequestDetailsActivity::class.java)
                            intent.putExtra("bundle",fragmentBundle)
                            startActivity(intent)
                        }
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