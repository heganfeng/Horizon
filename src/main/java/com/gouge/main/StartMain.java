package com.gouge.main;

import com.gouge.base.*;
import com.gouge.service.LoginFunction;
import com.gouge.service.ResetFunction;
import com.gouge.service.main.RegisterFunction;
import com.sun.awt.AWTUtilities;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * Created by Godden on 2018/8/6.
 */
public class StartMain extends MyFrame {

    public StartMain(){
//        this.setIconImage(this.getToolkit().getImage(new URL(SystemVariable.gougeIco)));
        this.setSize(400,400);
        this.setTitle("登陆");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置没有窗口
//        this.setUndecorated(true);
//        AWTUtilities.setWindowOpaque(this, false);
//        this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        this.setVisible(false);
        JPanel backPanel= SwingHelp.getJpanel(0,0,400,400);


        JLabel userNameLable=SwingHelp.getJLabel("用户名:",30, 90, 80, 50);
        JLabel passWordLable=SwingHelp.getJLabel("密码:",30,140,80,50);

        JTextField userNameFiled=SwingHelp.getJTextField("userName",120, 100, 150, 30);
        JPasswordField passWordFiled=SwingHelp.getJPasswordField("passWord",120, 150, 150, 30);

        LoginFunction function = new LoginFunction();
        function.setUserNameField(userNameFiled);
        function.setPasswordField(passWordFiled);
        function.setKeyListener();
        JButton loginButton=SwingHelp.getJButton("登陆",60,250, 100, 50,function);
        loginButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
        ResetFunction resetFunction = new ResetFunction() ;
        resetFunction.setFileds(new JTextField[] {userNameFiled,passWordFiled});
        JButton resetButton=SwingHelp.getJButton("重置",180,250, 100, 50,resetFunction);
        resetButton.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        RegisterFunction registerFunction = new RegisterFunction();
        JButton register=SwingHelp.getJButton("注册",290,250, 100, 50,registerFunction);
        register.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));

        SwingHelp.JpanelAdd(backPanel,userNameLable,passWordLable,userNameFiled,
                passWordFiled,loginButton,resetButton,register);
        this.add(backPanel);
        backPanel.repaint();
        this.setVisible(true);
        userNameFiled.requestFocus();
        this.repaint();
    }

    public static void main(String[] args) {
        //HttpUtile.sendHttpPost(Path.httpUrl+"v1/login","{\"username\":\"gouge\",\"password\":\"gouge\"}");
        /** UIManager中UI字体相关的key */
        String[] DEFAULT_FONT = new String[]{
                "Table.font"
                , "TableHeader.font"
                , "CheckBox.font"
                , "Tree.font"
                , "Viewport.font"
                , "ProgressBar.font"
                , "RadioButtonMenuItem.font"
                , "ToolBar.font"
                , "ColorChooser.font"
                , "ToggleButton.font"
                , "Panel.font"
                , "TextArea.font"
                , "Menu.font"
                , "TableHeader.font"
                // ,"TextField.font"
                , "OptionPane.font"
                , "MenuBar.font"
                , "Button.font"
                , "Label.font"
                , "PasswordField.font"
                , "ScrollPane.font"
                , "MenuItem.font"
                , "ToolTip.font"
                , "List.font"
                , "EditorPane.font"
                , "Table.font"
                , "TabbedPane.font"
                , "RadioButton.font"
                , "CheckBoxMenuItem.font"
                , "TextPane.font"
                , "PopupMenu.font"
                , "TitledBorder.font"
                , "ComboBox.font"
        };
        // 调整默认字体
        for (int i = 0; i < DEFAULT_FONT.length; i++){
            UIManager.put(DEFAULT_FONT[i], new Font("微软雅黑", Font.PLAIN, 14));
        }
        BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible",false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SystemVariable.initVariable();
        GlobalVariable.loginFrame = new StartMain();
    }
}
