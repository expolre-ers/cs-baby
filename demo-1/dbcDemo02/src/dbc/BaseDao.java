package dbc;

import java.sql.*;

/*
 *  @项目名：  DemoZR
 *  @包名：    dbc
 *  @文件名:   BaseDao
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 16:10
 *  @描述：    TODO
 */
public class BaseDao {
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/demo02", "root", "root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close(Connection conn, PreparedStatement ptmt, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
            if(ptmt!=null){
                ptmt.close();
            }
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
