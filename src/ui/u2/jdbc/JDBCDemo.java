package ui.u2.jdbc;
/*演示通过jdbc连接数据库和进行增添、删和改*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCDemo {

    //获取一个数据库连接
    private Connection getConnection(){
        //1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //2.创建数据库连接字符串
            String dbURL = "jdbc:mysql://localhost:3306/hnb11";
            //3.建立数据库连接
            try {
                Connection connection = DriverManager.getConnection(dbURL,"root","root");
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
    private  void testInsertData(String accountValue, String password){
        //1.创建数据库连接
        try {
                Connection connection = getConnection();
                //2.构造添加数据的sql语句
                String sql = "insert into account (user_account,user_password) values( '"+accountValue+"','"+ password +"')";
                //3.执行sql语句
                Statement statement = connection.createStatement();
                //4.得到执行后的结果，确定是否添加成功
                int rows = statement.executeUpdate(sql);
                System.out.println("所影响的行为数："+rows);
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
    private  void testDeleteData(int id){
        //1.创建数据库连接
        try {
                Connection connection = getConnection();
                //2.构建删除语句
                String  sql = "delete from account where id="+id;
                //3.执行删除语句
                Statement statement = connection.createStatement();
                //4.获取执行所影响的行数，判断是否执行成功
                int rows = statement.executeUpdate(sql);
                System.out.println("有"+ rows + "行被删除成功！");
            } catch (SQLException e) {
                e.printStackTrace();
            }

    }
    private  void testUpdateDate(int id,String account,String password){
        try {
            //1.连接创建数据库
                Connection connection = getConnection();
                //2.创建update sql语句
            System.out.println(account);
            System.out.println(password);
                String sql = "update account set user_account='"+account+"',user_password='"+password+"' where id="+id;
                //3.执行修改语句
                Statement statement = connection.createStatement();
                //4.得到执行结果，判断是否修改成功
                int rows = statement.executeUpdate(sql);
                System.out.println("更新结果列数："+ (rows>0));

            } catch (SQLException e) {
                e.printStackTrace();
            }

    }



    public static void main(String[] args){
//        JDBCDemo demo = new JDBCDemo();
//        demo.getConnection();
//        demo.testInsertData(3,"ndhfs","dieuwdn");
//        demo.testDeleteData(1);
//        demo.testUpdateDate(7,"nihakheuu","ieu8wrhsbbz");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|          欢迎使用HNB 11 人工智能系统         |");
            System.out.println("|1.添加数据  2.修改数据  3.删除数据  4.系统退出|");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("请选择你要进行的操作···");
            int select = 0;
            select = scanner.nextInt();
            while (select < 1 || select > 4) {
                System.out.println("选择的操作不能识别，请重新选择;");
                select = scanner.nextInt();
            }
            String value = null;
            JDBCDemo demo = new JDBCDemo();
            if (select == 1) {
//                System.out.println("请输入要添加的账号和密码，中间用逗号分隔. 举例：126.com,233");
//                value = scanner.next();
//                String [] values = value.split(",");
//                demo.testInsertData(values[0],values[1]);
                System.out.println("~~~~~~添加数据~~~~~~");
                System.out.println("请输入要添加的账号：");
                String user_account = scanner.next();
                System.out.println("请输入要添加的密码：");
                String user_password = scanner.next();
                demo.testInsertData(user_account, user_password);
            } else if (select == 2) {
                //修改数据S
                System.out.println("~~~~~~修改数据~~~~~~");
                System.out.print("请输入要修改的id：");
                int id = scanner.nextInt();
                System.out.print("请输入要修改的账号：");
                String user_account = scanner.next();
                System.out.println(user_account);
                System.out.print("请输入要修改的密码：");
                String user_password = scanner.next();
                System.out.println(user_password);
                demo.testUpdateDate(id,user_account,user_password);
                //demo.testUpdateDate(Integer.parseInt(values[0]),values[1],values[2]);
            } else if (select == 3) {
                System.out.println("请输入要删除的id:");
                value = scanner.next();
                demo.testDeleteData(Integer.parseInt(value));
            } else if (select == 4) {
                System.exit(-1);
            }
        }
    }
}
