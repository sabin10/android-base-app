package com.sabinhantu.baseapp.fragments.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.asker.HomeAskerActivity
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AuthenticationAPI
import com.sabinhantu.baseapp.data.api.RegisterDonorApi
import com.sabinhantu.baseapp.data.api.RegisterVolunteerApi
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.UtilSharedPreferences
import com.sabinhantu.baseapp.helper.logErrorMessage
import com.sabinhantu.baseapp.model.Donor
import com.sabinhantu.baseapp.model.Volunteer
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

                call?.enqueue(object : Callback<Volunteer> {
                    override fun onResponse(call: Call<Volunteer>, response: Response<Volunteer>) {
                        "response=${response.body().toString()}".logErrorMessage()

                        if(response.isSuccessful){
                            Toast.makeText(
                                context,
                                "Register SUCCESSS",
                                Toast.LENGTH_SHORT
                            ).show()

                        intentToHomeAskerActivity()

                            context?.let { ctx ->
                                response.body()?.id?.let {
                                    UtilSharedPreferences.saveUser(ctx,it)
                                }
                            }

                            val intent = Intent(context, HomeAskerActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
                        }else {
                            Toast.makeText(context,"code=${response.code()} message=${response.message()}",Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Volunteer>, t: Throwable) {
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

    fun intentToHomeAskerActivity() {
        val intent = Intent(context, HomeAskerActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

}