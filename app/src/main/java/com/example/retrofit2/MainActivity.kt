package com.example.retrofit2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.retrofit2.response.LoginResponse
import kotlinx.android.synthetic.main.activity_main.*
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



        button.setOnClickListener {
            val userid = findViewById<EditText>(R.id.id)
            val passwd = findViewById<EditText>(R.id.passwd)
            Log.d(TAG, "userid = $userid")
            Log.d(TAG, "passwd = $passwd")

            Runnable {
                myAPI.login(userid.text.toString(), passwd.text.toString()).enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.d(TAG, t.message)
                        println("onFailure")
                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        println("onSuccess")
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

        hello.setOnClickListener {
            val userid = findViewById<EditText>(R.id.id)

            Runnable {
                myAPI.hello(userid.text.toString()).enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.d(TAG, t.message)
                        println("onFailure")
                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        println("onSuccess")
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
}
