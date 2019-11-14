package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class University {

    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        Student st = null;
        for (Student s: students){
            if(s.getAverageGrade() == averageGrade) st = s;
        }
        return st;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student stMax = null;
        List<Double> max = new ArrayList<>();
        for (Student s: students){
            max.add(s.getAverageGrade());
        }
        Collections.sort(max);
        double maxAg = max.get(max.size()-1);
        for (Student s: students){
            if(s.getAverageGrade() == maxAg) stMax = s;
        }
        return stMax;
    }

    public Student getStudentWithMinAverageGrade(){
        Student stMin = null;
        List<Double> min = new ArrayList<>();
        for (Student s: students){
            min.add(s.getAverageGrade());
        }
        Collections.sort(min);
        double minAg = min.get(0);
        for (Student s: students){
            if(s.getAverageGrade() == minAg) stMin = s;
        }
        return stMin;
    }

    public void expel(Student student){
        students.remove(student);
    }


}