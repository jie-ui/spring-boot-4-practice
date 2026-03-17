package com.Jiecode.cruddemo;

import com.Jiecode.cruddemo.dao.AppDao;
import com.Jiecode.cruddemo.entity.Instructor;
import com.Jiecode.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDao appDao) { // <--- 括号里保持为空！
        return runner -> { // <--- 这里的 runner 才是真正接收 String[] 的地方
           //createInstructor(appDao);
            //findInstructor(appDao);
            deleteInstructor(appDao);
        };
    }

    private void deleteInstructor(AppDao appDao) {
        int thId = 1;
        System.out.println("Deleting instructor Id: " + thId);
        appDao.deleteInstructorById(thId);
        System.out.println("Instructor deleted");
            ;

    }

    private void findInstructor(AppDao appDao) {

        int theId=1;
        System.out.println("findInstructor:"+theId);
        Instructor instructor = appDao.findInstructorById(theId);
        System.out.println(instructor);
    }


    private void createInstructor(AppDao appDao) {
        //create the instructor
        Instructor tempInstructor = new Instructor("ll@kk.com","bb","dd");

        //create the instrucotr detial
        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.123.com","code ");

        //associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        //save the instructor
        //Note this will also save the detials object
        //because of CasacdeType.ALL
        //
        System.out.println("Saving instructor: " + tempInstructor);
        appDao.save(tempInstructor);

        System.out.println("Saving instructorDetail: " + tempInstructorDetail);

    }

}
