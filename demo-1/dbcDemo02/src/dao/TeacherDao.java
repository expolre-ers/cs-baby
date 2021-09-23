package dao;

import bean.Teacher;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao
 *  @文件名:   TeacherDao
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 10:12
 *  @描述：    TODO
 */
public interface TeacherDao {
    Teacher findTeacher(int id, String password);
}
