package com.gouge.base.music;

import com.gouge.base.GlobalVariable;
import com.gouge.base.MyFrame;
import com.gouge.base.SwingHelp;
import com.gouge.base.SystemVariable;
import com.gouge.param.main.SwingMusicVo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.media.Player;
import javax.media.Time;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


/**
 *@autor Grandfen
 *@date Jun 27, 2013 3:05:13 PM
 *@version 1.0
 */
public class BackMusicFrame extends MyFrame implements Runnable{

    /**
     *
     */
    private static final long serialVersionUID = -1935552068607233632L;

    private JPanel movePanel = null;
    private JLabel showlable = null;
    private int index = 1;
    private int fenge = 0;
    private int fenm = 0;


    public BackMusicFrame(List<SwingMusicVo> mp3List){
//        this.setIconImage(this.getToolkit().getImage(MyMain.iconPath));
        this.setSize(600,150);
        this.setTitle("");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(null);
        JPanel backPanel= SwingHelp.getJpanel(0,0,600,150);
        JMenuBar mainBar=new JMenuBar();
        mainBar.setBounds(0, 0, 580, 20);
        mainBar.setAutoscrolls(true);
        mainBar.setLayout(null);
        JMenu mainMenu=new JMenu("音乐");
        mainMenu.setAutoscrolls(true);
        mainMenu.setBounds(0, 0, 50, 20);

        JMenu artistItem=new JMenu("歌曲");
        mainMenu.add(artistItem);
        for(final SwingMusicVo temp : mp3List){
            JMenuItem musicItem=new JMenuItem(temp.getMusicName());
            musicItem.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    GlobalVariable.play.playThis(temp.getIndex());
                }
            });
            artistItem.add(musicItem);
        }


        JMenu OperationMenu=new JMenu("操作");
        OperationMenu.setBounds(50, 0, 80, 20);

        JMenuItem nextMusic=new JMenuItem("下一首");
        nextMusic.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                GlobalVariable.play.nextMusic();
            }
        });

        JMenuItem upMusc=new JMenuItem("上一首");
        upMusc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                GlobalVariable.play.upMusic();
            }
        });

        JMenuItem randomMusic=new JMenuItem("随机一首");
        randomMusic.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                GlobalVariable.play.randMusic();
            }
        });

        final JMenuItem orderPlay=new JMenuItem("顺序");
        final JMenuItem randomlay=new JMenuItem("随机");
        if(PlayerMusic.playFormat.equals("order")){
            orderPlay.setEnabled(false);
        }else if(PlayerMusic.playFormat.equals("random")){
            randomlay.setEnabled(false);
        }else{
            orderPlay.setEnabled(false);
        }
        orderPlay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                orderPlay.setEnabled(false);
                randomlay.setEnabled(true);
                PlayerMusic.playFormat="order";
            }
        });
        randomlay.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                orderPlay.setEnabled(true);
                randomlay.setEnabled(false);
                PlayerMusic.playFormat="random";
            }
        });
        OperationMenu.add(nextMusic);
        OperationMenu.add(upMusc);
        OperationMenu.add(randomMusic);
        OperationMenu.add(orderPlay);
        OperationMenu.add(randomlay);

        mainBar.add(mainMenu);
        mainBar.add(OperationMenu);
        backPanel.add(mainBar);


        try {
            ImageIcon icon = new ImageIcon(new URL(SystemVariable.nextMuscIco));
            JButton shang = new JButton(icon);
            shang.setBounds(0,20,30,30);
            shang.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    GlobalVariable.play.upMusic();
                }
            });
            ImageIcon icon1 = new ImageIcon(new URL(SystemVariable.onMuscIco));
            JButton jix = new JButton(icon1);
            jix.setBounds(30,20,30,30);
            jix.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {

                    if(GlobalVariable.play.isPlay()){
                        GlobalVariable.play.stop();
                    }else{
                        GlobalVariable.play.jix();
                    }
                }
            });
            ImageIcon icon2 = new ImageIcon(new URL(SystemVariable.upMuscIco));
            JButton xia = new JButton(icon2);
            xia.setBounds(60,20,30,30);
            xia.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    GlobalVariable.play.nextMusic();
                }
            });
            backPanel.add(shang);
            backPanel.add(jix);
            backPanel.add(xia);
            this.add(backPanel);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        JPanel xian =new JPanel();
        xian.setBackground(Color.black);
        xian.setBounds(100, 30, 400, 5);
        xian.repaint();
        backPanel.add(xian);

        movePanel = new JPanel();
        movePanel.setBackground(Color.red);
        movePanel.setBounds(90, 20, 10, 30);
        backPanel.add(movePanel);

        showlable = new JLabel();
        showlable.setBounds(510, 20, 50, 30);
        backPanel.add(showlable);
        this.setAlwaysOnTop(true);
        this.repaint();
        this.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                if(x >= 100 && x <= 500 && y > 50 && y <= 80){
                    System.out.println(x);
                    int eve = fenge*fenm+fenm+100;
                    if(x <= eve){
                        if((x-100)%(fenge+1) != 0){
                            setTime((x-100)/(fenge+1) +1);
                            int temp = (x-100)%(fenge+1);
                            movePanel.setBounds(x+temp-10, 20, 10, 30);
                        }else{
                            setTime((x-100)/(fenge+1));
                            movePanel.setBounds(x-10, 20, 10, 30);
                        }
                    }else{
                        int temp = 100 + (fenge+1)*fenm;
                        int dd = x - temp;
                        if(dd%fenge != 0 ){
                            setTime(fenm+dd/fenge+1);
                            int ss = dd%fenge;
                            movePanel.setBounds(x+ss-10, 20, 10, 30);
                        }else{
                            setTime(fenm+dd/fenge);
                            movePanel.setBounds(x-10, 20, 10, 30);
                        }
                    }
                }
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {
            }
        });
    }

    @SuppressWarnings("static-access")
    private void setTime(int time){
        index = time;
        GlobalVariable.play.player.setMediaTime(new Time((float)time));
    }

    public void setPlayInfo(Player play){
        index = 1;
        int longT = (int)play.getDuration().getSeconds();
        int fen = longT/60;
        int miao = 0;
        if(longT%60 != 0){
            miao = longT - 60*fen;
        }
        fenge = 400/longT;
        fenm = 400%longT;
        showlable.setText(fen + ":"+(miao < 10 ? "0"+miao:String.valueOf(miao)));
        movePanel.setBounds(90, 20, 10, 30);
    }

    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
                if(GlobalVariable.play.isPlay()){
                    index++;
                    if(movePanel.getX() < 490){
                        if(index <= fenm)
                            movePanel.setBounds(movePanel.getX()+(fenge+1), 20, 10, 30);
                        else
                            movePanel.setBounds(movePanel.getX()+fenge, 20, 10, 30);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
