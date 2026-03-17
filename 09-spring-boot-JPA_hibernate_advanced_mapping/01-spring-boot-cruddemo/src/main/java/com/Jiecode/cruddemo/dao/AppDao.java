package com.Jiecode.cruddemo.dao;

import com.Jiecode.cruddemo.entity.Instructor;

public interface AppDao {

    void save(Instructor theInstructor);
    Instructor findInstructorById(int theid);

    Instructor deleteInstructorById(int theid);

}
