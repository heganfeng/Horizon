package com.gouge.base;

import com.sun.awt.AWTUtilities;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Godden
 * Datetime : 2018/8/9 22:20.
 */
public class MyFrame extends JFrame{

    public MyFrame() throws HeadlessException {
        super();
        try {
//            this.setUndecorated(true);// 不绘制窗体的边框和标题栏。（Optional）

//            com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.5F);// 设置整个窗体的不透明度为0.5
            this.setIconImage(this.getToolkit().getImage(new URL(SystemVariable.gougeIco)));
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setResizable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
