package com.sanjeet.ahagurureducation.model;

public class Test {

    private int testNo;
    private String date;
    private String subject;
    private String topic;
    private int maxMarks;

    public Test() {
    }

    public Test(int testNo, String date, String subject, String topic, int maxMarks) {
        this.testNo = testNo;
        this.date = date;
        this.subject = subject;
        this.topic = topic;
        this.maxMarks = maxMarks;
    }

    public int getTestNo() {
        return testNo;
    }

    public void setTestNo(int testNo) {
        this.testNo = testNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(int maxMarks) {
        this.maxMarks = maxMarks;
    }
}
