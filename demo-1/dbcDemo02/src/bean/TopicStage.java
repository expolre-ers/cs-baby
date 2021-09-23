package bean;

/*
 *  @项目名：  DemoZR
 *  @包名：    bean
 *  @文件名:   TopicStage
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 9:51
 *  @描述：    TODO
 */
public class TopicStage {
    private int sid;
    private String stage_name;

    public TopicStage(int sid, String stage_name) {
        this.sid = sid;
        this.stage_name = stage_name;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getStage_name() {
        return stage_name;
    }

    public void setStage_name(String stage_name) {
        this.stage_name = stage_name;
    }
}
