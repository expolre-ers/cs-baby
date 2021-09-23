package dao;

import bean.Record;

import java.util.List;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao
 *  @文件名:   RecordDao
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 10:11
 *  @描述：    TODO
 */
public interface RecordDao {
    boolean doRecord(Record record);

    List<Record> findRecordById(int stu_id);

    List<Record> findRecordByCid(int cid);

    boolean findRecordByU_idAndExam_id(int uid, int exam_id);
}
