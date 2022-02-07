package com.example.quizapp_oblig1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.quizapp_oblig1.classes.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDao extends SQLiteOpenHelper {

    public static final String STUDENT_TABLE = "STUDENT_TABLE";

    public StudentDao(@Nullable Context context) {
        super(context, "student.db", null, 1);
    }

    /**
     * onCreate will be called the first time something is done to the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENT_NAME TEXT, STUDENT_IMG INTEGER)";
        db.execSQL(createTableStatement);

    }

    /**
     * Will be called if database version number changes
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("STUDENT_NAME", student.getName());
        cv.put("STUDENT_IMG", student.getImage());

        long insert = db.insert(STUDENT_TABLE,null , cv);
        if(insert == -1){
            return false;
        }
        return true;
    }

    public List<Student> getAllStudents(){

        List<Student> returnList = new ArrayList<>();

        // Gets data from the local database

        String query = "SELECT * FROM " + STUDENT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            // If happens will loop through the results and add to list.

            while(cursor.moveToNext()){
                int studentID = cursor.getInt(0);
                String studentName = cursor.getString(1);
                int studentImg = cursor.getInt(2);

                Student newStudent = new Student(studentID, studentName, studentImg);
                returnList.add(newStudent);
            }
        }
        cursor.close();
        db.close();
        return returnList;
    }

}
