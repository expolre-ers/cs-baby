package dao.impl;

import bean.Student;
import dao.StudentDao;
import dbc.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao.impl
 *  @文件名:   StudentDaoImpl
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 16:41
 *  @描述：    TODO
 */
public class StudentDaoImpl implements StudentDao {
    BaseDao bd = new BaseDao();
    Connection conn = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    @Override
    public boolean doStudent(Student student) {
        String sql = "insert into student(id,name,password,cid) values(?,?,?,?)";
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1,student.getStu_id());
            ptmt.setString(2,student.getStu_name());
            ptmt.setString(3,student.getStu_password());
            ptmt.setInt(4,student.getCid());
            int row = ptmt.executeUpdate();
            if(row>0){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return false;
    }

    @Override
    public void updateStudent(Student student, String newPsw) {
        String sql = "update student set password = ? where id = ?";
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1,newPsw);
            ptmt.setInt(2,student.getStu_id());
            ptmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
    }

    @Override
    public Student findStudent(int id, String password) {
        Student student = null;
        String sql = "select * from student where id = "+id+" and password = "+password;
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            if(rs.next()){
                student = new Student(id,rs.getString(2),password,rs.getInt(4));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return student;
    }

    @Override
    public List<Student> findStudentByCid(int cid) {
        List<Student> students = new ArrayList<Student>();
        String sql = "select * from student where cid ="+cid;
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            while (rs.next()){
                students.add(new Student(rs.getInt(1),rs.getString(2),rs.getString(3),cid));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return students;
    }

    @Override
    public String findStu_nameById(int uid) {
        String sql = "select * from student where id ="+uid;
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            if(rs.next()){
                return rs.getString(2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return null;
    }
}
