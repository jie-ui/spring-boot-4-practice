package com.jiecode.cruddemo.dao;

import com.jiecode.cruddemo.entity.Student;

import java.util.List;

//idetifin the model what can they do
public interface StudentDao {
    void save(Student theStudent);
    Student findById(Integer id);
    List<Student> findAll();
    void update(Student theStudent);
    int deleteAll();

    void delete(int studentId);
}
