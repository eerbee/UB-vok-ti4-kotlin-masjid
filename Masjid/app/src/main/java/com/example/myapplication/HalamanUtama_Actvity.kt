package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_halaman_utama.*


class HalamanUtama_Actvity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_utama)
        setSupportActionBar(toolbar as Toolbar)
        title = "Halaman Utama"

        card_identitas_masjid.setOnClickListener()
        {
            val intent = Intent(this, IdentitasMasjid_Activity::class.java)
            startActivity(intent)
        }

        card_jadwal_sholat.setOnClickListener()
        {
            val intent = Intent(this, JadwalSholat_Activity::class.java)
            startActivity(intent)
        }

        card_pengumuman_masjid.setOnClickListener()
        {
            val intent = Intent(this, PengumumanMasjid_Activity::class.java)
            startActivity(intent)
        }

        card_marquee.setOnClickListener()
        {
            val intent = Intent(this, MarqueeMasjid_Activity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.example_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when (item.itemId)
        {
            R.id.item_logout ->
            {
                val sharedPreferences = getSharedPreferences("CEKLOGIN", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("STATUS", "0")
                editor.apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}

