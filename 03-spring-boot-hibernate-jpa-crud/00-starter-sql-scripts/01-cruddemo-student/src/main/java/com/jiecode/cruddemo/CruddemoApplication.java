package com.jiecode.cruddemo;

import com.jiecode.cruddemo.dao.StudentDao;
import com.jiecode.cruddemo.entity.Student;
import org.hibernate.dialect.function.OracleTruncFunction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {
/*
The main method is the entry point of the application.
SpringApplication.run bootstraps the entire framework,
starts the embedded Tomcat server,
 and initializes the Application Context which manages all the beans.
 */
	public static void main(String[] args) {

        SpringApplication.run(CruddemoApplication.class, args);
	}
// [1] Tells Spring to register the returned object as a "Bean" in the Application Context.
    @Bean
    //the commanlinerunner from the spring boot framework
    //exceuted after the spring beans have been loaded
    /*
  After the Application Context is refreshed and all Beans are loaded,
  Spring Boot will automatically detect
  and execute all beans that implement the CommandLineRunner interface
     */
    public CommandLineRunner commandLineRunner(StudentDao studentDao) {
        //2.defines a method that returns a commandLineRunner instance
        //3.return : hands the crerate object over to the spring container
        //4.runner-> a Lambda expression implementing the run method of the interface
        //5.'runner': The variable representing the command-line arguments (String[]) passed at startup.
        //// 6. The logic inside this block executes automatically once the Spring application has fully started.
        return runner -> {

            //createStudent(studentDao);
            //createMultipleStudents(studentDao);
            //readStudent(studentDao);
            //queryForStudent(studentDao);
            //updateStudent(studentDao);
            //deleteStudent(studentDao);
            deleteAllStudents(studentDao);
        };

    }

    private void deleteAllStudents(StudentDao studentDao) {
        System.out.println("Deleting all students");
        int numRowsDeleted = studentDao.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);
    }

    private void deleteStudent(StudentDao studentDAO) {
        int studentId = 3;
        System.out.println("Deleting student id: " + studentId);
        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDao studentDAO) {

        // retrieve student based on the id: primary key
        int studentId = 1;
        System.out.println("Getting student with id: " + studentId);
        Student myStudent = studentDAO.findById(studentId);

        // change first name to "John"
        System.out.println("Updating student ...");
        myStudent.setFirstName("John");

        // update the student
        studentDAO.update(myStudent);

        // display the updated student
        System.out.println("Updated student: " + myStudent);
    }


    private void queryForStudent(StudentDao studentDao) {
        //get a list of students
        List<Student> theStudents = studentDao.findAll();
        //display list of students
        for(Student temStudent : theStudents){
            System.out.println(temStudent);
        }
    }

    private void readStudent(StudentDao studentDao) {

            //create a student object
            System.out.println("Creating new student ...");
            Student tempStudent = new Student(  "zz","kk","123@qq.com");
            //save the student
            System.out.println("Saving the student ...");
            studentDao.save(tempStudent);
            //display id of the saved student
            int theId = tempStudent.getId();
            System.out.println("saved student Generated id " + theId);
            //retrieve student based on the id:primary key
            System.out.println("Reading student with id " + theId);
            Student myStudent = studentDao.findById(theId);

            //display student
            System.out.println("Found the student " + myStudent);
        }


    private void createMultipleStudents(StudentDao studentDao) {

        // create multiple students
        System.out.println("Creating 3 student objects ...");
        Student tempStudent1 = new Student("John", "Doe", "john@luv2code.com");
        Student tempStudent2 = new Student("Mary", "Public", "mary@luv2code.com");
        Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv2code.com");

        // save the student objects
        System.out.println("Saving the students ...");
        studentDao.save(tempStudent1);
        studentDao.save(tempStudent2);
        studentDao.save(tempStudent3);
    }

    private void createStudent(StudentDao studentDao) {

        // create the student object
        System.out.println("Creating new student object ...");
        Student tempStudent = new Student("Paul", "Doe", "paul@luv2code.com");

        // save the student object
        System.out.println("Saving the student ...");
        studentDao.save(tempStudent);

        // display id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }


}


