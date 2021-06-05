package com.example.myapplication


import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    var client = OkHttpClient()
    lateinit var btn1: Button
    lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.Button1)
        btn1.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "Toast Message", Toast.LENGTH_SHORT).show()
            var request = Request.Builder()
                .url("https://api.unsplash.com/photos/random/?client_id=52p26noSNMPK_3Dz20blhMPpXcMqtiB2gAIppXnBMR4")
                .build()
            var response = client.newCall(request).enqueue( object: Callback{
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) throw IOException("unexpected code")
                        val moshi= Moshi.Builder().build()
                        val gistPhotoAdapter= moshi.adapter(UnsplashPhoto::class.java)
                        val gist = gistPhotoAdapter.fromJson(response.body!!.source())

                        println(gist!!.alt_description)
                        }
                }
            })
        })
        btn2 = findViewById(R.id.Button2)
        btn2.setOnClickListener(View.OnClickListener {

            var request= Request.Builder()
                .url("https:/youtube.com")
                .build()
            var response =client.newCall(request).execute()
            Log.d("AppName", response.body!!.string())
        })
    }


}