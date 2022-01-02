package com.example.secretary_mobile

import android.Manifest
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.secretary_mobile.databinding.ActivityUploadBinding
import java.io.File

class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)


        val actionBar = supportActionBar
        actionBar!!.hide()

        var binding = ActivityUploadBinding.inflate(getLayoutInflater());
        var view = binding.getRoot();

        setContentView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val PERMISSION_REQUEST_CODE = 1
            if (ContextCompat.checkSelfPermission(
                    this@UploadActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this@UploadActivity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                } else {
                    ActivityCompat.requestPermissions(
                        this@UploadActivity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        PERMISSION_REQUEST_CODE
                    )
                }
            }
        }

        binding.btnMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.btnSubmit.setOnClickListener {
            val request =
                DownloadManager.Request(Uri.parse(binding.editTextPersonName.text.toString()))
                    .setTitle("data")
                    .setDescription("Downloading data...")
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setAllowedOverMetered(true)
                    .setDestinationInExternalPublicDir(
                        Environment.DIRECTORY_DOWNLOADS,
                        "dbData.txt"
                    )

            Toast.makeText(this, "File is being downlaoded ", Toast.LENGTH_SHORT).show()
            val dm = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            dm.enqueue(request)
            //1 second delay
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                loadData()
            }, 1000)
        }
    }

    fun loadData() {
        var columnCounter = 0
        val externalFile = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "dbData.txt"
        )

        val db = DBHelper(this, null)
        if (externalFile.exists()) {

            val text = externalFile.readText()
            val lines: List<String> = text.split("\n")

            for (line in lines) {
                for (c: Char in line) {
                    if (c == ',')
                        columnCounter++
                }
                if (columnCounter == 12) {
                    var collumns: List<String> = line.split(",")
                    db.addStudent(collumns)
                }
                if (columnCounter == 13) {
                    var collumns: List<String> = line.split(",")
                    db.addTeacher(collumns)
                }
                if (columnCounter == 14) {
                    var collumns: List<String> = line.split(",")
                    db.addEmployee(collumns)
                }
                columnCounter = 0
            }
            Toast.makeText(this, "Data has been uploaded", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(
                this,
                "Something wrong, probably your file doesn't exist :c",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}