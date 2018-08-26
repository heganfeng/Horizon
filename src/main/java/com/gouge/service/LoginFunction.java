package com.gouge.service;

import com.alibaba.fastjson.JSON;
import com.gouge.base.*;
import com.gouge.base.tools.EncryptTool;
import com.gouge.main.MainFrame;
import com.gouge.main.frame.ChatFrame;
import com.gouge.main.thread.SocketThread;
import com.gouge.param.SocketSendData;
import com.gouge.param.test.TestParam;
import com.gouge.param.user.SwingUser;
import com.gouge.param.user.TSysUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

/**
 * Created by Godden
 * Datetime : 2018/8/7 17:11.
 */


public class   LoginFunction implements ButtonService{
    private  final Logger logger = LogManager.getLogger(LoginFunction.class);

    private JTextField userNameField;
    private JPasswordField passwordField;

    @Override
    public void execute() {
        login();
    }


    public void login(){
        try {
            String name = userNameField.getText();
            String pasword = String.valueOf(passwordField.getPassword());
            if(null == name || name.trim().equals("")){
                JOptionPane.showMessageDialog(null,"用户名不能为空!");
                return;
            }
            if(null == pasword || pasword.trim().equals("")){
                JOptionPane.showMessageDialog(null,"密码不能为空!");
                return;
            }
            TestParam param = new TestParam();
            param.setUsername(name);
            param.setPassword(pasword);
            SwingUser user = (SwingUser) HttpConnectionHelp.httpPost(Path.httpUrl+"v1/login",param, SwingUser.class);
            if(user != null){
                JOptionPane.showMessageDialog(null,"登陆成功,欢迎:"+user.getNickName());
                GlobalVariable.loginUser=user;
                GlobalVariable.loginFrame.setVisible(false);//影藏登录界面
                GlobalVariable.mainFrame = new MainFrame();
                ActionListener listener = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        GlobalVariable.mainFrame.setVisible(true);
                    }
                };
                GlobalVariable.taskTray = new TaskTray(listener);
                GlobalVariable.taskTray.createTray();
                loginSocket(user);
            }else {
                JOptionPane.showMessageDialog(null,"登陆失败!");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }

    public void loginSocket(SwingUser user){
        Socket socket = null;
        try {
            socket = new Socket(Path.SOCKET_IP,Path.SOCKET_PORT);
            SocketSendData data = new SocketSendData();
            data.setUserId(user.getId());
            data.setUserName(user.getNickName());
            data.setSendType(99);
            data.setContent("登陆服务器！");
            Writer writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(EncryptTool.getEnCode(JSON.toJSONString(data), SystemVariable.key)+"\n");
            writer.flush();
            logger.info("登录socket服务器成功！");
            GlobalVariable.chatFrame = new ChatFrame(writer);
            new Thread(new SocketThread(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setKeyListener(){
        userNameField.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    login();
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });
        passwordField.addKeyListener(new KeyListener(){
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==10){
                    login();
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });
    }

    public JTextField getUserNameField() {
        return userNameField;
    }

    public void setUserNameField(JTextField userNameField) {
        this.userNameField = userNameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }
}
