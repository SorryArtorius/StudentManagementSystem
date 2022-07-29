package com.j16.servlet;

import com.alibaba.fastjson.JSONObject;
import com.j16.pojo.*;
import com.j16.service.impl.AdminServiceImpl;
import com.j16.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.substring;

/**
 * @author Master
 * 主操作类
 */
@WebServlet("/adminServlet")
public class AdminServlet extends HttpServlet {

    /**
     * 定义修改专用学号
     */
    public int studentNoEdit = 0;

    /**
     * 首页图形数据加载
     *
     * @param resp
     * @throws IOException
     */
    private static void homepageDataAcquisition(HttpServletResponse resp) throws IOException {
        /**
         * 获取课程数量
         */
        int getTheTotalNumberOfCourses = new AdminServiceImpl().getTheTotalNumberOfCourses();
        /**
         * 获取学生总数
         */
        int countStudent = new AdminServiceImpl().countStudent();
        /**
         * 考试及格率
         */
        double getStudentExamPassRates = new AdminServiceImpl().getStudentExamPassRates();
        /**
         * 男女比例值
         */
        double getTheRatioOfMaleToFemaleValue = new AdminServiceImpl().getTheRatioOfMaleToFemaleValue();
        /**
         * 获取成绩与姓名
         */
        List<Echarts> echarts = new AdminServiceImpl().getGradesAndNames();
        /**
         * 获取热门的科目信息
         */
        List<Echarts> echartsSuject = new AdminServiceImpl().getPopularCourses();
        List<Echarts> echartsTheGradePointAverage = new AdminServiceImpl().getTheGradePointAverage();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getTheTotalNumberOfCourses", getTheTotalNumberOfCourses);
        jsonObject.put("countStudent", countStudent);
        jsonObject.put("getStudentExamPassRates", getStudentExamPassRates);
        jsonObject.put("getTheRatioOfMaleToFemaleValue", getTheRatioOfMaleToFemaleValue);
        jsonObject.put("echarts", echarts);
        jsonObject.put("echartsSuject", echartsSuject);
        jsonObject.put("echartsTheGradePointAverage", echartsTheGradePointAverage);
        String dataCollection = jsonObject.toJSONString();
        resp.getWriter().write(dataCollection);
        System.out.println(dataCollection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tag = null;


        if (req.getParameter("tag") != null) {
            tag = req.getParameter("tag");
        }
        if ("show".equals(tag)) {
            showIndex(req, resp);
        } else if ("cookie".equals(tag)) {
            getCookie(req, resp);
        } else if ("tables".equals(tag)) {
            showTable(req, resp);
        } else if ("charts".equals(tag)) {
            showCharts(req, resp);
        } else if ("showTables".equals(tag)) {
            getPagination(req, resp);
        } else if ("logout".equals(tag)) {
            logout(req, resp);
        } else if ("cards".equals(tag)) {
            showCards(req, resp);
        } else if ("tableAdd".equals(tag)) {
            showButtons(req, resp);
        } else if ("tablesEdit".equals(tag)) {
            if (req.getParameter("studentNo") != null) {
                studentNoEdit = Integer.parseInt(req.getParameter("studentNo"));
            }
            showTablesEdit(req, resp);
        } else if ("tablesEdits".equals(tag)) {
            tablesEdits(resp);
        } else if ("selectGradeAndSex".equals(tag)) {
            selectGradeAndSex(resp);
        } else if ("deleteStudent".equals(tag)) {
            deleteStudent(req, resp);
        } else if ("passwordSelect".equals(tag)) {
            String oldPassword = req.getParameter("oldPassword");
            Admin admin = (Admin) req.getSession().getAttribute("admin");
            boolean booleanPassword = new AdminServiceImpl().passwordComparison(admin.getUserName(), oldPassword);
            resp.getWriter().write(booleanPassword + "");
        } else if ("homepageDataAcquisition".equals(tag)) {
            homepageDataAcquisition(resp);
        } else if ("studentNoSelect".equals(tag)) {
            int studentNo = Integer.parseInt(req.getParameter("studentNoSelect"));
            boolean studentNoSelect = new AdminServiceImpl().selectStudent(studentNo);
            resp.getWriter().write(studentNoSelect + "");
        } else if ("loadGradesAndCourses".equals(tag)) {
            int studentNo = Integer.parseInt(req.getParameter("studentNoSelect"));
            String studentName = new AdminServiceImpl().getStudentNameUsingStudentNumber(studentNo);
            List<Echarts> loadGradesAndCourses = new AdminServiceImpl().getCourseNameAndGrade(studentNo);
            req.getSession().setAttribute("loadGradesAndCoursesSize", loadGradesAndCourses.size());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("studentName", studentName);
            jsonObject.put("loadGradesAndCourses", loadGradesAndCourses);
            String json = jsonObject.toJSONString();
            resp.getWriter().write(json);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tag = null;
        if (req.getParameter("tag") != null) {
            tag = req.getParameter("tag");
        }
        if ("show".equals(tag)) {
            showIndex(req, resp);
        } else if ("updateTable".equals(tag)) {
            updateStudent(req, resp);
        } else if ("addStudent".equals(tag)) {
            addStudent(req, resp);
        } else if ("updateAdminPassword".equals(tag)) {
            updateAdminPassword(req, resp);
        } else if ("updateStudentResult".equals(tag)) {
            updateStudentResult(req, resp);

        }

    }


    /**
     * 学生成绩修改
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void updateStudentResult(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentNo = Integer.parseInt(req.getParameter("studentNoWow"));
        int loadGradesAndCoursesSize = (int) req.getSession().getAttribute("loadGradesAndCoursesSize");
        int[] studentScore = new int[loadGradesAndCoursesSize];
        for (int i = 0; i < loadGradesAndCoursesSize; i++) {
            studentScore[i] = Integer.parseInt(req.getParameter(String.format("studentScore%d", i)));
        }

        List<Echarts> loadGradesAndCourses = new AdminServiceImpl().getCourseNameAndGrade(studentNo);
        for (int i = 0; i < loadGradesAndCourses.size(); i++) {
            loadGradesAndCourses.get(i).setValue(studentScore[i]);
        }
        for (int i = 0; i < loadGradesAndCourses.size(); i++) {
            new AdminServiceImpl().updateStudentResult(studentNo, loadGradesAndCourses.get(i));
        }
        showButtons(req, resp);
    }

    /**
     * 更新管理员密码
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    private void updateAdminPassword(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
        String oldPassword = req.getParameter("password");
        String password = req.getParameter("newPassword");
        if (adminServiceImpl.passwordComparison(admin.getUserName(), oldPassword)) {
            adminServiceImpl.updateAdminPassword(admin.getUserName(), password);
            logout(req, resp);
        } else {
            showButtons(req, resp);
        }
    }

    /**
     * 退出程序
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /**
         * 注销 清除session
         */
        req.getSession().removeAttribute("admin");
        req.getSession().invalidate();
        System.out.println("已注销登录");
        resp.sendRedirect("login.html");
    }


    /**
     * 读写储存在cookie里的数值
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        Admin admins = adminServiceImpl.getAdmin(admin.getUserName(), admin.getUserPwd());

        String avatar = "";
        if (adminServiceImpl.selectAvatar(admin.getUserName())) {
            avatar = admins.getAvatar();
        } else {
            avatar = "avatar/hea_img.jpeg";
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Admin", admin);
        jsonObject.put("avatar", avatar);
        String jo = jsonObject.toJSONString();
        resp.getWriter().println(jo);
    }


    /**
     * 删除学生
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
        int studentNo = 0;
        if (req.getParameter("studentNo") != null) {
            studentNo = Integer.parseInt(req.getParameter("studentNo"));
        }

        adminServiceImpl.deleteStudent(studentNo);
        showTable(req, resp);
    }

    /**
     * 获取年级与性别
     *
     * @param resp
     * @throws IOException
     */
    private void selectGradeAndSex(HttpServletResponse resp) throws IOException {
        int sex = new AdminServiceImpl().getTheRatioOfMaleToFemale();
        String sexName = "";
        if (sex > 0) {
            sexName = "男";
        } else {
            sexName = "女";
        }
        Statistics statsMax = new Statistics();
        List<Statistics> stats = new AdminServiceImpl().getGradeRatio();
        statsMax.setCountGradeId(0);
        for (int i = 0; i < stats.size(); i++) {
            if (stats.get(i).getCountGradeId() > statsMax.getCountGradeId()) {
                statsMax = stats.get(i);
            }
        }
        /**
         * 获取年级信息
         */
        List<Grade> grade = new AdminServiceImpl().indexClassGrade();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sexName", sexName);
        jsonObject.put("statsMax", statsMax);
        jsonObject.put("grade", grade);
        String js = jsonObject.toJSONString();
        resp.getWriter().write(js);
    }


    /**
     * 添加学生
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void addStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
        Student student = new Student();
        String studentName = req.getParameter("studentName");
        String studentPwd = req.getParameter("studentPwd");
        String studentPwdMd5 = Md5Util.mD5(studentPwd);
        String studentSex = req.getParameter("sex");
        int studentGrade = Integer.parseInt(req.getParameter("grade"));
        String studentPhone = req.getParameter("studentPhone");
        String studentAddress = req.getParameter("studentAddress");
        String studentBornDate = req.getParameter("studentBornDate");
        String studentEmail = req.getParameter("studentEmail");
        String studentIdentityCard = req.getParameter("studentIdentityCard");
        student.setStudentName(studentName);
        student.setLoginPwd(studentPwdMd5);
        student.setSex(studentSex);
        student.setGradeId(studentGrade);
        student.setPhone(studentPhone);
        student.setAddress(studentAddress);
        student.setBornDate(studentBornDate);
        student.setEmail(studentEmail);
        student.setIdentityCard(studentIdentityCard);


        /**
         * 判断学生是否曾经存在
         */
        if (adminServiceImpl.judgeIdentityCard(student.getIdentityCard())) {
            int studentNo = adminServiceImpl.obtainStudentNo(student.getIdentityCard());
            String oldStudentName = adminServiceImpl.getStudentNameUsingStudentNumber(studentNo);
            /**
             * 截取原来的姓名的姓
             */
            String newStudentName = substring(oldStudentName, 0, 1);
            /**
             * 截取姓之后的数据
             */
            studentName = substring(studentName, 1);
            /**
             * 姓名拼接
             */
            newStudentName += studentName;
            student.setStudentName(newStudentName);
            /**
             * 学号还是原来的学号
             */
            student.setStudentNo(studentNo);

            if (adminServiceImpl.updateStudentState(student) > 0) {
                showTable(req, resp);
            }
        } else {
            if (adminServiceImpl.addStudent(student) > 0) {
                showTable(req, resp);
            } else {
                showButtons(req, resp);
            }
        }
    }

