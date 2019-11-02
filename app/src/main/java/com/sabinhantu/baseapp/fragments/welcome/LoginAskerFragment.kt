package com.sabinhantu.baseapp.fragments.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.data.NetworkEventBus
import com.sabinhantu.baseapp.data.NetworkState
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AuthenticationAPI
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.Constants
import com.sabinhantu.baseapp.helper.logErrorMessage
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_login_asker.*
import kotlinx.android.synthetic.main.fragment_login_asker.edt_email
import kotlinx.android.synthetic.main.fragment_login_asker.edt_password
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginAskerFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): LoginAskerFragment {
            return LoginAskerFragment()
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
        return inflater.inflate(R.layout.fragment_login_asker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        btn_login?.setOnClickListener {
            login()
        }

        tv_login_as_donor?.setOnClickListener {
            onAddFragmentByTAG(Constants.FragmentTags.TAG_FRAGMENT_LOGIN_DONOR)
        }

        tv_register.setOnClickListener {
            onAddFragmentByTAG(Constants.FragmentTags.TAG_FRAGMENT_REGISTER_STEP_1)
        }
    }

    private fun login() {

        val service =
            RetrofitClientInstance.retrofitInstance?.create<AuthenticationAPI>(AuthenticationAPI::class.java)
        val call = service?.loginAsVolunteer(
            edt_email.text.trim().toString(),
            edt_password.text.trim().toString())

        call?.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                "response=${response.body()}".logErrorMessage()

                Toast.makeText(
                    context,
                    "Login SUCCESSS",
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