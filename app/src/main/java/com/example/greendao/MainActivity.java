package com.example.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.greendao.dbmanager.DataBaseUtils;
import com.student.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataBaseUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbUtils = new DataBaseUtils(this);
    }

    public void insert(View v) {
        Student student = new Student();
        student.setName("张三");
        student.setAge(23);
        student.setAddress("成都");
        dbUtils.insertStudent(student);
        Toast.makeText(this, "插入成功", Toast.LENGTH_SHORT).show();
    }

    public void insertMtu(View v) {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setAddress("武汉");
            student.setName("李四" + i);
            student.setAge(24 + i);
            list.add(student);
        }
        boolean flag = dbUtils.insertStudents(list);
        if (flag) {
            Toast.makeText(this, "批量插入成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View v) {
        Student student = new Student();
        student.setId(10l);
        student.setAge(100);
        student.setName("jack");
        student.setAddress("北京育知同创科技有限公司");
        dbUtils.updateStudent(student);
    }
}
