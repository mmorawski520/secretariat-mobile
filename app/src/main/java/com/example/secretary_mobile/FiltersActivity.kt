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
        "image_path",
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
        btnDataYoungerPicker = findViewById<Button>(R.id.btnPickYoungerDate)
        btnDataOlderPicker = findViewById<Button>(R.id.btnPickOlderDate)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val spinnerOrderByAscDesc = findViewById<Spinner>(R.id.spinnerOrderByAscDesc)
        val spinnerOrderByField = findViewById<Spinner>(R.id.spinnerOrderByField)
        val spinnerSearchField = findViewById<Spinner>(R.id.spinnerSearchField)
        val editTextField = findViewById<EditText>(R.id.editTextSearch)
        val actionBar = supportActionBar
        actionBar!!.hide()
        btnDataYoungerPicker.setOnClickListener {
            clickDataPickerYoungerThan(binding.getRoot())
            /* btnDataYoungerPicker.text=youngerThan*/
        }
        btnDataOlderPicker.setOnClickListener {
            clickDataPickerOlderThan(binding.getRoot())
            //  btnDataOlderPicker.text=olderThan
        }
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

            if (youngerThan == "" && olderThan == "") {
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

            }
            if (youngerThan != "" && olderThan == "") {
                if (editTextField.text.toString().trim().isNotEmpty()) {
                    if (spinnerSearchField.selectedItem.toString() != null && spinnerSearchField.selectedItem.toString() != "") {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('"+youngerThan+"',7,4) || substr('"+youngerThan+"',4,2) || substr('"+youngerThan+"',1,2) ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                        } else {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('"+youngerThan+"',7,4) || substr('"+youngerThan+"',4,2) || substr('"+youngerThan+"',1,2) ";
                        }
                    } else {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) < substr('"+youngerThan+"',7,4) || substr('"+youngerThan+"' ,4,2) || substr('"+youngerThan+"',1,2)  ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                        } else {
                            select = "SELECT * FROM " + curTable+" WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) < substr('"+youngerThan+"',7,4) || substr('"+youngerThan+"',4,2) || substr('"+youngerThan+"',1,2) ";
                        }
                    }
                } else {
                    if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                        select =
                            "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) < substr('"+youngerThan+"',7,4) || substr('"+youngerThan+"',4,2) || substr('"+youngerThan+"',1,2)  ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();

                    } else {
                        select = "SELECT * FROM " + curTable+" WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) < substr('"+youngerThan+"',7,4) || substr('"+youngerThan+"',4,2) || substr('"+youngerThan+"',1,2) ";
                    }
                }
            }
            if (youngerThan == "" && olderThan != "") {
                if (editTextField.text.toString().trim().isNotEmpty()) {
                    if (spinnerSearchField.selectedItem.toString() != null && spinnerSearchField.selectedItem.toString() != "") {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"',4,2) || substr('"+olderThan+"',1,2) ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();

                        } else {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"',4,2) || substr('"+olderThan+"',1,2) ";
                        }
                    } else {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"' ,4,2) || substr('"+olderThan+"',1,2)  ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                        } else {
                            select = "SELECT * FROM " + curTable+" WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"',4,2) || substr('"+olderThan+"',1,2) ";
                        }
                    }
                } else {
                    if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                        select =
                            "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"',4,2) || substr('"+olderThan+"',1,2)  ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();

                    } else {
                        select = "SELECT * FROM " + curTable+" WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"',4,2) || substr('"+olderThan+"',1,2) ";
                    }
                }
            }
            if (youngerThan != "" && olderThan != "") {
                if (editTextField.text.toString().trim().isNotEmpty()) {
                    if (spinnerSearchField.selectedItem.toString() != null && spinnerSearchField.selectedItem.toString() != "") {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"',4,2) || substr('"+olderThan+"',1,2) ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();

                        } else {
                            select =
                                "SELECT * FROM " + curTable + " WHERE " + spinnerSearchField.selectedItem.toString() + " LIKE '%" + editTextField.text.toString() + "%' AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"',4,2) || substr('"+olderThan+"',1,2) ";
                        }
                    } else {
                        if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                            select =
                                "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"' ,4,2) || substr('"+olderThan+"',1,2) AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('"+youngerThan+"',7,4) || substr('"+youngerThan+"',4,2) || substr('"+youngerThan+"',1,2) ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();
                        } else {
                            select = "SELECT * FROM " + curTable+" WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"',4,2) || substr('"+olderThan+"',1,2) AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('"+youngerThan+"',7,4) || substr('"+youngerThan+"',4,2) || substr('"+youngerThan+"',1,2)";
                        }
                    }
                } else {
                    if ((spinnerOrderByAscDesc.selectedItem.toString() != "" && spinnerOrderByAscDesc.selectedItem.toString() != null) && (spinnerOrderByField.selectedItem.toString() != "" && spinnerOrderByField.selectedItem.toString() != null)) {
                        select =
                            "SELECT * FROM " + curTable + " WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"',4,2) || substr('"+olderThan+"',1,2) AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('"+youngerThan+"',7,4) || substr('"+youngerThan+"',4,2) || substr('"+youngerThan+"',1,2) ORDER BY " + spinnerOrderByField.selectedItem.toString() + " " + spinnerOrderByAscDesc.selectedItem.toString();

                    } else {
                        select = "SELECT * FROM " + curTable+" WHERE  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birth_date,1,2) > substr('"+olderThan+"',7,4) || substr('"+olderThan+"',4,2) || substr('"+olderThan+"',1,2) AND  substr(birth_date,7,4) || substr(birth_date,4,2) || substr(birh_date,1,2) < substr('"+youngerThan+"',7,4) || substr('"+youngerThan+"',4,2) || substr('"+youngerThan+"',1,2) ";
                    }
                }
            }
            val intent = Intent(this@FiltersActivity, MainActivity::class.java)
            intent.putExtra("dbData", select)
            startActivity(intent)
        }




    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDataPickerYoungerThan(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


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
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


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