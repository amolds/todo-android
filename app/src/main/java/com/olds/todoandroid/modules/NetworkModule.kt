package com.olds.todoandroid.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.olds.todoandroid.api.TodoApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://10.1.10.32:18080/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    fun provideTodoApi(retrofit: Retrofit): TodoApi =
        retrofit.create(TodoApi::class.java)
}