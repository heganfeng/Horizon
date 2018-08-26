package com.gouge.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gouge.base.*;
import com.gouge.base.music.PlayerMusic;
import com.gouge.param.main.SwingMenuVo;
import com.gouge.service.ButtonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by Godden
 * Datetime : 2018/8/7 19:17.
 */
public class MainFrame extends MyFrame {

    private static final Logger logger = LogManager.getLogger(MainFrame.class);

    public MainFrame(){
        GlobalVariable.play = new PlayerMusic();
        this.setTitle("主界面");
        this.setSize(816,738);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        JPanel backPanel= SwingHelp.getJpanel(0,0,816,738);

        JMenuBar menuBar = SwingHelp.getJMenuBar(0, 0, 800, 30);
        List<Object> list = (List<Object>)HttpConnectionHelp.httpPost(Path.httpUrl+"v1/menu/getMenus",null,List.class);
        if(null != list && list.size() > 0){
            int x = 0;
            for (Object vo : list){
                SwingMenuVo parseVo = JSONObject.parseObject(JSON.toJSONString(vo),SwingMenuVo.class);
                JMenu mainMenu= SwingHelp.getJMenu(parseVo.getMenuName(),x, 0, 80, 30);
                mainMenu.addSeparator();
                x = x + 80;
                menuBar.add(mainMenu);
                if(!parseVo.getMenus().isEmpty()){
                    iterationMenus(parseVo.getMenus(),mainMenu,null);
                }
            }
        }
        CmdTextArea textArea = new CmdTextArea();
        textArea.addKeyListener(textArea);
        textArea.addCaretListener(textArea);
        textArea.setFont(new Font("宋体", Font.PLAIN, 14));
        textArea.append("-->");
        textArea.requestFocus();
        textArea.setCaretPosition(textArea.getText().length());
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(0,30,800,650);

        backPanel.add(menuBar);
        backPanel.add(scrollPane);
        this.add(backPanel);
        this.repaint();
    }

    private void iterationMenus(List<SwingMenuVo> list,JMenu mainMenu,JMenuItem jMenuItem){
        for (SwingMenuVo chirdVo : list){
            JMenu tempJmenu = null;
            JMenuItem menuItem = null;
            if(null != chirdVo.getMenus() && chirdVo.getMenus().size() > 0){
                tempJmenu= SwingHelp.getJMenu(chirdVo.getMenuName(),0, 0, 80, 30);
                tempJmenu.addSeparator();
                mainMenu.add(tempJmenu);
            }else{
                try {
                    ButtonService service = null;
                    String className =  chirdVo.getClassName();
                    if(chirdVo.getClassName() != null && chirdVo.getClassName().length() > 10){
                        Class clasz = Class.forName(chirdVo.getClassName());
                        service = (ButtonService) clasz.newInstance();
                    }
                    menuItem = SwingHelp.getJMenuItem(chirdVo.getMenuName(),'a',service);
                    mainMenu.add(menuItem);
                } catch (ClassNotFoundException e) {
                    logger.error("找不到类"+chirdVo.getClassName(),e);
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if(null != chirdVo.getMenus() && chirdVo.getMenus().size() > 0){
                iterationMenus(chirdVo.getMenus(),tempJmenu,menuItem);
            }
        }
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
