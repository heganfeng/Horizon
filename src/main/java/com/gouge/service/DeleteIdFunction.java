package com.gouge.service;

import com.alibaba.fastjson.JSON;
import com.gouge.base.*;
import com.gouge.param.main.IdParam;
import com.gouge.param.main.SwingMenuVo;

import javax.swing.*;

/**
 * Created by Godden
 * Datetime : 2018/8/8 1:17.
 */
public class DeleteIdFunction implements ButtonService{
    private String id;
    private String tableName;
    private JFrame frame;
    private MenuFunciton parntFrame;
    @Override
    public void execute() {
        if (tableName.equals("swing_menu")){
            IdParam param = new IdParam();
            param.setId(id);
            JsonResult jr  = HttpUtile.sendHttpPost(Path.httpUrl+"v1/menu/deleteSwingMenuById",JSON.toJSONString(param));
            if (jr.getResult().equals("000")){
                JOptionPane.showMessageDialog(null,"删除成功");
            }else
                JOptionPane.showMessageDialog(null,jr.getDesc());
            this.frame.setVisible(false);
            parntFrame.reloadDate(parntFrame.getParam(null));
        }
    }

    public void reloadId(String id){
        this.id = id;
    }

    public DeleteIdFunction(String id,String tableName,JFrame frame,MenuFunciton MenuFunciton){
        this.id = id;
        this.tableName = tableName;
        this.frame = frame;
        this.parntFrame = MenuFunciton;
    }
}
