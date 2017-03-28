package com.mylhyl.cygadapter.sample.entities;

/**
 * Created by hupei on 2016/7/13.
 */
public class Student implements Comparable<Student> {
    public int age;
    public String name;

    public Student() {
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Student another) {
        return another.age - age;//降序
    }
}
