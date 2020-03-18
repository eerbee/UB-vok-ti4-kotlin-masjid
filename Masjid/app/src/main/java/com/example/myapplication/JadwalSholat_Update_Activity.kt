package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_jadwal_sholat__update.*
import org.json.JSONArray

class JadwalSholat_Update_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jadwal_sholat__update)

        btn_updatejadwal.setOnClickListener{

            var data_dhuha :String = input_dhuha.text.toString()
            var data_shubuh :String = input_shubuh.text.toString()
            var data_dhuhur :String = input_dhuhur.text.toString()
            var data_ashar :String = input_ashar.text.toString()
            var data_maghrib :String = input_maghrib.text.toString()
            var data_isha :String = input_isya.text.toString()

            postkeserver(data_dhuha, data_shubuh, data_dhuhur, data_ashar, data_maghrib, data_isha)

            finish()
            overridePendingTransition( 0, 0)
            startActivity(intent)
            overridePendingTransition( 0, 0)
        }

    }

    fun postkeserver(data1:String, data2:String, data3:String, data4:String, data5:String, data6:String)
    {
        AndroidNetworking.post("http://192.168.100.63/masjid/update_jadwal_masjid.php")
            .addBodyParameter("dhuha", data1)
            .addBodyParameter("shubuh", data2)
            .addBodyParameter("dhuhur", data3)
            .addBodyParameter("ashar", data4)
            .addBodyParameter("maghrib", data5)
            .addBodyParameter("isha", data6)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener
            {
                override fun onResponse(response: JSONArray)
                {

                }

                override fun onError(anError: ANError?)
                {

                }
            })
    }

    override fun onBackPressed()
    {
        val intent = Intent(this,JadwalSholat_Activity::class.java)
        startActivity(intent)
    }
}
