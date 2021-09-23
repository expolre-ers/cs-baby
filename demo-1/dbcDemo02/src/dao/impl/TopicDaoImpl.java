package dao.impl;


import bean.Topic;
import dao.TopicDao;
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
 *  @文件名:   TopicDaoImpl
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/20 14:49
 *  @描述：    TODO
 */
public class TopicDaoImpl implements TopicDao {
    BaseDao bd = new BaseDao();
    Connection conn = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    @Override
    public boolean doTopic(Topic topic) {
        String sql = "insert into topic(question,opts,answer,analysis,tid,lid,sid) values(?,?,?,?,?,?,?)";
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, topic.getQuestion());
            ptmt.setString(2, topic.getOptions());
            ptmt.setString(3, topic.getAnswer());
            ptmt.setString(4, topic.getAnalysis());
            ptmt.setInt(5, topic.getTid());
            ptmt.setInt(6, topic.getLid());
            ptmt.setInt(7, topic.getSid());
            int row = ptmt.executeUpdate();
            if (row > 0) {
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
    public List<Topic> findTopic(int tid, int lid, int sid) {
        List<Topic> topics = new ArrayList<Topic>();
        String sql = "select * from topic where 1=1";
        if (tid != 0) {
            sql += " and tid=" + tid;
        }
        if (lid != 0) {
            sql += " and lid=" + lid;
        }
        if (sid != 0) {
            sql += " and sid=" + sid;
        }
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                topics.add(new Topic(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return topics;
    }

    @Override
    public Topic findTopicById(int topic_Id) {
        Topic topic = null;
        String sql = "select * from topic where id = " + topic_Id;
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                topic = new Topic(topic_Id, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return topic;
    }
}
