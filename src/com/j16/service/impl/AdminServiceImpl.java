package com.j16.service.impl;

import com.j16.dao.impl.AdminDaoImpl;
import com.j16.pojo.*;
import com.j16.service.AdminService;

import java.util.List;

import static java.util.Objects.isNull;

/**
 * 管理员类的实现
 *
 * @author Master
 */
public class AdminServiceImpl implements AdminService {
    AdminDaoImpl adminDaoImpl = new AdminDaoImpl();


    /**
     * 判断数据库是否存在此用户
     *
     * @param userName
     * @return
     */
    @Override
    public boolean selectAdmin(String userName) {
        return adminDaoImpl.selectAdmin(userName);
    }

    /**
     * 查询后台是否存在此用户名 含已注销
     *
     * @param userName
     * @return
     */
    @Override
    public boolean selectAdmins(String userName) {
        return adminDaoImpl.selectAdmins(userName);
    }

    /**
     * 如果用户已存在 则激活重新使用
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @Override
    public int updateAdminState(String userName, String userPwd) {
        return adminDaoImpl.updateAdminState(userName, userPwd);
    }

    /**
     * 登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @Override
    public Admin getAdmin(String userName, String userPwd) {
        Admin admin = adminDaoImpl.getAdmin(userName);
        if (isNull(admin.getUserName())) {
            return null;
        } else {
            if (admin.getUserPwd().equals(userPwd)) {
                return admin;
            } else {
                return null;
            }
        }
    }

    /**
     * 注册
     *
     * @param admin
     * @return
     */
    @Override
    public int registerAdmin(Admin admin) {
        return adminDaoImpl.registerAdmin(admin);
    }


    /**
     * 获取所有学生信息
     *
     * @return
     */
    @Override
    public List<Student> obtainStudent() {
        return adminDaoImpl.obtainStudent();
    }


    /**
     * 分页查询学生信息
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<Student> paginationStudent(int pageIndex, int pageSize) {
        return adminDaoImpl.paginationStudent(pageIndex, pageSize);
    }


    /**
     * 查询学生总数
     *
     * @return
     */
    @Override
    public int countStudent() {
        return adminDaoImpl.countStudent();
    }

    /**
     * 分页查询学生信息 多表查询
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public List<StudentAttached> paginationStudentAttached(int pageIndex, int pageSize) {
        return adminDaoImpl.paginationStudentAttached(pageIndex, pageSize);
    }

    @Override
    public Student getStudents(int studentNo) {
        return adminDaoImpl.getStudents(studentNo);
    }


    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    @Override
    public int addStudent(Student student) {
        return adminDaoImpl.addStudent(student);
    }

    /**
     * 删除学生
     *
     * @param studentNo
     * @return
     */
    @Override
    public int deleteStudent(int studentNo) {
        return adminDaoImpl.deleteStudent(studentNo);
    }

    /**
     * 修改学生
     *
     * @param student
     * @return
     */
    @Override
    public int updateStudent(Student student) {
        return adminDaoImpl.updateStudent(student);
    }


    /**
     * 根据id搜索字符
     *
     * @param studentNo
     * @return
     */
    @Override
    public StudentAttached getGradeChinese(int studentNo) {
        return adminDaoImpl.getGradeChinese(studentNo);
    }


    /**
     * 班级索引
     *
     * @return
     */
    @Override
    public List<Grade> indexClassGrade() {
        return adminDaoImpl.indexClassGrade();
    }


    /**
     * 获取男女比例
     *
     * @return
     */
    @Override
    public int getTheRatioOfMaleToFemale() {
        return adminDaoImpl.getTheRatioOfMaleToFemale();
    }


    /**
     * 获取班级比例
     *
     * @return
     */
    @Override
    public List<Statistics> getGradeRatio() {
        return adminDaoImpl.getGradeRatio();
    }

    /**
     * 添加时判断学生是否曾经存在
     *
     * @param IdentityCard
     * @return
     */
    @Override
    public boolean judgeIdentityCard(String IdentityCard) {
        return adminDaoImpl.judgeIdentityCard(IdentityCard);
    }


