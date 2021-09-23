package bean;

/*
 *  @项目名：  DemoZR
 *  @包名：    bean
 *  @文件名:   Exam
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 9:50
 *  @描述：    TODO
 */
public class Exam {
    private int exam_id;
    private String exam_name;
    private String start_time;
    private int time_len;
    private int nums;
    private int score;
    private int tid;
    private int cid;
    private int lid;
    private int sid;
    private String topicsId;

    public Exam(int exam_id, String exam_name, String start_time, int time_len, int nums, int score, int tid, int cid, int lid, int sid, String topicsId) {
        this.exam_id = exam_id;
        this.exam_name = exam_name;
        this.start_time = start_time;
        this.time_len = time_len;
        this.nums = nums;
        this.score = score;
        this.tid = tid;
        this.cid = cid;
        this.lid = lid;
        this.sid = sid;
        this.topicsId = topicsId;
    }

    public Exam(String exam_name, String start_time, int time_len, int nums, int score, int tid, int cid, int lid, int sid, String topicsId) {
        this.exam_name = exam_name;
        this.start_time = start_time;
        this.time_len = time_len;
        this.nums = nums;
        this.score = score;
        this.tid = tid;
        this.cid = cid;
        this.lid = lid;
        this.sid = sid;
        this.topicsId = topicsId;
    }

    public int getExam_id() {
        return exam_id;
    }

    public void setExam_id(int exam_id) {
        this.exam_id = exam_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getTime_len() {
        return time_len;
    }

    public void setTime_len(int time_len) {
        this.time_len = time_len;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getTopicsId() {
        return topicsId;
    }

    public void setTopicsId(String topicsId) {
        this.topicsId = topicsId;
    }
}
