package com.sabinhantu.baseapp.fragments.welcome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.Constants
import kotlinx.android.synthetic.main.fragment_login_donor.*

class LoginDonorFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): LoginDonorFragment {
            return LoginDonorFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_login_donor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        btn_login.setOnClickListener {
            login()
        }

        tv_login_as_asker.setOnClickListener {
            onReplaceFragmentByTAG(Constants.FragmentTags.TAG_FRAGMENT_LOGIN_ASKER)
        }
    }

    private fun login() {
        Toast.makeText(context,"Login !!!",Toast.LENGTH_LONG).show()
    }
}