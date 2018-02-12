package com.example.getgo.getgo_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.text.AutoText;
import android.view.autofill.AutofillValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.app.ListActivity;
import java.util.ArrayList;


public class CoursesActivity extends AppCompatActivity implements OnClickListener{

    /*These values are being refrenced from the xml files*/
    private Button btnAdd;
    private ListView lv;
    private AutoCompleteTextView course_box;
    private EditText mark_box;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private static String[] Classes = new String[] {"English 30-1", "Math 30-1", "Physics 30-1", "English 30-2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);

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

    public boolean onCreateOptions(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
