package dao;

import bean.Topic;

import java.util.List;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao
 *  @文件名:   TopicDao
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 10:12
 *  @描述：    TODO
 */
public interface TopicDao {
    boolean doTopic(Topic topic);

    List<Topic> findTopic(int tid, int lid, int sid);

    Topic findTopicById(int topic_Id);
}
