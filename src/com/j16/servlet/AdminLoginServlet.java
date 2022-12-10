package com.j16.servlet;

import com.j16.pojo.Admin;
import com.j16.service.impl.AdminServiceImpl;
import com.j16.util.Md5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;

/**
 * ManagementUser的请求
 *
 * @author Master
 */
@WebServlet("/adminUserServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
        String tag = "";
        if (req.getParameter("tag") != null) {
            tag = req.getParameter("tag");
        }

        if ("select".equals(tag)) {
            String user = req.getParameter("userName");
            boolean bool = adminServiceImpl.selectAdmin(user);
            resp.getWriter().write(bool + "");
        } else if ("login".equals(tag)) {
            resp.sendRedirect("login.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tag = "";
        if (req.getParameter("tag") != null) {
            tag = req.getParameter("tag");
        }

        if ("login".equals(tag)) {
            login(req, resp);
        } else if ("register".equals(tag)) {
            register(req, resp);

        }


    }


    /**
     * 注册块
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    private void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
        Admin admin = new Admin();
        String user = req.getParameter("userNames");
        String pwd = req.getParameter("userPwd");
        String pwds = req.getParameter("userPwds");
        String mD5Pwd = Md5Util.mD5(pwd);
        boolean userBool = adminServiceImpl.selectAdmin(user);
        if (!userBool && pwd.equals(pwds)) {
            if (adminServiceImpl.selectAdmins(user)) {
                adminServiceImpl.updateAdminState(user, mD5Pwd);
                resp.sendRedirect("adminServlet?tag=show");
            } else {
                admin.setUserName(user);
                admin.setUserPwd(mD5Pwd);
                admin.setMark(1);
                adminServiceImpl.registerAdmin(admin);
                resp.sendRedirect("adminServlet?tag=show");
            }

        } else {
            System.out.println("请勿修改前台页面");
        }
    }

    /**
     * 登录块
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
        String inputUsername = req.getParameter("inputUsername");
        String inputPassword = req.getParameter("inputPassword");
        String inputPasswordMd5 = Md5Util.mD5(inputPassword);
        if (isNull(inputUsername) || isNull(inputPassword) || "".equals(inputUsername) || "".equals(inputPassword)) {
            resp.sendRedirect("login.html");
        } else {
            Admin admin = adminServiceImpl.getAdmin(inputUsername, inputPasswordMd5);
            if (!isNull(admin)) {
                /**
                 * 登录成功后保存cookie
                 */
                if ("1".equals(req.getParameter("customChecks"))) {
                    Cookie user = new Cookie("user", admin.getUserName());
                    Cookie pwd = new Cookie("pwd", admin.getUserPwd());
                    user.setMaxAge(30 * 30 * 24 * 7);
                    pwd.setMaxAge(30 * 30 * 24 * 7);
                    resp.addCookie(user);
                    resp.addCookie(pwd);
                }

                /**
                 * 注册成功后存储属性
                 */
                req.getSession().setAttribute("admin", admin);
//                req.getRequestDispatcher("adminServlet?tag=show").forward(req, resp);
                resp.sendRedirect("adminServlet?tag=show");
            } else {
                resp.sendRedirect("login.html");
            }
        }
    }
}
