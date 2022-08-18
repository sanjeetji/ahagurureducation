package com.sanjeet.ahagurureducation.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.sanjeet.ahagurureducation.R;
import com.sanjeet.ahagurureducation.dataBase.DatabaseHandler;
import com.sanjeet.ahagurureducation.model.Mark;
import com.sanjeet.ahagurureducation.model.Test;
import com.sanjeet.ahagurureducation.ui.adapter.TestAdapter;

import java.util.ArrayList;
import java.util.List;


public class TestFragment extends Fragment {


    private FloatingActionButton floatingActionButton;
    private DatabaseHandler db;
    private RecyclerView recyclerView;
    private TestAdapter testAdapter;
    private List<Test> testList;
    private Button addStudent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        testList = new ArrayList<>();
        db = new DatabaseHandler(requireActivity());
        floatingActionButton = view.findViewById(R.id.add_test_fab);
        recyclerView = view.findViewById(R.id.rv_Test);
        addStudent = view.findViewById(R.id.addStudent);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        getTestData();
        floatingActionButton.setOnClickListener(view1 -> {
            addTestDialog();
        });
        addStudent.setOnClickListener(view1 -> {
            addStudentMarkDialog();
        });
        return view;
    }

    private void addStudentMarkDialog() {
        EditText studentMark;
        Button addTest;
        ImageView calncelImg;
        View view = LayoutInflater.from(requireActivity()).inflate(R.layout.add_student_mark_dialog, null);
        //initialize alert builder.
        AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());

        //set custom alert dialog to tha alertdialog builder
        alert.setView(view);
        studentMark = (EditText) view.findViewById(R.id.etStudentMark);
        addTest = (Button) view.findViewById(R.id.addTest);
        calncelImg = (ImageView) view.findViewById(R.id.imgCancle);

        final AlertDialog dialog = alert.create();
        dialog.show();
        calncelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(studentMark.getText().toString())) {
                    Toast.makeText(requireActivity(), "Marks are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    Long success = db.addStudentMark(new Mark(Integer.parseInt(studentMark.getText().toString())));
                    if (success > -1) {
                        Toast.makeText(requireActivity(), "Student marks added", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void getTestData() {

        testList = db.getAllTests();
        testAdapter = new TestAdapter(testList, requireActivity());
        recyclerView.setAdapter(testAdapter);
        testAdapter.notifyDataSetChanged();
    }

    private void addTestDialog() {
        EditText testNo, date, subject, topic, maxMarks;
        Button addTest;
        ImageView calncelImg;
        ProgressBar progressBar;
        View view = LayoutInflater.from(requireActivity()).inflate(R.layout.add_test_dialog, null);
        //initialize alert builder.
        AlertDialog.Builder alert = new AlertDialog.Builder(requireActivity());

        //set our custom alert dialog to tha alertdialog builder
        alert.setView(view);
        testNo = (EditText) view.findViewById(R.id.etTestNo);
        date = (EditText) view.findViewById(R.id.etTestDate);
        subject = (EditText) view.findViewById(R.id.etTestSubject);
        topic = (EditText) view.findViewById(R.id.etTestTopic);
        maxMarks = (EditText) view.findViewById(R.id.etTestMaxMarks);
        addTest = (Button) view.findViewById(R.id.addTest);
        calncelImg = (ImageView) view.findViewById(R.id.imgCancle);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        final AlertDialog dialog = alert.create();
        dialog.show();
        calncelImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(testNo.getText().toString())) {
                    Toast.makeText(requireActivity(), "Test no is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(date.getText().toString())) {
                    Toast.makeText(requireActivity(), "Date is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(subject.getText().toString())) {
                    Toast.makeText(requireActivity(), "Subject is mandatory", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(topic.getText().toString())) {
                    Toast.makeText(requireActivity(), "Topic is mandatory email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(maxMarks.getText().toString())) {
                    Toast.makeText(requireActivity(), "Max marks is mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    Long success = db.addTest(new Test(Integer.parseInt(testNo.getText().toString()),
                            date.getText().toString(),
                            subject.getText().toString(),
                            topic.getText().toString(),
                            Integer.parseInt(maxMarks.getText().toString())));
                    if (success > -1) {
                        Toast.makeText(requireActivity(), "Test added", Toast.LENGTH_SHORT).show();
                        getTestData();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(requireActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}