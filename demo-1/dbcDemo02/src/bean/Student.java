package bean;

/*
 *  @项目名：  DemoZR
 *  @包名：    bean
 *  @文件名:   Student
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 9:49
 *  @描述：    TODO
 */
public class Student {
    private int stu_id;
    private String stu_name;
    private String stu_password;
    private int cid;

    public Student(int stu_id, String stu_name, String stu_password, int cid) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.stu_password = stu_password;
        this.cid = cid;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_password() {
        return stu_password;
    }

    public void setStu_password(String stu_password) {
        this.stu_password = stu_password;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
