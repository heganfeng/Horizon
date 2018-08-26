package com.gouge.service.main;

import com.alibaba.fastjson.JSON;
import com.gouge.base.*;
import com.gouge.param.PageInfo;
import com.gouge.param.main.SwingMusicAdvancedParam;
import com.gouge.param.main.UserRelationAdvancedParam;
import com.gouge.service.ButtonService;
import com.gouge.tablemodel.MusicPageModel;
import com.gouge.tablemodel.UnifiedModel;
import com.gouge.tablemodel.UserRelationModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Godden
 * Datetime : 2018/8/9 17:20.
 */
public class UserRelationFunction extends MyFrame implements ButtonService {
    private boolean isFramLoad = false;
    private String pageUrl = Path.httpUrl+"v1/user/getPageUserRelations";
    private String [] titleArr = {"Id","用户名","昵称","亲密度"};
    private String [] fieldArr = {"id","friendCode","friendName","familiarity"};
    @Override
    public void execute() {
        if(!isFramLoad) {
            isFramLoad = true;
            newFrame();
        }else{
            this.setVisible(true);
        }
    }

    public UserRelationFunction(){

    }
    private JTable jt;
    private PageInfo pageInfo;
    private JTextField queryNameField;
    public void  newFrame(){
        this.setTitle("好友管理");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        JPanel backPanel= SwingHelp.getJpanel(0,0,900,600);
        JButton jButton = SwingHelp.getJButton("查询",260,20,100,50,null);
        AddRelationFunction function = new AddRelationFunction();
        JButton newButton = SwingHelp.getJButton("新增",370,20,100,50,function);
        queryNameField = SwingHelp.getJTextField("friendName",50,20,200,50);

        UserRelationAdvancedParam param = getParam(null);
        UnifiedModel sm = new UnifiedModel(pageUrl,UserRelationAdvancedParam.class,titleArr,fieldArr, JSON.toJSONString(param));
        jt = new JTable(sm);
        setPageInfo(sm.getPageInfo());
        setPagePanel(this);
        JScrollPane jsp = new JScrollPane(jt);
        jsp.setBounds(0,150,900,360);
        jt.getColumnModel().getColumn(0).setMaxWidth(60);
        jt.getColumnModel().getColumn(3).setMaxWidth(60);
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){//实现双击

                } else
                    return;
            }
        });
        jButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                reloadDate(getParam(null));
            }
        });

        SwingHelp.JpanelAdd(backPanel,jButton,jsp,queryNameField,newButton);
        this.add(backPanel);
        this.repaint();
    }

    public void reloadDate(UserRelationAdvancedParam param){
        UnifiedModel sm = new UnifiedModel(pageUrl,UserRelationAdvancedParam.class,titleArr,fieldArr, JSON.toJSONString(param));
        jt.setModel(sm);
        setPageInfo(sm.getPageInfo());
        jt.getColumnModel().getColumn(0).setMaxWidth(60);
        jt.getColumnModel().getColumn(3).setMaxWidth(60);
        jt.repaint();
    }

    public UserRelationAdvancedParam getParam(Integer index){
        String firendName = queryNameField.getText();
        if(StringUtils.isEmpty(firendName))
            firendName = null;

        UserRelationAdvancedParam param = new UserRelationAdvancedParam();
        param.setPageSize(20);
        param.setPageIndex(index == null ? 0 : index);
        param.setUserId(GlobalVariable.loginUser.getId());
        param.setFriendName(firendName);
        return param;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
    public void setPagePanel(final UserRelationFunction in){
        JButton previousPageButton = SwingHelp.getJButton("上一页",100,80,100,50,null);
        JLabel currentPageLable = SwingHelp.getJLabel("当前第：",200,80,60,50);
        final JTextField pageTextField = SwingHelp.getJTextField("goPage",260,80,50,50);
        pageTextField.setText(String.valueOf(getPageInfo().getPageIndex()));
        JLabel jLabel = SwingHelp.getJLabel("页,总共"+getPageInfo().getPageCount()+"页,总记录:"+getPageInfo().getRecordCount()+"条",310,80,150,50);
        JButton goPageButton = SwingHelp.getJButton("跳转",470,80,100,50,null);
        JButton nextPageButton = SwingHelp.getJButton("下一页",580,80,100,50,null);
        nextPageButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(getPageInfo().getPageIndex() < getPageInfo().getPageCount()){
                    in.reloadDate(in.getParam(getPageInfo().getPageIndex()));
                    pageTextField.setText(String.valueOf(getPageInfo().getPageIndex()));
                    in.repaint();
                }
            }
        });
        previousPageButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(getPageInfo().getPageIndex() > 1){
                    in.reloadDate(in.getParam(getPageInfo().getPageIndex()-2));
                    pageTextField.setText(String.valueOf(getPageInfo().getPageIndex()));
                    in.repaint();
                }
            }
        });
        goPageButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                String gopage = pageTextField.getText();
                if(StringUtils.isEmpty(gopage)){
                    try{
                        Integer.valueOf(gopage);
                    }catch (Exception e1){
                        return;
                    }
                    return;
                }
                in.reloadDate(in.getParam(Integer.valueOf(gopage)-1));
            }
        });
        in.add(previousPageButton);
        in.add(currentPageLable);
        in.add(jLabel);
        in.add(goPageButton);
        in.add(pageTextField);
        in.add(nextPageButton);
    }
}
