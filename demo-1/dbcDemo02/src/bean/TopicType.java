package bean;

/*
 *  @项目名：  DemoZR
 *  @包名：    bean
 *  @文件名:   TopicType
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 9:51
 *  @描述：    TODO
 */
public class TopicType {
    private int tid;
    private String type_name;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public TopicType(int tid, String type_name) {
        this.tid = tid;
        this.type_name = type_name;
    }
}
