package bean;

/*
 *  @项目名：  DemoZR
 *  @包名：    bean
 *  @文件名:   Teacher
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 9:49
 *  @描述：    TODO
 */
public class Teacher {
    private int tea_id;
    private String tea_name;
    private String tea_password;

    public Teacher(int tea_id, String tea_name, String tea_password) {
        this.tea_id = tea_id;
        this.tea_name = tea_name;
        this.tea_password = tea_password;
    }

    public int getTea_id() {
        return tea_id;
    }

    public void setTea_id(int tea_id) {
        this.tea_id = tea_id;
    }

    public String getTea_name() {
        return tea_name;
    }

    public void setTea_name(String tea_name) {
        this.tea_name = tea_name;
    }

    public String getTea_password() {
        return tea_password;
    }

    public void setTea_password(String tea_password) {
        this.tea_password = tea_password;
    }
}
