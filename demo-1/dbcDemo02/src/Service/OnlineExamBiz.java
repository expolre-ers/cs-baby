package Service;

import bean.*;
import dao.*;
import dao.impl.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 *  @项目名：  DemoZR
 *  @包名：    Service
 *  @文件名:   OnlineExamBiz
 *  @创建者:   JCHEN
 *  @创建时间:  2021/9/20 13:35
 *  @描述：    TODO
 */
public class OnlineExamBiz {
    TeacherDao teacherDao = new TeacherDaoImpl();
    StudentDao studentDao = new StudentDaoImpl();
    ClassDao classDao = new ClassDaoImpl();
    ExamDao examDao = new ExamDaoImpl();
    TopicDao topicDao = new TopicDaoImpl();
    RecordDao recordDao = new RecordDaoImpl();
    TypeDao typeDao = new TypeDaoImpl();
    LevelDao levelDao = new LevelDaoImpl();
    StageDao stageDao = new StageDaoImpl();

    List<Clazz> classes = null;
    List<TopicStage> stages = null;
    List<TopicLevel> levels = null;
    List<TopicType> types = null;
    List<Exam> exams = null;
    List<Record> records = null;

    Scanner input = new Scanner(System.in);
    Teacher teacher = null;
    Student student = null;
    boolean turnBack;

    public void show() {
        while (true) {
            System.out.println("===================在线考试平台====================");
            do {
                turnBack = false;
                System.out.println("请选择您的身份（1.教师    2.学生）：");
                int role = input.nextInt();
                if (role == 1 || role == 2) {
                    login(role);
                } else {
                    System.out.println("身份选择错误，请重新选择！");
                    turnBack = true;
                }
            } while (turnBack);
            do {
                turnBack = false;
                if (teacher != null) {
                    showTeacherPage();
                }
                if (student != null) {
                    showStudentPage();
                }
                System.out.println("是否返回功能界面？(y/n)");
                String ans = input.next();
                if (ans.equals("y")) {
                    turnBack = true;
                } else {
                    teacher = null;
                    student = null;
                }
            } while (turnBack);
        }
    }

    public void login(int role) {
        do {
            turnBack = false;
            System.out.println("请输入登录账号：");
            int uId = input.nextInt();
            System.out.println("请输入登录密码：");
            String uPassword = input.next();
            if (role == 1) {
                teacher = teacherDao.findTeacher(uId, uPassword);
                if (teacher == null) {
                    System.out.println("教师账号或密码错误，请重新登录！");
                    turnBack = true;
                } else {
                    System.out.println("欢迎教师" + teacher.getTea_name() + "!");
                }
            } else if (role == 2) {
                student = studentDao.findStudent(uId, uPassword);
                if (student == null) {
                    System.out.println("学生账号或密码错误，请重新登录！");
                    turnBack = true;
                } else {
                    System.out.println("欢迎学生" + student.getStu_name() + "!");
                }
            }
        } while (turnBack);
    }

    public void showTeacherPage() {
        System.out.println("---------------------教师界面---------------------");
        do {
            turnBack = false;
            System.out.println("\t\t\t\t\t1.创建班级");
            System.out.println("\t\t\t\t\t2.录入学生");
            System.out.println("\t\t\t\t\t3.录入试题");
            System.out.println("\t\t\t\t\t4.创建考试");
            System.out.println("\t\t\t\t\t5.查看学生成绩");
            System.out.println("\t\t\t\t\t6.查看试卷");
            System.out.println("\t\t\t\t\t7.退出");
            System.out.println("请选择：");
            int teaOpt = input.nextInt();
            if (teaOpt < 1 || teaOpt > 7) {
                System.out.println("请重新选择！");
                turnBack = true;
            }
            switch (teaOpt) {
                case 1:
                    addClassProcess();
                    break;
                case 2:
                    addStudentProcess();
                    break;
                case 3:
                    addTopicProcess();
                    break;
                case 4:
                    addExamProcess();
                    break;
                case 5:
                    showRecordToTea();
                    break;
                case 6:
                    showExamsInfo();
                    break;
                case 7:
                    //signOut();
                    break;
            }
        } while (turnBack);
    }

