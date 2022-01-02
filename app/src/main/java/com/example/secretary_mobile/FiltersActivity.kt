package com.example.secretary_mobile

import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.secretary_mobile.databinding.ActivityFiltersBinding
import com.example.secretary_mobile.databinding.ActivityMainBinding
import java.util.*

class FiltersActivity : AppCompatActivity() {

    var curTable = "students";
    var arrayOrderByAscDesc = arrayOf<String>("ASC", "DESC")
    var arrayOfFields = arrayOf<String>(
        "id",
        "first_name",
        "second_name",
        "last_name",
        "maiden_name",
        "fathers_name",
        "mothers_name",
        "birth_date",
        "pesel",
        "gender",
        "current_class",
        "groups"
    )
    var youngerThan: String? = ""
    var olderThan: String? = ""
    var select: String = ""
    lateinit var btnDataYoungerPicker: Button
    lateinit var btnDataOlderPicker: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_filters)


        var binding = ActivityFiltersBinding.inflate(getLayoutInflater());
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val spinnerOrderByAscDesc = findViewById<Spinner>(R.id.spinnerOrderByAscDesc)
        val spinnerOrderByField = findViewById<Spinner>(R.id.spinnerOrderByField)
        val spinnerSearchField = findViewById<Spinner>(R.id.spinnerSearchField)
        val editTextField = findViewById<EditText>(R.id.editTextSearch)
        val actionBar = supportActionBar

        actionBar!!.hide()
        btnDataYoungerPicker = findViewById<Button>(R.id.btnPickYoungerDate)
        btnDataOlderPicker = findViewById<Button>(R.id.btnPickOlderDate)

        btnDataYoungerPicker.setOnClickListener {
            clickDataPickerYoungerThan(binding.getRoot())
        }
        btnDataOlderPicker.setOnClickListener {
            clickDataPickerOlderThan(binding.getRoot())
        }

        //Adapters
        var adapterOrderByAscDesc =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOrderByAscDesc)
        var adapterArrayOfFields =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOfFields)

        //initialization of spinners
        spinnerOrderByAscDesc.adapter = adapterOrderByAscDesc

        fun refreshSpinners(){
            adapterArrayOfFields =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOfFields)
            spinnerOrderByField.adapter = adapterArrayOfFields
            spinnerSearchField.adapter = adapterArrayOfFields
        }
        refreshSpinners()
        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                var radio: RadioButton = findViewById(checkedId)
                if (radio.text == "students") {
                    curTable = "students"
                    arrayOfFields = arrayOf<String>(
                        "id",
                        "first_name",
                        "second_name",
                        "last_name",
                        "maiden_name",
                        "fathers_name",
                        "mothers_name",
                        "birth_date",
                        "pesel",
                        "gender",
                        "current_class",
                        "groups"
                    )
                    refreshSpinners()
                }
                if (radio.text == "employees") {
                    curTable = "employees"
                    arrayOfFields = arrayOf<String>(
                        "id",
                        "first_name",
                        "second_name",
                        "last_name",
                        "maiden_name",
                        "fathers_name",
                        "mothers_name",
                        "birth_date",
                        "pesel",
                        "gender",
                        "date_of_employment",
                        "job_description",
                        "job_position",
                        "tenure"
                    )
                    refreshSpinners()
                }
                if (radio.text == "teachers") {
                    curTable = "teachers"
                    arrayOfFields = arrayOf<String>(
                        "id",
                        "first_name",
                        "second_name",
                        "last_name",
                        "maiden_name",
                        "fathers_name",
                        "mothers_name",
                        "birth_date",
                        "pesel",
                        "gender",
                        "taught_subjects",
                        "tutor",
                        "date_of_employment"
                    )
                    refreshSpinners()
                }
            })

        btnSearch.setOnClickListener {
            select = Selection.chooseSelection(
                youngerThan,
                olderThan,
                editTextField,
                spinnerSearchField,
                spinnerOrderByAscDesc,
                curTable,
                spinnerOrderByField
            )
            val intent = Intent(this@FiltersActivity, MainActivity::class.java)
            intent.putExtra("dbData", select)
            startActivity(intent)
        }

    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDataPickerYoungerThan(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                youngerThan = "$dayOfMonth.${monthOfYear + 1}.$year 00:00:00"
                Toast.makeText(
                    this,
                    "Selected date $dayOfMonth.${monthOfYear + 1}.$year",
                    Toast.LENGTH_SHORT
                ).show()
                btnDataYoungerPicker.text = "$dayOfMonth.${monthOfYear + 1}.$year"
            },
            year,
            month,
            day
        )
        dpd.show()
    }

    fun clickDataPickerOlderThan(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                olderThan = "$dayOfMonth.${monthOfYear + 1}.$year 00:00:00"
                Toast.makeText(
                    this,
                    "Selected date $dayOfMonth.${monthOfYear + 1}.$year",
                    Toast.LENGTH_SHORT
                ).show()
                btnDataOlderPicker.text = "$dayOfMonth.${monthOfYear + 1}.$year"
            },
            year,
            month,
            day
        )
        dpd.show()
    }
}