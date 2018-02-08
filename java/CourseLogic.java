package getgo.getgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.HashMap;
import java.lang.String;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void main(String[] args) {
        //Hard coded example of a dictionary containing student courses and marks. This array has to be sorted when obtained from the database.
        HashMap<String, Integer> Student = new HashMap<>();
        Student.put("English 30-1", 90);
        Student.put("Mathematics 30-1", 71);
        Student.put("Chemistry 30", 69);
        Student.put("Biology 30", 91);
        Student.put("Music 30", 90);
        //Hard coded conditions list of lists; This is for U of A Science.
        ArrayList<ArrayList<String>> Science = new ArrayList<ArrayList<String>>();
        ArrayList<String> cond1 = new ArrayList<>(Arrays.asList("English 30-1"));
        Science.add(cond1);
        ArrayList<String> cond2 = new ArrayList<>(Arrays.asList("Mathematics 30-1"));
        Science.add(cond2);
        ArrayList<String> cond3 = new ArrayList<>(Arrays.asList("Biology 30", "Mathematics 31", "Physics 30", "Chemistry 30"));
        Science.add(cond3);
        ArrayList<String> cond4 = new ArrayList<>(Arrays.asList("Biology 30", "Mathematics 31", "Physics 30", "Chemistry 30"));
        Science.add(cond4);
        ArrayList<String> cond5 = new ArrayList<>(Arrays.asList("History 30", "Drama 30", "Art 30", "French 30", "Spanish 30"));
        Science.add(cond5);

        //Call to the checkCourse function, it will either return null which means the student meet the requirements, an arrayList containing all the
        //condition that they failed, or a arrayList containing an average which means they failed the average requirement.
        ArrayList check = checkCourse(Student, Science, 99);
        System.out.print(check);
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

    public static double calculateAverage(ArrayList<Integer> marks){
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
