package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Dao生成器
 */
public class DaoMaker {

    public static void main(String[] args) {
        //创建一个实体对象
        Schema schema = new Schema(1, "com.student.entity");
        addStudent(schema);
        //dao层相关操作
        schema.setDefaultJavaPackageDao("com.student.dao");
        try {
            new DaoGenerator().generateAll(schema,"app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Schema schema) {
        //javaBean对象，数据库表
        Entity student = schema.addEntity("Student");
        //数据库表的字段
        student.addIdProperty();
        student.addStringProperty("name");
        student.addIntProperty("age");
        student.addStringProperty("address");
    }
}
