package newGUI;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class SalesStatisticsFrame extends JFrame {
    {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private JScrollPane scpDemo;
    private JTableHeader jth;
    private JTable tabDemo;
    private JButton btnShow;
    private JButton b1;
    private JButton b2;
    private JTextField b3;

    public SalesStatisticsFrame() {
        super("朴朴抓取数据");        //JFrame的标题名称
        this.setSize(600, 600);        //控制窗体大小
        this.setLayout(null);        //自定义布局
        this.setLocation(400, 100);    //点击运行以后，窗体在屏幕的位置
        this.scpDemo = new JScrollPane();
        this.b1 = new JButton("删除");
        this.b2 = new JButton("查找");
        this.b3 = new JTextField();
        this.btnShow = new JButton("显示全部数据");
        this.b1.setBounds(50, 480, 100, 30);
        this.b2.setBounds(400, 480, 100, 30);
        this.b3.setBounds(225, 480, 100, 30);
        this.scpDemo.setBounds(10, 50, 580, 400);    //设置滚动框大小
        this.btnShow.setBounds(10, 10, 120, 30);    //设置按钮
        this.btnShow.addActionListener(new ActionListener()    //给“显示数据”按钮添加事件响应。
        {
            public void actionPerformed(ActionEvent ae) {
                btnShow_ActionPerformed(ae);
            }
        });
        
        //将组件加入到窗体中
        add(this.scpDemo);
        add(this.btnShow);
        add(this.b1);
        add(this.b3);
        add(this.b2);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //连接数据库并显示到表格中
    public void btnShow_ActionPerformed(ActionEvent ae) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mysql?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";//注意设置时区
            String username = "root";
            String passwords = "root";
            Connection conn = DriverManager.getConnection(url, username, passwords);
            String sql = "select * from commodity";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            rs = pstm.executeQuery();
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            Object[][] info = new Object[count][3];
            String[] title = {"名称", "产地", "价格"};
            count = 0;
            while (rs.next()) {
                info[count][0] = rs.getString("name");
                info[count][2] = rs.getDouble("price");
                info[count][1] = rs.getString("origin");
                count++;
            }
            // 创建JTable
            this.tabDemo = new JTable(info, title);
            // 显示表头
            this.jth = this.tabDemo.getTableHeader();
            // 将JTable加入到带滚动条的面板中
            this.scpDemo.getViewport().add(tabDemo);
            //按钮删除
            this.b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int con = tabDemo.getSelectedRow();
                    String name = (String) tabDemo.getValueAt(con, 0);
                    //调用方法
                    delete(name);

                }
            });
            //按钮查找
            this.b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s = b3.getText();
                    //调用方法
                    query(name);

                }
            });
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据源错误", "错误", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(null, "数据操作错误", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new SalesStatisticsFrame();
    }
}


