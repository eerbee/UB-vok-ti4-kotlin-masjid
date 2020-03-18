package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toolbar
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas_masjid.btn_update
import kotlinx.android.synthetic.main.activity_pengumuman_masjid.*
import org.json.JSONArray
import org.json.JSONObject

class PengumumanMasjid_Activity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman_masjid)
        //to change title of activity
//        val actionBar = supportActionBar
//        if (actionBar != null) {
//            actionBar.title = "New Title"
//        }

        getDariServer()

        btn_update.setOnClickListener {
            var data_judul: String = edit_text.text.toString()
            var data_isi : String = edit_text2.text.toString()
            var data_aktif = "aktif"

            postKeServer(data_judul, data_isi, data_aktif)
            finish()
            overridePendingTransition( 0, 0)
            startActivity(intent)
            overridePendingTransition( 0, 0)
        }
        
    }

    fun getDariServer()
    {
        AndroidNetworking.get("http://192.168.100.63/masjid/pengumuman_masjid_json.php")
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
                        Log.e("_kotlinJudulPengmuman", jsonObject.optString("judul_pengumuman"))
                        Log.e("_kotlinIsiPengumuman", jsonObject.optString("isi_pengumuman"))

                        output_judul.text = jsonObject.optString("judul_pengumuman")
                        output_isi.text = jsonObject.optString("isi_pengumuman")
                    }
                }

                override fun onError(anError: ANError)
                {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postKeServer(data1:String, data2:String, data3:String)
    {
        AndroidNetworking.post("http://192.168.100.63/masjid/update_pengumuman_masjid.php")
            .addBodyParameter("judul_pengumuman", data1)
            .addBodyParameter("isi_pengumuman", data2)
            .addBodyParameter("aktif", data3)
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

    override fun onBackPressed()
    {
        val intent = Intent(this,HalamanUtama_Actvity::class.java)
        startActivity(intent)
    }
}
