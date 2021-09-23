package dao.impl;

import bean.TopicStage;
import bean.TopicType;
import dao.TypeDao;
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
 *  @文件名:   TypeDaoImpl
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/20 14:47
 *  @描述：    TODO
 */
public class TypeDaoImpl implements TypeDao {
    BaseDao bd = new BaseDao();
    Connection conn = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    @Override
    public List<TopicType> findAllType() {
        List<TopicType> types = new ArrayList<TopicType>();
        String sql = "select * from type";
        conn = bd.getConnection();
        try {
            ptmt = conn.prepareStatement(sql);
            rs = ptmt.executeQuery();
            while (rs.next()){
                types.add(new TopicType(rs.getInt(1),rs.getString(2)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            bd.close(conn, ptmt, rs);
        }
        return types;
    }
}
