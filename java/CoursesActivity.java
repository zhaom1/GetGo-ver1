package com.example.getgo.getgo_v1;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Siham on 2018-02-20.
 *
 * This Class will contain references to the list of courses
 * and can also place other lists of data information we need for logic.
 */

public class CoursesActivity extends AppCompatActivity implements OnClickListener {

    //a list to store all the products
    List<Course> CourseList;

    /*These values are being referenced from the xml files*/
    private Button btnAdd;
    private ListView lv;
    private AutoCompleteTextView course_box;
    private EditText mark_box;
    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayList<String> Classes = new ArrayList<>();
    ArrayAdapter<String> adapter_classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Singleton singleton = new Singleton();
        singleton.execute();
        JsonObject jsonObject = singleton.doInBackground();

        //initializing the productlist
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        //Fills the dropdown menu with the variables in the array Classes
        adapter_classes = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, Classes);
        autoCompleteTextView.setAdapter(adapter_classes);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);

        //Assigning variables to the list_box, the marks text box and the add button
        course_box = findViewById(R.id.autoCompleteTextView);
        mark_box = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.add_button);
        btnAdd.setOnClickListener(this);

        lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);

        CourseList = new ArrayList<>();
    }

    //When buttton is clicked, joins the strings and places in tex box
    public void onClick(View v) {
        String course_name = course_box.getText().toString();
        String course_mark = mark_box.getText().toString();

        Log.d("size","Size: " + CourseList.size());

        if (course_mark.length() > 0 && course_mark.length() > 0) {
            String join = course_name + "    " + course_mark + "%";
            adapter.add(join);
            mark_box.setText("");
            course_box.setText("");
        }
    }
}
