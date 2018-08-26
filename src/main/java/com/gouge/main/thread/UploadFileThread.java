package com.gouge.main.thread;

import com.gouge.base.*;

import javax.swing.*;

/**
 * Created by Godden
 * Datetime : 2018/8/11 16:52.
 */
public class UploadFileThread implements Runnable{
    private String path;
    private JLabel textLable;
    private InfiniteProgressPanel glasspane;
    private JFrame frame;

    public UploadFileThread(InfiniteProgressPanel glasspane,String path,JLabel textLable,JFrame frame){
        this.path = path;
        this.textLable=textLable;
        this.glasspane = glasspane;
        this.frame = frame;
    }

    @Override
    public void run() {
        JsonResult jsonResult =  HttpUtile.uploadFile(Path.httpUrl+"v1/upload/uploadMusic",textLable.getText());
        if(jsonResult.getResult().equals("000")) {
            GlobalVariable.play.loadMusic();
            JOptionPane.showMessageDialog(null,"上传成功！");
            textLable.setText("");
            glasspane.stop();
            frame.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(null,jsonResult.getDesc());
            glasspane.stop();
        }
    }
}
