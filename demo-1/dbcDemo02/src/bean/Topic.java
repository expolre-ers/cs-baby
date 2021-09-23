package bean;

/*
 *  @项目名：  DemoZR
 *  @包名：    bean
 *  @文件名:   Topic
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 9:50
 *  @描述：    TODO
 */
public class Topic {
    private int topic_id;
    private String question;
    private String options;
    private String answer;
    private String analysis;
    private int tid;
    private int lid;
    private int sid;

    public Topic(int topic_id, String question, String options, String answer, String analysis, int tid, int lid, int sid) {
        this.topic_id = topic_id;
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.analysis = analysis;
        this.tid = tid;
        this.lid = lid;
        this.sid = sid;
    }

    public Topic(String question, String options, String answer, String analysis, int tid, int lid, int sid) {
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.analysis = analysis;
        this.tid = tid;
        this.lid = lid;
        this.sid = sid;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
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
}
