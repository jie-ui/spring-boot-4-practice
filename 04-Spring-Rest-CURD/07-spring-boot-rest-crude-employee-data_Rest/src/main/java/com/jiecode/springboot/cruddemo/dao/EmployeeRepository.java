package com.jiecode.springboot.cruddemo.dao;

import com.jiecode.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/*
@RepositoryRestResource(path = "members") 注解是将 EmployeeRepository 自动暴露为 RESTful API 的关键，它会将 CRUD 操作映射为 HTTP 请求。

path 参数自定义了 API 的路径，默认是根据实体类名生成路径，您可以通过设置 path 来更改它。
 */

@RepositoryRestResource(path="members")
///members 代替了默认的 /employees。

//Employee entity type
// Integer primry key
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //that's it  no need towrite any code
}
