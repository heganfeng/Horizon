package com.gouge.base;

import com.gouge.param.PageInfo;
import com.gouge.service.main.UserRelationFunction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Godden
 * Datetime : 2018/8/15 23:55.
 */
public class PageFrame extends MyFrame {

    protected JPanel backPanel;
    protected JTable jt;
    protected PageInfo pageInfo;
    protected JScrollPane jsp;
    protected JButton queryButton;
    public PageFrame(){
        backPanel= SwingHelp.getJpanel(0,0,0,0);

        queryButton = new JButton("查询");
        jt = new JTable();
        jsp = new JScrollPane(jt);
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){//实现双击
                    rowDoubleClick(e);
                } else
                    return;
            }
        });

        SwingHelp.JpanelAdd(backPanel,queryButton,jsp);
        this.add(backPanel);
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     * 添加分页
     * @param initialHeight
     */
    public void setPagePanel(int initialHeight){
        JButton previousPageButton = SwingHelp.getJButton("上一页",100,initialHeight,100,50,null);
        JLabel currentPageLable = SwingHelp.getJLabel("当前第：",200,initialHeight,60,50);
        final JTextField pageTextField = SwingHelp.getJTextField("goPage",260,initialHeight,50,50);
        pageTextField.setText(String.valueOf(getPageInfo().getPageIndex()));
        JLabel jLabel = SwingHelp.getJLabel("页,总共"+getPageInfo().getPageCount()+"页,总记录:"+getPageInfo().getRecordCount()+"条",310,initialHeight,150,50);
        JButton goPageButton = SwingHelp.getJButton("跳转",470,initialHeight,100,50,null);
        JButton nextPageButton = SwingHelp.getJButton("下一页",580,initialHeight,100,50,null);
        nextPageButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(getPageInfo().getPageIndex() < getPageInfo().getPageCount()){
                    pageReloadDate(getPageInfo().getPageIndex());
                    pageTextField.setText(String.valueOf(getPageInfo().getPageIndex()));
                    backPanel.repaint();
                }
            }
        });
        previousPageButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(getPageInfo().getPageIndex() > 1){
                    pageReloadDate(getPageInfo().getPageIndex()-2);
                    pageTextField.setText(String.valueOf(getPageInfo().getPageIndex()));
                    backPanel.repaint();
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
                pageReloadDate(Integer.valueOf(gopage)-1);
            }
        });
        backPanel.add(previousPageButton);
        backPanel.add(currentPageLable);
        backPanel.add(jLabel);
        backPanel.add(goPageButton);
        backPanel.add(pageTextField);
        backPanel.add(nextPageButton);
    }

    /**
     * 分页加载数据的方法
     * @param index
     */
    protected  void pageReloadDate(int index){

    }

    /**
     * table 行双击事件
     */
    protected void  rowDoubleClick(MouseEvent e){

    }
}
