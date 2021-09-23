package dao.impl;

import bean.Clazz;
import bean.TopicStage;
import dao.StageDao;
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
 *  @文件名:   StageDaoImpl
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/20 14:39
 *  @描述：    TODO
 */
public class StageDaoImpl implements StageDao {
    BaseDao bd = new BaseDao();
    Connection conn = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    @Override
    public List<TopicStage> findAllStage() {
        List<TopicStage> stages = new ArrayList<TopicStage>();
        String sql = "select * from stage";
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            while (rs.next()){
                stages.add(new TopicStage(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return stages;
    }
}
