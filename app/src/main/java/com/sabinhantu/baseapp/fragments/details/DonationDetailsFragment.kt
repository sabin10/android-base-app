package com.sabinhantu.baseapp.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.helper.logErrorMessage
import kotlinx.android.synthetic.main.fragment_donation_details.*

class DonationDetailsFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): DonationDetailsFragment{
            return DonationDetailsFragment()
        }
    }

    private var companyId: Long? = null
    private var description: String? = null
    private var location: String? = null
    private var status: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_donation_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        companyId = arguments?.getLong("companyId")?.let { it ->
//            Toast.makeText(context, "companyId=$it", Toast.LENGTH_SHORT).show()
            it
        }

        description = arguments?.getString("description")?.let { it ->
//            Toast.makeText(context, "companyId=$it", Toast.LENGTH_SHORT).show()
            it
        }

        location = arguments?.getString("location")?.let { it ->
//            Toast.makeText(context, "companyId=$it", Toast.LENGTH_SHORT).show()
            it
        }

        status = arguments?.getString("status")?.let { it ->
//            Toast.makeText(context, "companyId=$it", Toast.LENGTH_SHORT).show()
            it
        }

        "companyId=$companyId description=$description location=$location status=$status".logErrorMessage()

        tv_description.setText(description)
        tv_location.setText(location)
        tv_status.setText(status)
    }
}