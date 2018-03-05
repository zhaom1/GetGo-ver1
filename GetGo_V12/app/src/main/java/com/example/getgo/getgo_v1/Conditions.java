package com.example.getgo.getgo_v1;

public class Conditions {

    public String id;
    public String institution_id;
    public String department_id;
    public String min_mark;

    public Conditions(String id, String institution, String department, String mark) {
        this.id = id;
        this.institution_id = institution;
        this.department_id = department;
        this.min_mark = mark;
    }

    public String getCondtionId() {
        return id;
    }

    public String getInstitutionId() {
        return institution_id;
    }

    public String getDepartmentId() {
        return department_id;
    }

    public String  getMinMark() {
        return min_mark;
    }

}