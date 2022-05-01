package Exercises;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Delete {
    public static void delete(String name) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";//注意设置时区
            String username = "root";
            String passwords = "root";
            Connection conn = DriverManager.getConnection(url, username, passwords);
            PreparedStatement ps = conn.prepareStatement("delete from commodity where name=?");
            ps.setString(1, name);
            int f = ps.executeUpdate();
            if (f > 0) {
                JOptionPane.showMessageDialog(null, "成功删除数据");
            } else {
                JOptionPane.showMessageDialog(null, "没有删除数据");
            }
            ps.close();
            conn.close();
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(null, "连接失败");
        }
    }
}
