package com.sabinhantu.baseapp.fragments.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.Constants
import kotlinx.android.synthetic.main.fragment_register_step_1.*

class RegisterStep1Fragment : SABBaseFragment() {

    companion object {
        fun newInstance(): RegisterStep1Fragment {
            return RegisterStep1Fragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_register_step_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {

        btn_register_donor.setOnClickListener {
            onAddFragmentByTAG(Constants.FragmentTags.TAG_FRAGMENT_REGISTER_STEP_2_DONOR)
        }

        btn_register_person.setOnClickListener {
            onAddFragmentByTAG(Constants.FragmentTags.TAG_FRAGMENT_REGISTER_STEP_2_ASKER)
        }
    }

}