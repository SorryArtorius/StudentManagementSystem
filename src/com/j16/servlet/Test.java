package com.j16.servlet;

import com.alibaba.fastjson.JSONObject;
import com.j16.dao.impl.AdminDaoImpl;
import com.j16.pojo.Admin;
import com.j16.pojo.Echarts;
import com.j16.pojo.Student;
import com.j16.pojo.StudentAttached;
import com.j16.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeString.substring;

public class Test {
    public static void main(String[] args) {
//        AdminServiceImpl adminService = new AdminServiceImpl();
////        System.out.println(adminService.selectAdmin("admin")+"");
//
//       Admin admin =  adminService.getAdmin("admin","admin");
//       System.out.println(admin.getUserName()+"====="+admin.getUserPwd());

//        AdminDaoImpl adminDaoImpl = new AdminDaoImpl();
//
//        List<Student> sudent = adminDaoImpl.paginationStudent(1,10);
//
//        for (Student student : sudent) {
//            System.out.println(student.getStudentName()+"-----"+student.getAddress());
//        }
//
//        int count = adminDaoImpl.countStudent();
//        System.out.println("学生总数:"+count);

//        List<StudentAttached> students = new AdminServiceImpl().paginationStudentAttached(1,10);
//        /**
//         * 分页处理
//         */
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("students",students);
//        String student = jsonObject.toJSONString();
//        System.out.println(student);

        AdminServiceImpl adminService = new AdminServiceImpl();
//        System.out.println(adminService.obtainStudentNo("432627200002060391"));
//        if (adminService.selectAvatar("lisi")){
//            System.out.println("成功");
//        }else {
//            System.out.println("失败");
//        }

//        Echarts echarts = new Echarts();
//        echarts.setKey("Java基础");
//        echarts.setValue(70);
//        new AdminServiceImpl().updateStudentResult(10000,echarts);

        String takeOverStudentName="张之洞";
        takeOverStudentName = substring(takeOverStudentName,1);
        String oldStudentName = new AdminServiceImpl().getStudentNameUsingStudentNumber( 10000);
        String newStudentName = substring(oldStudentName,0,1);
        newStudentName+=takeOverStudentName;
        System.out.println(newStudentName);


    }

}
