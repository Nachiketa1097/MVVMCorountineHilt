package com.toroi.ftl.mvvmcorountinehilt.di

import com.toroi.ftl.mvvmcorountinehilt.api.AuthInterceptor
import com.toroi.ftl.mvvmcorountinehilt.api.NotesApi
import com.toroi.ftl.mvvmcorountinehilt.api.UserApi
import com.toroi.ftl.mvvmcorountinehilt.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providesUserAPI(retrofit: Retrofit): UserApi { // UserApi k case me interceptor ki jrurat nahi h
        return retrofit.create(UserApi::class.java)
    }

    //Retrofit setup for NotesApi with interceptor
    @Singleton
    @Provides
    fun providesOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun providesAuthRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient) //we are getting okHttpClient from above  fun providesOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient method
            .baseUrl(Constants.BASE_URL).build()
    }

    @Singleton
    @Provides
    fun providesNote(retrofit: Retrofit): NotesApi {// NotesApi k case me interceptor ki jrurat  h
        return retrofit.create(NotesApi::class.java)
    }
}