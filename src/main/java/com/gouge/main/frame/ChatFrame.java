package com.gouge.main.frame;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gouge.base.*;
import com.gouge.param.SocketSendData;
import com.gouge.param.user.SwingUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Writer;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Godden
 * Datetime : 2018/8/17 11:29.
 */
public class ChatFrame extends MyFrame {
    private JTabbedPane tabbedPane = new JTabbedPane();
    private Map<String,ChatPanel> chatPanelMap = new HashMap<>();
    private Writer writer;
    public ChatFrame(Writer writer){
        this.writer = writer;
        this.add(tabbedPane, BorderLayout.CENTER);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(900,700);
        this.setLocationRelativeTo(null);
    }

    public String addChatByUserCode(String userCode){
        JsonResult jsonResult =  HttpUtile.sendHttpPost(Path.httpUrl+"v1/user/getUserByUserCode",userCode);
        if(jsonResult.getResult().equals("000")) {
            if (jsonResult.getData() == null){
                return new StringBuffer("找不到用户").append(userCode).toString();
            }else{
                SwingUser user = JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()), SwingUser.class);
                addChat(user);
                return null;
            }
        }else{
            return new StringBuffer("找不到用户").append(userCode).toString();
        }
    }

    public void addChat(SwingUser user) {
        if(chatPanelMap.containsKey(user.getId())){
            tabbedPane.addTab(null, chatPanelMap.get(user.getId()));
            tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, chatPanelMap.get(user.getId()).getTab());
        }else{
            ChatPanel content = new ChatPanel(tabbedPane,user,writer);
            chatPanelMap.put(user.getId(),content);
            tabbedPane.addTab(null, content);
            tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, content.getTab());
        }
        this.setVisible(true);
    }

    public void putInlineInfo(SocketSendData data){
        if(data.getSendType() != 100){
            if(chatPanelMap.get(data.getUserId()) == null){
                JsonResult jsonResult =  HttpUtile.sendHttpPost(Path.httpUrl+"v1/user/getUserById",data.getUserId());
                if(jsonResult.getResult().equals("000")) {
                    SwingUser user = JSONObject.parseObject(JSON.toJSONString(jsonResult.getData()), SwingUser.class);
                    addChat(user);
                }else{

                }
            }

            chatPanelMap.get(data.getUserId()).appendString(data.getUserName(),data.getContent());
        }

    }

}
