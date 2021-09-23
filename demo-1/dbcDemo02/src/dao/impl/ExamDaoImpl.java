package dao.impl;

import bean.Exam;
import dao.ExamDao;
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
 *  @文件名:   ExamDaoImpl
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/20 14:50
 *  @描述：    TODO
 */
public class ExamDaoImpl implements ExamDao {
    BaseDao bd = new BaseDao();
    Connection conn = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    @Override
    public boolean doExam(Exam exam) {
        String sql = "insert into exam(name,start_time,time_len,nums,score,tid,cid,lid,sid,topicsId) values(?,?,?,?,?,?,?,?,?,?)";
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, exam.getExam_name());
            ptmt.setString(2, exam.getStart_time());
            ptmt.setInt(3, exam.getTime_len());
            ptmt.setInt(4, exam.getNums());
            ptmt.setInt(5, exam.getScore());
            ptmt.setInt(6, exam.getTid());
            ptmt.setInt(7, exam.getCid());
            ptmt.setInt(8, exam.getLid());
            ptmt.setInt(9, exam.getSid());
            ptmt.setString(10, exam.getTopicsId());
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
    public List<Exam> findExamByCid(int cid) {
        List<Exam> exams = new ArrayList<Exam>();
        String sql = "select * from exam where cid=" + cid;
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                exams.add(new Exam(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
                        rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return exams;
    }

    @Override
    public Exam findExamById(int exam_id) {
        Exam exam = null;
        String sql = "select * from exam where id = " + exam_id;
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            if(rs.next()){
                exam = new Exam(exam_id,rs.getString(2),rs.getString(3),rs.getInt(4),
                        rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),
                        rs.getInt(9),rs.getInt(10),rs.getString(11));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return exam;
    }
}
