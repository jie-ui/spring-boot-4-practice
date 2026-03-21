package com.jiecode.aopdemo;

import com.jiecode.aopdemo.dao.AccountDAO;
import com.jiecode.aopdemo.dao.MembershipDAO;
import com.jiecode.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO,
                                               TrafficFortuneService theTrafficFortuneService) {

        return runner -> {

            // demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
            //demoTheAfterReturningAdvice(theAccountDAO);
            //demoTheAfterThorwingAdvice( theAccountDAO);
            demoTheAroundAdvice( theTrafficFortuneService);

        };
    }

    private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
        System.out.println("\nMain Program:demoTheAroundAdvice");
        System.out.println("Calling getFortune");
        String data = theTrafficFortuneService.getFortune();
        System.out.println(data);
        System.out.println("Done calling getFortune");

    }

    private void demoTheAfterThorwingAdvice(AccountDAO theAccountDAO) {
        // call method to find the accounts
        List<Account> theAccounts = null;
        try{
            //add a boolean flag to simulate excpetions
            boolean tripwire = true;
            theAccountDAO.findAccounts(tripwire);
        }
        catch (Exception e){
            System.out.println("\n\nMain Program:Caught exception"+e.getMessage());
        }

        // display the accounts
        System.out.println("\n\nMain Program: demoTheAfterThorwingAdvice");
        System.out.println("----");

        System.out.println(theAccounts);

        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {

        // call method to find the accounts
        List<Account> theAccounts = theAccountDAO.findAccounts();

        // display the accounts
        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
        System.out.println("----");

        System.out.println(theAccounts);

        System.out.println("\n");
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

        // call the business method
        Account myAccount = new Account();
        myAccount.setName("Madhu");
        myAccount.setLevel("Platinum");

        theAccountDAO.addAccount(myAccount, true);
        theAccountDAO.doWork();

        // call the accountdao getter/setter methods
        theAccountDAO.setName("foobar");
        theAccountDAO.setServiceCode("silver");

        String name = theAccountDAO.getName();
        String code = theAccountDAO.getServiceCode();

        // call the membership business method
        theMembershipDAO.addSillyMember();
        theMembershipDAO.goToSleep();

    }

}




