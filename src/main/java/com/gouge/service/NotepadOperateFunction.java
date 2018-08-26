package com.gouge.service;

import com.alibaba.fastjson.JSON;
import com.gouge.base.*;
import com.gouge.param.main.NotepadParam;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

/**
 * Created by Godden
 * Datetime : 2018/8/14 1:01.
 */
public class NotepadOperateFunction extends MyFrame implements ButtonService {
    private boolean isFramLoad = false;
    private JTextPane pane = null;
    private JTextField titleField = null;
    private JTextField keywordtitleField = null;
    @Override
    public void execute() {
        if(!isFramLoad) {
            isFramLoad = true;
            newFrame();
        }else{
            titleField.setText("");
            keywordtitleField.setText("");
            pane.setText("");
            this.setVisible(true);
        }
    }

    public NotepadOperateFunction(){
//        HttpUtile.sendHttpPost(Path.httpUrl+"v1/login","{\"username\":\"gouge\",\"password\":\"gouge\"}");
//        newFrame();
    }

    public void newFrame(){
        this.setTitle("添加记事");
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration()).bottom;
        this.setSize(width,height);
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        JPanel backPanel= SwingHelp.getJpanel(0,0,width,height);

        JLabel tileLable = SwingHelp.getJLabel("主题:",0,20,100,40);
        titleField = SwingHelp.getJTextField("title",70,20,800,40);
        JLabel keywordLable = SwingHelp.getJLabel("关键字:",0,70,100,40);
        keywordtitleField = SwingHelp.getJTextField("title",70,70,800,40);
        pane = new JTextPane();
        pane.setContentType("text/html");

        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(0,200,width,height-300);

        JMenuBar bar = new JMenuBar();
        JMenu operater = new JMenu("操作");
        String[]  operaterArray = {"加粗", "倾斜", "下划线"};
        Action []  operaterAction = {new StyledEditorKit.BoldAction(), new StyledEditorKit.ItalicAction(),
                new StyledEditorKit.UnderlineAction()};
        for (int i = 0; i < operaterArray.length;i++){
            JMenuItem tempItem = new JMenuItem(operaterArray[i]);
            tempItem.setAction(operaterAction[i]);
            tempItem.setText(operaterArray[i]);
            operater.add(tempItem);
        }
        operaterArray = null;operaterAction=null;
        bar.add(operater);
        JMenu pailie = new JMenu("排列");
        String[]  orderArray = {"向左", "居中", "向右"};
        int []  orderAction = {StyleConstants.ALIGN_LEFT,StyleConstants.ALIGN_CENTER,StyleConstants.ALIGN_RIGHT};
        for (int i = 0; i < orderArray.length;i++){
            JMenuItem tempItem = new JMenuItem(orderArray[i]);
            tempItem.setAction(new StyledEditorKit.AlignmentAction("Left Align",orderAction[i]));
            tempItem.setText(orderArray[i]);
            pailie.add(tempItem);
        }
        operaterArray = null;operaterAction=null;
        bar.add(pailie);
        JMenu menu = new JMenu("字体");
        String[] fontTypes = {"仿宋", "新宋体", "楷体", "微软正黑体", "黑体","微软雅黑","宋体"};
        for (int i = 0; i < fontTypes.length;i++)
        {
            JMenuItem nextTypeItem = new JMenuItem(fontTypes[i]);
            nextTypeItem.setAction(new StyledEditorKit.FontFamilyAction(fontTypes[i], fontTypes[i]));
            menu.add(nextTypeItem);
        }
        bar.add(menu);
        JMenu fontsize = new JMenu("大小");
        int[] fontSizes = {6, 8,10,12,14, 16, 20,24, 32,36,48,72};
        for (int i = 0; i < fontSizes.length;i++)
        {
            JMenuItem nextSizeItem = new JMenuItem(String.valueOf(fontSizes[i]));
            nextSizeItem.setAction(new StyledEditorKit.FontSizeAction(String.valueOf(fontSizes[i]), fontSizes[i]));
            fontsize.add(nextSizeItem);
        }
        bar.add(fontsize);
        bar.setBounds(0,170,200,30);
        JMenu imgMenu= new JMenu("图片");
        JMenuItem imgMenuItem = new JMenuItem("插入图片");
        imgMenuItem.setAction(new DocImageAction("图片",pane));
//        imgMenuItem.addActionListener(this);
        imgMenu.add(imgMenuItem);
        bar.add(imgMenu);

        JButton button = SwingHelp.getJButton("保存",width/2,height-90,100,50,null);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveNotepad();
            }
        });

        SwingHelp.JpanelAdd(backPanel,scrollPane,button,bar,tileLable,titleField,keywordLable,keywordtitleField);
        this.add(backPanel);
        this.repaint();
        this.setVisible(true);
    }

    public void saveNotepad(){
        String title = titleField.getText();
        if(StringUtils.isEmpty(title)){
            JOptionPane.showMessageDialog(null,"主题不能为空!");
            return;
        }
        NotepadParam param = new NotepadParam();
        param.setTitle(title);
        param.setKeyword(keywordtitleField.getText());
        param.setContent(pane.getText());
        JsonResult jsonResult =  HttpUtile.sendHttpPost(Path.httpUrl+"v1/notepad/saveOrUpdateNotepad", JSON.toJSONString(param));
        if(jsonResult.getResult().equals("000")) {
            JOptionPane.showMessageDialog(null,"保存成功!");
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null,jsonResult.getDesc());
        }
    }

    public static void main(String[] args) {
        new NotepadOperateFunction();
    }
}
