package dao.impl;

import bean.Teacher;
import dao.TeacherDao;
import dbc.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao.impl
 *  @文件名:   TeacherDaoImpl
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 16:10
 *  @描述：    TODO
 */
public class TeacherDaoImpl implements TeacherDao {
    BaseDao bd = new BaseDao();
    Connection conn = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    @Override
    public Teacher findTeacher(int id, String password) {
        Teacher teacher = null;
        String sql = "select * from teacher where id = "+id+" and password = "+password;
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            if(rs.next()){
                teacher = new Teacher(id,rs.getString(2),password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return teacher;
    }
}
