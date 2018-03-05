package com.example.getgo.getgo_v1;

/**
 * Created by Michael Zhao on 3/1/2018.
 * Class for checking logic.
 */

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.lang.String;
import java.util.ArrayList;

public class CourseLogic {
    public static void main(String[] args) {
        String institution = "1";
        String department = "3";
        ArrayList<String> condId;
        ArrayList<ArrayList<String>> links = new ArrayList<>();
        ArrayList<ArrayList<String>> courses = new ArrayList<>();
        ArrayList<ArrayList<String>> courseNames = new ArrayList<>();
        condId = getConditionId(institution, department);
        if (condId == null) {
            return;
        }

        for (String id: condId) {
            links.add(getLinks(id));
        }

        for (ArrayList list : links) {
            ArrayList<String> groupCourses = new ArrayList<>();
            for (Object group : list) {
                groupCourses.addAll(getGroups(institution, group.toString()));
            }
            courses.add(groupCourses);
        }


        for (ArrayList list : courses) {
            ArrayList<String> names = new ArrayList<>();
            for (Object id : list) {
                if (getCourses(id.toString()) != null) {
                    names.add(getCourses(id.toString()));
                }
            }
            courseNames.add(names);
        }
        
        //Hard coded example of a dictionary containing student courses and marks. This array has to be sorted when obtained from the database.
        HashMap<String, Integer> Student = new HashMap<>();
        Student.put("English Language Arts 30-1", 90);
        Student.put("Mathematics 30-1", 71);
        Student.put("Physics 30", 69);
        Student.put("Chemistry 30", 91);
        Student.put("Mathematics 31", 90);

        //Call to the checkCourse function, it will either return null which means the student meet the requirements, an arrayList containing all the
        //condition that they failed, or a arrayList containing an average which means they failed the average requirement.
        ArrayList check = checkCourse(Student, courseNames, 99);
        System.out.print(check);
    }
    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }
            return buffer.toString();
        } finally {
            if (reader != null){
                reader.close();
            }
        }
    }

    private static Conditions[] parseCondition() throws Exception {
        String json = readUrl("http://localhost:8080/backend_Getgo/get_conditions.php");
        Gson gson = new Gson();
        Conditions[] conditions = gson.fromJson(json, Conditions[].class);

        return conditions;
    }

    private static ArrayList<String> getConditionId(String institution, String department) {
        Conditions[] conditionList;
        try {
            conditionList = parseCondition();
        }
        catch (Exception e){
            System.out.print("parseCondition failed");
            return null;
        }
        ArrayList<String> condId = new ArrayList<>();
        for (Conditions cond : conditionList){
            if (cond.institution_id.equals(institution) && cond.department_id.equals(department)){
                condId.add(cond.id);
            }
        }
        return condId;
    }
    private static ConditionLinks[] parseConditionLinks() throws Exception {
        String json = readUrl("http://localhost:8080/backend_Getgo/get_condition_links.php");
        Gson gson = new Gson();
        ConditionLinks[] links = gson.fromJson(json, ConditionLinks[].class);

        return links;
    }

    private static ArrayList<String> getLinks(String condId) {
        ConditionLinks[] linkList;
        try {
            linkList = parseConditionLinks();
        }
        catch (Exception e) {
            System.out.print("parseConditionLinks failed");
            return null;
        }
        ArrayList<String> condGroup = new ArrayList<>();
        for (ConditionLinks link : linkList){
            if (link.condition_id.equals(condId)) {
                condGroup.add(link.condition_group);
            }
        }
        return condGroup;
    }
    private static Groups[] parseGroups() throws Exception {
        String json = readUrl("http://localhost:8080/backend_Getgo/get_groups.php");
        Gson gson = new Gson();
        Groups[] groups = gson.fromJson(json, Groups[].class);

        return groups;
    }

    private static ArrayList<String> getGroups(String institution, String group) {
        Groups[] groupList;
        try {
            groupList = parseGroups();
        }
        catch (Exception e){
            System.out.print("parseGroups failed");
            return null;
        }
        ArrayList<String> courses = new ArrayList<>();
        for (Groups groups : groupList) {
            if (groups.institution_id.equals(institution) && groups.group.equals(group)) {
                courses.add(groups.course_id);
            }
        }
        return courses;
    }

    private static Course[] parseCourses() throws Exception {
        String json = readUrl ("http://localhost:8080/backend_Getgo/get_all_courses2.php");
        Gson gson = new Gson();
        Course[] courses = gson.fromJson(json, Course[].class);

        return courses;
    }

    private static String getCourses(String id) {
        Course[] courseList;
        try {
            courseList = parseCourses();
        }
        catch (Exception e){
            System.out.print("parseGroups failed");
            return null;
        }
        for (Course course: courseList) {
            if (course.course_id.equals(id)) {
                return course.course_name;
            }
        }
        return null;
    }


    public static ArrayList checkCourse (HashMap<String, Integer> student, ArrayList<ArrayList<String>> faculty, int average){
        HashMap<String, Integer> thisStudent = new HashMap<>();
        ArrayList<Integer> courseMarks = new ArrayList<>();
        thisStudent = (HashMap<String, Integer>)student.clone();
        ArrayList<ArrayList<String>> failedConditions = new ArrayList<ArrayList<String>>();
        for (ArrayList courseList : faculty) {
            boolean found = false;
            for (Object course : courseList){
                if (thisStudent.get(course) != null){
                    courseMarks.add(thisStudent.get(course));
                    thisStudent.remove(course);
                    found = true;
                    break;
                }
            }
            if(!found){
                failedConditions.add(courseList);
            }
        }
        if (!failedConditions.isEmpty()){
            return failedConditions;
        }
        if(calculateAverage(courseMarks) >= average){
            return null;
        }
        ArrayList<Integer> requiredAverage = new ArrayList<>();
        requiredAverage.add(average);
        return requiredAverage;
    }

    private static double calculateAverage(ArrayList<Integer> marks){
        int sum = 0;
        if(!marks.isEmpty()){
            for (int mark: marks){
                sum += mark;
            }
            return sum/marks.size();
        }
        return sum;
    }
}
