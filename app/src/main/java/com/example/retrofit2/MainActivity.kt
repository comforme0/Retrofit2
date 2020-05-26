package com.example.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.retrofit2.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var myAPI : APIServer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val TAG = MainActivity::class.java.simpleName
        setContentView(R.layout.activity_main)

        retrofit = RetrofitClient.getInstance()
        myAPI = retrofit.create(APIServer::class.java)

        val userid = findViewById<EditText>(R.id.id)
        val passwd = findViewById<EditText>(R.id.passwd)

        Runnable {
            myAPI.login(userid, passwd).enqueue(object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(TAG, t.message)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    Log.d(TAG, "Response : ${response.body()!!.resultCode}")
                    Log.d(TAG, "Response : ${response.body()!!.resultMsg}")

                    Log.d(TAG, "response : ${response.errorBody()}")
                    Log.d(TAG, "response : ${response.message()}")
                    Log.d(TAG, "response : ${response.code()}")
                    Log.d(TAG, "response : ${response.raw().request().url().uri()}")
                }
            })
        }.run()
    }
}
