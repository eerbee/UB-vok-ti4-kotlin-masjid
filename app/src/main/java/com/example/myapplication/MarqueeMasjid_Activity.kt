package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas_masjid.*
import kotlinx.android.synthetic.main.activity_marquee_masjid.*
import kotlinx.android.synthetic.main.activity_marquee_masjid.btn_update
import org.json.JSONArray
import org.json.JSONObject

class MarqueeMasjid_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marquee_masjid)
        this.title = "Marquee Masjid"

        getServer()

        btn_add.setOnClickListener {
            val isi = input_isi.text.toString()
            Log.i("proses_add", isi)
            postServer(isi)

            finish()
            overridePendingTransition( 0, 0)
            startActivity(intent)
            overridePendingTransition( 0, 0)
        }

        btn_update.setOnClickListener {
            var isi_marquee : String = input_isi.text.toString();

            updateData(isi_marquee)
            finish()
            overridePendingTransition( 0, 0)
            startActivity(intent)
            overridePendingTransition( 0, 0)
        }
    }

    fun postServer(data1: String)
    {
        Log.i("proses_add", data1)
        AndroidNetworking.post("http://192.168.100.63/masjid/add_marquee.php")
            .addBodyParameter("isi_marquee", data1)
            .setPriority(Priority.MEDIUM).build()
            .getAsJSONArray(object : JSONArrayRequestListener
            {
                override fun onResponse(response: JSONArray)
                {

                }
                override fun onError(anError: ANError?)
                {
                    Log.i("_err", anError.toString())
                }
            })
    }

    @SuppressLint("WrongConstant")
    fun getServer()
    {
        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val slides = ArrayList<Marquee>()
        AndroidNetworking.get("http://192.168.100.63/masjid/marquee_json.php")
            .setPriority(Priority.MEDIUM).build().getAsJSONObject(object :
                JSONObjectRequestListener
            {
                override fun onResponse(response: JSONObject) {
                    Log.e("kotlinResponse", response.toString())
                    val jsonArray: JSONArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                        var isi1 = jsonObject.optString("isi_marquee").toString()
                        slides.add(Marquee("$isi1"))
                    }
                    val adapter = MarqueeAdapter(slides)
                    recyclerView.adapter = adapter
                }
                override fun onError(anError: ANError?)
                {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun updateData(data1:String)
    {
        AndroidNetworking.post("http://192.168.100.63/masjid/update_marquee.php")
            .addBodyParameter("isi_marquee", data1)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener
            {
                override fun onResponse(response: JSONArray)
                {

                }

                override fun onError(anError: ANError)
                {
                    Log.i("_err", anError.toString())
                }
            })
    }
}
