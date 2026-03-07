package com.jiecode.cruddemo.dao;

import com.jiecode.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

//1,告诉spring这是一个DAO组件
//spring会自动扫描它并翻译数据库相关异常
@Repository
//表示是studentDAO接口的具体实现
public class StudentDAOImpl implements StudentDao{
    //define field for entity manager
    //它是JPA提供的API，专门用来增删改查实体
    private EntityManager em;

    //inject entity manager using constructor injection
    @Autowired
    //告诉spring请从你的容器里找一个entitymanager实例给我
    public void setEm(EntityManager em) {
        this.em = em;
    }

    //implement save method
    @Override
    //事务管理，因为我们要执行写入操作save
    //必须开启事务，如果保存过程中出错了。它会自动回滚数据，保证数据库安全
    @Transactional
    //Creat
    public void save(Student theStudent){
        //persist shi jap的方法，它把一个java对象
        //转换成一条SQL inset语句，并存入数据库对应的表格中
        em.persist(theStudent);
    }
    //Read
    /*
    表示这个方法正在实现StudentDao接口中定义的findById方法
    方法接受一个整数类型的id作为参数返回一个student的对象
    em.find是JAP提供的核心查询方法：
    student.class 告诉JAP把查询结果“翻译”成哪一个java的类
    id 告诉按照哪一个主键值去搜索
     */

    @Override
    public Student findById(Integer id) {
        return em.find(Student.class,id);
    }

    @Override
    public List<Student> findAll() {
        //create query
        TypedQuery<Student> theQuery = em.createQuery("FROM Student", Student.class);
        //return query results
        return theQuery.getResultList();
    }
//update
    @Override
    //因为你要改数据，如果改了数据不能回滚，就会发生损害
    @Transactional
    public void update(Student theStudent) {
        em.merge(theStudent);
    }
//delete all
    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = em.createQuery("DELETE FROM Student").executeUpdate();

        return numRowsDeleted;
    }
   //delete single
    @Override
    @Transactional
    public void delete(int studentId) {
// retrieve the student
        Student theStudent = em.find(Student.class, studentId);

        // delete the student
        em.remove(theStudent);

    }


}
