package Test;

import bean.TopicStage;
import dao.impl.StageDaoImpl;

import java.util.List;

/*
 *  @项目名：  DemoZR
 *  @包名：    Test
 *  @文件名:   TestPro
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/21 18:10
 *  @描述：    TODO
 */
public class TestPro {
    public static void main(String [] args){
        List<TopicStage> stages = new StageDaoImpl().findAllStage();
        stages.remove(0);
        for (TopicStage stage : stages) {
            System.out.println(stage.getSid() + "." + stage.getStage_name());
        }
    }
}
