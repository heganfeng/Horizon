package com.gouge.main.thread;

import com.gouge.base.MyFrame;
import com.gouge.base.SwingHelp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Godden
 * Datetime : 2018/8/12 1:16.
 */
public class TipsFrameThread implements Runnable{

    private MyFrame tipsFrame;
    private int width = 300;
    private int height = 200;
    private int x;
    private int y;
    private int tipstillseconds = 5;
    private int sends = tipstillseconds;
    private boolean isEnter = false;

    public TipsFrameThread(String title,String content){
        tipsFrame = new MyFrame();
        tipsFrame.setTitle("提示");
        x= Toolkit.getDefaultToolkit().getScreenSize().width-Toolkit.getDefaultToolkit().getScreenInsets(tipsFrame.getGraphicsConfiguration()).right-width-5;
        y=Toolkit.getDefaultToolkit().getScreenSize().height-Toolkit.getDefaultToolkit().getScreenInsets(tipsFrame.getGraphicsConfiguration()).bottom-5;
        tipsFrame.setBounds(x, y, width, height);
        tipsFrame.setVisible(true);
        tipsFrame.setLayout(null);
        tipsFrame.setAlwaysOnTop(true);
        if (title != null){
            JLabel smallLabe = SwingHelp.getJLabel("<html><font size=\"3\" color=\"red\">"+title+"</font></html>",0,10,300,30);
            tipsFrame.add(smallLabe);
        }
        JLabel textLabe = SwingHelp.getJLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+content+"</html>",0,40,300,150);
        textLabe.setVerticalAlignment(SwingConstants.NORTH);
        tipsFrame.add(textLabe);
        textLabe.repaint();
    }

    @Override
    public void run() {
        int driftY = 0;
        tipsFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                sends = tipstillseconds;
                isEnter = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                isEnter = false;
            }
        });
        while(true){
            try {
                Thread.sleep(10);
                driftY = driftY + 4;
                tipsFrame.setBounds(x, y-driftY, width, height);
                if(driftY >= 200){
                    while (true){
                        Thread.sleep(1000);
                        if(!isEnter){
                            sends--;
                            if(sends < 0){
                                tipsFrame.setVisible(false);
                                break;
                            }
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            if(sends < 0){
                break;
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new TipsFrameThread("音乐加载","音乐加载完成"));
        thread.start();
    }
}
