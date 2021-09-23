package dao.impl;

import bean.TopicLevel;
import dao.LevelDao;
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
 *  @文件名:   LevelDaoImpl
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/20 14:43
 *  @描述：    TODO
 */
public class LevelDaoImpl implements LevelDao {
    BaseDao bd = new BaseDao();
    Connection conn = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    @Override
    public List<TopicLevel> findAllLevel() {
        List<TopicLevel> levels = new ArrayList<TopicLevel>();
        String sql = "select * from level";
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            while (rs.next()){
                levels.add(new TopicLevel(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return levels;
    }
}
