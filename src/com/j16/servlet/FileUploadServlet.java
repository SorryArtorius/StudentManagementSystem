package com.j16.servlet;

import com.j16.pojo.Admin;
import com.j16.service.impl.AdminServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


/**
 * 文件上传
 *
 * @author Master
 */
@WebServlet("/fileUploadServlet")
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File[] listFiles = null;
        if (req.getParameter("listFile") != null) {
            listFiles = (File[]) req.getAttribute("listFile");
        }
        //请求信息中的内容是否是multipart类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        //上传文件的存储路径（服务器文件系统上的绝对文件路径）
        String uploadFilePath = req.getSession().getServletContext().getRealPath("avatar/");

//        //创建临时文件目录路径
//        File tempPatchFile = new File("d:\\temp\\buffer\\");
//        if (!tempPatchFile.exists())  //判断文件或目录是否存在
//        {
//            tempPatchFile.mkdirs();   //创建指定的目录，包括所有必需但不存在的父目录
//        }
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置缓冲区大小4kb
            factory.setSizeThreshold(4096);
            //设置上传文件用到临时文件存放路径
//            factory.setRepository(tempPatchFile);
            ServletFileUpload upload = new ServletFileUpload(factory);
            //设置单个文件的最大限制
            upload.setSizeMax(1024 * 1024 * 4);
            try {
                //解析form表单中所有文件
                List<FileItem> items = upload.parseRequest(req);
                //处理表单中所有的字段
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {   //依次处理每个文件
                    FileItem item = iter.next();
                    if (!item.isFormField()) {  //文件表单字段
                        String fileName = item.getName();
                        /*//通过Arrays类的asList()方法创建固定长度的集合
                        List<String> filType = Arrays.asList("gif", "bmp", "jpg");
                        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                        if (!filType.contains(ext))  //判断文件类型是否在允许范围内
                            System.out.print("上传失败，文件类型只能是gif、bmp、jpg");*/
                        //else {
                        if (fileName != null && !fileName.equals("")) {
                            AdminServiceImpl adminService = new AdminServiceImpl();
                            //File fullFile = new File(item.getName());
                            //解决文件重名的问题
                            String newFileName = UUID.randomUUID() + "-" + fileName;
                            File saveFile = new File(uploadFilePath, newFileName);

                            //上传
                            item.write(saveFile);
                            System.out.print("上传成功后的文件名是：" + newFileName +
                                    "，文件大小是：" + item.getSize() + "bytes!");
                            Admin admin = (Admin) req.getSession().getAttribute("admin");
                            StringBuffer avatar = new StringBuffer();
                            avatar.append("avatar\\");
                            avatar.append(newFileName);
                            adminService.fileAvatarUpdate(String.valueOf(avatar), admin.getId());

                        }
                        //}
                    }
                }
                System.out.println("操作完成");
            } catch (FileUploadBase.SizeLimitExceededException ex) {
                System.out.print("上传失败，文件太大，单个文件的最大限制是：" + upload.getSizeMax() + "bytes!");
            } catch (Exception e) {
                e.printStackTrace();
            }

            // ----分割
            /**
             * 获取文件目录
             */
            File files = new File(uploadFilePath);
            if (files.exists()) {
                /**
                 * 判断目录是否存在 如果存在将他转发出去
                 */
                File[] listFile = files.listFiles();
                /**
                 * 读取目录下的文件 然后通过转发显示在下载页面
                 */

                req.setAttribute("listFile", listFile);

            }
//            req.getRequestDispatcher("adminServlet?tag=show").forward(req, resp);
            resp.sendRedirect("adminServlet?tag=show");

        }
    }
}
