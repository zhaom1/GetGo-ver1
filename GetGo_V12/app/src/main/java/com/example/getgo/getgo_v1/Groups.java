package com.example.getgo.getgo_v1;

public class Groups {

    public String course_id;
    public String institution_id;
    public String group;


    public Groups(String course_id, String institution, String group) {
        this.course_id = course_id;
        this.institution_id = institution;
        this.group = group;
    }

    public String getCondtionId() {
        return course_id;
    }

    public String getInstitutionId() {
        return institution_id;
    }

    public String getDepartmentId() {
        return group;
    }

}