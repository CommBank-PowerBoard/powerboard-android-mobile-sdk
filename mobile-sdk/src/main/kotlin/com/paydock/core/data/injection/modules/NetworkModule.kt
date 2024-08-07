package com.paydock.core.data.injection.modules

import com.paydock.BuildConfig
import com.paydock.MobileSDK
import com.paydock.core.domain.mapper.mapToSSLPin
import com.paydock.core.network.NetworkClientBuilder
import org.koin.dsl.module

/**
 * Network-based module for handling network components using Ktor.
 *
 * This module provides singleton and factory components for network operations.
 * - Singleton components are kept unique within the Koin container.
 * - Factory components provide a new instance each time they are requested.
 */
/**
 * Network-based module for handling network components using Ktor.
 *
 * This module provides singleton and factory components for network operations.
 * - Singleton components are kept unique within the Koin container.
 * - Factory components provide a new instance each time they are requested.
 */
val networkModule = module {
    includes(dispatchersModule)
    single {
        NetworkClientBuilder.create()
            .setBaseUrl(MobileSDK.getInstance().baseUrl)
            .setSslPins(MobileSDK.getInstance().environment.mapToSSLPin())
            .setDebug(BuildConfig.DEBUG)
            .build()
    }
}