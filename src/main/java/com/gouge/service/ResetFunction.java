package com.gouge.service;

import javax.swing.*;

/**
 * Created by Godden
 * Datetime : 2018/8/7 18:01.
 */
public class ResetFunction implements ButtonService{

    private JTextField []  fileds;
    @Override
    public void execute() {
        for (JTextField field : fileds){
            field.setText("");
        }
    }

    public JTextField[] getFileds() {
        return fileds;
    }

    public void setFileds(JTextField[] fileds) {
        this.fileds = fileds;
    }
}
