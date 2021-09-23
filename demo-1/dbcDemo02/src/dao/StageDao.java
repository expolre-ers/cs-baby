package dao;

import bean.TopicStage;

import java.util.List;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao
 *  @文件名:   StageDao
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 14:01
 *  @描述：    TODO
 */
public interface StageDao {
    List<TopicStage> findAllStage();
}
