package com.noreplypratap.random.network

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashSet

/*
  This Network Manager is a singleton class,
  It is used for getting a network callback to check the network type of the user.
  To add or remove network callback use addNetworkCallback() & removeNetworkCallback() methods.
 */

class NetworkManager @Inject constructor(private val connectivityManager: ConnectivityManager) {
    private var network: Network? = null
    private val networkCallbacks: HashSet<NetworkService> = hashSetOf()
    private val networkCapabilities: NetworkCapabilities?
        get() = connectivityManager.getNetworkCapabilities(network)
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            this@NetworkManager.network = network
            updateCallbacks(availableNetwork())
        }

        override fun onLost(network: Network) {
            if (this@NetworkManager.network == network) {
                updateCallbacks(NetworkState(false, NetworkType.UNKNOWN))
            }
        }
    }

    init {
        registerNetworkCallback()
    }

    fun addNetworkCallback(networkService: NetworkService) {
        networkCallbacks.add(networkService)
    }

    fun removeNetworkCallback(networkService: NetworkService) {
        networkCallbacks.remove(networkService)
    }

    fun updateCallbacks(state: NetworkState) {
        networkCallbacks.forEach { networkService ->
            networkService.onNetworkChanged(state)
        }
    }

    fun availableNetwork(): NetworkState {
        val networkState =
            if (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true) {
                NetworkState(true, NetworkType.CELLULAR)
            } else if (networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true) {
                NetworkState(true, NetworkType.WIFI)
            } else {
                NetworkState(true, NetworkType.UNKNOWN)
            }
        return networkState
    }

    private fun registerNetworkCallback() {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }
}

data class NetworkState(
    val isConnected: Boolean,
    val networkType: NetworkType
)

enum class NetworkType {
    CELLULAR,
    WIFI,
    UNKNOWN;

    @SuppressLint("DefaultLocale")
    override fun toString(): String = name.lowercase(Locale.getDefault())
}

interface NetworkService {
    fun onNetworkChanged(networkState: NetworkState)
}