package com.sanjeet.ahagurureducation.ui.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sanjeet.ahagurureducation.GetAllTestesAttendByStudent;
import com.sanjeet.ahagurureducation.R;
import com.sanjeet.ahagurureducation.dataBase.DatabaseHandler;
import com.sanjeet.ahagurureducation.model.Student;
import com.sanjeet.ahagurureducation.model.Test;
import com.sanjeet.ahagurureducation.ui.adapter.StudentAdapter;
import com.sanjeet.ahagurureducation.ui.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;


public class StudentFragment extends Fragment implements GetAllTestesAttendByStudent {


    private FloatingActionButton floatingActionButton;
    private DatabaseHandler db;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static int rollNoIs;

    private RecyclerView recyclerViewTest;
    private TestAdapter testAdapter;
    private List<Test> testList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        studentList = new ArrayList<>();
        db = new DatabaseHandler(requireActivity());
        floatingActionButton = view.findViewById(R.id.add_student_fab);
        recyclerView = view.findViewById(R.id.rv_Student);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));


        getStudentData();
        floatingActionButton.setOnClickListener(view1 -> {
            addStudentDialog();
        });
        return view;
    }

    private void getStudentData() {

        studentList = db.getAllStudents();
        studentAdapter = new StudentAdapter(studentList, requireActivity(), this);
        recyclerView.setAdapter(studentAdapter);
        studentAdapter.notifyDataSetChanged();
    }

    private void addStudentDialog() {
        EditText rollNo, name, email, etClass;
        Button addStudent;
        ImageView calncelImg;
        ProgressBar progressBar;
        View view = LayoutInflater.from(requireActivity()).inflate(R.layout.add_student_dialog, null);
        //initialize alert builder.
        AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());

        //set custom alert dialog to tha alertdialog builder
        alert.setView(view);
        rollNo = (EditText) view.findViewById(R.id.etTestNo);
        name = (EditText) view.findViewById(R.id.etTestDate);
        email = (EditText) view.findViewById(R.id.etTestSubject);
        etClass = (EditText) view.findViewById(R.id.etTestTopic);
        addStudent = (Button) view.findViewById(R.id.addStudent);
        calncelImg = (ImageView) view.findViewById(R.id.imgCancle);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        rollNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int count = 0;
                if (!charSequence.toString().equals("")) {
                    int roll = Integer.parseInt(charSequence.toString());
                    while (db.findTask(roll).moveToNext()) {
                        count++;
                        break;
                    }
                    if (count != 0) {
                        rollNo.setError("Roll no " + charSequence.toString() + " is already exist.");
                        rollNo.requestFocus();
                        return;
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        final AlertDialog dialog = alert.create();
        dialog.show();
        calncelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(rollNo.getText().toString())) {
                    Toast.makeText(requireActivity(), "Roll no is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(requireActivity(), "Name no is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email.getText().toString())) {
                    Toast.makeText(requireActivity(), "Email is mandatory", Toast.LENGTH_SHORT).show();
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    Toast.makeText(requireActivity(), "Enter Valid email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(etClass.getText().toString())) {
                    Toast.makeText(requireActivity(), "Class no is mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    rollNoIs = Integer.parseInt(rollNo.getText().toString());
                    Long success = db.addStudent(new Student(Integer.parseInt(rollNo.getText().toString()), name.getText().toString(),
                            email.getText().toString(), etClass.getText().toString()));
                    if (success > -1) {
                        Toast.makeText(requireActivity(), "Student added", Toast.LENGTH_SHORT).show();
                        getStudentData();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    @Override
    public void getAllTest(int rollNo) {
        ImageView calncelImg;
        List<Test> testList = db.getAllTestsByRollNo(rollNo);
        View view = LayoutInflater.from(requireActivity()).inflate(R.layout.test_attened_by_student_dialog, null);
        //initialize alert builder.
        AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());
        alert.setView(view);
        calncelImg = (ImageView) view.findViewById(R.id.imgCancle);
        recyclerViewTest = view.findViewById(R.id.rvTestsAttenedByStudent);

        recyclerViewTest.setLayoutManager(new LinearLayoutManager(requireActivity()));

        testAdapter = new TestAdapter(testList, requireActivity());
        recyclerViewTest.setAdapter(testAdapter);

        final AlertDialog dialog = alert.create();
        dialog.show();
        calncelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}