package bean;

/*
 *  @项目名：  DemoZR
 *  @包名：    bean
 *  @文件名:   Clazz
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/18 9:50
 *  @描述：    TODO
 */
public class Clazz {
    private int cid;
    private String c_name;

    public Clazz(int cid, String c_name) {
        this.cid = cid;
        this.c_name = c_name;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
