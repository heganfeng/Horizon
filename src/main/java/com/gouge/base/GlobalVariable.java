package com.gouge.base;

import com.gouge.base.music.PlayerMusic;
import com.gouge.main.frame.ChatFrame;
import com.gouge.param.user.SwingUser;

import javax.swing.*;

/**
 * Created by Godden
 * Datetime : 2018/8/9 20:25.
 */
public class GlobalVariable {
    //音乐
    public  static PlayerMusic play;
    //系统托盘
    public static TaskTray taskTray;
    //主界面
    public static JFrame mainFrame;

    //登陆界面
    public static JFrame loginFrame;

    public static SwingUser loginUser;

    /**
     * 聊天窗口
     */
    public static ChatFrame chatFrame;
}
