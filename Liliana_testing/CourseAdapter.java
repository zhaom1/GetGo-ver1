package ca.macewan.getgo.getgo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Quyen Tang on 2018-02-17.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private Context Ctx;
    private List<Course> courseList;

    public CourseAdapter(Context Ctx, List<Course> courseList) {
        this.Ctx = Ctx;
        this.courseList = courseList;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCourse;

        public CourseViewHolder(View itemView) {
            super(itemView);
            //need to modify the xml file here
            textViewCourse = itemView.findViewById(R.id.textView);

        }
    }


    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.activity_course, null);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.textViewCourse.setText(course.getCourseTitle());

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}

