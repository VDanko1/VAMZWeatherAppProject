package com.example.semestrlnaprcafinal.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Táto trieda mi vracia inštanciu retrofitu
 * pretože ju využívam v dvoch triedach
 * a je to na zabezpečenie aby mi sa mi neopakoval kód.
 *
 */


class RetrofitClass {

    /**
     * Slúži mi na získanie inštancie retrofitu
     * @return ApiInterface
     *
     * inšpirácia (nie prevzatie) - https://www.youtube.com/watch?v=RSYTn-O3r34&t=1161s&ab_channel=ResoCoder
     */
    fun getRetrofit(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        return retrofit;
    }



}