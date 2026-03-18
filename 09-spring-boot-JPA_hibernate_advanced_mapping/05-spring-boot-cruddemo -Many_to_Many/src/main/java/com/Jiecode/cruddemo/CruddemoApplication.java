package com.Jiecode.cruddemo;

import com.Jiecode.cruddemo.dao.AppDAO;
import com.Jiecode.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO) {

        return runner -> {

            //createCourseAndStudents(appDAO);
            //findCourseAndStudents(appDAO);
            //findStudentAndCourses(appDAO);
            //addMoreCoursesForStudent(appDAO);
            //deleteCourse(appDAO);
            deleteStudent(appDAO);
            
             


        };
    }

    private void deleteStudent(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Deleting student id: " + theId);

        appDAO.deleteStudentById(theId);

        System.out.println("Done!");
    }



    private void addMoreCoursesForStudent(AppDAO appDAO) {

        int theId = 2;
        Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

        // create more courses
        Course tempCourse1 = new Course("Rubik's Cube - How to Speed Cube");
        Course tempCourse2 = new Course("Atari 2600 - Game Development");

        // add courses to student
        tempStudent.addCourse(tempCourse1);
        tempStudent.addCourse(tempCourse2);

        System.out.println("Updating student: " + tempStudent);
        System.out.println("associated courses: " + tempStudent.getCourses());

        appDAO.update(tempStudent);

        System.out.println("Done!");
    }

    private void findStudentAndCourses(AppDAO appDAO) {


            int theId = 2;
            Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);

            System.out.println("Loaded student: " + tempStudent);
            System.out.println("Courses: " + tempStudent.getCourses());

            System.out.println("Done!");

    }

    private void findCourseAndStudents(AppDAO appDAO) {
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);

        System.out.println("Loaded course: " + tempCourse);
        System.out.println("Students: " + tempCourse.getStudents());

        System.out.println("Done!");
    }

    private void createCourseAndStudents(AppDAO appDAO) {

        // create a course
        Course tempCourse = new Course("Pacman - How To Score One Million Points");

        // create the students
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");

        // add students to the course
        tempCourse.addStudent(tempStudent1);
        tempCourse.addStudent(tempStudent2);

        // save the course and associated students
        System.out.println("Saving the course: " + tempCourse);
        System.out.println("associated students: " + tempCourse.getStudents());

        appDAO.save(tempCourse);

        System.out.println("Done!");
    }

    private void deleteCourseAndReviews(AppDAO appDAO) {
        int theId=10;
        System.out.println("Delete Course and Reviews by ID"+theId);
        appDAO.deleteCourseById(theId);
        System.out.println("Done");
    }

    private void retrievCourseAndReviews(AppDAO appDAO) {
        //get the course and review
        int theId = 10;
       Course theCourse = appDAO.findCourseAndReviewsByCourseId(theId);
        //print the course
        System.out.println(theCourse);
        //print the reviews
        System.out.println(theCourse.getReviews());

        System.out.println("done");
    }

    private void createCourseAndReviews(AppDAO appDAO) {
        //create a course
        Course theCourse = new Course("Java2");


        //add some reviews
       theCourse.addReview(new Review("good"));
        theCourse.addReview(new Review("good love it"));
        theCourse.addReview(new Review(" Not good"));

        //
        System.out.println(theCourse.getReviews());
        //save the course
        appDAO.save(theCourse);
        System.out.println("Done!");
    }

    private void deleteCourse(AppDAO appDAO) {
        int theId = 10;

        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Done!");
    }

    //update courses
    private void updateCourses(AppDAO appDAO) {
        int theId = 10;
        //find the course
        System.out.println("Finding course id: " + theId);
        Course tempCourse = appDAO.findCourseById(theId);
        //update the course
        System.out.println("updateing course id: " + theId);
        tempCourse.setTitle("Enjoy the simple things");
        appDAO.update(tempCourse);
        System.out.print("done");
    }

    private void updateInstructor(AppDAO appDAO) {

        int theId = 1;

        // find the instructor
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        // update the instructor
        System.out.println("Updating instructor id: " + theId);
        tempInstructor.setLastName("TESTER");

        appDAO.update(tempInstructor);

        System.out.println("Done!");
    }

    //Join FETCH
    //OIN FETCH 就是在查询时手动实现了 EAGER 的效果，即使注解写的是 LAZY！
    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

        int theId = 1;

        // find the instructor
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        //
        //设置要查询的讲师ID为1
        int theId = 1;
       //find the instructor

        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println(tempInstructor);
        //find courses for instructor
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);
        //associate the object/ 所以你必须手动查：
        tempInstructor.setCourses(courses);//// 手动组装
        //才能拿到数据
        System.out.println(tempInstructor.getCourses());
    }

    //根据id去找数据// Eager

    private void findInstructorWithCourses(AppDAO appDAO) {
        //set find instructor Id
        int theId=1;
        //print what instructor is finding
        System.out.println("Finding instructor with id "+theId);
        //evoke app.DAO go to database to retrieve
        Instructor temInstructor = appDAO.findInstructorById(theId);
        //print the instructor information
        System.out.println("TempInstructor "+temInstructor);
        //evoke tempInstructor.getCourse().Jpa will find does not loading, it will immediately to execute SQL
        //你根本不用手动查课程，它自动就查好了
        System.out.println("the associate course:" + temInstructor.getCourses());
        System.out.println("Done!");
    }

    //数据库输入数据，存如新数据
    private void createInstructorWithCourse(AppDAO appDAO) {
        //create the instructor
        Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@luv2code.com");

        //create the instructor detial
        InstructorDetail tempInstructorDetail = new InstructorDetail(
                "http://www.youtube.com",
                "Video Games");
        //associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);
        //create some course
        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
        Course tempCourse2 = new Course("The Pinball Masterclass");

        //Add courses to instructor

        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        //save the instructor
        //Note :this will ALso save the course
        //because of cascadeType.PERSIST
        System.out.println("Saving instructor: " + tempInstructor);
        System.out.println("The courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);

        System.out.println("Done!");

    }

    private void deleteInstructorDetail(AppDAO appDAO) {
        int theId= 1;
        System.out.println("deleting instructor detial id: " + theId);
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("done deleting instructor detial id: " + theId);
    }

    private void findInstructorDetail(AppDAO appDAO) {

        // get the instructor detail object
        int theId = 3;
        InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

        // print the instructor detail
        System.out.println("tempInstructorDetail: " + tempInstructorDetail);

        // print the associated instructor
        System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

        System.out.println("Done!");
    }

    private void deleteInstructor(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Deleting instructor id: " + theId);

        appDAO.deleteInstructorById(theId);

        System.out.println("Done!");
    }

    private void findInstructor(AppDAO appDAO) {

        int theId = 3;
        System.out.println("Finding instructor id: " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());

    }

    private void createInstructor(AppDAO appDAO) {

		/*
		// create the instructor
		Instructor tempInstructor =
				new Instructor("Chad", "Darby", "darby@luv2code.com");

		// create the instructor detail
		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.luv2code.com/youtube",
						"Luv 2 code!!!");
		*/

        // create the instructor
        Instructor tempInstructor =
                new Instructor("Madhu", "Patel", "madhu@luv2code.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail =
                new InstructorDetail(
                        "http://www.luv2code.com/youtube",
                        "Guitar");

        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // save the instructor
        //
        // NOTE: this will ALSO save the details object
        // because of CascadeType.ALL
        //
        System.out.println("Saving instructor: " + tempInstructor);
        appDAO.save(tempInstructor);

        System.out.println("Done!");
    }
}