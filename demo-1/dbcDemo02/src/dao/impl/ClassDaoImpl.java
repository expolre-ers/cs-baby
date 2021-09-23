package dao.impl;

import bean.Clazz;
import dao.ClassDao;
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
 *  @文件名:   ClassDaoImpl
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 16:22
 *  @描述：    TODO
 */
public class ClassDaoImpl implements ClassDao {
    BaseDao bd = new BaseDao();
    Connection conn = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    @Override
    public boolean doClass(int id, String name) {
        String sql = "insert into clazz values(?,?)";
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.setString(2, name);
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
    public List<Clazz> findAllClass() {
        List<Clazz> classes = new ArrayList<Clazz>();
        String sql = "select * from clazz";
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            while (rs.next()){
                classes.add(new Clazz(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return classes;
    }
}
