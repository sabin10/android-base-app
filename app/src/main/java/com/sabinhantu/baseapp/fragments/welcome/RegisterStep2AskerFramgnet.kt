package com.sabinhantu.baseapp.fragments.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AuthenticationAPI
import com.sabinhantu.baseapp.data.api.RegisterDonorApi
import com.sabinhantu.baseapp.data.api.RegisterVolunteerApi
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.logErrorMessage
import com.sabinhantu.baseapp.model.Donor
import kotlinx.android.synthetic.main.fragment_register_step_2_asker.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterStep2AskerFramgnet : SABBaseFragment() {

    companion object {
        fun newInstance(): RegisterStep2AskerFramgnet {
            return RegisterStep2AskerFramgnet()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_register_step_2_asker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        btn_register.setOnClickListener {
            Toast.makeText(context,"REGISTER AS ASKER", Toast.LENGTH_SHORT).show()


            if(
                edt_password.text.trim().toString() ==
                edt_password_confirm.text.trim().toString()
            ){

                val data = RegisterVolunteerApi(
                    name = edt_name.text.trim().toString(),
                    email = edt_email.text.trim().toString(),
                    type = "company",
                    password = edt_password.text.trim().toString(),
                    phoneNumber = edt_phone.text.toString(),
                    picture = null
                )

                val service =
                    RetrofitClientInstance.retrofitInstance?.create<AuthenticationAPI>(AuthenticationAPI::class.java)
                val call = service?.registerAsVolunteer(data)

                call?.enqueue(object : Callback<JsonObject> {
                    override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                        "response=${response.body()}".logErrorMessage()
                        Toast.makeText(
                            context,
                            "Register SUCCESSS",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                        Toast.makeText(
                            context,
                            "Something went wrong ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })

            } else {
                Toast.makeText(context, " Passwords don't match ", Toast.LENGTH_SHORT).show()
            }



        }

    }

}