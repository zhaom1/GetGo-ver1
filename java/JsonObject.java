package com.example.getgo.getgo_v1;

import java.util.ArrayList;

/**
 * Created by Siham on 2018-02-20.
 *
 * This Class will contain references to the list of courses
 * and can also place other lists of data information we need for logic.
 */

public class JsonObject {

    /*
     * This List can be referenced anywhere
     *  and contains all the courses in the DB
     */
    public ArrayList<Course> course = new ArrayList<>();


    /*
     * Returns a list of the Course names used for the UI DropDown.
     */
    public ArrayList<String> getNames(){
        ArrayList<String> courseNames = new ArrayList<String>();
        for(int i=0; i < this.course.size(); i++){
            courseNames.add(course.get(i).name);
        }
        return courseNames;
    }
}
