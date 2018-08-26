package com.gouge.service.main;

import com.alibaba.fastjson.JSON;
import com.gouge.base.*;
import com.gouge.param.user.SwingUser;
import com.gouge.service.ButtonService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * Created by Godden
 * Datetime : 2018/8/14 9:20.
 */
public class RegisterFunction extends MyFrame implements ButtonService {
    private boolean isFramLoad = false;
    private MyFrame frame;
    @Override
    public void execute() {
        if(!isFramLoad) {
            isFramLoad = true;
            newRegisterFrame();
            this.setVisible(true);
        }else{
            resset();
            this.setVisible(true);
        }
    }

    public RegisterFunction(){

    }

    private JTextField userCodeField = null;
    private JTextField nickNameField = null;
    private JPasswordField passwordField = null;
    private JPasswordField confirmPassField = null;
    private JTextField telField = null;
    private JTextField emailField = null;

    public void newRegisterFrame(){
        frame = this;
        this.setTitle("注册");
        this.setSize(600,430);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        JPanel backPanel= SwingHelp.getJpanel(0,0,600,430);

        JLabel userCodeLable = SwingHelp.getJLabel("用户名:",0,20,90,40);
        JLabel nickNameLable = SwingHelp.getJLabel("昵称:",0,70,90,40);
        JLabel passwordLable = SwingHelp.getJLabel("密码:",0,120,90,40);
        JLabel confirmPassLable = SwingHelp.getJLabel("确认密码:",0,170,90,40);
        JLabel telLable = SwingHelp.getJLabel("电话:",0,220,90,40);
        JLabel emailLable = SwingHelp.getJLabel("邮箱:",0,270,90,40);

        userCodeField = SwingHelp.getJTextField("userCode",100,20,200,40);
        final JLabel  informationLable = SwingHelp.getJLabel("",300,20,150,40);
        nickNameField = SwingHelp.getJTextField("nickName",100,70,400,40);
        passwordField = SwingHelp.getJPasswordField("password",100,120,400,40);
        confirmPassField = SwingHelp.getJPasswordField("confirmPassword",100,170,400,40);
        telField = SwingHelp.getJTextField("tel",100,220,400,40);
        emailField = SwingHelp.getJTextField("email",100,270,400,40);
        userCodeField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                informationLable.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                JsonResult jsonResult =  HttpUtile.sendHttpPost(Path.httpUrl+"v1/user/checkUserAlredy", userCodeField.getText());
                if(jsonResult.getResult().equals("000")) {
                    boolean flag = Boolean.valueOf(String.valueOf(jsonResult.getData()));
                    if(flag){
                       informationLable.setText("<html><font color=red size=3>该用户已经存在！</font></html>");
                    }else
                        informationLable.setText("");
                }else{
                    JOptionPane.showMessageDialog(null,jsonResult.getDesc());
                }
            }
        });

        JButton registerButton = SwingHelp.getJButton("注册",100,320,100,50,null);
        JButton resetButton = SwingHelp.getJButton("重置",210,320,100,50,null);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!StringUtils.isEmpty(informationLable.getText())){
                    JOptionPane.showMessageDialog(null,"该用户已经存在！");
                    return;
                }
                String userCode = userCodeField.getText();
                String nickName = nickNameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String password1 = String.valueOf(confirmPassField.getPassword());
                if(StringUtils.isEmpty(userCode)){
                    JOptionPane.showMessageDialog(null,"用户名不能为空！");
                    return;
                }
                if(StringUtils.isEmpty(nickName)){
                    JOptionPane.showMessageDialog(null,"昵称不能为空！");
                    return;
                }
                if(StringUtils.isEmpty(password)){
                    JOptionPane.showMessageDialog(null,"密码不能为空！");
                    return;
                }
                if(StringUtils.isEmpty(password1)){
                    JOptionPane.showMessageDialog(null,"确认密码不能为空！");
                    return;
                }
                if(!password.equals(password1)){
                    JOptionPane.showMessageDialog(null,"两次密码不一致！");
                    return;
                }
                SwingUser user = new SwingUser();
                user.setUserCode(userCode);
                user.setNickName(nickName);
                user.setPassword(password);
                user.setTel(telField.getText());
                user.setEmail(emailField.getText());
                JsonResult jsonResult =  HttpUtile.sendHttpPost(Path.httpUrl+"v1/user/register", JSON.toJSONString(user));
                if(jsonResult.getResult().equals("000")) {
                    JOptionPane.showMessageDialog(null,"注册成功!");
                    frame.setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(null,jsonResult.getDesc());
                }
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resset();
            }
        });
        SwingHelp.JpanelAdd(backPanel,userCodeLable,nickNameLable,passwordLable,confirmPassLable,telLable,emailLable,
                userCodeField,nickNameField,passwordField,confirmPassField,telField,emailField,registerButton,resetButton,informationLable);
        this.add(backPanel);
        this.repaint();
    }

    public void resset(){
        userCodeField.setText("");
        nickNameField.setText("");
        passwordField.setText("");
        confirmPassField.setText("");
        telField.setText("");
        emailField.setText("");
    }
}