    /**
     * 信息修改
     *
     * @param resp
     * @throws IOException
     */
    private void tablesEdits(HttpServletResponse resp) throws IOException {
        Student student = new AdminServiceImpl().getStudents(studentNoEdit);
        StudentAttached studentAttached = new AdminServiceImpl().getGradeChinese(studentNoEdit);
        List<Grade> grades = new AdminServiceImpl().indexClassGrade();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("student", student);
        jsonObject.put("grades", grades);
        jsonObject.put("studentGrade", studentAttached.getGradeName());
        String studentJsonObject = jsonObject.toJSONString();
        resp.getWriter().write(studentJsonObject);
    }


    /**
     * 获取分页属性
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void getPagination(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /**
         * 获取学生总数
         */
        int countStudent = new AdminServiceImpl().countStudent();

        /**
         * 获取前台设置的分页属性 ---起始设置
         */
        int pagesIndex = 0;
        if (req.getParameter("pagesIndex") != null) {
            pagesIndex = Integer.parseInt(req.getParameter("pagesIndex"));
        }

        /**
         * 获取前台设置的分页属性 ---分页量
         */
        int pageSize = 10;
        if (req.getParameter("pageSize") != null) {
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        }

        /**
         * 分页数量
         */
        int pages = (int) Math.ceil((double) countStudent / pageSize);
        List<StudentAttached> students = new AdminServiceImpl().paginationStudentAttached(pagesIndex, pageSize);
        /**
         * 分页处理
         */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("students", students);
        jsonObject.put("pageIndex", pagesIndex);
        jsonObject.put("pageSize", pageSize);
        jsonObject.put("pagesMax", pages);
        jsonObject.put("countStudent", countStudent);
        String student = jsonObject.toJSONString();
        resp.getWriter().write(student);
    }


    /**
     * 学生信息更新方法
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
        Student student = new Student();
        int studentNo = Integer.parseInt(req.getParameter("studentNo"));
        String studentName = req.getParameter("studentName");
        String studentPwd = req.getParameter("studentPwd");
        String studentSex = req.getParameter("studentSex");
        int studentGradeName = Integer.parseInt(req.getParameter("studentGrade"));
        String studentPhone = req.getParameter("studentPhone");
        String studentAddress = req.getParameter("studentAddress");
        String studentBornDate = req.getParameter("studentBornDate");
        String studentEmail = req.getParameter("studentEmail");
        String studentIdentityCard = req.getParameter("studentIdentityCard");
        student.setStudentNo(studentNo);
        student.setStudentName(studentName);
        student.setLoginPwd(studentPwd);
        student.setSex(studentSex);
        student.setGradeId(studentGradeName);
        student.setPhone(studentPhone);
        student.setAddress(studentAddress);
        student.setBornDate(studentBornDate);
        student.setEmail(studentEmail);
        student.setIdentityCard(studentIdentityCard);
        adminServiceImpl.updateStudent(student);
        showTable(req, resp);
    }


    /**
     * 显示Index页面 复用性高 抽离
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user/index.html").forward(req, resp);
//        resp.sendRedirect("user/index.html");
    }

    /**
     * 显示tables页面
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user/tables.html").forward(req, resp);
//        resp.sendRedirect("user/tables.html");
    }

    /**
     * 显示数据页
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showCharts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user/charts.html").forward(req, resp);
//        resp.sendRedirect("user/charts.html");
    }


    /**
     * 聚合查询页
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showCards(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user/cards.html").forward(req, resp);
//        resp.sendRedirect("user/cards.html");
    }


    /**
     * 学生信息操作页
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showButtons(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user/buttons.html").forward(req, resp);
//        resp.sendRedirect("user/buttons.html");
    }

    /**
     * 表数据修改
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showTablesEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user/tablesEdit.html").forward(req, resp);
    }


}
