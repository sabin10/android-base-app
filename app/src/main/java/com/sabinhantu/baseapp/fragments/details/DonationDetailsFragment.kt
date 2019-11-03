package com.sabinhantu.baseapp.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AskRequest
import com.sabinhantu.baseapp.data.api.AskResponse
import com.sabinhantu.baseapp.data.api.AskerAPI
import com.sabinhantu.baseapp.data.api.AuthenticationAPI
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.UtilSharedPreferences
import com.sabinhantu.baseapp.helper.logErrorMessage
import com.sabinhantu.baseapp.model.Volunteer
import kotlinx.android.synthetic.main.fragment_donation_details.*
import kotlinx.android.synthetic.main.fragment_login_asker.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DonationDetailsFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): DonationDetailsFragment{
            return DonationDetailsFragment()
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
        return inflater.inflate(R.layout.fragment_donation_details, container, false)
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

        val data = AskRequest(
            reservedQuantity = edt_donate.text.trim().toString().toInt(),
            companyId = companyId ?: return,
            volunteerId = volunteerId?.toLong() ?: return
        )
        "data=$data".logErrorMessage()
        val service =
            RetrofitClientInstance.retrofitInstance?.create<AskerAPI>(AskerAPI::class.java)
        val call = service?.createAsk(
            data
        )

        call?.enqueue(object : Callback<AskResponse> {
            override fun onResponse(call: Call<AskResponse>, response: Response<AskResponse>) {
                "response=${response.body().toString()}".logErrorMessage()

                if(response.isSuccessful){

                    Toast.makeText(
                        context,
                        "Request for food SUCCESSS",
                        Toast.LENGTH_SHORT
                    ).show()
                    val data = response.body()
                    "data.quantity=${data?.quantity} data.reserved=${data?.reservedQuantity}".logErrorMessage()
                    tv_status.setText("Available ${data?.quantity?.minus(data?.reservedQuantity)}/${data?.quantity} packages of food")

                }else {
                    Toast.makeText(context,"code=${response.code()} message=${response.message()}",Toast.LENGTH_SHORT).show()
                }

            }

            override fun onFailure(call: Call<AskResponse>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Something went wrong ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

    }
}