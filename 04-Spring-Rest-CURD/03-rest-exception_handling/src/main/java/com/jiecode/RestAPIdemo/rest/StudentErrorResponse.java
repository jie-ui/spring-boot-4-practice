package com.jiecode.RestAPIdemo.rest;

public class StudentErrorResponse {

    private int Status;
    private String Message;
    private long TimeStamp;

    public StudentErrorResponse(){}
    public StudentErrorResponse(int Status, String Message, long TimeStamp) {
        this.Status = Status;
        this.Message = Message;
        this.TimeStamp = TimeStamp;
    }

    public int getStatus() {
        return Status;
    }

    public String getMessage() {
        return Message;
    }

    public long getTimeStamp() {
        return TimeStamp;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setTimeStamp(long timeStamp) {
        TimeStamp = timeStamp;
    }
}
