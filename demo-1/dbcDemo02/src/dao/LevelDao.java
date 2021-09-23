package dao;

import bean.TopicLevel;

import java.util.List;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao
 *  @文件名:   LevelDao
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 14:00
 *  @描述：    TODO
 */
public interface LevelDao {
    List<TopicLevel> findAllLevel();
}
