package com.sabinhantu.baseapp.fragments.donor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.welcome.LoginDonorFragment

class RaisesDonorFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): RaisesDonorFragment {
            return RaisesDonorFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_raises_donor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}