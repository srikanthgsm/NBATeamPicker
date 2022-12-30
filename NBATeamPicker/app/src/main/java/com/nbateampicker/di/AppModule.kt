package com.nbateampicker.di

import com.nbateampicker.BuildConfig
import com.nbateampicker.data.api.PublicService
import com.nbateampicker.util.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class AppModule {
    var serverHostname: String = ""

    @Singleton
    @Provides
    fun publicService(): PublicService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL_NEW)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(getOkHttpClient())
            .build()
            .create(PublicService::class.java)
    }

    /**
     * OkHttpClient
     */
    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        return builder.build()
    }
}
