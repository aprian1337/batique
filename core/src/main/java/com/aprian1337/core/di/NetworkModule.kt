package com.aprian1337.core.di

import com.aprian1337.core.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun providOkHttpClient(): OkHttpClient{
        val host = "batikita.herokuapp.com"
        val certPinner = CertificatePinner.Builder()
            .add(host, "sha256/5f/NUn3GOUSMuCPInul0grjzxaejU6eYGuny7/m6TPU=")
            .add(host, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
            .add(host, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .add(host, "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I=")
            .build()
        return OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certPinner)
            .build()
    }

    @Provides
    fun provideApi(client: OkHttpClient): com.aprian1337.core.data.source.remote.network.ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(com.aprian1337.core.data.source.remote.network.ApiService::class.java)
    }
}