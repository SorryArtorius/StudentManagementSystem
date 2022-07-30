package com.j16.service;

import com.j16.pojo.*;

import java.util.List;

/**
 * @author Master
 * 管理员用户的接口
 */
public interface AdminService {

    /**
     * 查询后台是否存在此用户名 不含已注销
     *
     * @param userName
     * @return
     */
    boolean selectAdmin(String userName);


    /**
     * 查询后台是否存在此用户名 含已注销
     *
     * @param userName
     * @return
     */
    boolean selectAdmins(String userName);

    /**
     * 如果用户已存在 则激活重新使用
     *
     * @param userName
     * @param userPwd
     * @return
     */
    int updateAdminState(String userName, String userPwd);

    /**
     * 登录验证
     *
     * @param userName
     * @param userPwd
     * @return
     */
    Admin getAdmin(String userName, String userPwd);


    /**
     * 注册
     *
     * @param admin
     * @return
     */
    int registerAdmin(Admin admin);

    /**
     * 获取所有学生信息
     *
     * @return
     */
    List<Student> obtainStudent();


    /**
     * 分页查询学生信息
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<Student> paginationStudent(int pageIndex, int pageSize);


    /**
     * 分页查询学生信息 多表查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<StudentAttached> paginationStudentAttached(int pageIndex, int pageSize);


    /**
     * 获得年级的中文名称 顺便获对象
     *
     * @param studentNo
     * @return
     */
    StudentAttached getGradeChinese(int studentNo);

    /**
     * 获取学生信息的总条数
     *
     * @return
     */
    int countStudent();

    /**
     * 获取需要修改信息的学生所有信息
     *
     * @param studentNo
     * @return
     */
    Student getStudents(int studentNo);


    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    int addStudent(Student student);


    /**
     * 删除学生
     *
     * @param studentNo
     * @return
     */
    int deleteStudent(int studentNo);

    /**
     * 修改学生
     *
     * @param student
     * @return
     */
    int updateStudent(Student student);


    /**
     * 索引班级
     *
     * @return
     */
    List<Grade> indexClassGrade();


    /**
     * 获取男女比例
     *
     * @return
     */
    int getTheRatioOfMaleToFemale();

    /**
     * 获取班级比例
     *
     * @return
     */
    List<Statistics> getGradeRatio();


    /**
     * 添加时要判断学生是否曾经存在
     *
     * @param IdentityCard
     * @return
     */
    boolean judgeIdentityCard(String IdentityCard);


    /**
     * 如果学生曾经存在 则更新状态 恢复激活
     *
     * @param student
     * @return
     */
    int updateStudentState(Student student);


    /**
     * 根据学生身份证获取学生的学生ID
     *
     * @param identityCard
     * @return
     */
    int obtainStudentNo(String identityCard);


    /**
     * 查询头像文件链接是否存在
     *
     * @param adminUserName
     * @return
     */
    boolean selectAvatar(String adminUserName);

    /**
     * 上传时顺便将路径传输
     *
     * @param avatar
     * @param adminId
     * @return
     */
    int fileAvatarUpdate(String avatar, int adminId);


    /**
     * 判断原始密码是否正确
     *
     * @param user
     * @param pwd
     * @return
     */
    boolean passwordComparison(String user, String pwd);

    /**
     * 更新密码
     *
     * @param user
     * @param pwd
     * @return
     */
    int updateAdminPassword(String user, String pwd);


    /**
     * 获取课程总数
     *
     * @return
     */
    int getTheTotalNumberOfCourses();

    /**
     * 获取学生考试及格率
     *
     * @return
     */
    double getStudentExamPassRates();

    /**
     * 获取男女比例值
     *
     * @return
     */
    double getTheRatioOfMaleToFemaleValue();


    /**
     * 获取成绩与姓名
     *
     * @return
     */
    List<Echarts> getGradesAndNames();


    /**
     * 获取热门的课程
     *
     * @return
     */
    List<Echarts> getPopularCourses();


    /**
     * 获取年级的平均分
     *
     * @return
     */
    List<Echarts> getTheGradePointAverage();


    /**
     * 查询学生是否存在
     *
     * @param studentNo
     * @return
     */
    boolean selectStudent(int studentNo);


    /**
     * 根据学号获取学生名称
     *
     * @param studentNo
     * @return
     */
    String getStudentNameUsingStudentNumber(int studentNo);


    /**
     * 获取课程名称和成绩
     *
     * @param studentNo
     * @return
     */
    List<Echarts> getCourseNameAndGrade(int studentNo);


    /**
     * 更新学生成绩
     *
     * @param studentNo
     * @param echarts
     * @return
     */
    int updateStudentResult(int studentNo, Echarts echarts);


    /**
     * 对学生姓名进行模糊查询
     *
     * @param studentName
     * @return
     */
    List<Student> fuzzyQueryStudent(String studentName);
}
