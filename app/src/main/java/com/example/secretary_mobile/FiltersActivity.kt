package com.example.secretary_mobile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import com.example.secretary_mobile.databinding.ActivityFiltersBinding

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
        "image_path",
        "gender",
        "current_class",
        "groups"
    )
    var select: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_filters)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val spinnerOrderByAscDesc = findViewById<Spinner>(R.id.spinnerOrderByAscDesc)
        val spinnerOrderByField = findViewById<Spinner>(R.id.spinnerOrderByField)
        val spinnerSearchField = findViewById<Spinner>(R.id.spinnerSearchField)
        val editTextField = findViewById<EditText>(R.id.editTextSearch)
        val actionBar = supportActionBar
        actionBar!!.hide()
        //Adapters
        var adapterOrderByAscDesc =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOrderByAscDesc)
        var adapterArrayOfFields =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOfFields)

        //initialization of spinners
        spinnerOrderByAscDesc.adapter = adapterOrderByAscDesc
        spinnerOrderByField.adapter = adapterArrayOfFields
        spinnerSearchField.adapter = adapterArrayOfFields

        radioGroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                var radio: RadioButton = findViewById(checkedId)
                if (radio.text == "students") {
                    //  getStudents()
                    curTable = "students"
                }
                if (radio.text == "employees") {
                    //     getEmployees()
                    curTable = "employees"
                }
                if (radio.text == "teachers") {
                    //  getTeachers()
                    curTable = "teachers"
                }
            })

        btnSearch.setOnClickListener {
            if (editTextField.text.toString().trim().isNotEmpty()) {
                if (spinnerSearchField.selectedItem.toString() != null && spinnerSearchField.selectedItem.toString() != "") {
                    if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                        select =
                            "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                    } else {
                        select =
                            "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%'";
                    }
                } else {
                    if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                        select =
                            "SELECT * FROM " + curTable + " ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                    } else {
                        select = "SELECT * FROM " + curTable;
                    }
                }
            } else {
                if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                    select =
                        "SELECT * FROM " + curTable + " ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                } else {
                    select = "SELECT * FROM " + curTable;
                }
            }
            val intent = Intent(this@FiltersActivity, MainActivity::class.java)
            intent.putExtra("dbData", select)
            startActivity(intent)
        }
    }
}