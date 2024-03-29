package com.sabinhantu.baseapp.fragments.asker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.welcome.WelcomeActivity
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AskerAPI
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.Constants
import com.sabinhantu.baseapp.helper.UtilSharedPreferences
import com.sabinhantu.baseapp.model.Volunteer
import kotlinx.android.synthetic.main.fragment_profile_asker.*
import kotlinx.android.synthetic.main.fragment_profile_donor.*
import kotlinx.android.synthetic.main.fragment_profile_donor.tv_name
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileAskerFragment: SABBaseFragment() {
    companion object {
        fun newInstance(): ProfileAskerFragment {
            return ProfileAskerFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_asker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    fun initViews() {
        makeApiCall()
        btn_logout_asker.setOnClickListener {
            val intent = Intent(context, WelcomeActivity::class.java)
            intent.putExtra(Constants.FragmentTags.TAG_FRAGMENT, Constants.FragmentTags.TAG_FRAGMENT_LOGIN_DONOR)
            startActivity(intent)
            activity?.finish()
        }
    }

    fun makeApiCall() {
        val service = RetrofitClientInstance.retrofitInstance?.create<AskerAPI>(
            AskerAPI::class.java)

        context?.let {
            val call = service?.getVolunteerProfile(UtilSharedPreferences.getUserId(context!!))

            call?.enqueue(object : Callback<Volunteer>{
                override fun onFailure(call: Call<Volunteer>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Something went wrong ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(call: Call<Volunteer>, response: Response<Volunteer>) {
                    tv_name.setText(response.body()?.name)
                    tv_email_asker.setText(response.body()?.email)
                    tv_phone_asker.setText(response.body()?.phoneNumber)
                }
            })

        }

    }
}