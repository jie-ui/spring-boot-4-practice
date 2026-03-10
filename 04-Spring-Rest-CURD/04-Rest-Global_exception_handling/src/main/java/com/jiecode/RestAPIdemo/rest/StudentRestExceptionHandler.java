package com.jiecode.RestAPIdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    //add exception handling code here
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
