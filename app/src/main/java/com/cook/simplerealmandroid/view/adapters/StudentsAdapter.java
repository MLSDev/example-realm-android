package com.cook.simplerealmandroid.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cook.simplerealmandroid.R;
import com.cook.simplerealmandroid.app.SimpleRealmApp;
import com.cook.simplerealmandroid.model.Student;
import com.cook.simplerealmandroid.tools.DateFormatter;

import io.realm.RealmResults;

/**
 * Created by roma on 03.11.15.
 */
public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder>{

    private RealmResults<Student> students;

    public StudentsAdapter(RealmResults<Student> students) {
        this.students = students;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        holder.tvName.setText(students.get(position).getName());
        String birthday = SimpleRealmApp.getInstance().getString(R.string.birthday) + DateFormatter.convertDateToString(students.get(position).getBirthday());
        holder.tvBirthday.setText(birthday);
        String email = SimpleRealmApp.getInstance().getString(R.string.birthday) + students.get(position).getEmail();
        holder.tvEmail.setText(email);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvBirthday;
        private TextView tvEmail;

        public StudentViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_student_name);
            tvBirthday = (TextView) itemView.findViewById(R.id.tv_birthday);
            tvEmail = (TextView) itemView.findViewById(R.id.tv_email);
        }
    }

}
