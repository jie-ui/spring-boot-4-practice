package com.Jiecode.cruddemo.dao;

import com.Jiecode.cruddemo.entity.Course;
import com.Jiecode.cruddemo.entity.Instructor;
import com.Jiecode.cruddemo.entity.InstructorDetail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);



    //one to many update Instructor
    void update(Instructor theInstructor);

    //update Course
    void update (Course theCourse);
    Course findCourseById(int theId);

    void deleteCourseById(int theId);


}