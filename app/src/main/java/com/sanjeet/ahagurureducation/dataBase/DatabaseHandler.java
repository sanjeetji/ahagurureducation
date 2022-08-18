package com.sanjeet.ahagurureducation.dataBase;

import static com.sanjeet.ahagurureducation.ui.fragments.StudentFragment.rollNoIs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sanjeet.ahagurureducation.model.Mark;
import com.sanjeet.ahagurureducation.model.Student;
import com.sanjeet.ahagurureducation.model.Test;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "studentDatabase";

    private static final String TABLE_STUDENT = "student";
    private static final String KEY_ID = "id";
    private static final String KEY_ROLL_NO = "roll_no";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CLASS = "class";

    private static final String KEY_TEST_ID = "id";
    private static final String TABLE_TEST = "test";
    private static final String KEY_TEST_NO = "test_no";
    private static final String KEY_DATE = "date";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_TOPIC = "topic";
    private static final String KEY_MAX_MARKS = "max_marks";
    private static final String KEY_ROLL = "roll_no";

    private static final String TABLE_STUDENT_MARKS = "marks";
    private static final String KEY_MARK_ID = "id";
    private static final String KEY_MARK = "mark";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_ROLL_NO + " integer,"
                + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_CLASS + " TEXT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);

        String CREATE_TEST_TABLE = "CREATE TABLE " + TABLE_TEST + "("
                + KEY_TEST_ID + " INTEGER PRIMARY KEY,"
                + KEY_TEST_NO + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_SUBJECT + " TEXT,"
                + KEY_TOPIC + " TEXT,"
                + KEY_MAX_MARKS + " TEXT,"
                + KEY_ROLL + " integer,"
                + "FOREIGN KEY (" + KEY_ROLL + ") REFERENCES " + TABLE_STUDENT + KEY_ID + ")";
        db.execSQL(CREATE_TEST_TABLE);

        String CREATE_MARK_TABLE = "CREATE TABLE " + TABLE_STUDENT_MARKS + "("
                + KEY_MARK_ID + " INTEGER PRIMARY KEY,"
                + KEY_MARK + " integer" + ")";
        db.execSQL(CREATE_MARK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT_MARKS);
        onCreate(db);
    }

    //Add Student Marks
    public Long addStudentMark(Mark mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MARK, mark.getMark());
        // Inserting Row
        Long success = db.insert(TABLE_STUDENT_MARKS, null, values);
        db.close();
        return success;
    }

    // Add Test
    public Long addTest(Test test) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_TEST_NO, test.getTestNo());
        values.put(KEY_DATE, test.getDate());
        values.put(KEY_SUBJECT, test.getSubject());
        values.put(KEY_TOPIC, test.getTopic());
        values.put(KEY_MAX_MARKS, test.getMaxMarks());
        values.put(KEY_ROLL, rollNoIs);
        // Inserting Row
        Long success = db.insert(TABLE_TEST, null, values);
        db.close();
        return success;
    }

    //Get All Test
    public List<Test> getAllTests() {
        List<Test> testList = new ArrayList<Test>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TEST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Test test = new Test();
                test.setTestNo(Integer.parseInt(cursor.getString(1)));
                test.setDate(cursor.getString(2));
                test.setSubject(cursor.getString(3));
                test.setTopic(cursor.getString(4));
                test.setMaxMarks(Integer.parseInt(cursor.getString(5)));
                // Adding contact to list
                testList.add(test);
            } while (cursor.moveToNext());
        }

        // return contact list
        return testList;
    }

    //Get Test of a specific student
    public List<Test> getAllTestsByRollNo(int rollNo) {
        List<Test> testList = new ArrayList<Test>();
        // Select All Query
        String query = "SELECT  * FROM " + TABLE_TEST + " WHERE " +
                KEY_ROLL_NO + " = '" + rollNo + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Test test = new Test();
                test.setTestNo(Integer.parseInt(cursor.getString(1)));
                test.setDate(cursor.getString(2));
                test.setSubject(cursor.getString(3));
                test.setTopic(cursor.getString(4));
                test.setMaxMarks(Integer.parseInt(cursor.getString(5)));
                // Adding contact to list
                testList.add(test);
            } while (cursor.moveToNext());
        }

        // return contact list
        return testList;
    }

    //Add students
    public Long addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ROLL_NO, student.getStuRollNo());
        values.put(KEY_NAME, student.getStuName());
        values.put(KEY_EMAIL, student.getStuEmail());
        values.put(KEY_CLASS, student.getStuClass());

        // Inserting Row
        Long success = db.insert(TABLE_STUDENT, null, values);
        db.close();
        return success;
    }

    //get All students
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setStuRollNo(Integer.parseInt(cursor.getString(1)));
                student.setStuName(cursor.getString(2));
                student.setStuEmail(cursor.getString(3));
                student.setStuClass(cursor.getString(4));
                // Adding contact to list
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        // return contact list
        return studentList;
    }

    // checking roll is exist or not
    public Cursor findTask(int taskName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + KEY_ROLL_NO + " FROM " + TABLE_STUDENT + " WHERE " +
                KEY_ROLL_NO + " = '" + taskName + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

}
