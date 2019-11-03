package com.sabinhantu.baseapp.fragments.asker

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject
import com.sabinhantu.baseapp.R
import com.sabinhantu.baseapp.data.RetrofitClientInstance
import com.sabinhantu.baseapp.data.api.AskFoodRequest
import com.sabinhantu.baseapp.data.api.AskerAPI
import com.sabinhantu.baseapp.data.api.DonorAPI
import com.sabinhantu.baseapp.fragments.SABBaseFragment
import com.sabinhantu.baseapp.fragments.donor.DonateDonorFragment
import com.sabinhantu.baseapp.helper.UtilSharedPreferences
import kotlinx.android.synthetic.main.fragment_ask_asker.*
import kotlinx.android.synthetic.main.fragment_ask_asker.et_date
import kotlinx.android.synthetic.main.fragment_ask_asker.et_description
import kotlinx.android.synthetic.main.fragment_ask_asker.et_number_packages
import kotlinx.android.synthetic.main.fragment_donate_donor.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            createAsk()
        }
    }

    private fun createAsk() {
        val service = RetrofitClientInstance.retrofitInstance?.create<AskerAPI>(
            AskerAPI::class.java)

        val call = service?.createAskFood(
            AskFoodRequest(
                quantity = et_number_packages.text.toString().toInt(),
                volunteerId = UtilSharedPreferences.getUserId(context!!).toLong(),
                address = et_location.text.toString(),
                description = et_description.text.toString(),
                date = et_date.text.toString()
            )
        )
//        showAlertDialogOnSucces()

        call?.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Toast.makeText(
                    context,
                    "Something went wrong ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful) {
                    showAlertDialogOnSucces()
//                    Toast.makeText(
//                        context,
//                        "Ask for food SUCCESS",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }
            }

        })
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