package com.j16.dao.impl;

import com.j16.dao.AdminDao;
import com.j16.pojo.*;
import com.j16.util.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户名查重
 *
 * @author Master
 */
public class AdminDaoImpl implements AdminDao {


    /**
     * 校验管理员用户名是否已存在 不含已注销
     *
     * @param userName
     * @return
     */
    @Override
    public boolean selectAdmin(String userName) {
        String sql = " select * from admin where userName=? and  mark=1";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return false;
    }

    /**
     * 校验管理员用户名是否已存在 含已注销
     *
     * @param userName
     * @return
     */
    @Override
    public boolean selectAdmins(String userName) {
        String sql = " select * from admin where userName=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return false;
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
        String sql = "update admin set mark=1,userPwd=? where userName=? ";
        return BaseDao.additionsDeletionsModifications(sql, userPwd, userName);
    }

    /**
     * 获取管理员对象
     *
     * @param userName
     * @return
     */
    @Override
    public Admin getAdmin(String userName) {
        Admin get_Admin = new Admin();
        String sql = " select * from admin where userName=? and mark=1";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next()) {
                get_Admin.setId(rs.getInt(1));
                get_Admin.setUserName(rs.getString(2));
                get_Admin.setUserPwd(rs.getString(3));
                get_Admin.setAvatar(rs.getString(4));
                get_Admin.setMark(rs.getInt(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeAll(conn, ps, rs);
        }
        return get_Admin;
    }


    /**
     * 管理员注册
     *
     * @param admin
     * @return
     */
    @Override
    public int registerAdmin(Admin admin) {
        String sql = "insert into admin values(null,?,?,null,1)";
        return BaseDao.additionsDeletionsModifications(sql, admin.getUserName(), admin.getUserPwd());
    }

    /**
     * 获取所有学生信息
     *
     * @return
     */
    @Override
    public List<Student> obtainStudent() {
        List<Student> students = new ArrayList<>();
        String sql = "select * from student";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentNo(rs.getInt("studentNo"));
                student.setStudentName(rs.getString("studentName"));
                student.setLoginPwd(rs.getString("loginPwd"));
                student.setSex(rs.getString("sex"));
                student.setGradeId(rs.getInt("gradeId"));
                student.setPhone(rs.getString("phone"));
                student.setAddress(rs.getString("address"));
                student.setBornDate(rs.getString("bornDate"));
                student.setEmail(rs.getString("email"));
                student.setIdentityCard(rs.getString("identityCard"));
                student.setMark(rs.getInt("mark"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return students;
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
        List<Student> students = new ArrayList<>();
        String sql = "select * from student where mark=1 limit ?,?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, (pageIndex - 1) * pageSize);
            ps.setObject(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setStudentNo(rs.getInt("studentNo"));
                student.setStudentName(rs.getString("studentName"));
                student.setLoginPwd(rs.getString("loginPwd"));
                student.setSex(rs.getString("sex"));
                student.setGradeId(rs.getInt("gradeId"));
                student.setPhone(rs.getString("phone"));
                student.setAddress(rs.getString("address"));
                student.setBornDate(rs.getString("bornDate"));
                student.setEmail(rs.getString("email"));
                student.setIdentityCard(rs.getString("identityCard"));
                student.setMark(rs.getInt("mark"));
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return students;
    }


    /**
     * 查询学生总数
     *
     * @return
     */
    @Override
    public int countStudent() {
        String sql = "select count(*) from student where mark=1";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
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
        List<StudentAttached> students = new ArrayList<>();
        String sql = "select student.studentNo,student.studentName,student.loginPwd,student.sex,grade.gradeName,student.phone,student.address,student.bornDate,student.email,student.identityCard,student.mark from student  left join grade on student.gradeId = grade.gradeId where student.mark=1 limit ?,?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, (pageIndex - 1) * pageSize);
            ps.setObject(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentAttached studentAttached = new StudentAttached();
                studentAttached.setStudentNo(rs.getInt("studentNo"));
                studentAttached.setStudentName(rs.getString("studentName"));
                studentAttached.setLoginPwd(rs.getString("loginPwd"));
                studentAttached.setSex(rs.getString("sex"));
                studentAttached.setGradeName(rs.getString("gradeName"));
                studentAttached.setPhone(rs.getString("phone"));
                studentAttached.setAddress(rs.getString("address"));
                studentAttached.setBornDate(rs.getString("bornDate"));
                studentAttached.setEmail(rs.getString("email"));
                studentAttached.setIdentityCard(rs.getString("identityCard"));
                studentAttached.setMark(rs.getInt("mark"));
                students.add(studentAttached);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    @Override
    public Student getStudents(int studentNo) {
        Student student = new Student();
        String sql = "select * from student where studentNo=? and mark=1";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, studentNo);
            rs = ps.executeQuery();
            while (rs.next()) {
                student.setStudentNo(rs.getInt("studentNo"));
                student.setStudentName(rs.getString("studentName"));
                student.setLoginPwd(rs.getString("loginPwd"));
                student.setSex(rs.getString("sex"));
                student.setGradeId(rs.getInt("gradeId"));
                student.setPhone(rs.getString("phone"));
                student.setAddress(rs.getString("address"));
                student.setBornDate(rs.getString("bornDate"));
                student.setEmail(rs.getString("email"));
                student.setIdentityCard(rs.getString("identityCard"));
                student.setMark(rs.getInt("mark"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return student;
    }

    /**
     * 添加学生-----不填学号时调用
     *
     * @param student
     * @return
     */
    @Override
    public int addStudent(Student student) {
        String sql = "insert into student values(null,?,?,?,?,?,?,?,?,?,1)";
        return BaseDao.additionsDeletionsModifications(sql, student.getStudentName(), student.getLoginPwd(), student.getSex()
                , student.getGradeId(), student.getPhone(), student.getAddress(), student.getBornDate(), student.getEmail(), student.getIdentityCard());
    }

    /**
     * 添加时要判断学生是否曾经存在
     *
     * @param IdentityCard
     * @return
     */
    @Override
    public boolean judgeIdentityCard(String IdentityCard) {
        String sql = "select * from student where identityCard=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, IdentityCard);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 删除学生
     *
     * @param studentNo
     * @return
     */
    @Override
    public int deleteStudent(int studentNo) {
        String sql = "update student set mark=0 where studentNo=? ";
        return BaseDao.additionsDeletionsModifications(sql, studentNo);
    }


    /**
     * 修改学生
     *
     * @param student
     * @return
     */
    @Override
    public int updateStudent(Student student) {
        String sql = "update student set studentName=?,loginPwd=?,sex=?,gradeId=?,phone=?,address=?,bornDate=?,email=?, identityCard=? where studentNo=?";
        return BaseDao.additionsDeletionsModifications(sql, student.getStudentName(), student.getLoginPwd(), student.getSex(), student.getGradeId()
                , student.getPhone(), student.getAddress(), student.getBornDate(), student.getEmail(), student.getIdentityCard(), student.getStudentNo());
    }


    /**
     * 获取年级的中文名称
     *
     * @param studentNo
     * @return
     */
    @Override
    public StudentAttached getGradeChinese(int studentNo) {
        StudentAttached student = new StudentAttached();
        String sql = "select student.studentNo,student.studentName,student.loginPwd,student.sex,grade.gradeName,student.phone,student.address,student.bornDate,student.email,student.identityCard,student.mark from student  left join grade on student.gradeId = grade.gradeId where student.mark=1 and student.studentNo = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, studentNo);
            rs = ps.executeQuery();
            while (rs.next()) {
                student.setStudentNo(rs.getInt("studentNo"));
                student.setStudentName(rs.getString("studentName"));
                student.setLoginPwd(rs.getString("loginPwd"));
                student.setSex(rs.getString("sex"));
                student.setGradeName(rs.getString("gradeName"));
                student.setPhone(rs.getString("phone"));
                student.setAddress(rs.getString("address"));
                student.setBornDate(rs.getString("bornDate"));
                student.setEmail(rs.getString("email"));
                student.setIdentityCard(rs.getString("identityCard"));
                student.setMark(rs.getInt("mark"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return student;
    }


    /**
     * 班级索引
     *
     * @return
     */
    @Override
    public List<Grade> indexClassGrade() {
        List<Grade> grades = new ArrayList<>();
        String sql = "select * from grade";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Grade grade = new Grade();
                grade.setGradeId(rs.getInt(1));
                grade.setGradeName(rs.getString(2));
                grades.add(grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return grades;
    }


    /**
     * 获取男女比例
     *
     * @return
     */
    @Override
    public int getTheRatioOfMaleToFemale() {
        String sql = "select (select count(sex) from student where sex='男')>(select count(sex) from student where sex='女')";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 获取班级比例
     *
     * @return
     */
    @Override
    public List<Statistics> getGradeRatio() {
        List<Statistics> stats = new ArrayList<>();
        String sql = "select grade.gradeId,count(grade.gradeId) from student left join grade on student.gradeId = grade.gradeId  GROUP BY grade.gradeId";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Statistics statistics = new Statistics();
                statistics.setGradeId(rs.getInt(1));
                statistics.setCountGradeId(rs.getInt(2));
                stats.add(statistics);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stats;
    }


    /**
     * 注册时如果学生曾经存在则激活以前的学号
     *
     * @param student
     * @return
     */
    @Override
    public int updateStudentState(Student student) {
        String sql = "update student set studentName=?,loginPwd=?,sex=?, gradeId=?,phone=?,address=?,bornDate=?,email=?,mark=1 where studentNo=?";
        return BaseDao.additionsDeletionsModifications(sql,student.getStudentName(),student.getLoginPwd(),student.getSex()
        ,student.getGradeId(),student.getPhone(),student.getAddress(),student.getBornDate(),student.getEmail(),student.getStudentNo());
    }


    /**
     * 通过学生身份号查询学生ID
     *
     * @param identityCard
     * @return
     */
    @Override
    public int obtainStudentNo(String identityCard) {
        String sql = "select studentNo from student where identityCard=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, identityCard);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 查询头像文件链接是否存
     *
     * @param adminUserName
     * @return
     */
    @Override
    public boolean selectAvatar(String adminUserName) {
        String sql = "select * from admin where userName=? and !ISNULL(avatar)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, adminUserName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
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
        String sql = "update admin set avatar=? where id=?";
        return BaseDao.additionsDeletionsModifications(sql, avatar, adminId);
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
        String sql = "select * from admin where userName=? and userPwd=?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user);
            ps.setObject(2, pwd);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
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
        String sql = "update admin set  userPwd =? where userName=?";
        return BaseDao.additionsDeletionsModifications(sql, pwd, user);
    }


    /**
     * 获取课程总数
     *
     * @return
     */
    @Override
    public int getTheTotalNumberOfCourses() {
        String sql = "select count(*) from subject";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    /**
     * 获取学生考试及格率
     *
     * @return
     */
    @Override
    public double getStudentExamPassRates() {
        String sql = "select 1-((select count(*) from result where studentResult<60)/(select count(*) from result));";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public double getTheRatioOfMaleToFemaleValue() {
        String sql = "select (select count(*) from student where sex='男')/(select count(*) from student)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 获取成绩与姓名
     *
     * @return
     */
    @Override
    public List<Echarts> getGradesAndNames() {
        List<Echarts> listEcharts = new ArrayList<>();
        String sql = "select student.studentName,result.studentResult from student left outer join result on student.studentNo=result.studentNo  where !ISNULL(result.studentResult) order by result.studentResult";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Echarts echarts = new Echarts();
                echarts.setName(rs.getString(1));
                echarts.setValue(rs.getInt(2));
                listEcharts.add(echarts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listEcharts;
    }

    /**
     * 获取热门的课程
     *
     * @return
     */
    @Override
    public List<Echarts> getPopularCourses() {
        List<Echarts> listEcharts = new ArrayList<>();
        String sql = "select subject.subjectName,count(subject.subjectNo) as counts from result left outer join subject on  result.subjectNo = subject.subjectNo group by subject.subjectNo HAVING counts ORDER by counts desc";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Echarts echarts = new Echarts();
                echarts.setName(rs.getString(1));
                echarts.setValue(rs.getInt(2));
                listEcharts.add(echarts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listEcharts;
    }

    /**
     * 获取年级的平均分
     *
     * @return
     */
    @Override
    public List<Echarts> getTheGradePointAverage() {
        List<Echarts> listEcharts = new ArrayList<>();
        String sql = "select grade.gradeName,avg(studentResult) from result left outer join grade  on grade.gradeId=result.subjectNo group by subjectNo";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Echarts echarts = new Echarts();
                echarts.setName(rs.getString(1));
                echarts.setValue((int) rs.getDouble(2));
                listEcharts.add(echarts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listEcharts;
    }


    /**
     * 查询学生是否存在
     *
     * @param studentNo
     * @return
     */
    @Override
    public boolean selectStudent(int studentNo) {
        String sql = "select * from student where studentNo=? and mark=1";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, studentNo);
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 根据学号获取学生名称
     *
     * @param studentNo
     * @return
     */
    @Override
    public String getStudentNameUsingStudentNumber(int studentNo) {
        String name = "";
        String sql = "select studentName from student where studentNo=?;";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, studentNo);
            rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return name;
    }


    /**
     * 获取课程名称和成绩
     *
     * @param studentNo
     * @return
     */
    @Override
    public List<Echarts> getCourseNameAndGrade(int studentNo) {
        String sql = "select su.subjectName,re.studentResult from student  as st left outer join result  as re on st.studentNo = re.studentNo left outer join subject as su on re.subjectNo = su.subjectNo where !ISNULL(re.studentResult) AND st.studentNo=? AND st.mark=1";
        List<Echarts> echarts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, studentNo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Echarts echart = new Echarts();
                echart.setName(rs.getString(1));
                echart.setValue((int) rs.getDouble(2));
                echarts.add(echart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return echarts;
    }

    /**
     * 更新学生成绩
     *
     * @param studentNo
     * @param echarts
     * @return
     */
    @Override
    public int updateStudentResult(int studentNo,Echarts echarts) {
        String sql = "update student  as st left outer join result  as re on st.studentNo = re.studentNo left outer join subject as su on re.subjectNo = su.subjectNo set re.studentResult=? where !ISNULL(re.studentResult) AND st.studentNo=?  AND su.subjectName=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1,echarts.getValue());
            ps.setObject(2,studentNo);
            ps.setObject(3,echarts.getName());
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                BaseDao.closeAll(conn, ps, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}

