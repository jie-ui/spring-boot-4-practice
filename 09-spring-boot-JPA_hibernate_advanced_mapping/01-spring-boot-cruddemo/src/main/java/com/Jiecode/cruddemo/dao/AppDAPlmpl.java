package com.Jiecode.cruddemo.dao;

import com.Jiecode.cruddemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AppDAPlmpl implements AppDao {
    //define feild for entiy manager

    private EntityManager em;

    //inject entity mannager suing construcot injecction
    @Autowired
    public AppDAPlmpl(EntityManager em) {
        this.em = em;
    }


    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        // 调用 entityManager 的 persist 方法来执行 INSERT 语句
        em.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theid) {
        return em.find(Instructor.class, theid);
    }

    @Override
    @Transactional
    public Instructor deleteInstructorById(int theid) {
        // 1. 根据 ID 找到讲师
        Instructor theinstructor = em.find(Instructor.class, theid);

        // 2. 判空检查（防止删不存在的 ID 报空指针异常）
        if (theinstructor != null) {
            // 3. 执行删除（注意：em.remove 没有返回值）
            em.remove(theinstructor);
        }

        // 4. 返回被删除的对象（此时它已不在数据库，但在 Java 内存中还存在）
        return theinstructor;
    }

}
