package com.sabinhantu.baseapp.data.interceptors

import android.content.Context
import android.content.ContextWrapper
import android.net.ConnectivityManager
import com.sabinhantu.baseapp.data.NetworkEventBus
import com.sabinhantu.baseapp.data.NetworkState
import com.sabinhantu.baseapp.helper.logErrorMessage
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject


class ConnectivityStatus(base: Context) : ContextWrapper(base) {
    companion object {
        fun isConnected(context: Context): Boolean {
            val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as
                    ConnectivityManager
            val connection = manager.activeNetworkInfo
            connection?.isConnected?.run {
                return this
            }

            return false
        }
    }
}

class NetworkInterceptor(private val context: Context) : Interceptor {

    private val networkEvent:NetworkEventBus = NetworkEventBus

    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()

        /**
         * Check if there is internet available in the device.
         * If not, pass the networkState as NoInternet.
         * */
        if (!ConnectivityStatus.isConnected(context)) {
            networkEvent.publish(NetworkState.NoInternet)
        } else {
            try {
                val response = chain.proceed(request)

                val networkState = NetworkState.from(response.code())

                networkState.api = request.url().toString()
                "response.isSuccessful=${response.isSuccessful}".logErrorMessage()
                when (response.isSuccessful) {
                    true -> {
                        networkState.message = null
                    }

                    else -> {
                        val responseBodyCopy = response.peekBody(java.lang.Long.MAX_VALUE)
                        val content = responseBodyCopy.string()
                        "api=${response.request()} CODE=${response.code()}".logErrorMessage()
                        networkState.message = getMessage(content)
                    }
                }

                networkEvent.publish(networkState)
                return response
            } catch (e: Throwable) {
                "error UNKONW =${e.message}".logErrorMessage()
                "error UNKONW =${e.localizedMessage}".logErrorMessage()
                "error UNKONW =${e.cause}".logErrorMessage()
                networkEvent.publish(NetworkState.Unknown)
            }
        }

        return null
    }

    private fun getMessage(response: String): String? = try {
        JSONObject(response).getString(NetworkState.MESSAGE)
    } catch (ex: JSONException) {
        null
    }
}