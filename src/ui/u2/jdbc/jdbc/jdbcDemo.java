//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ui.u2.jdbc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcDemo {
    public JdbcDemo() {
    }

    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/hnb11";

            try {
                Connection connection = DriverManager.getConnection(dbURL, "root", "root");
                return connection;
            } catch (SQLException var3) {
                var3.printStackTrace();
            }
        } catch (ClassNotFoundException var4) {
            var4.printStackTrace();
        }

        return null;
    }

    private void close(Statement statement, Connection connection) {
        try {
            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    private void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

    }

    private void insertData(String accountValue, String password) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = this.getConnection();
            String sql = "insert into account (user_account,user_password) values( '" + accountValue + "','" + password + "')";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("所影响的行为数：" + rows);
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            this.close(statement, connection);
        }

    }

    private void deleteData(int id) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = this.getConnection();
            String sql = "delete from account where id=" + id;
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("有" + rows + "行被删除成功！");
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            this.close(statement, connection);
        }

    }

    private void updateDate(int id, String account, String password) {
        Connection connection = null;
        Statement statement = null;

        try {
            connection = this.getConnection();
            System.out.println(account);
            System.out.println(password);
            String sql = "update account set user_account='" + account + "',user_password='" + password + "' where id=" + id;
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("更新结果列数：" + (rows > 0));
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            this.close(statement, connection);
        }

    }

    private String[][] bestFindAllDate() {
        String[][] datas = new String[100][3];
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int index = 0;
        connection = this.getConnection();
        String sql = "select * from account";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            StringBuffer buffer;
            for(buffer = new StringBuffer(); resultSet.next(); ++index) {
                datas[index][0] = "" + resultSet.getInt("id");
                datas[index][1] = "" + resultSet.getString("user_account");
                datas[index][2] = "" + resultSet.getString("user_password");
                System.out.println();
            }

            System.out.println(buffer.toString());
        } catch (SQLException var11) {
            var11.printStackTrace();
        } finally {
            this.close(resultSet, statement, connection);
        }

        return datas;
    }

    private void findAllDataFormatOutput() {
        String[][] datas = this.bestFindAllDate();
        StringBuffer buffer = new StringBuffer();
        buffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + System.lineSeparator());
        buffer.append("id\t\t\tuser_account\t\t\tuser_password\t\t\t" + System.lineSeparator());
        buffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + System.lineSeparator());

        for(int i = 0; i < datas.length; ++i) {
            String[] values = datas[i];
            if (values[0] != null && values[1] != null && values[2] != null) {
                buffer.append(String.format("%-12s%-24s%-20s", values[0], values[1], values[2]));
                buffer.append(System.lineSeparator());
            }
        }

        System.out.println(buffer.toString());
    }

    private void findAllData() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = this.getConnection();
        String sql = "select * from account";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            StringBuffer buffer = new StringBuffer();
            buffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + System.lineSeparator());
            buffer.append("id\t\t\tuser_accont\t\t\tuser_password\t\t\t" + System.lineSeparator());
            buffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + System.lineSeparator());
            System.out.println(buffer);

            while(resultSet.next()) {
                System.out.printf("%-12d", resultSet.getInt("id"));
                System.out.printf("%-24s", resultSet.getString("user_account"));
                System.out.printf("%-15s", resultSet.getString("user_password"));
                System.out.println();
            }

            System.out.println(buffer.toString());
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            this.close(resultSet, statement, connection);
        }

    }

    private void findAccountDataById() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = this.getConnection();

        try {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("           id查询               ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("请输入要查询的id:");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            String sql = "select * from account where id=" + id + "";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println("id\t\t\tuser_account\t\t\tuser_password\t\t\t");

            while(resultSet.next()) {
                System.out.printf("%-12d", resultSet.getInt("id"));
                System.out.printf("%-24s", resultSet.getString("user_account"));
                System.out.printf("%-16s", resultSet.getString("user_password"));
                System.out.println();
            }
        } catch (SQLException var10) {
            var10.printStackTrace();
        } finally {
            this.close(resultSet, statement, connection);
        }

    }

    private void findAccountDataLikeKey(String keyWord) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        connection = this.getConnection();

        try {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("             模糊查询                ");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("请输入要查询的字样:");
            Scanner scanner = new Scanner(System.in);
            keyWord = scanner.next();
            String sql = "select * from account where user_account like '%" + keyWord + "%'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println("id\t\t\tuser_account\t\t\tuser_password\t\t\t");

            while(resultSet.next()) {
                System.out.printf("%-12d", resultSet.getInt("id"));
                System.out.printf("%-24s", resultSet.getString("user_account"));
                System.out.printf("%-16s", resultSet.getString("user_password"));
                System.out.println();
            }
        } catch (SQLException var15) {
            var15.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException var14) {
                var14.printStackTrace();
            }

        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|                           欢迎使用HNB 11 人工智能系统                          |");
            System.out.println("|1.查询数据  2.添加数据  3.修改数据  4.删除数据  5.id查询  6.模糊查询  7.系统退出|");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("请选择你要进行的操作···");

            int select;
            for(select = scanner.nextInt(); select < 1 || select > 7; select = scanner.nextInt()) {
                System.out.println("选择的操作不能识别，请重新选择;");
            }

            String value = null;
            JdbcDemo demo = new JdbcDemo();
            if (select == 1) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("          查询所有数据          ");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                demo.findAllDataFormatOutput();
            } else {
                String user_account;
                if (select == 2) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("             添加数据          ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("请输入要添加的账号：");
                    user_account = scanner.next();
                    System.out.println("请输入要添加的密码：");
                    user_account = scanner.next();
                    demo.insertData(user_account, user_account);
                } else if (select == 3) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("             修改数据          ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.print("请输入要修改的id：");
                    int id = scanner.nextInt();
                    System.out.print("请输入要修改的账号：");
                    user_account = scanner.next();
                    System.out.print("请输入要修改的密码：");
                    String user_password = scanner.next();
                    demo.updateDate(id, user_account, user_password);
                } else if (select == 4) {
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("            删除id             ");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("请输入要删除的id:");
                    value = scanner.next();
                    demo.deleteData(Integer.parseInt(value));
                } else if (select == 5) {
                    demo.findAccountDataById();
                } else if (select == 6) {
                    demo.findAccountDataLikeKey("");
                } else if (select == 7) {
                    System.exit(-1);
                }
            }
        }
    }
}
