package com.example.getgo.getgo_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.autofill.AutofillValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class CoursesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Classes);
        autoCompleteTextView.setAdapter(adapter);
    }

    private static String[] Classes = new String[] {"English 30-1", "Math 30-1", "Physics 30-1", "English 30-2"};

    public boolean onCreateOptions(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
