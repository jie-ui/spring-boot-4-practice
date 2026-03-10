package com.jiecode.springboot.cruddemo.dao;

import com.jiecode.springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;//导入jjava标准库中List接口定义

@Repository

public class EmployeeDAOJpaImpl  implements EmployeeDAO {

//define field  entitymanager
    private EntityManager entityManager;

//set up constructor injection
@Autowired
public EmployeeDAOJpaImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
}


    @Override
    public List<Employee> findAll(){
    //create aquery
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        //execute query and get reuslt list
        List<Employee> employees = query.getResultList();
        //return the results
        return employees;
    }



    @Override
    public Employee findById(int theid) {
    //get employee
        Employee employee = entityManager.find(Employee.class, theid);
        //return employee
        return employee;
    }

    @Override

    public Employee save(Employee theemployee) {

    //save employee
        Employee dbemployee = entityManager.merge(theemployee);
        //return the dbEmployee
        return dbemployee;
    }

    @Override
    public void deleteById(int theid) {
    //find employee by id
        Employee theemployee = entityManager.find(Employee.class, theid);

        //remove employee
        entityManager.remove(theemployee);

    }




}
