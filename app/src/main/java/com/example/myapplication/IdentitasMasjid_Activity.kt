package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_halaman_utama.*
import kotlinx.android.synthetic.main.activity_halaman_utama.toolbar
import kotlinx.android.synthetic.main.activity_identitas_masjid.*
import org.json.JSONArray
import org.json.JSONObject

class IdentitasMasjid_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas_masjid)
//        setSupportActionBar(toolbar as Toolbar)
        this.title = "Identitas Masjid"

        getDariServer()

        btn_update.setOnClickListener {
            var data_namamasjid : String = editText.text.toString();
            var data_alamatmasjid : String = editText2.text.toString();

            postKeServer(data_namamasjid, data_alamatmasjid)
            finish()
            overridePendingTransition( 0, 0)
            startActivity(intent)
            overridePendingTransition( 0, 0)
        }
    }

    fun getDariServer()
    {
        AndroidNetworking.get("http://192.168.100.63/masjid/identitas_masjid_json.php")
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
                        Log.e("_kotlinNamaMasjid", jsonObject.optString("nama_masjid"))
                        Log.e("_kotlinAlamatMasjid", jsonObject.optString("alamat_masjid"))

                        textView.text = jsonObject.optString("nama_masjid")
                        textView2.text = jsonObject.optString("alamat_masjid")
                    }
                }

                override fun onError(anError: ANError)
                {
                    Log.i("_err", anError.toString())
                }
            })
    }

    fun postKeServer(data1:String, data2:String)
    {
        AndroidNetworking.post("http://192.168.100.63/masjid/update_identitas_masjid.php")
            .addBodyParameter("nama_masjid", data1)
            .addBodyParameter("alamat_masjid", data2)
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
