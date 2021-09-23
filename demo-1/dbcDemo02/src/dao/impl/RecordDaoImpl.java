package dao.impl;

import bean.Record;
import dao.RecordDao;
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
 *  @文件名:   RecordDaoImpl
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/20 14:51
 *  @描述：    TODO
 */
public class RecordDaoImpl implements RecordDao {
    BaseDao bd = new BaseDao();
    Connection conn = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    @Override
    public boolean doRecord(Record record) {
        String sql = "insert into record(stu_id,exam_id,cid,opts,score) values(?,?,?,?,?)";
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, record.getStu_id());
            ptmt.setInt(2, record.getExam_id());
            ptmt.setInt(3, record.getCid());
            ptmt.setString(4, record.getOpts());
            ptmt.setInt(5, record.getScore());
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
    public List<Record> findRecordById(int stu_id) {
        List<Record> records = new ArrayList<Record>();
        String sql = "select * from record where stu_id=" + stu_id;
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                records.add(new Record(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return records;
    }

    @Override
    public List<Record> findRecordByCid(int cid) {
        List<Record> records = new ArrayList<Record>();
        String sql = "select * from record where cid=" + cid;
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            while (rs.next()) {
                records.add(new Record(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getString(5), rs.getInt(6)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return records;
    }

    @Override
    public boolean findRecordByU_idAndExam_id(int uid, int exam_id) {
        String sql = "select * from record where stu_id=" + uid + " and exam_id=" + exam_id;
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return false;
    }
}
