package com.example.semestrlnaprcafinal

import com.example.semestrlnaprcafinal.data.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClass {

    fun getRetrofit(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        return retrofit;
    }



}