    public void addClassProcess() {
        classes = classDao.findAllClass();
        System.out.println("---------------------创建班级---------------------");
        do {
            turnBack = false;
            System.out.println("请输入您要创建的班级编号：");
            int cid = input.nextInt();
            for (Clazz clazz : classes) {
                if (cid == clazz.getCid()) {
                    System.out.println("班级编号已存在！请重新输入！");
                    turnBack = true;
                    break;
                }
            }
            if (!turnBack) {
                System.out.println("请输入您要创建的班级名称：");
                String cName = input.next();
                if (classDao.doClass(cid, cName)) {
                    System.out.println(cName + "创建成功！");
                }
            }
        } while (turnBack);
    }

    public void addStudentProcess() {
        System.out.println("---------------------录入学生---------------------");
        System.out.println("请选择录入班级：");
        int cid = chooseClassProcess();
        List<Student> students = studentDao.findStudentByCid(cid);
        int stu_id;
        if (students.isEmpty()) {
            String id = cid + "01";
            stu_id = Integer.parseInt(id);
        } else {
            stu_id = students.get(students.size() - 1).getStu_id() + 1;
        }
        System.out.println("请输入学生姓名：");
        String stu_name = input.next();
        Student stu = new Student(stu_id, stu_name, "123456", cid);
        if (studentDao.doStudent(stu)) {
            System.out.println("录入学生" + stu_name + "成功！");
        }
    }

    public void showAllClass() {
        classes = classDao.findAllClass();
        System.out.println("班级编号\t\t\t班级名称");
        for (Clazz clazz : classes) {
            System.out.println(clazz.getCid() + "\t\t" + clazz.getC_name());
        }
    }

    public int chooseClassProcess() {
        showAllClass();
        int classId;
        do {
            turnBack = true;
            System.out.println("请输入班级编号：");
            classId = input.nextInt();
            for (Clazz clazz : classes) {
                if (classId == clazz.getCid()) {
                    turnBack = false;
                    break;
                }
            }
            if (turnBack) {
                System.out.println("您输入的班级编号不存在！请输入已存在的班级编号！");
            }
        } while (turnBack);
        return classId;
    }

    public void addTopicProcess() {
        System.out.println("---------------------录入试题---------------------");
        System.out.println("请选择题目类型：");
        int topicType = chooseTopicTypeProcess(0);
        System.out.println("请选择题目难度：");
        int topicLevel = chooseTopicLevelProcess(0);
        System.out.println("请选择题目阶段：");
        int topicStage = chooseTopicStageProcess(0);
        input.nextLine();  //读取前面的回车符\n
        System.out.println("请输入题目：");
        String topic_name = input.nextLine();
        System.out.println("请输入选项（选项间用一个空格隔开）：");
        String topic_opts = input.nextLine();
        System.out.println("请输入正确答案：");
        String real_answer = input.next();
        System.out.println("请输入题目解析：");
        String topic_analysis = input.next();
        if (topicDao.doTopic(new Topic(topic_name, topic_opts, real_answer, topic_analysis, topicType, topicLevel, topicStage))) {
            System.out.println("试题录入成功！");
        }
    }


    private int chooseTopicStageProcess(int style) {
        stages = stageDao.findAllStage();
        int stageId;
        if (style != 1) {
            stages.remove(0);
        }
        for (TopicStage stage : stages) {
            System.out.println(stage.getSid() + "." + stage.getStage_name());
        }
        do {
            turnBack = true;
            System.out.println("请选择：");
            stageId = input.nextInt();
            for (TopicStage stage : stages) {
                if (stageId == stage.getSid()) {
                    turnBack = false;
                    break;
                }
            }
            if (turnBack) {
                System.out.println("阶段选择错误，请重新选择！");
            }
        } while (turnBack);
        return stageId;
    }

    private int chooseTopicLevelProcess(int style) {
        levels = levelDao.findAllLevel();
        int levelId;
        if (style != 1) {
            levels.remove(0);
        }
        for (TopicLevel level : levels) {
            System.out.println(level.getLid() + "." + level.getLevel_name());
        }
        do {
            turnBack = true;
            System.out.println("请选择：");
            levelId = input.nextInt();
            for (TopicLevel level : levels) {
                if (levelId == level.getLid()) {
                    turnBack = false;
                    break;
                }
            }
            if (turnBack) {
                System.out.println("难度选择错误，请重新选择！");
            }
        } while (turnBack);
        return levelId;
    }

