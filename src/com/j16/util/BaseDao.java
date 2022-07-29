package com.j16.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author Master
 * 复用形数据库调用设置
 */
public class BaseDao {
    static String driver;
    static String userName;
    static String password;
    static String url;

    static {
        try {
            Properties properties = new Properties();
            InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(is);
            driver = properties.getProperty("driver");
            userName = properties.getProperty("userName");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * conn 连接模块
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭代码复用性高
     */
    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            } else if (ps != null) {
                ps.close();
            } else if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 增删改高度复用
     */
    public static int additionsDeletionsModifications(String sql, Object... obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = BaseDao.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject((i + 1), obj[i]);
            }
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(conn, ps, null);
        }
        return 0;
    }
}
