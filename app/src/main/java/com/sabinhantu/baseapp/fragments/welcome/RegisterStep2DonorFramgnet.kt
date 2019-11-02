package com.sabinhantu.baseapp.fragments.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import kotlinx.android.synthetic.main.fragment_register_step_2_donor.*

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
            Toast.makeText(context,"REGISTER AS DONOR", Toast.LENGTH_SHORT).show()
        }
    }

}