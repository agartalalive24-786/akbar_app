package com.akbar.chessvisionpro.di

import com.akbar.chessvisionpro.data.remote.LichessApi
import com.akbar.chessvisionpro.data.remote.LichessService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val LICHESS_BASE_URL = "https://lichess.org/"
    
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    
    @Provides
    @Singleton
    fun provideLichessApi(okHttpClient: OkHttpClient): LichessApi {
        return Retrofit.Builder()
            .baseUrl(LICHESS_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LichessApi::class.java)
    }
    
    @Provides
    @Singleton
    fun provideLichessService(lichessApi: LichessApi): LichessService {
        return LichessService(lichessApi)
    }
}
