package com.example.getgo.getgo_v1;

public class Course {

    public String course_id;
    public String course_name;
    public String credits;
    public String description;

    public Course(String id, String title, String credits, String description) {
        this.course_id = id;
        this.course_name = title;
        this.credits = credits;
        this.description = description;
    }

    public String getCourseId() {
        return course_id;
    }

    public String getCourseTitle() {
        return course_name;
    }

    public String getCourseDescription() {
        return description;
    }

    public String  getCourseCredits() {
        return credits;
    }

}
