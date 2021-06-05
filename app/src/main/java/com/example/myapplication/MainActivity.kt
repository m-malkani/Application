package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity()
{
    var client = OkHttpClient()
    lateinit var btn1: Button
    lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.Button1)
        btn1.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "Toast Message", Toast.LENGTH_SHORT).show()
            var request = Request.Builder()
                .url("https://api.unsplash.com/photos/random/?client_id=52p26noSNMPK_3Dz20blhMPpXcMqtiB2gAIppXnBMR4")
                .build()

            client.newCall(request).enqueue(object : Callback
            {
                override fun onFailure(call: Call, e: IOException)
                {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response)
                {
                    response.use {

                        val jsonDataString = response.body!!.string()
                        val json = JSONObject(jsonDataString)
                        val rawUrl = json.getJSONObject("urls").getString("raw")

                        Log.d("UnsplashApplication", rawUrl)
                    }
                }
            })
        })
        btn2 = findViewById(R.id.Button2)
        btn2.setOnClickListener(View.OnClickListener {

        })
    }


}