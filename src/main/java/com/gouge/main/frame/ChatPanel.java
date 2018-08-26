package com.gouge.main.frame;

import com.alibaba.fastjson.JSON;
import com.gouge.base.GlobalVariable;
import com.gouge.base.SwingHelp;
import com.gouge.base.SystemVariable;
import com.gouge.base.tools.EncryptTool;
import com.gouge.main.Test;
import com.gouge.param.SocketSendData;
import com.gouge.param.user.SwingUser;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Godden
 * Datetime : 2018/8/17 11:46.
 */
public class ChatPanel extends JPanel {
    private ImageIcon closeXIcon = new ImageIcon(ChatPanel.class.getResource("/close_gaitubao_com_20x20.png"));
    private Dimension closeButtonSize;
    private JPanel content;
    private JPanel tab;
    private JTextPane pane;
    private JTextArea area;
    private JScrollPane scrollPane;
    private Writer writer1 = null;
    public ChatPanel(final JTabbedPane tabbedPane, final SwingUser user, Writer writer){
        tab = new JPanel();
        tab.setOpaque(false);
        content = this;
        this.writer1 = writer;
        JLabel tabLabel = new JLabel("与" +user.getNickName()+"对话");
        JButton tabCloseButton = new JButton(closeXIcon);
        closeButtonSize = new Dimension(closeXIcon.getIconWidth() + 2, closeXIcon.getIconHeight() + 2);
        tabCloseButton.setPreferredSize(closeButtonSize);
        tabCloseButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int closeTabNumber = tabbedPane.indexOfComponent(content);
                tabbedPane.removeTabAt(closeTabNumber);
            }
        });
        this.setLayout(null);
        JPanel backPanel= SwingHelp.getJpanel(0,0,900,700);
        backPanel.setLayout(null);
        pane = new JTextPane();
        pane.setEditable(false);
        scrollPane = new JScrollPane(pane);
        scrollPane.setBounds(0,0,880,400);
        scrollPane.doLayout();

        area = new JTextArea();
        area.setBounds(0,430,880,120);
        area.setBorder (BorderFactory.createLineBorder(Color.RED,3));
        JButton send = new JButton("发送");
        send.setBounds(250,560,100,50);
        backPanel.add(scrollPane);
        backPanel.add(area);
        backPanel.add(send);
        this.add(backPanel);
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SocketSendData data = new SocketSendData();
                    data.setUserId(GlobalVariable.loginUser.getId());
                    data.setUserName(GlobalVariable.loginUser.getNickName());
                    data.setSendType(0);
                    data.setSendUserId(user.getId());
                    data.setContent(area.getText());
                    //发送信息
                    writer1.write(EncryptTool.getEnCode(JSON.toJSONString(data), SystemVariable.key)+"\n");
                    writer1.flush();
                    Document docs = pane.getDocument();//获得文本对象
                    docs.insertString(docs.getLength(), "\n我: "+area.getText(),null);//对文本进行追加
                    JScrollBar bar=scrollPane.getVerticalScrollBar();
                    bar.setValue(bar.getMaximum());
                } catch (BadLocationException e1) {
                    JOptionPane.showMessageDialog(null,"连接不上服务器，请重新登陆重试！");
                    e1.printStackTrace();
                } catch (IOException e1) {
                    if (e1 instanceof SocketException){
                        JOptionPane.showMessageDialog(null,"连接不上服务器，请重新登陆重试！");
                    }
                    e1.printStackTrace();
                } catch (Exception e1) {
                    if (e1 instanceof SocketException){
                        JOptionPane.showMessageDialog(null,"连接不上服务器，请重新登陆重试！");
                    }
                    e1.printStackTrace();
                }
                area.setText("");
            }
        });
        tab.add(tabLabel, BorderLayout.WEST);
        tab.add(tabCloseButton, BorderLayout.EAST);
    }

    public void appendString(String name,String str){
        Document docs = pane.getDocument();//获得文本对象
        try {
            docs.insertString(docs.getLength(), "\n"+name+": "+str,null);//对文本进行追加
            JScrollBar bar=scrollPane.getVerticalScrollBar();
            bar.setValue(bar.getMaximum());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public JPanel getTab(){
        return tab;
    }
}
