package com.sanjeet.ahagurureducation.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeet.ahagurureducation.GetAllTestesAttendByStudent;
import com.sanjeet.ahagurureducation.R;
import com.sanjeet.ahagurureducation.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<Student> studentList;
    private Context context;
    private GetAllTestesAttendByStudent getAllTestesAttendByStudent;

    public StudentAdapter(List<Student> studentList, Context context, GetAllTestesAttendByStudent getAllTestesAttendByStudent) {
        this.studentList = studentList;
        this.context = context;
        this.getAllTestesAttendByStudent = getAllTestesAttendByStudent;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {

        Student student = studentList.get(position);
        int id = student.getStuRollNo();
        holder.rollNo.setText(String.valueOf(id));
        holder.name.setText(student.getStuName());
        holder.email.setText(student.getStuEmail());
        holder.stuClass.setText(student.getStuClass());

        holder.itemView.setOnClickListener(view -> {
            getAllTestesAttendByStudent.getAllTest(student.getStuRollNo());
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rollNo, name, email, stuClass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rollNo = itemView.findViewById(R.id.tvTestNo);
            name = itemView.findViewById(R.id.tvTestDate);
            email = itemView.findViewById(R.id.tvTestSubject);
            stuClass = itemView.findViewById(R.id.tvTestTopic);
        }
    }
}
