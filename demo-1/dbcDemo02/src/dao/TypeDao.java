package dao;

import bean.TopicType;

import java.util.List;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao
 *  @文件名:   TypeDao
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 14:02
 *  @描述：    TODO
 */
public interface TypeDao {
    List<TopicType> findAllType();
}
