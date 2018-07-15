package ui.u2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountFrame extends JFrame {

    private  JPanel panelContent= new JPanel();

    //上部放置查询相关组件的面板
    private JPanel panelSearch = new JPanel();
    //下部提供add,del,modify操作面板
    private JPanel panelProcess = new JPanel();
    private JTextField txtSearch = new JTextField();
    private JButton btnSearch = new JButton("Search");
    private JButton btnAdd = new JButton("Add");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnModify = new JButton("Modify");
    private JLabel labMessage = new JLabel("你好！");
    //添加功能所用的面板，包含很多组件
    private  JPanel panelAdd = new JPanel();
    private  JLabel labAddTitle = new JLabel("标题：");
    private  JTextField txtAddTitle = new JTextField();
    private  JLabel labAddAccount = new JLabel("账号：");
    private  JTextField txtAddAccount = new JTextField();
    private  JLabel labAddPassword = new JLabel("密码：");
    private  JTextField txtAddPasswrd = new JTextField();
    private  JLabel labAddRemark = new JLabel("标记：");
    private  JTextField txtAddRemark = new JTextField();

    public AccountFrame(){
        //=====初始化组件=====
        //设置布局
        panelSearch.setLayout(new BorderLayout());
        //添加搜索框到中间部分
        panelSearch.add(txtSearch);
        //添加搜索按钮到右边
        panelSearch.add(btnSearch,BorderLayout.EAST);
        //=====初始化爬panelAdd面板组件====
        panelAdd.setLayout(new GridLayout(4,2));
        panelAdd.add(labAddTitle);
        panelAdd.add(txtAddTitle);
        panelAdd.add(labAddAccount);
        panelAdd.add(txtAddAccount);
        panelAdd.add(labAddPassword);
        panelAdd.add(txtAddPasswrd);
        panelAdd.add(labAddRemark);
        panelAdd.add(txtAddRemark);
        panelAdd.setVisible(false);
        //===进程布局===
        panelProcess.setLayout(new FlowLayout());
        panelProcess.add(btnAdd);
        panelProcess.add(btnDelete);
        panelProcess.add(btnModify);
        //添加搜索面板到上方
        this.add(panelSearch,BorderLayout.NORTH);
        //添加操作面板到下面
        this.add(panelProcess,BorderLayout.SOUTH);
        this.add(panelAdd,BorderLayout.CENTER);
        this.add(labMessage);

        //====监听事件====
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //获取文本框输入的内容
                String message = txtSearch.getText();
                //将文本内容付给标签显示
                labMessage.setText(message);
                //设置文本颜色
                labMessage.setForeground(Color.magenta);
                AccountFrame.this.getLayout().removeLayoutComponent(labMessage);
                AccountFrame.this.add(panelAdd);
                panelAdd.setVisible(true);
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountFrame.this.remove(labMessage);
                AccountFrame.this.add(panelAdd);
                AccountFrame.this.setVisible(false);
                AccountFrame.this.setVisible(true);
                String text = btnAdd.getText();
                if (text.equals("add")){
                    //进入添加记录的操作
                    //移除labMessage组件
                    AccountFrame.this.remove(labMessage);
                    //添加panel到中间
                    AccountFrame.this.add(panelAdd);
                    btnDelete.setText("cancle");
                    btnModify.setVisible(false);
                    text = "save";
                }else{//还原回初始状态
                    text = "add";
                    AccountFrame.this.remove(panelAdd);
                    labMessage.setText("保存成功！");
                    AccountFrame.this.add(labMessage);
                    btnDelete.setText("Delete");
                    btnModify.setVisible(false);

                }
                btnAdd.setText(text);
                //类似以页面刷新覆盖
                AccountFrame.this.setVisible(false);
                AccountFrame.this.setVisible(true);

            }
        });



        //=====设定窗口相关属性====
        this.setSize(600,600);
        this.setTitle("账号首页");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public static void main(String[] args){
        new AccountFrame();
    }
}
