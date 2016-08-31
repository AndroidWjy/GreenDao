package com.example.greendao.dbmanager;

import android.content.Context;

import com.student.entity.Student;

import java.util.List;

/**
 * 操作数据库
 * Created by Administrator on 2016/8/31.
 */
public class DataBaseUtils {

    private static DaoManager manager;
    private final static String TAG = "MainActivity";

    public DataBaseUtils(Context context) {
        manager = DaoManager.getInstance();
        manager.init(context);
    }

    /**
     * 插入数据
     *
     * @param student
     * @return
     */
    public boolean insertStudent(Student student) {
        boolean flag = false;
        try {
            manager.getDaoSession().insert(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 批量操作
     *
     * @param students
     * @return
     */
    public boolean insertStudents(final List<Student> students) {
        boolean flag = false;
        try {
            //批量操作
            manager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (Student student : students) {
                        manager.getDaoSession().insertOrReplace(student);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 更新某个student
     *
     * @param student
     * @return
     */
    public boolean updateStudent(Student student) {
        boolean flag = false;
        try {
            manager.getDaoSession().update(student);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
