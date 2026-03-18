package com.Jiecode.cruddemo.dao;

import com.Jiecode.cruddemo.entity.Course;
import com.Jiecode.cruddemo.entity.Instructor;
import com.Jiecode.cruddemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        // retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        //retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);
        //remove the  associate obkect refenere
        //break bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);
        //delete the instructor detail
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        //create query
        //// 创建JPQL查询：从Course表中查找，条件是instructor.id等于传入的ID
        TypedQuery<Course> query = entityManager.createQuery("from Course  where instructor.id = :data", Course.class);
        // 设置查询参数：把:data替换为实际的theId
        query.setParameter("data", theId);
        //EXECUTE  query // 执行查询，获取结果列表
        List<Course> courses = query.getResultList();
        return courses;
    }
//Join Fetch 这个方法用JOIN FETCH一次性查出了讲师+课程所有数据！
    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                + "JOIN FETCH i.courses "
                + "WHERE i.id = :data", Instructor.class);
        query.setParameter("data", theId);
        //execute query
        Instructor instructor = query.getSingleResult();
        return instructor;

    }
//one to many

@Override
@Transactional
public void update(Instructor tempInstructor) {
    entityManager.merge(tempInstructor);
}

    @Override
    public void update(Course theCourse) {
        entityManager.merge(theCourse);


    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }
    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        // retrieve the course
        Course tempCourse = entityManager.find(Course.class, theId);

        // delete the course
        entityManager.remove(tempCourse);
    }
}