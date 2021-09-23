package dao;

import bean.Clazz;

import java.util.List;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao
 *  @文件名:   ClassDao
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 10:11
 *  @描述：    TODO
 */
public interface ClassDao {
    boolean doClass(int id,String name);

    List<Clazz> findAllClass();
}
