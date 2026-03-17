package com.Jiecode.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="instructor_detail")
public class InstructorDetail {

    // annotate the class as an entity and map to db table


    //define the fields

    //annotate the fields wiht db column names

    //create consturcotrs

    //generate geeter/setter methods

    //generate toString() method
    @Id //声明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//指定生成主键策略
    @Column(name="id")//建立映射关心
    private int id;

    @Column(name="youtubeChannel")
    private String youtobeChannel;

    @Column(name="hobby")
    private String hobby;


    public InstructorDetail() {}
    public InstructorDetail(String youtobeChannel, String hobby) {
        this.youtobeChannel = youtobeChannel;
        this.hobby = hobby;

    }

    public String getYoutobeChannel() {
        return youtobeChannel;
    }

    public String getHobby() {
        return hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setYoutobeChannel(String youtobeChannel) {
        this.youtobeChannel = youtobeChannel;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "InstructorDetail{" +
                "id=" + id +
                ", youtobeChannel='" + youtobeChannel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}

