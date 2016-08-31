# GreenDao
### Android的ORM框架GreenDAO

## ORM：对象关系映射
### 主要思想：将关系数据库中表的数据映射成为对象，以对象的形式展现，把对数据库的操作转化为对这些对象的操作

### 主要配置步骤
1. 参数配置
```java
compile 'de.greenrobot:greendao:2.1.0'
```
2. 建立文件夹(src/main下建立java-gen)
3. 配置main函数
```java
sourceSets {
    main {
        java.srcDirs = ['src/main/java', 'src/main/java-gen']
      }
  }
```
4. 新建lib库，执行main函数，添加简单配置文件
```java
compile 'de.greenrobot:greendao-generator:2.1.0'
```
* DaoMaster：保存sqlitedatebase对象以及操作DAO，内部类OpenHelper和DevOpenHelper实现SQLiteOpenHelper并创建数据库的框架
* DaoSession：会话层。操作具体的DAO对象
* XXXDao：实际生成的DAO类，通常对应具体java类，比如NoteDao等；
* XXXEntity:持久的实体对象。
