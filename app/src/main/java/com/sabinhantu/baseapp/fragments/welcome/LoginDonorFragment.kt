package com.sabinhantu.baseapp.fragments.welcome

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.donor.HomeDonorActivity
import com.google.gson.JsonObject
import com.sabinhantu.baseapp.data.NetworkEventBus
import com.sabinhantu.baseapp.data.NetworkState
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AuthenticationAPI
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.Constants
import com.sabinhantu.baseapp.helper.UtilSharedPreferences
import com.sabinhantu.baseapp.helper.logErrorMessage
import com.sabinhantu.baseapp.model.Donor
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_login_donor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.android.synthetic.main.fragment_login_donor.*
import kotlinx.android.synthetic.main.fragment_login_donor.btn_login
import kotlinx.android.synthetic.main.fragment_login_donor.tv_register

class LoginDonorFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): LoginDonorFragment {
            return LoginDonorFragment()
        }
    }


    override fun onResume() {
        super.onResume()
        registerNetworkEventBus()
    }

    override fun onPause() {
        super.onPause()
        NetworkEventBus.unregister(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(com.sabinhantu.baseapp.R.layout.fragment_login_donor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        edt_email.setText("bogdand@gmail.com")
        edt_password.setText("parola")
        btn_login.setOnClickListener {
            login()
        }

        tv_login_as_asker.setOnClickListener {
            onAddFragmentByTAG(Constants.FragmentTags.TAG_FRAGMENT_LOGIN_ASKER)
        }

        tv_register.setOnClickListener {
            onAddFragmentByTAG(Constants.FragmentTags.TAG_FRAGMENT_REGISTER_STEP_1)
        }
    }

    private fun login() {
//        Toast.makeText(context,"Login !!!",Toast.LENGTH_LONG).show()
        toHomeDonor()
    }

    private fun toHomeDonor() {

        val service =
            RetrofitClientInstance.retrofitInstance?.create<AuthenticationAPI>(AuthenticationAPI::class.java)
        val call = service?.loginAsDonor(
            edt_email.text.trim().toString(),
            edt_password.text.trim().toString())

        call?.enqueue(object : Callback<Donor> {
            override fun onResponse(call: Call<Donor>, response: Response<Donor>) {
                if(response.isSuccessful){

                    "response=${response.body().toString()}".logErrorMessage()

                    context?.let { ctx ->
                        response.body()?.id?.let {
                            UtilSharedPreferences.saveUser(ctx,it)
                        }
                    }

                    intentToHomeDonoActivity()

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
    }

    fun intentToHomeDonoActivity() {
        val intent = Intent(context, HomeDonorActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }



    /**
     * Handle network errors
     */
    fun registerNetworkEventBus() =
        NetworkEventBus.register(this, Consumer { networkState ->
            when (networkState) {
//                NetworkState.NoInternet ->
//                    mRequestAction?.let {
//                        it.requestNoInternetAction()?.let {
//                            "will load offline.".logErrorMessage()
//                        } ?: run {
//                            showInternetAlert()
//                        }
//                    }
//
//                ECNetworkState.Unauthorized -> {
//                    context?.showContactUsAlertWithAction()
//                }

                NetworkState.OK, NetworkState.Created, NetworkState.Accepted -> {
                    val successMessage =
                        "SUCCESS: Request result state -> ${networkState.name}. API: ${networkState.api}"
//                    context?.showDebugToast(successMessage)

                    Toast.makeText(context,successMessage,Toast.LENGTH_SHORT).show()

                    networkState.message?.let { message ->
                        mAlertCallback?.showAlert(message)
                    }
                }

                else -> {
                    "Request result state: ${networkState.name}".logErrorMessage()
                    mAlertCallback?.showAlert(
                        message = networkState.message ?: "Unkown error"
                    )
                }
            }

            if(networkState != NetworkState.Created) {
                hideProgressDialog()
            }
        })
}