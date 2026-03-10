package com.jiecode.springboot.cruddemo.dao;


import com.jiecode.springboot.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();//Get
    Employee findById(int theid);//get single one by id

    Employee save(Employee employee); //post update
    void deleteById(int theid); //delete by id
    //void delete(Employee employee); //delete all



}
