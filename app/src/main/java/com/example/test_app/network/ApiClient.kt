package com.example.test_app.network

import android.util.Log
import com.example.test_app.BuildConfig
import com.example.test_app.entity.QueryResult
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val API_URL = "https://api.mercadolibre.com/sites/MLA/"

    private var mInterface: AppService

    init {
        //An interceptor is added to be able to view api calls and responses in the log.
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.i("ApiClient", message)
            }
        })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG)
            builder.addInterceptor(loggingInterceptor)

        val client = builder.build()

        val restAdapter = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build()

        //Creates the service
        mInterface = restAdapter.create(AppService::class.java)
    }

    fun getServiceClient() = mInterface

    interface AppService {

        @GET("search")
        fun getQueryProducts(
            @Header("ACCESS_TOKEN") accessToken: String?,
            @Query("q") query: String
        ): Observable<QueryResult>

    }
}