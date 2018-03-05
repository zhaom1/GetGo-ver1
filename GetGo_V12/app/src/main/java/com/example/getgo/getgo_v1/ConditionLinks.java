package com.example.getgo.getgo_v1;

public class ConditionLinks {

    public String id;
    public String condition_id;
    public String condition_group;

    public ConditionLinks(String link, String condition, String group) {
        this.id = link;
        this.condition_id = condition;
        this.condition_group = group;
    }

    public String getLinkId() {
        return id;
    }

    public String getCondtionId() {
        return condition_id;
    }

    public String getConditionGroup() {
        return condition_group;
    }

}