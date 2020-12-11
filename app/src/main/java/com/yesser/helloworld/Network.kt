package com.yesser.helloworld

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity

class Network {
    companion object {
        fun hayRed(activity: AppCompatActivity):Boolean{
            // Utilice ConnectivityManager para comprobar si está realmente conectado a Internet,
            // y si es así, qué tipo de conexión existe.
            val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            var networkTransport = ""
            var sdkVersion = ""
            var available = "No"


            if(Build.VERSION.SDK_INT > 22 ){
                // para dispositivos con API >= 23
                sdkVersion = "Version > 22"
                val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
                // NET_CAPABILITY_VALIDATED - Indica que la conectividad en esta red se validó con éxito.
                // NET_CAPABILITY_INTERNET - Indica que esta red debería poder conectarse a Internet.
                if(networkCapabilities!=null && networkCapabilities.hasCapability(
                        NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)){
                    available = "como"
                    if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                        networkTransport = "WIFI"
                        return true
                    }
                    else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                        networkTransport = "Celular"
                        return true
                    }

                }
            }
            else{
                // para dispositivos con API < 23
                sdkVersion = "Version < 22"
                val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
                val isConnected: Boolean = activeNetwork?.isConnected == true
                val isWiFi: Boolean = activeNetwork?.type == ConnectivityManager.TYPE_WIFI
                val isMob: Boolean = activeNetwork?.type == ConnectivityManager.TYPE_MOBILE
                if(isConnected) {
                    available = "como "
                    if (isWiFi) {
                        networkTransport = "WIFI"
                        return true
                    } else if (isMob) {
                        networkTransport = "Celular"
                        return true
                    }
                }
            }
            return false
        }
    }
}