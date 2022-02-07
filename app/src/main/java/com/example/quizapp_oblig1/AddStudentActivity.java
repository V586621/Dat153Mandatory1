package com.example.quizapp_oblig1;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import com.example.quizapp_oblig1.classes.Student;
import com.example.quizapp_oblig1.dao.StudentDao;

public class AddStudentActivity extends AppCompatActivity {

    /*
       Method that sets a new view when creating the class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);


        Button addPicture = findViewById(R.id.addImage);
        Button addEntry = findViewById(R.id.addEntryButton);
        EditText nameInput = findViewById(R.id.nameInput);

        /**
         * Method to set the image selected in the gallery
         */
        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        // Handle the returned Uri
                        ImageView imageView = findViewById(R.id.imageView);
                        imageView.setImageURI(uri);

                    }
                });



        addPicture.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        }));
        ImageView imageView = findViewById(R.id.imageView);


        /**
         * onClickListener to add new student (Save entry)
         */

        addEntry.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString();
                int picture = R.drawable.default_pic;

                Student s = new Student(name, picture);
                StudentDao databaseHelper = new StudentDao(AddStudentActivity.this);

                databaseHelper.addStudent(s);

                startActivity(new Intent(AddStudentActivity.this, MainActivity.class));

            }
        }));


    }

    }


