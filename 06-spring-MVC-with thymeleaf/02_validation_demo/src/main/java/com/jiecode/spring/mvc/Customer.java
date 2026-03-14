package com.jiecode.spring.mvc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jiecode.spring.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {
    private String firstName;
    // NOtnull ensures that the field is not null
    //Size sets a size constraint on the field ,checking the minimum and or maximum length of the Value
    @NotNull(message="is required")
    @Size(min=1,message="is required")
    private String lastName ="";

    //Min ensures that the Value of the field is greater than or equal to a specifed minimun value
    @Min(value=0,message = "must be greater than or equal to zero")
    @Max(value=10,message ="must be less than or equal to 10")
    @NotNull(message="is required")
    private Integer freePasses;
//specifes a regular expression that the field value must macht
    @Pattern(regexp="^[a-zA-Z0-9]{5}",message="Only 5 chars/digits")
    private String postalcode;

    @CourseCode
    private String courseCode;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public  Integer getFreePasses() {
        return freePasses;
    }
    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }
    public String getPostalcode() {
        return postalcode;
    }
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
