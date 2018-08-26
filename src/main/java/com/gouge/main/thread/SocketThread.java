package com.gouge.main.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gouge.base.GlobalVariable;
import com.gouge.base.HttpUtile;
import com.gouge.base.Path;
import com.gouge.base.SystemVariable;
import com.gouge.base.tools.EncryptTool;
import com.gouge.main.frame.ChatFrame;
import com.gouge.param.SocketSendData;
import com.gouge.param.user.SwingUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * Created by Godden
 * Datetime : 2018/8/14 18:40.
 */
public class SocketThread implements Runnable {
    private final Logger logger = LogManager.getLogger(SocketThread.class);
    private Socket socket;
    BufferedReader reader=null;
    Writer writer = null;
    int seconds = 0;
    public SocketThread(final Socket socket){
        this.socket = socket;
        try {
            reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        sendNullSendData(writer);
        while (true){
            try {
                String lineString="";
                while( (lineString = reader.readLine())!=null ){
                    logger.info("接受到服务器信息:{}",EncryptTool.getDeCode(lineString, SystemVariable.key));
                    GlobalVariable.chatFrame.putInlineInfo(JSONObject.parseObject(EncryptTool.getDeCode(lineString, SystemVariable.key),SocketSendData.class));
                }
                Thread.sleep(500);
            } catch (IOException e) {
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void sendNullSendData(final Writer writer){
        (new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    seconds = seconds + 500;
                    if(seconds/1000 >= 60*5){
                        SocketSendData data = new SocketSendData();
                        data.setUserId(GlobalVariable.loginUser.getId());
                        data.setUserName(GlobalVariable.loginUser.getNickName());
                        data.setSendType(100);
                        data.setContent("空数据包！");
                        try {
                            writer.write(EncryptTool.getEnCode(JSON.toJSONString(data), SystemVariable.key)+"\n");
                            writer.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        seconds = 0;
                    }
                }
            }
        }).start();
    }

}