    private int chooseTopicTypeProcess(int style) {
        types = typeDao.findAllType();
        int typeId;
        if (style != 1) {
            types.remove(0);
        }
        for (TopicType type : types) {
            System.out.println(type.getTid() + "." + type.getType_name());
        }
        do {
            turnBack = true;
            System.out.println("请选择：");
            typeId = input.nextInt();
            for (TopicType type : types) {
                if (typeId == type.getTid()) {
                    turnBack = false;
                    break;
                }
            }
            if (turnBack) {
                System.out.println("类型选择错误，请重新选择！");
            }
        } while (turnBack);
        return typeId;
    }

    public void addExamProcess() {
        System.out.println("---------------------新建考试---------------------");
        System.out.println("请选择考试班级：");
        int exam_cid = chooseClassProcess();
        System.out.println("请输入试卷名称：");
        String exam_name = input.next();
        System.out.println("请选择试题阶段：");
        int stage_id = chooseTopicStageProcess(1);
        System.out.println("请选择试题难度：");
        int level_id = chooseTopicLevelProcess(1);
        System.out.println("请选择试题类型：");
        int type_id = chooseTopicTypeProcess(1);
        input.nextLine();
        System.out.println("请输入开考时间(格式：2021-09-01 16:00:00)：");
        String exam_startTime = input.nextLine();
        System.out.println("请输入考试时长(单位/分钟)：");
        int exam_len = input.nextInt();
        System.out.println("请输入试题数量：");
        int nums = input.nextInt();
        System.out.println("请输入试题分值：");
        int score = input.nextInt();
        List<Topic> topics = topicDao.findTopic(type_id, level_id, stage_id);  //符合条件的题目
        String topicsId = "";   //存放符合数量条件的试题id
        for (int i = 0; i < nums; i++) {
            topicsId += topics.get(i).getTopic_id() + ",";
        }
        if (examDao.doExam(new Exam(exam_name, exam_startTime, exam_len, nums, score, type_id, exam_cid, level_id, stage_id, topicsId))) {
            System.out.println("试卷创建成功！");
        }
    }

    public void showRecordToTea() {
        System.out.println("请选择要查看的班级：");
        int cid = chooseClassProcess();
        records = recordDao.findRecordByCid(cid);
        if (records.isEmpty()) {
            System.out.println(cid + "班暂无任何考试！");
        } else {
            System.out.println(cid + "班有以下考试：");
            showExamByCid(cid);
            int exam_id;
            do {
                turnBack = true;
                System.out.println("请输入试卷编号：");
                exam_id = input.nextInt();
                for (Exam exam : exams) {
                    if (exam_id == exam.getExam_id()) {
                        turnBack = false;
                        break;
                    }
                }
                if (turnBack) {
                    System.out.println("考试编号输入错误！请重新输入！");
                }
            } while (turnBack);
            System.out.println(cid + "班" + "《" + examDao.findExamById(exam_id).getExam_name() + "》考试情况如下：");
            System.out.println("学号\t\t\t\t姓名\t\t\t分数");
            for (Record record : records) {
                if (record.getExam_id() == exam_id) {
                    System.out.println(record.getStu_id() + "\t\t" + studentDao.findStu_nameById(record.getStu_id()) + "\t\t\t" + record.getScore());
                }
            }
        }
    }

    private void showExamsInfo() {
        System.out.println("---------------------试卷详情---------------------");
        System.out.println("选择班级查看班级已有试卷：");
        int cid = chooseClassProcess();
        if (showExamByCid(cid)) {
            int exam_id;
            do {
                turnBack = true;
                System.out.println("输入试卷编号查看试卷详情：");
                exam_id = input.nextInt();
                for (Exam exam : exams) {
                    if (exam_id == exam.getExam_id()) {
                        turnBack = false;
                        break;
                    }
                }
                if (turnBack) {
                    System.out.println("考试编号输入错误！请重新输入！");
                }
            } while (turnBack);
            Exam exam = examDao.findExamById(exam_id);
            String topics_id = exam.getTopicsId();
            String[] topicsId = topics_id.split(",");   //得到各道题号
            List<Topic> topics = new ArrayList<Topic>();   //保存该试卷的所有题目
            for (String topicId : topicsId) {
                int topic_Id = Integer.parseInt(topicId);
                topics.add(topicDao.findTopicById(topic_Id));
            }
            for (int i = 0; i < topics.size(); i++) {
                System.out.print(i + 1 + "." + topics.get(i).getQuestion()+"("+exam.getScore()+"分)");
                if (topics.get(i).getTid() == 1) {
                    System.out.println("(单选题)");
                } else if (topics.get(i).getTid() == 2) {
                    System.out.println("(多选题)");
                }
                String[] topic_opts = topics.get(i).getOptions().split(" ");
                for (String opt : topic_opts) {
                    System.out.println(opt);
                }
                System.out.println("正确答案为：" + topics.get(i).getAnswer());
                System.out.println("解析："+topics.get(i).getAnalysis());
            }
        }
    }

