package ca.macewan.getgo.getgo;

/**
 * Created by Quyen Tang on 2018-02-06.
 */

public class Course {
    private int course_id;
    private String course_name;
    private int credits;
    private String description;

    public Course(int id, String title, int credits, String description) {
        this.course_id = id;
        this.course_name = title;
        this.credits = credits;
        this.description = description;
    }

    public int getCourseId() {
        return course_id;
    }

    public String getCourseTitle() {
        return course_name;
    }

    public String getCourseDescription() {
        return description;
    }

    public int getCourseCredits() {
        return credits;
    }

}
