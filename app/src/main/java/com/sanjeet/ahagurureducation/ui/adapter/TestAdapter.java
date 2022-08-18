package com.sanjeet.ahagurureducation.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sanjeet.ahagurureducation.R;
import com.sanjeet.ahagurureducation.model.Test;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private List<Test> testList;
    private Context context;

    public TestAdapter(List<Test> testList, Context context) {
        this.testList = testList;
        this.context = context;
    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {

        Test test = testList.get(position);
        int testNo = test.getTestNo();
        int maxMarks = test.getMaxMarks();
        holder.testNo.setText(String.valueOf(testNo));
        holder.date.setText(test.getDate());
        holder.subject.setText(test.getSubject());
        holder.topic.setText(test.getTopic());
        holder.maxMarks.setText(String.valueOf(maxMarks));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView testNo,date,subject,topic,maxMarks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            testNo =  itemView.findViewById(R.id.tvTestNo);
            date = itemView.findViewById(R.id.tvTestDate);
            subject = itemView.findViewById(R.id.tvTestSubject);
            topic = itemView.findViewById(R.id.tvTestTopic);
            maxMarks = itemView.findViewById(R.id.tvTestMaxMarks);
        }
    }
}
