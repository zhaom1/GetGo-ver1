package ca.macewan.getgo.getgo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity implements OnClickListener{

    private static final String URL_COURSES = "http://localhost:1101/backend_Getgo/get_all_courses.php";
    List<Course> CourseList;
    /*These values are being refrenced from the xml files*/
    private Button btnAdd;
    private ListView lv;
    private AutoCompleteTextView course_box;
    private EditText mark_box;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    //initializing the product list
    //CourseList = new ArrayList<>();

    //the recyclerView
    RecyclerView recyclerView;
    private static String[] Classes = new String[] {"English 30-1", "Math 30-1", "Physics 30-1", "English 30-2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);


        //initializing the product list
        CourseList = new ArrayList<>();
        //Fills the dropdown menu with the variables in the array Classes
        ArrayAdapter<String> adapter_classes = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Classes);
        autoCompleteTextView.setAdapter(adapter_classes);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);

        //Assigning variables to the list_box, the marks text box and the add button
        course_box = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        mark_box = (EditText) findViewById(R.id.editText);
        btnAdd = (Button)findViewById(R.id.add_button);
        btnAdd.setOnClickListener(this);

        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(adapter);

        // this method will fetch and parse json
        loadCourses();
    }

    //When buttton is clicked, joins the strings and places in tex box
    public void onClick(View v)
    {
        String course_name = course_box.getText().toString();
        String course_mark = mark_box.getText().toString();

        if(course_mark.length() > 0 && course_mark.length() > 0)
        {
            String join = course_name + "    " + course_mark + "%";
            adapter.add(join);
            mark_box.setText("");
            course_box.setText("");
        }
    }

    private void loadCourses() {
        /*
        * Author: Liliana Quyen Tang
        * Creating a String request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener which returns a JSON response as a string
        * and a Error Listener
         */

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_COURSES, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                    //converting the string to json array object
                    try {
                        JSONArray array = new JSONArray(response);
                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {
                            //getting course object from JSON array
                            JSONObject course = array.getJSONObject(i);

                            //adding the course to the course list
                            CourseList.add(new Course(
                                    course.getInt("course_id"),
                                    course.getString("course_name"),
                                    course.getInt("credits"),
                                    course.getString("description")
                            ));
                        }
                        //creating adapter object and setting it to recyclerview
                        CourseAdapter adapter = new CourseAdapter(CourseActivity.this, CourseList);
                        recyclerView.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            },
            new Response.ErrorListener(){
                @Override
                        public void onErrorResponse(VolleyError error){

                        }
            });
        //adding our String request to queue
            Volley.newRequestQueue(this).add(stringRequest);
    }
}
