package com.jiecode.aopdemo;

import com.jiecode.aopdemo.dao.AccountDAO;
import com.jiecode.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean

    public CommandLineRunner commandLineRunner(AccountDAO accountDAO, MembershipDAO theMemberhsipDAO) {


            return runner ->{
              demoTheBeforeAdvice(accountDAO,theMemberhsipDAO);
            };
        }

    private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO theMemberhsipDAO) {
        //call the business method

        accountDAO.addAccount();
        theMemberhsipDAO.addAccount();


    }
}




