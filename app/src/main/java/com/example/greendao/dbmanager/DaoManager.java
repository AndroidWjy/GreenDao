package com.example.greendao.dbmanager;

import android.content.Context;

import com.student.dao.DaoMaster;
import com.student.dao.DaoSession;

/**
 * 数据操作管理者，降低耦合
 * Created by Administrator on 2016/8/31.
 */
public class DaoManager {
    public volatile static DaoManager manager;
    private final static String DB_NAME = "student_db";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private Context context;
    private DaoMaster.DevOpenHelper devOpenHelper;

    /**
     * 单例模式，双重效验模型
     *
     * @return
     */
    public static DaoManager getInstance() {
        DaoManager instance = null;
        if (manager == null) {
            synchronized (DaoManager.class) {
                if (instance == null) {
                    instance = new DaoManager();
                    manager = instance;
                }
            }
        }
        return instance;
    }

    /**
     * 初始化上下文对象
     *
     * @param context
     */
    public void init(Context context) {
        this.context = context;
    }

    /**
     * 获取daoMaster
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            //相当于SQLiteOpenHelper，创建一个可读可写数据库
            devOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
            daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 拿到daoSession
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * 关闭连接
     */
    public void closeConnection() {
        closeDaoSession();
        closeHelper();
    }


    public void closeHelper() {
        if (devOpenHelper != null) {
            devOpenHelper.close();
            devOpenHelper = null;
        }
    }

    public void closeDaoSession() {
        if (daoSession != null) {
            daoSession.clear();
            daoSession = null;
        }
    }
}
