package com.cba.sample.feature.threeDS.injection

import com.cba.sample.core.data.injection.DispatchersModule
import com.cba.sample.core.data.injection.NetworkModule
import com.cba.sample.feature.threeDS.data.api.ThreeDSApi
import com.cba.sample.feature.threeDS.data.repository.ThreeDSRepositoryImpl
import com.cba.sample.feature.threeDS.domain.repository.ThreeDSRepository
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
class ThreeDSModule {

    @Singleton
    @Provides
    fun provideThreeDSApi(retrofit: Retrofit): ThreeDSApi {
        return retrofit.create(ThreeDSApi::class.java)
    }

    @Singleton
    @Provides
    fun provideThreeDSRepository(
        @Named("IO") dispatcher: CoroutineDispatcher,
        threeDSApi: ThreeDSApi,
    ): ThreeDSRepository {
        return ThreeDSRepositoryImpl(dispatcher, threeDSApi)
    }
}