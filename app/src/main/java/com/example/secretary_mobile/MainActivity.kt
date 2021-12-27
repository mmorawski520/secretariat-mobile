package com.example.secretary_mobile

import android.content.Intent
import android.content.pm.ActivityInfo
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.secretary_mobile.databinding.ActivityMainBinding
import com.example.secretary_mobile.databinding.ActivityUploadBinding

class MainActivity : AppCompatActivity() {
    var select: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar

        actionBar!!.hide()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        var binding = ActivityMainBinding.inflate(getLayoutInflater());
        var view = binding.getRoot();
        setContentView(view)


        var Cursor: Cursor
        val db = DBHelper(this, null)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        try {
            select = intent.getStringExtra("dbData")
            Cursor = db.basicCustomSelect(select.toString())
            if (Cursor.getColumnIndex("groups") != -1) {
                binding.recyclerView.adapter = getStudents(Cursor)
            }
            if (Cursor.getColumnIndex("taught_subjects") != -1) {
                binding.recyclerView.adapter = getTeachers(Cursor)
            }
            if (Cursor.getColumnIndex("tenure") != -1) {
                binding.recyclerView.adapter = getEmployees(Cursor)
            }
        } catch (e: Throwable) {
            Cursor = db.basicCustomSelect("SELECT * FROM students")
            binding.recyclerView.adapter = getStudents(Cursor)
        }
        //Btn binding
        binding.btnUpload.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }
        binding.btnFilters.setOnClickListener {
            val intent = Intent(this, FiltersActivity::class.java)
            startActivity(intent)
        }
    }

    fun getStudents(selectStudents: Cursor?): Adapter {

        val data = ArrayList<ItemsViewModel>()

        if (selectStudents!!.moveToFirst()) {
            while (selectStudents.moveToNext()) {
                try {
                    data.add(
                        ItemsViewModel(
                            selectStudents.getString(selectStudents.getColumnIndex("id")),
                            selectStudents.getString(selectStudents.getColumnIndex("first_name")),
                            selectStudents.getString(selectStudents.getColumnIndex("second_name")),
                            selectStudents.getString(selectStudents.getColumnIndex("last_name")),
                            selectStudents.getString(selectStudents.getColumnIndex("maiden_name")),
                            selectStudents.getString(selectStudents.getColumnIndex("fathers_name")),
                            selectStudents.getString(selectStudents.getColumnIndex("mothers_name")),
                            selectStudents.getString(selectStudents.getColumnIndex("birth_date")),
                            selectStudents.getString(selectStudents.getColumnIndex("pesel")),
                            selectStudents.getString(selectStudents.getColumnIndex("image_path")),
                            selectStudents.getString(selectStudents.getColumnIndex("gender")),
                            selectStudents.getString(selectStudents.getColumnIndex("current_class")),
                            selectStudents.getString(selectStudents.getColumnIndex("groups")),
                            "",
                            ""
                        )
                    )
                } catch (e: Throwable) {
                }
            }
        }
        return Adapter(data)
    }

    fun getEmployees(selectEmloyees: Cursor?): Adapter {

        val data = ArrayList<ItemsViewModel>()

        if (selectEmloyees!!.moveToFirst()) {
            while (selectEmloyees.moveToNext()) {
                try {
                    data.add(
                        ItemsViewModel(

                            selectEmloyees.getString(selectEmloyees.getColumnIndex("id")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("first_name")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("second_name")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("last_name")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("maiden_name")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("fathers_name")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("mothers_name")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("birth_date")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("pesel")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("image_path")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("gender")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("date_of_employment")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("job_description")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("job_position")),
                            selectEmloyees.getString(selectEmloyees.getColumnIndex("tenure"))
                        )
                    )
                } catch (e: Throwable) {
                }
            }
        }
        return Adapter(data)
    }

    fun getTeachers(getTeachers: Cursor?): Adapter {

        val data = ArrayList<ItemsViewModel>()

        if (getTeachers!!.moveToFirst()) {
            while (getTeachers.moveToNext()) {
                try {
                    data.add(
                        ItemsViewModel(
                            getTeachers.getString(getTeachers.getColumnIndex("id")),
                            getTeachers.getString(getTeachers.getColumnIndex("first_name")),
                            getTeachers.getString(getTeachers.getColumnIndex("second_name")),
                            getTeachers.getString(getTeachers.getColumnIndex("last_name")),
                            getTeachers.getString(getTeachers.getColumnIndex("maiden_name")),
                            getTeachers.getString(getTeachers.getColumnIndex("fathers_name")),
                            getTeachers.getString(getTeachers.getColumnIndex("mothers_name")),
                            getTeachers.getString(getTeachers.getColumnIndex("birth_date")),
                            getTeachers.getString(getTeachers.getColumnIndex("pesel")),
                            getTeachers.getString(getTeachers.getColumnIndex("image_path")),
                            getTeachers.getString(getTeachers.getColumnIndex("gender")),
                            getTeachers.getString(getTeachers.getColumnIndex("date_of_employment")),
                            getTeachers.getString(getTeachers.getColumnIndex("class_tutor")),
                            getTeachers.getString(getTeachers.getColumnIndex("taught_subjects")),
                            ""
                        )
                    )
                } catch (e: Throwable) {
                }
            }
        }
        return Adapter(data)
    }
}