package com.jiecode.RestAPIdemo.rest;

import com.jiecode.RestAPIdemo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    //define endpoint for /student"--return alist of students
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
        //check the studentId again list size

        if(studentId>=students.size()||(studentId < 0 )){
    throw  new StudentNotFoundException("student id not found -"+studentId);
        }
        return students.get(studentId);
    }

    //add an excrption hanlder using @exceptionhandler
    /*
    如果没有这个程序会直接出现一堆咯不懂的java报错
    这对前端开发者和用户非常不友好
    @ExceptionHandler的作用就是截取错误，并把错误包装成一个漂亮的
    JSON发出去
    逻辑：
    @ExceptionHanlder 这是一个监听器，它告诉spring只要代码里抛出了
    StudentNotFoundException这种错误，就别人程序崩溃，交给我来处理
    ResponseEntity：这是整个HTTPResponse的包装盒，它可以让你手动设置Statuscode
    StudentErrorRespond这个是是自己定义的一个javaleipojo用来存放错误信息
    studentnotfoundexception从它这里得到错误信息
     */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        //create a studentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return responseEntity

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

   //add another exception handler to cach any exception(cathc all
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        //return responseEntity

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


}
