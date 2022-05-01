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


                rs = ps.executeQuery();
                // 将查询获得的记录数据，转换成适合生成JTable的数据形式
                Object[][] info = new Object[count][3];
                String[] title = {"名称", "产地", " 价格"};
                count = 0;
                while (rs.next()) {
                    info[count][1] = rs.getString("name");
                    info[count][2] = rs.getDouble("Price");
                    info[count][0] = rs.getString("origin");
                    count++;
                }
                // 创建JTable
                this.tabDemo = new JTable(info, title);
                // 显示表头
                this.jth = this.tabDemo.getTableHeader();
                // 将JTable加入到带滚动条的面板中
                this.scpDemo.getViewport().add(tabDemo);

            } catch (Exception e) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(null, "查询失败");
                e.printStackTrace();
            }
        }
}