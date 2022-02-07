package com.example.quizapp_oblig1;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

import com.example.quizapp_oblig1.utils.RandomGenerator;
import com.example.quizapp_oblig1.classes.Student;
import com.example.quizapp_oblig1.dao.StudentDao;

public class QuizActivity extends AppCompatActivity {


    private Student correctStudent;
    private int currentScore = 0, questionsAttempted = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        View button = findViewById(R.id.nextbutton);
        onNext(button);


    }

    public void onNext(View v){
        StudentDao db = new StudentDao(QuizActivity.this);
        List<Student> listOfStudents = db.getAllStudents();

        RandomGenerator randomGenerator = new RandomGenerator(listOfStudents);

        ImageView imgview = findViewById(R.id.imageView);
        TextView text1 = findViewById(R.id.text1);
        TextView text2 = findViewById(R.id.text2);
        TextView text3 = findViewById(R.id.text3);

        View option1 = findViewById(R.id.option1);
        View option2 = findViewById(R.id.option2);
        View option3 = findViewById(R.id.option3);

        TextView result = findViewById(R.id.result);


        //  Resetting backgroundcolor on next question


        option1.setBackgroundColor(getResources().getColor(R.color.white));
        option2.setBackgroundColor(getResources().getColor(R.color.white));
        option3.setBackgroundColor(getResources().getColor(R.color.white));

        /**
         * Picks up a random student and puts a picture in a quiz
         */

        correctStudent = randomGenerator.generateCorrectStudent();
        List<Student> optionList = randomGenerator.generateOptions();
        imgview.setImageResource(correctStudent.getImage());

        /**
         * Shuffle the list to randomize the order of options
         */
        Collections.shuffle(listOfStudents);

        text1.setText(optionList.get(0).getName());
        text2.setText(optionList.get(1).getName());
        text3.setText(optionList.get(2).getName());

        onAnswer(v, text1, text2, text3, option1, option2, option3, result);



    }

    public void onAnswer(View v, TextView text1, TextView text2, TextView text3, View option1, View option2, View option3, TextView result){

        /**
         * onClickListenes to check which option the user has selected
         * changes background colors as the answer is correct or incorrect
         */

        text1.setOnClickListener(view -> {
            if(correctStudent.getName().toLowerCase().equals(text1.getText().toString().toLowerCase())){
                currentScore++;
                option1.setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                option1.setBackgroundColor(getResources().getColor(R.color.red));
            }
            questionsAttempted++;
            result.setText(currentScore + "/" + questionsAttempted);

        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(correctStudent.getName().equalsIgnoreCase(text2.getText().toString())){
                    currentScore++;
                    option2.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    option2.setBackgroundColor(getResources().getColor(R.color.red));
                }
                questionsAttempted++;
                result.setText(currentScore + "/" + questionsAttempted);
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(correctStudent.getName().toLowerCase().equals(text3.getText().toString().toLowerCase())){
                    currentScore++;
                    option3.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    option3.setBackgroundColor(getResources().getColor(R.color.red));
                }
                questionsAttempted++;
                result.setText(currentScore + "/" + questionsAttempted);
            }
        });




    }


}
