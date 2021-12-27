package com.example.secretary_mobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.secretary_mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.hide()

        var binding = ActivityMainBinding.inflate(getLayoutInflater());
        var view = binding.getRoot();

        setContentView(view);

        binding.btnUpload.setOnClickListener{
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }
        binding.btnFilters.setOnClickListener{
            val intent = Intent(this, FiltersActivity::class.java)
            startActivity(intent)
        }
    // ActivityMainBinding binding;
    }
}