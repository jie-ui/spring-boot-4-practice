package com.jiecode.springboot.cruddemo.dao;

import com.jiecode.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

//Employee entity type
// Integer primry key
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //that's it  no need towrite any code
}
