package dao;

import bean.Exam;

import java.util.List;

/*
 *  @项目名：  DemoZR
 *  @包名：    dao
 *  @文件名:   ExamDao
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 10:12
 *  @描述：    TODO
 */
public interface ExamDao {
    boolean doExam(Exam exam);

    List<Exam> findExamByCid(int cid);

    Exam findExamById(int exam_id);
}
