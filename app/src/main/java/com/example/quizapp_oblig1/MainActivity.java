package com.example.quizapp_oblig1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;

import com.example.quizapp_oblig1.classes.Student;
import com.example.quizapp_oblig1.dao.StudentDao;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
       Retrieves all the buttons in main-actitivy to send the user
       for new activity
         */
        Button addButton = findViewById(R.id.addButton);
        Button quizButton = findViewById(R.id.quizbutton);
        Button databaseButton = findViewById(R.id.databaseButton);

        addButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddStudentActivity.class)));
        quizButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, QuizActivity.class)));
        databaseButton.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DatabaseActivity.class)));

        //Creates new students (cats) for the quiz app to use

        StudentDao db = new StudentDao(MainActivity.this);

        Student cat1 = new Student("White Cat", R.drawable.whitecat);

        Student cat2 = new Student("Black Cat", R.drawable.blackcat);

        Student cat3 = new Student("Ginger Cat", R.drawable.gingercat);

        db.addStudent(cat1);

        db.addStudent(cat2);

        db.addStudent(cat3);

    }


    }