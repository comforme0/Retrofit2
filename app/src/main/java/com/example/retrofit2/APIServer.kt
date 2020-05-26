package com.example.retrofit2

import android.widget.EditText
import com.example.retrofit2.response.LoginResponse
import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServer {
    @GET("login")
//    @FormUrlEncoded
    fun login(
        @Query("userid") userid: EditText,   // POST는 @Field
        @Query("pssswd") passwd: EditText
    ) : Call<LoginResponse>
}