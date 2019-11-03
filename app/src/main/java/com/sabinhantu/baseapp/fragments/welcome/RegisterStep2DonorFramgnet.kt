package com.sabinhantu.baseapp.fragments.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.donor.HomeDonorActivity
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AuthenticationAPI
import com.sabinhantu.baseapp.data.api.RegisterDonorApi
import com.sabinhantu.baseapp.data.repository.AuthRepository
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.UtilSharedPreferences
import com.sabinhantu.baseapp.helper.logErrorMessage
import com.sabinhantu.baseapp.model.Donor
import kotlinx.android.synthetic.main.fragment_register_step_2_donor.*
import kotlinx.android.synthetic.main.fragment_register_step_2_donor.btn_register
import kotlinx.android.synthetic.main.fragment_register_step_2_donor.edt_name
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterStep2DonorFramgnet : SABBaseFragment() {

    companion object {
        fun newInstance(): RegisterStep2DonorFramgnet {
            return RegisterStep2DonorFramgnet()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_register_step_2_donor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        btn_register.setOnClickListener {
//            Toast.makeText(context,"REGISTER AS DONOR", Toast.LENGTH_SHORT).show()

            if(
                edt_password.text.trim().toString() ==
                        edt_password_confirm.text.trim().toString()
                    ){

                val data = RegisterDonorApi(
                    name = edt_name.text.trim().toString(),
                    email = edt_email.text.trim().toString(),
                    type = "company",
                    cui = edt_cui.text.trim().toString(),
                    password = edt_password.text.trim().toString(),
                    latitudeX = null,
                    latitudeY = null,
                    phoneNumber = edt_phone.text.toString(),
                    picture = null
                )

                val service =
                    RetrofitClientInstance.retrofitInstance?.create<AuthenticationAPI>(AuthenticationAPI::class.java)
                val call = service?.registerAsDonor(data)

                call?.enqueue(object : Callback<Donor> {
                    override fun onResponse(call: Call<Donor>, response: Response<Donor>) {
                        "response=${response.body().toString()}".logErrorMessage()
                        if(response.isSuccessful){
                            Toast.makeText(
                                context,
                                "Register SUCCESSS",
                                Toast.LENGTH_SHORT
                            ).show()

                            context?.let { ctx ->
                                response.body()?.id?.let {
                                    UtilSharedPreferences.saveUser(ctx,it)
                                }
                            }

                            val intent = Intent(context, HomeDonorActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
                        }else {
                            Toast.makeText(context,"code=${response.code()} message=${response.message()}",Toast.LENGTH_SHORT).show()
                        }

                    }

                    override fun onFailure(call: Call<Donor>, t: Throwable) {
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