    public void showStudentPage() {
        System.out.println("---------------------学生界面---------------------");
        do {
            turnBack = false;
            System.out.println("\t\t\t\t\t1.选择考试");
            System.out.println("\t\t\t\t\t2.查看成绩");
            System.out.println("\t\t\t\t\t3.修改密码");
            System.out.println("\t\t\t\t\t4.退出");
            System.out.println("请选择功能：");
            int stuOpt = input.nextInt();
            if (stuOpt < 1 || stuOpt > 4) {
                System.out.println("请重新选择！");
                turnBack = true;
            }
            switch (stuOpt) {
                case 1:
                    doExamProcess();
                    break;
                case 2:
                    showRecordToStu(student.getStu_id());
                    break;
                case 3:
                    updatePsw();
                    break;
                case 4:
                    //signOut();
                    break;
            }
        } while (turnBack);
    }

    public boolean isTime(Exam exam) {
        String start_time = exam.getStart_time();
        long time_len = exam.getTime_len();
        long uLen = time_len * 60 * 1000;    //  考试时长/毫秒
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date st = sf.parse(start_time);    //开考时间
            Date now = sf.parse(sf.format(new Date()));     //现在时间
            System.out.println("开考时间" + sf.format(st));
            System.out.println("现在时间" + sf.format(new Date()));
            long umin = st.getTime() - now.getTime();   //距离开考所剩毫秒
            if (umin > 0) {
                System.out.println("还未到考试时间！");
                return false;
            } else if (umin >= -(uLen / 2)) {
                return true;
            } else if (umin >= -uLen) {
                System.out.println("考试时间过半，不允许加入考试！");
                return false;
            } else {
                System.out.println("考试时间已过！");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void doExamProcess() {
        int exam_id = chooseExamProcess();
        if (exam_id != 0) {
            Exam exam = examDao.findExamById(exam_id);
            if (isTime(exam)) {
                String topics_id = exam.getTopicsId();
                String[] topicsId = topics_id.split(",");
                List<Topic> topics = new ArrayList<Topic>();
                for (String topicId : topicsId) {
                    int topic_Id = Integer.parseInt(topicId);
                    topics.add(topicDao.findTopicById(topic_Id));
                }
                System.out.println("---------------------考试界面---------------------");
                int stu_score = 0;  //学生得分
                int topic_score = exam.getScore();   //题目分值
                String stu_opts = "";    //学生每道题的选择
                for (int i = 0; i < topics.size(); i++) {
                    System.out.print(i + 1 + "." + topics.get(i).getQuestion());
                    if (topics.get(i).getTid() == 1) {
                        System.out.println("(单选题)");
                    } else if (topics.get(i).getTid() == 2) {
                        System.out.println("(多选题)");
                    }
                    String[] topic_opts = topics.get(i).getOptions().split(" ");
                    for (String opt : topic_opts) {
                        System.out.println(opt);
                    }
                    System.out.print("您的答案为：");
                    String stu_ans = input.next().toUpperCase(Locale.ROOT);
                    if (stu_ans.equals(topics.get(i).getAnswer())) {
                        stu_score += topic_score;
                    }
                    stu_opts += stu_ans + ",";
                }
                System.out.println("考试结束，正在提交你的试卷...");
                if (recordDao.doRecord(new Record(student.getStu_id(), exam_id, student.getCid(), stu_opts, stu_score))) {
                    System.out.println("试卷提交成功！");
                }
            }
        }
    }

    public int chooseExamProcess() {
        System.out.println("你所在班级有以下考试：");
        if (showExamByCid(student.getCid())) {
            int exam_id;
            do {
                turnBack = true;
                System.out.println("请输入考试编号进入考试：");
                exam_id = input.nextInt();
                if (recordDao.findRecordByU_idAndExam_id(student.getStu_id(), exam_id)) {
                    System.out.println("您已完成该考试！不能重复考试！");
                    System.out.println("正在返回学生功能界面...");
                    return 0;
                } else {
                    for (Exam exam : exams) {
                        if (exam_id == exam.getExam_id()) {
                            turnBack = false;
                            break;
                        }
                    }
                    if (turnBack) {
                        System.out.println("考试编号输入错误！请重新输入！");
                    }
                }
            } while (turnBack);
            return exam_id;
        }
        return 0;
    }

    public boolean showExamByCid(int cid) {
        exams = examDao.findExamByCid(cid);
        if (exams.isEmpty()) {
            System.out.println("该班级无考试...");
            return false;
        } else {
            System.out.println("考试编号\t\t考试名称\t\t\t考试时间\t\t\t考试时长/分钟");
            for (Exam exam : exams) {
                System.out.println(exam.getExam_id() + "\t\t" + exam.getExam_name() + "\t\t" + exam.getStart_time() + "\t\t" + exam.getTime_len());
            }
            return true;
        }
    }

    public void showRecordToStu(int stu_id) {
        System.out.println("---------------------考试查看---------------------");
        records = recordDao.findRecordById(stu_id);
        if (records.isEmpty()) {
            System.out.println("暂无考试成绩可查询...");
            return;
        }
        System.out.println("考试编号\t\t考试名称\t\t\t分数");
        for (Record record : records) {
            System.out.println(record.getExam_id() + "\t\t\t" + examDao.findExamById(record.getExam_id()).getExam_name() + "\t\t\t" + record.getScore());
        }
        int exam_id;
        String[] stu_opts = null;  //保存学生各题的答案
        do {
            turnBack = true;
            System.out.println("输入对应考试编号查看考试详情：");
            exam_id = input.nextInt();
            for (Record record : records) {
                if (exam_id == record.getExam_id()) {
                    turnBack = false;
                    String opts = record.getOpts();
                    stu_opts = opts.split(",");  //得到学生各题的答案
                    break;
                }
            }
            if (turnBack) {
                System.out.println("请输入正确的考试编号！");
            }
        } while (turnBack);
        System.out.println("---------------------考试详情---------------------");
        Exam exam = examDao.findExamById(exam_id);
        String topics_id = exam.getTopicsId();
        String[] topicsId = topics_id.split(",");   //得到各道题号
        List<Topic> topics = new ArrayList<Topic>();   //保存该试卷的所有题目
        for (String topicId : topicsId) {
            int topic_Id = Integer.parseInt(topicId);
            topics.add(topicDao.findTopicById(topic_Id));
        }
        for (int i = 0; i < topics.size(); i++) {
            System.out.print(i + 1 + "." + topics.get(i).getQuestion()+"("+exam.getScore()+"分)");
            if (topics.get(i).getTid() == 1) {
                System.out.println("(单选题)");
            } else if (topics.get(i).getTid() == 2) {
                System.out.println("(多选题)");
            }
            String[] topic_opts = topics.get(i).getOptions().split(" ");
            for (String opt : topic_opts) {
                System.out.println(opt);
            }
            System.out.println("正确答案为：" + topics.get(i).getAnswer());
            System.out.println("你的答案为：" + stu_opts[i]);
            System.out.println("解析："+topics.get(i).getAnalysis());
        }
    }

    public void updatePsw() {
        System.out.println("---------------------密码更改---------------------");
        do {
            turnBack = false;
            System.out.println("请输入旧密码：");
            String password = input.next();
            if (password.equals(student.getStu_password())) {
                System.out.println("请输入新密码：");
                String newPsd = input.next();
                studentDao.updateStudent(student, newPsd);
                student = studentDao.findStudent(student.getStu_id(), newPsd);
            } else {
                System.out.println("密码错误，请重新输入！");
                turnBack = true;
            }
        } while (turnBack);
        System.out.println("密码修改成功！");
    }
}
