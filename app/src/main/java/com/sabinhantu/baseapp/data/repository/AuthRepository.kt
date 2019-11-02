package com.sabinhantu.baseapp.data.repository

import com.sabinhantu.baseapp.data.api.AuthenticationAPI
import com.sabinhantu.baseapp.data.client.APIClient

object AuthRepository{

    private val authenticationAPI: AuthenticationAPI by lazy {
        APIClient.retrofitClient.create(AuthenticationAPI::class.java)
    }

    fun loginAsDonor(email: String, password: String) =
        authenticationAPI.loginAsDonor(email, password)


}
