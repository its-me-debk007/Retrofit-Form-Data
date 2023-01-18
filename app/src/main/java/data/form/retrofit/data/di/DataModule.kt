package data.form.retrofit.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.form.retrofit.BuildConfig
import data.form.retrofit.data.network.ApiService
import data.form.retrofit.data.repository.RepositoryImpl
import data.form.retrofit.domain.repository.Repository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService) : Repository = RepositoryImpl(apiService)
}
