package bookManager;

import java.sql.*;
import java.util.Scanner;

public class Book {
    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String Url = "jdbc:mysql://localhost:3306/bookmanager";
            try {
                Connection connection = DriverManager.getConnection(Url, "root", "root");
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private  void close(ResultSet resultSet,Statement statement ,Connection connection){
        try {
            if (resultSet != null){
            resultSet.close();
        }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void insertBook(String name,String publisher,String author){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.getConnection();
            String sql = "insert into book (book_name,book_publisher,book_author) values('"+name+"','"+publisher+"','"+author+"')";
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("所影响的行为数：" + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement,connection);
        }
    }

    private void updateBook(int id,String name,String publisher,String author){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.getConnection();
            String sql = "update book set book_name='"+name+"',book_publisher='"+publisher+"',book_author='"+author+"'where id="+id;
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println("更新结果为："+(rows>0));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement,connection);
        }
    }
    private void deleteBook(int id){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = this.getConnection();
            String sql = "delete from book where id="+ id;
            statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);
            System.out.println(rows+ "行被删除成功！");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String[][] bestFindAllBook() {
        String[][] books = new String[100][4];
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int index = 0;
        connection = this.getConnection();
        String sql = "select * from book";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            StringBuffer buffer;
            for(buffer = new StringBuffer(); resultSet.next(); ++index) {
                books[index][0] = "" + resultSet.getInt("id");
                books[index][1] = "" + resultSet.getString("book_name");
                books[index][2] = "" + resultSet.getString("book_publisher");
                books[index][3] = "" + resultSet.getString("book_author");
            }
            System.out.println(buffer.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet, statement, connection);
        }
        return books;
    }

    private void findAllBookFormatOutput() {
        String[][] books = this.bestFindAllBook();
        StringBuffer buffer = new StringBuffer();
        buffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + System.lineSeparator());
        buffer.append("id\t\t\tbook_name\t\t\tbook_publisher\t\t\tbook_author" + System.lineSeparator());
        buffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + System.lineSeparator());
        for(int i = 0; i < books.length; ++i) {
            String[] values = books[i];
            if (values[0] != null && values[1] != null && values[2] != null && values[3] != null) {
                buffer.append(String.format("%-12s%-20s%-24s%-30s", values[0], values[1], values[2],values[3]));
                buffer.append(System.lineSeparator());
            }
        }
        System.out.println(buffer.toString());
    }

    private  void findBookDataLikeKey(String key){
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
            key = scanner.next();
            String sql = "select * from book where book_name like '%" + key+ "%' or book_publisher like '%"+key+"%' or book_author like'%"+ key+"%'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            System.out.println("id\t\t\tbook_name\t\t\t book_publisher\t\t\tbook_author\t\t\t");
            while(resultSet.next()) {
                System.out.printf("%-12d", resultSet.getInt("id"));
                System.out.printf("%-24s", resultSet.getString("book_name"));
                System.out.printf("%-20s",resultSet.getString("book_publisher"));
                System.out.printf("%-20s",resultSet.getString("book_author"));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.close(resultSet,statement,connection);
        }
    }
    public  static  void main(String[] args){
        Book book = new Book();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("|                       欢迎使用book_manager人工智能系统                          |");
            System.out.println("|1.添加书籍  2.修改数据  3.删除数据  4.查询所有书籍  5.模糊查询  6.系统退出|");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("请选择你要进行的操作···");

            int select;
            for(select = scanner.nextInt(); select < 1 || select > 6; select = scanner.nextInt()) {
                System.out.println("选择的操作不能识别，请重新选择;");
            }
            String value = null;
            Book demo = new Book();
            if (select == 1){
                String book_name;
                String book_publisher;
                String book_author;
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("          添加书籍           ");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("请输入要添加的书籍名称：");
                book_name = scanner.next();
                System.out.println("请输入要添加的出版商：");
                book_publisher = scanner.next();
                System.out.println("请输入要添加的书籍作者：");
                book_author = scanner.next();
                book.insertBook(book_name,book_publisher,book_author);
            }else if (select == 2){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("          修改数据           ");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.print("请输入要修改的id：");
                int id = scanner.nextInt();
                System.out.print("请输入要修改的书籍名称：");
                 String book_name = scanner.next();
                System.out.print("请输入要修改的出版商：");
                String book_publisher = scanner.next();
                System.out.print("请输入要修改的作者名字：");
                String book_author= scanner.next();
                book.updateBook(id,book_name,book_publisher,book_author);
            }else if(select == 3){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("          删除数据           ");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("请输入要删除的id:");
                value = scanner.next();
                book.deleteBook(Integer.parseInt(value));
            }else if (select == 4){
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("          查看所有数据           ");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                book.findAllBookFormatOutput();
            }else if (select == 5){
              book.findBookDataLikeKey("");
            }else if (select == 6){
                System.exit(0);
            }
        }
    }
}
