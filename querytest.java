package java6763.lesson10;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class querytest {
    private JTable tabDemo;
    private JTableHeader jth;
    private JScrollPane scpDemo;

    public void query(String name) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/mysql?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";//注意设置时区
                String username = "root";
                String passwords = "root";
                Connection conn = DriverManager.getConnection(url, username, passwords);
                PreparedStatement ps = conn.prepareStatement("select * from commodity where  name like ?");
                ps.setString(1, "%"+name+"%");
                ResultSet rs = ps.executeQuery();
                int count = 0;
                while (rs.next()) {
                    count++;
                }
