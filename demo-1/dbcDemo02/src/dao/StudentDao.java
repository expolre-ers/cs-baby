package dao;

import bean.Student;

import java.util.List;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao
 *  @文件名:   StudentDao
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 10:11
 *  @描述：    TODO
 */
public interface StudentDao {
    boolean doStudent(Student student);

    void updateStudent(Student student, String newPsw);

    Student findStudent(int id, String password);

    List<Student> findStudentByCid(int cid);

    String findStu_nameById(int uid);
}
