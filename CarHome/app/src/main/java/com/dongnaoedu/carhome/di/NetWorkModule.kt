package com.dongnaoedu.carhome.di

import android.util.Log
import com.dongnaoedu.carhome.init.SERVER_URL
import com.dongnaoedu.carhome.remote.CarBrandService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
// 这里使用了 ApplicationComponent，因此 NetworkModule 绑定到 Application 的生命周期。
@InstallIn(ApplicationComponent::class)
@Module
object NetWorkModule {

    /**
     * @Provides 常用于被 @Module 注解标记类的内部的方法，并提供依赖项对象。
     * @Singleton 提供单例
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Log.d("ning", it)
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCarBrandService(retrofit: Retrofit): CarBrandService {
        return retrofit.create(CarBrandService::class.java)
    }

}