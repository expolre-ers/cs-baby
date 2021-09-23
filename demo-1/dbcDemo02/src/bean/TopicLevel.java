package bean;

/*
 *  @项目名：  DemoZR
 *  @包名：    bean
 *  @文件名:   TopicLevel
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 9:51
 *  @描述：    TODO
 */
public class TopicLevel {
    private int lid;
    private String level_name;

    public TopicLevel(int lid, String level_name) {
        this.lid = lid;
        this.level_name = level_name;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }
}
