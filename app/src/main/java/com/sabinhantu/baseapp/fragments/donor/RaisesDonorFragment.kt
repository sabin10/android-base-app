package com.sabinhantu.baseapp.fragments.donor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.activities.adapters.RequestAdapter
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.welcome.LoginDonorFragment
import com.sabinhantu.baseapp.model.RequestFood
import kotlinx.android.synthetic.main.fragment_raises_donor.*

class RaisesDonorFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): RaisesDonorFragment {
            return RaisesDonorFragment()
        }
    }

    private lateinit var adapter: RequestAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_raises_donor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val array = ArrayList<RequestFood>()
        array.add(RequestFood.getPlaceholder())
        array.add(RequestFood.getPlaceholder())
        array.add(RequestFood.getPlaceholder())
        array.add(RequestFood.getPlaceholder())
        array.add(RequestFood.getPlaceholder())

        adapter = RequestAdapter(array)

        rv_requests.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        rv_requests.adapter = adapter
    }


}