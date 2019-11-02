package com.sabinhantu.baseapp.fragments.asker

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.donor.DonateDonorFragment
import kotlinx.android.synthetic.main.fragment_ask_asker.*
import kotlinx.android.synthetic.main.fragment_ask_asker.et_date
import kotlinx.android.synthetic.main.fragment_ask_asker.et_description
import kotlinx.android.synthetic.main.fragment_ask_asker.et_number_packages
import kotlinx.android.synthetic.main.fragment_donate_donor.*

class AskAskerFragment : SABBaseFragment() {
    companion object {
        fun newInstance(): AskAskerFragment {
            return AskAskerFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ask_asker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        btn_ask.setOnClickListener {

        }
    }

    private fun createAsk() {

    }

    private fun showAlertDialogOnSucces() {
        val builder = AlertDialog.Builder(context)
//        builder.setTitle("")
        builder.setMessage("Your ask is proccesed")
        //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
        }
        builder.show()
    }


}