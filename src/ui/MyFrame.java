package ui;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    public MyFrame(){
        GridLayout gridLayout = new GridLayout(5,2);
        this.setLayout(gridLayout);
        JTextField txtAccount = new JTextField();
        JLabel labAccount = new JLabel("Account");
        JTextField txtPassword = new JTextField();
        JLabel labPassword = new JLabel("Password");
        JTextField txtAddress = new JTextField();
        JLabel labAddress = new JLabel("Address");
        JTextField txtRemark = new JTextField();
        JLabel labRemark = new JLabel("Remark");
        JButton btnOk = new JButton("Ok");
        JButton btnCancle = new JButton("Cancle");

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtAccount.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"不能为空");
                    txtAccount.setBorder(BorderFactory.createLineBorder(Color.cyan));
                }else if (txtPassword.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"不能为空");
                    txtPassword.setBorder(BorderFactory.createLineBorder(Color.magenta));
                }else if(txtAddress.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"不能为空");
                    txtAddress.setBorder(BorderFactory.createLineBorder(Color.CYAN));
                }else if(txtRemark.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"不能为空");
                    txtRemark.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }else{
                    String message = "account:"+txtAccount.getText()+"\npassword:"+txtPassword.getText()+"\naddress:"+txtAddress.getText()+"\nremark:"+txtRemark.getText();
                    JOptionPane.showMessageDialog(null,message);
                }
            }
        });

        this.add(labAccount);
        this.add(txtAccount);
        this.add(labPassword);
        this.add(txtPassword);
        this.add(labAddress);
        this.add(txtAddress);
        this.add(labRemark);
        this.add(txtRemark);
        this.add(btnOk);
        this.add(btnCancle);

        this.setSize(600,600);
        this.setTitle("添加信息界面");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args){
        new MyFrame();
    }
}
