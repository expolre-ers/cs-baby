package bean;

/*
 *  @项目名：  DemoZR
 *  @包名：    bean
 *  @文件名:   Record
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 9:50
 *  @描述：    TODO
 */
public class Record {
    private int rec_id;
    private int stu_id;
    private int exam_id;
    private int cid;
    private String opts;
    private int score;

    public Record(int rec_id, int stu_id, int exam_id, int cid, String opts, int score) {
        this.rec_id = rec_id;
        this.stu_id = stu_id;
        this.exam_id = exam_id;
        this.cid = cid;
        this.opts = opts;
        this.score = score;
    }

    public Record(int stu_id, int exam_id, int cid, String opts, int score) {
        this.stu_id = stu_id;
        this.exam_id = exam_id;
        this.cid = cid;
        this.opts = opts;
        this.score = score;
    }

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getOpts() {
        return opts;
    }

    public void setOpts(String opts) {
        this.opts = opts;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
