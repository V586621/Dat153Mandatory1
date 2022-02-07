package com.example.quizapp_oblig1.utils;

import com.example.quizapp_oblig1.classes.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomGenerator {

    private Student previousStudent;
    private List<Student> listOfStudents;
    private Student correctStudent;

    public RandomGenerator(List<Student> studentList){
        this.listOfStudents = studentList;

    }

    /**
     * @return list with three names, one correct name.
     */
    public List<Student> generateOptions(){

        List<Student> returnOptionsList = new ArrayList<>();
        returnOptionsList.add(this.correctStudent);

        int indexCorrect = listOfStudents.indexOf(this.correctStudent);
        int index2 = 0;
        int index3 = 0;

        if(indexCorrect == listOfStudents.size() - 1){
            index2 = indexCorrect - 1;
            index3 = indexCorrect - 2;
        }

        else if(indexCorrect == 0){
            index2 = indexCorrect + 1;
            index3 = indexCorrect + 2;
        } else {
            index2 = indexCorrect + 1;
            index3 = indexCorrect - 1;
        }

        Student option2 = listOfStudents.get(index2);
        Student option3 = listOfStudents.get(index3);
        returnOptionsList.add(option2);
        returnOptionsList.add(option3);

        Collections.shuffle(returnOptionsList);
        return returnOptionsList;


    }

    public Student generateCorrectStudent(){
        /**
         * Trying to minimize same student showing up multiple times in a row.
         */
        int index = generateRandomIndex();

        if(index == listOfStudents.indexOf(previousStudent)){
            index = generateRandomIndex();
        }

        this.correctStudent = listOfStudents.get(index);
        setPreviousStudent(correctStudent);
        this.correctStudent.setCorrectStudent(true);
        return this.correctStudent;

    }

    private int generateRandomIndex(){

        return (int) (Math.random() * ((listOfStudents.size())));

    }

    public List<Student> getListOfStudents() {
        return listOfStudents;
    }

    private void setPreviousStudent(Student s){
        this.previousStudent = s;
    }
    private Student getPreviousStudent(){
        return this.previousStudent;
    }
}