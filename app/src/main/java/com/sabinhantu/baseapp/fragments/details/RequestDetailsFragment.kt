package com.sabinhantu.baseapp.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.*
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.UtilSharedPreferences
import com.sabinhantu.baseapp.helper.logErrorMessage
import kotlinx.android.synthetic.main.fragment_request_details.*
import kotlinx.android.synthetic.main.fragment_request_details.btn_ok
import kotlinx.android.synthetic.main.fragment_request_details.edt_donate
import kotlinx.android.synthetic.main.fragment_request_details.tv_description
import kotlinx.android.synthetic.main.fragment_request_details.tv_location
import kotlinx.android.synthetic.main.fragment_request_details.tv_status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestDetailsFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): RequestDetailsFragment{
            return RequestDetailsFragment()
        }
    }

    private var companyId: Long? = null
    private var description: String? = null
    private var location: String? = null
    private var status: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_request_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companyId = arguments?.getLong("companyId")?.let { it ->
            //            Toast.makeText(context, "companyId=$it", Toast.LENGTH_SHORT).show()
            it
        }

        description = arguments?.getString("description")?.let { it ->
            //            Toast.makeText(context, "companyId=$it", Toast.LENGTH_SHORT).show()
            it
        }

        location = arguments?.getString("location")?.let { it ->
            //            Toast.makeText(context, "companyId=$it", Toast.LENGTH_SHORT).show()
            it
        }

        status = arguments?.getString("status")?.let { it ->
            //            Toast.makeText(context, "companyId=$it", Toast.LENGTH_SHORT).show()
            it
        }

        "companyId=$companyId description=$description location=$location status=$status".logErrorMessage()

        tv_description.setText(description)
        tv_location.setText(location)
        tv_status.setText(status)

        btn_ok.setOnClickListener {
            createAsk()
        }

    }


    fun createAsk() {

        val volunteerId = context?.let { ctx ->
            UtilSharedPreferences.getUserId(ctx)
        }

        val data = ProvideRequest(
            reserved = edt_donate.text.trim().toString().toInt(),
            requestId = companyId?.toLong() ?: return
        )
        "data=$data".logErrorMessage()

        val service =
            RetrofitClientInstance.retrofitInstance?.create<DonorAPI>(DonorAPI::class.java)
        val call = service?.provide(
            data
        )

        call?.enqueue(object : Callback<ProvideResponse> {
            override fun onResponse(call: Call<ProvideResponse>, response: Response<ProvideResponse>) {
                "response=${response.body().toString()}".logErrorMessage()

                if(response.isSuccessful){

                    Toast.makeText(
                        context,
                        "Request for food SUCCESSS",
                        Toast.LENGTH_SHORT
                    ).show()
                    val data = response.body()
                    "data.quantity=${data?.quantity} data.reserved=${data?.reservedQuantity}".logErrorMessage()
                    tv_status.setText("Still need ${data?.quantity?.minus(data?.reservedQuantity)}/${data?.quantity} packages of food")

                }else {
                    Toast.makeText(context,"code=${response.code()} message=${response.message()}",
                        Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<ProvideResponse>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Something went wrong ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

    }

}