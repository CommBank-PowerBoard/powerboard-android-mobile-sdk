package com.cba.sample.core.data.injection

import com.cba.sample.feature.card.data.utils.CreateVaultTokenRequestAdapterFactory
import com.cba.sample.feature.threeDS.data.utils.Create3DSChargeRequestAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.paydock.BuildConfig
import com.paydock.MobileSDK
import com.paydock.core.domain.model.Environment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideBaseUrl(): String {
        return when (MobileSDK.getInstance().environment) {
            Environment.STAGING -> "https://api.staging.powerboard.commbank.com.au"
            Environment.PRE_PRODUCTION -> "https://api.preproduction.powerboard.commbank.com.au"
            Environment.PRODUCTION -> "https://api.powerboard.commbank.com.au"
        }
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapterFactory(CreateVaultTokenRequestAdapterFactory())
        .registerTypeAdapterFactory(Create3DSChargeRequestAdapterFactory())
        .create()


    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, okHttp: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttp)
            baseUrl(baseUrl)
        }.build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(1, TimeUnit.MINUTES)
            readTimeout(1, TimeUnit.MINUTES)
            writeTimeout(5, TimeUnit.MINUTES)
            retryOnConnectionFailure(true)
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                addInterceptor(logging)
            }
        }.build()
    }
}