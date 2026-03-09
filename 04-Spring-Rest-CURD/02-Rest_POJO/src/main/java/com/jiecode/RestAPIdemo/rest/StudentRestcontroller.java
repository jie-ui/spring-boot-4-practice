package com.jiecode.RestAPIdemo.rest;

import com.jiecode.RestAPIdemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
/*
@RestController 告诉spring，这个类里的方法返回的结果要自动转成Josn格式
并放在HTTPresponse的messagebody里
 */
@RestController
//这是一个根路径
@RequestMapping("/api")
public class StudentRestcontroller {
//定义一个变量来存在学生的列表
    private List<Student> students ;
    //define @postcongtruct to load the sutdent data onlu once
/*在这个个bean初始化完成之后立即执行一次
你不想每次有人访问网址都重新创建一遍列表，它保证了数据只在程序启动时
加载一次
 */
    @PostConstruct
    public void loaddData(){
        students = new ArrayList<>();
        students.add(new Student("jie","Li"));
        students.add(new Student("joy","Wu"));
        students.add(new Student("bob","Li"));

    }
    //define endpoint for"/student"--return alist of students
/*
对应http的get请求，spring会自动吧这个list里的三个student
对象变成JSOn数组传递给前端
 */
    @GetMapping("/students")
    public List<Student> getStudents(){

        return students;
    }
    //define endpoint or"/students/{studentId}"-return student at index
    /*
    {studentId}这是一个变量占位符，你在网址里输入的数字会被抓取下来
    @pathVariable 这个是把网址路径里的数字抓出来赋值给后面亏耗的int studentID
    这里会有一个隐患，indexOutOfBoundsException
     */
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable("studentId") int studentId){
        //just index into the list  keep it simple for now
        return students.get(studentId);
    }
}
