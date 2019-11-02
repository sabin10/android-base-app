package com.sabinhantu.baseapp.fragments.donor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.fragments.SABBaseFragment

class DonateDonorFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): DonateDonorFragment {
            return DonateDonorFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_donate_donor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}