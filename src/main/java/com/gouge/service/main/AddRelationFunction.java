package com.gouge.service.main;

import com.alibaba.fastjson.JSON;
import com.gouge.base.*;
import com.gouge.param.main.UserAdvancedParam;
import com.gouge.param.main.UserRelationAdvancedParam;
import com.gouge.service.ButtonService;
import com.gouge.tablemodel.UnifiedModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Created by Godden
 * Datetime : 2018/8/16 0:14.
 */
public class AddRelationFunction extends PageFrame  implements ButtonService {
    private boolean isFramLoad = false;
    private String pageUrl = Path.httpUrl+"v1/user/getPageCanAddRelationUsers";
    private String [] titleArr = {"Id","用户名","昵称","创建时间"};
    private String [] fieldArr = {"id","userCode","nickName","crtDate"};
    private JTextField queryNameField;

    @Override
    public void execute() {
        if(!isFramLoad) {
            isFramLoad = true;
            newFrame();
        }else{
            this.setVisible(true);
        }
    }

    public AddRelationFunction(){

    }
    private UnifiedModel sm;
    public void newFrame(){
        this.setTitle("添加好友");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        backPanel.setBounds(0,0,900,600);
        queryButton.setBounds(260,20,100,50);
        queryNameField = SwingHelp.getJTextField("nickName",50,20,200,50);
        UserAdvancedParam param = getParam(null);
        sm = new UnifiedModel(pageUrl,UserAdvancedParam.class,titleArr,fieldArr, JSON.toJSONString(param));
        jt.setModel(sm);
        setPageInfo(sm.getPageInfo());
        setPagePanel(80);
        jsp.setBounds(0,150,900,360);
        queryButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                reloadDate(getParam(null));
            }
        });
        backPanel.add(queryNameField);
        backPanel.repaint();
        this.repaint();
        this.setVisible(true);
    }

    @Override
    protected void pageReloadDate(int index) {
        reloadDate(getParam(index));
    }

    @Override
    protected void rowDoubleClick(MouseEvent e) {
        int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置
        int  col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); //获得列位置 String cellVal=(String)(tbModel.getValueAt(row,col)); //获得点击单元格数据 txtboxRow.setText((row+1)+""); txtboxCol.setText((col+1)+"");
        String id = String.valueOf(jt.getValueAt(row,0));
        if(GlobalVariable.loginUser.getId().equals(id)){
            JOptionPane.showMessageDialog(null,"不能添加自己！");
            return;
        }
        String nickName = String.valueOf(jt.getValueAt(row,2));
        UserRelationAdvancedParam param = new UserRelationAdvancedParam();
        param.setUserId(GlobalVariable.loginUser.getId());
        param.setFriendId(id);
        JsonResult jr  = HttpUtile.sendHttpPost(Path.httpUrl+"v1/user/addUserRelation",JSON.toJSONString(param));
        if (jr.getResult().equals("000")){
            JOptionPane.showMessageDialog(null,"添加"+nickName+"为好友成功！");
            this.setVisible(false);
        }else {
            JOptionPane.showMessageDialog(null,jr.getDesc());
        }

    }

    public void reloadDate(UserAdvancedParam param){
        UnifiedModel sm = new UnifiedModel(pageUrl,UserAdvancedParam.class,titleArr,fieldArr, JSON.toJSONString(param));
        jt.setModel(sm);
        setPageInfo(sm.getPageInfo());
        jt.repaint();
    }

    public UserAdvancedParam getParam(Integer index){
        String nickName = queryNameField.getText();
        if(StringUtils.isEmpty(nickName))
            nickName = null;

        UserAdvancedParam param = new UserAdvancedParam();
        param.setPageSize(20);
        param.setId(GlobalVariable.loginUser.getId());
        param.setPageIndex(index == null ? 0 : index);
        param.setNickName(nickName);
        return param;
    }

}
