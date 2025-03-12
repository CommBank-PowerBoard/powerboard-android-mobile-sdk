package com.cba.sample.feature.wallet.injection

import com.cba.sample.core.data.injection.DispatchersModule
import com.cba.sample.core.data.injection.NetworkModule
import com.cba.sample.feature.wallet.data.api.WalletApi
import com.cba.sample.feature.wallet.data.repository.WalletRepositoryImpl
import com.cba.sample.feature.wallet.domain.repository.WalletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DispatchersModule::class])
@InstallIn(SingletonComponent::class)
class WalletModule {

    @Singleton
    @Provides
    fun provideWalletApi(retrofit: Retrofit): WalletApi {
        return retrofit.create(WalletApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWalletRepository(
        @Named("IO") dispatcher: CoroutineDispatcher,
        walletApi: WalletApi,
    ): WalletRepository {
        return WalletRepositoryImpl(dispatcher, walletApi)
    }
}