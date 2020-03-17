package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_jadwal_sholat.*
import org.json.JSONArray
import org.json.JSONObject

class JadwalSholat_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_sholat)
        this.title = "Jadwal Sholat"

        getDariServer()

        btn_update_jadwal.setOnClickListener {
            startActivity(Intent(this, JadwalSholat_Update_Activity::class.java))
        }
    }

    fun getDariServer()
    {
        AndroidNetworking.get("http://192.168.100.63/masjid/contoh_jadwal_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener
            {
                override fun onResponse(response: JSONObject)
                {
                    Log.e("_kotlinResponce", response.toString())

                    val jsonArray : JSONArray = response.getJSONArray("result")
                    for (i: Int in 0 until jsonArray.length())
                    {
                        val jsonObject : JSONObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinShubuh", jsonObject.optString("shubuh"))
                        Log.e("_kotlinDhuha", jsonObject.optString("dhuha"))
                        Log.e("_kotlinDhuhur", jsonObject.optString("dhuhur"))
                        Log.e("_kotlinAshar", jsonObject.optString("ashar"))
                        Log.e("_kotlinMaghrib", jsonObject.optString("maghrib"))
                        Log.e("_kotlinIsya", jsonObject.optString("isya"))

                        txt_data_shubuh.setText(jsonObject.optString("shubuh"))
                        txt_data_dhuha.setText(jsonObject.optString("dhuha"))
                        txt_data_dhuhur.setText(jsonObject.optString("dhuhur"))
                        txt_data_ashar.setText(jsonObject.optString("ashar"))
                        txt_data_maghrib.setText(jsonObject.optString("maghrib"))
                        txt_data_isya.setText(jsonObject.optString("isya"))
                    }
                }

                override fun onError(anError: ANError)
                {
                    Log.i("_err", anError.toString())
                }
            })
    }

    override fun onBackPressed()
    {
        val intent = Intent(this,HalamanUtama_Actvity::class.java)
        startActivity(intent)
    }
}
