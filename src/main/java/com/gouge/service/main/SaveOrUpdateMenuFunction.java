package com.gouge.service.main;

import com.alibaba.fastjson.JSON;
import com.gouge.base.*;
import com.gouge.param.main.SwingMenuVo;
import com.gouge.service.ButtonService;
import com.gouge.service.MenuFunciton;

import javax.swing.*;

/**
 * Created by Godden
 * Datetime : 2018/8/8 12:07.
 */
public class SaveOrUpdateMenuFunction implements ButtonService {

    private JTextField idTextField;
    private JTextField parntIdTextField;
    private JTextField menuNameTextField;
    private JTextField isMenuTextField;
    private JTextField classNameTextField;
    private JTextField isActiveTextField;
    private JFrame frame;
    private MenuFunciton parntFrame;
    private boolean isSave;
    @Override
    public void execute() {
        newSaveOrUpdateMenuFrame();
    }

    public void setIsSave(boolean isSave){
        this.isSave = isSave;
    }

    public SaveOrUpdateMenuFunction(MenuFunciton MenuFunciton, JFrame frame, JTextField idTextField, JTextField parntIdTextField, JTextField menuNameTextField,
                                    JTextField isMenuTextField, JTextField classNameTextField, JTextField isActiveTextField,boolean isSave){
        this.idTextField = idTextField;
        this.parntIdTextField = parntIdTextField;
        this.menuNameTextField = menuNameTextField;
        this.isMenuTextField = isMenuTextField;
        this.classNameTextField = classNameTextField;
        this.isActiveTextField = isActiveTextField;
        this.frame = frame;
        this.parntFrame = MenuFunciton;
        this.isSave = isSave;
    }

    public void newSaveOrUpdateMenuFrame(){
        String menuName = menuNameTextField.getText();
        String isMenu = isMenuTextField.getText();
        String className = classNameTextField.getText();
        String isActive = isActiveTextField.getText();
        SwingMenuVo vo = new SwingMenuVo();
        if(StringUtils.isEmpty(menuName)){
            JOptionPane.showMessageDialog(null,"菜单名字不能为空！");
            return;
        }
        if(!StringUtils.isInteger(isMenu)){
            JOptionPane.showMessageDialog(null,"请正确输入是否为菜单！");
            return;
        }
        if(!StringUtils.isInteger(isActive)){
            JOptionPane.showMessageDialog(null,"请正确输入是否为激活！");
            return;
        }vo.setMenuName(menuName);
        vo.setIsMenu(Integer.parseInt(isMenu));
        vo.setClassName(className);
        vo.setIsActive(Integer.parseInt(isActive));
        String parntId = parntIdTextField.getText();
        if(StringUtils.isEmpty(parntId)){
            parntId = null;
        }
        vo.setParntId(parntId);
        String id = idTextField.getText();
        vo.setId(id);
        if(isSave)
            vo.setId(null);
        JsonResult jsonResult = HttpUtile.sendHttpPost(Path.httpUrl+"v1/menu/saveOrUpdateMenu", JSON.toJSONString(vo));
        if(jsonResult.getResult().equals("000")){
            if(isSave)
                JOptionPane.showMessageDialog(null,"新增成功！");
            else
                JOptionPane.showMessageDialog(null,"修改成功！");
        }else
            JOptionPane.showMessageDialog(null,jsonResult.getDesc());
        frame.setVisible(false);
        parntFrame.reloadDate(parntFrame.getParam(null));
    }
}
