package com.example.secretary_mobile

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "school", factory, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(queryCreateStudentsTable)
        db.execSQL(queryCreateEmployeesTable)
        db.execSQL(queryCreateTeachersTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS students")
        db.execSQL("DROP TABLE IF EXISTS teachers")
        db.execSQL("DROP TABLE IF EXISTS employees")
        onCreate(db)
    }

    fun addStudent(collumns: List<String>) {
        val values = ContentValues()
        val db = this.writableDatabase

        values.put("first_name", collumns[1])
        values.put("second_name", collumns[2])
        values.put("last_name", collumns[3])
        values.put("maiden_name", collumns[4])
        values.put("fathers_name", collumns[5])
        values.put("mothers_name", collumns[6])
        values.put("birth_date", collumns[7])
        values.put("pesel", collumns[8])
        values.put("image_path", collumns[9])
        values.put("gender", collumns[10])
        values.put("current_class", collumns[11])
        values.put("groups", collumns[12])

        db.insert("students", null, values)
    }

    fun addTeachers(collumns: List<String>) {
        val values = ContentValues()
        val db = this.writableDatabase

        values.put("first_name", collumns[1])
        values.put("second_name", collumns[2])
        values.put("last_name", collumns[3])
        values.put("maiden_name", collumns[4])
        values.put("fathers_name", collumns[5])
        values.put("mothers_name", collumns[6])
        values.put("birth_date", collumns[7])
        values.put("pesel", collumns[8])
        values.put("image_path", collumns[9])
        values.put("gender", collumns[10])
        values.put("class_tutor", collumns[11])
        values.put("taught_subjects", collumns[12])
        values.put("date_of_employment", collumns[13])

        db.insert("teachers", null, values)
    }

    fun addEmployee(collumns: List<String>) {
        val values = ContentValues()
        val db = this.writableDatabase

        values.put("first_name", collumns[1])
        values.put("second_name", collumns[2])
        values.put("last_name", collumns[3])
        values.put("maiden_name", collumns[4])
        values.put("fathers_name", collumns[5])
        values.put("mothers_name", collumns[6])
        values.put("birth_date", collumns[7])
        values.put("pesel", collumns[8])
        values.put("image_path", collumns[9])
        values.put("gender", collumns[10])
        values.put("date_of_employment", collumns[11])
        values.put("job_description", collumns[12])
        values.put("job_position", collumns[13])
        values.put("tenure", collumns[14])
        db.insert("employees", null, values)
    }

    fun basicCustomSelect(sql: String): Cursor {
        val db = this.readableDatabase
        return db.rawQuery(sql, null)
    }

    fun selectStudents(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * from students", null)
    }

    fun deleteStudents() {
        val db = this.writableDatabase
        db.rawQuery("DELETE FROM students", null)
    }

    val queryCreateStudentsTable = ("CREATE TABLE students (" +
            "id INTEGER NOT NULL," +
            "first_name TEXT," +
            "second_name TEXT," +
            "last_name TEXT," +
            "maiden_name TEXT," +
            "fathers_name TEXT," +
            "mothers_name TEXT," +
            "birth_date TEXT," +
            "pesel TEXT," +
            "image_path TEXT," +
            "gender TEXT," +
            "current_class TEXT," +
            "groups TEXT," +
            "PRIMARY KEY(id AUTOINCREMENT)" +
            ")")

    val queryCreateEmployeesTable = ("CREATE TABLE employees(" +
            "id INTEGER NOT NULL," +
            "first_name TEXT," +
            "second_name TEXT," +
            "last_name TEXT," +
            "maiden_name TEXT," +
            "fathers_name TEXT," +
            "mothers_name TEXT," +
            "birth_date TEXT," +
            "pesel TEXT," +
            "image_path TEXT," +
            "gender TEXT," +
            "date_of_employment TEXT," +
            "job_description TEXT," +
            "job_position TEXT," +
            "tenure TEXT," +
            "PRIMARY KEY(id AUTOINCREMENT)" +
            ")")

    val queryCreateTeachersTable = ("CREATE TABLE teachers (" +
            "id INTEGER NOT NULL," +
            "first_name TEXT, " +
            "second_name TEXT," +
            "last_name TEXT," +
            "maiden_name TEXT," +
            "fathers_name TEXT," +
            "mothers_name TEXT," +
            "birth_date TEXT," +
            "pesel TEXT," +
            "image_path TEXT," +
            "gender TEXT," +
            "date_of_employment TEXT," +
            "class_tutor TEXT," +
            "taught_subjects TEXT," +
            "PRIMARY KEY(id AUTOINCREMENT)" +
            ")")
}