    /**
     * 注册时如果学生曾经存在则激活以前的学号并且更新数据
     *
     * @param student
     * @return
     */
    @Override
    public int updateStudentState(Student student) {
        return adminDaoImpl.updateStudentState(student);
    }


    /**
     * 根据学生身份证获取学生的学生ID
     *
     * @param identityCard
     * @return
     */
    @Override
    public int obtainStudentNo(String identityCard) {
        return adminDaoImpl.obtainStudentNo(identityCard);
    }


    /**
     * 查询头像是否存在
     *
     * @param adminUserName
     * @return
     */
    @Override
    public boolean selectAvatar(String adminUserName) {
        return adminDaoImpl.selectAvatar(adminUserName);
    }


    /**
     * 上传时顺便将路径传输
     *
     * @param avatar
     * @param adminId
     * @return
     */
    @Override
    public int fileAvatarUpdate(String avatar, int adminId) {
        return adminDaoImpl.fileAvatarUpdate(avatar, adminId);
    }

    /**
     * 判断原始密码是否正确
     *
     * @param user
     * @param pwd
     * @return
     */
    @Override
    public boolean passwordComparison(String user, String pwd) {
        return adminDaoImpl.passwordComparison(user, pwd);
    }

    /**
     * 更新密码
     *
     * @param user
     * @param pwd
     * @return
     */
    @Override
    public int updateAdminPassword(String user, String pwd) {
        return adminDaoImpl.updateAdminPassword(user, pwd);
    }

    /**
     * 获取学生总数
     *
     * @return
     */
    @Override
    public int getTheTotalNumberOfCourses() {
        return adminDaoImpl.getTheTotalNumberOfCourses();
    }

    /**
     * 获取学生考试及格率
     *
     * @return
     */
    @Override
    public double getStudentExamPassRates() {
        return adminDaoImpl.getStudentExamPassRates();
    }


    /**
     * 获取男女比例值
     *
     * @return
     */
    @Override
    public double getTheRatioOfMaleToFemaleValue() {
        return adminDaoImpl.getTheRatioOfMaleToFemaleValue();
    }


    /**
     * 获取成绩与姓名
     *
     * @return
     */
    @Override
    public List<Echarts> getGradesAndNames() {
        return adminDaoImpl.getGradesAndNames();
    }


    /**
     * 获取热门的课程
     *
     * @return
     */
    @Override
    public List<Echarts> getPopularCourses() {
        return adminDaoImpl.getPopularCourses();
    }

    /**
     * 获取年级平均分
     *
     * @return
     */
    @Override
    public List<Echarts> getTheGradePointAverage() {
        return adminDaoImpl.getTheGradePointAverage();
    }


    /**
     * 查询学生是否存在
     *
     * @param studentNo
     * @return
     */
    @Override
    public boolean selectStudent(int studentNo) {
        return adminDaoImpl.selectStudent(studentNo);
    }

    /**
     * 根据学号获取学生名称
     *
     * @param studentNo
     * @return
     */
    @Override
    public String getStudentNameUsingStudentNumber(int studentNo) {
        return adminDaoImpl.getStudentNameUsingStudentNumber(studentNo);
    }

    /**
     * 获取课程名称和成绩
     *
     * @param studentNo
     * @return
     */
    @Override
    public List<Echarts> getCourseNameAndGrade(int studentNo) {
        return adminDaoImpl.getCourseNameAndGrade(studentNo);
    }

    /**
     * 更新学生成绩
     *
     * @param studentNo
     * @param echarts
     * @return
     */
    @Override
    public int updateStudentResult(int studentNo, Echarts echarts) {
        return adminDaoImpl.updateStudentResult(studentNo, echarts);
    }

    /**
     * 对学生姓名进行模糊查询
     *
     * @param studentName
     * @return
     */
    @Override
    public List<Student> fuzzyQueryStudent(String studentName) {
        return adminDaoImpl.fuzzyQueryStudent(studentName);
    }
}
