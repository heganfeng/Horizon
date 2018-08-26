package com.gouge.service;

import com.alibaba.fastjson.JSON;
import com.gouge.base.*;
import com.gouge.param.PageInfo;
import com.gouge.param.main.IdParam;
import com.gouge.param.main.MenuAdvancedParam;
import com.gouge.param.main.NotepadParam;
import com.gouge.tablemodel.MenuPageModel;
import com.gouge.tablemodel.NotepadPageModel;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Godden
 * Datetime : 2018/8/14 2:08.
 */
public class NotepadManagerFunction extends MyFrame implements ButtonService{
    private boolean isFramLoad = false;
    private JTextField queryTitleField = null;
    private JTextField queryKeywordField = null;
    private JTable jt;
    private PageInfo pageInfo;
    @Override
    public void execute() {
        if(!isFramLoad) {
            isFramLoad = true;
            newFrame();
        }else{
            this.setVisible(true);
        }
    }

    public NotepadManagerFunction(){
//        HttpUtile.sendHttpPost(Path.httpUrl+"v1/login","{\"username\":\"gouge\",\"password\":\"gouge\"}");
//        newFrame();
    }

    public void newFrame(){
        this.setTitle("记事本管理");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        JPanel backPanel= SwingHelp.getJpanel(0,0,900,600);
        queryTitleField = SwingHelp.getJTextField("title",50,20,200,50);
        queryKeywordField = SwingHelp.getJTextField("keyword",260,20,200,50);
        JButton queryButton = SwingHelp.getJButton("查询",500,20,100,50,null);

        NotepadParam param = getParam(null);
        NotepadPageModel sm = new NotepadPageModel(Path.httpUrl+"v1/notepad/getPageNotepads",param);
        jt = new JTable(sm);
        jt.setOpaque(false);
        setPageInfo(sm.getPageInfo());
        setPagePanel(this,jt);
        JScrollPane jsp = new JScrollPane(jt);
        jt.getColumnModel().getColumn(0).setMaxWidth(60);
        jsp.setBounds(0,150,900,360);
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){//实现双击
                    int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置
                    int  col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); //获得列位置 String cellVal=(String)(tbModel.getValueAt(row,col)); //获得点击单元格数据 txtboxRow.setText((row+1)+""); txtboxCol.setText((col+1)+"");
                    String id = String.valueOf(jt.getValueAt(row,0));
                    newEditNotepadFrame(id);
                } else
                    return;
            }
        });
        queryButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                reloadDate(getParam(null));
            }
        });

        SwingHelp.JpanelAdd(backPanel,queryTitleField,queryKeywordField,queryButton,jsp);
        this.add(backPanel);
        this.repaint();
        this.setVisible(true);
    }

    public void reloadDate(NotepadParam param){
        NotepadPageModel sm1 = new NotepadPageModel(Path.httpUrl+"v1/notepad/getPageNotepads",param);
        jt.setModel(sm1);
        setPageInfo(sm1.getPageInfo());
        jt.getColumnModel().getColumn(0).setMaxWidth(60);
        jt.repaint();
    }

    public NotepadParam getParam(Integer index){
        String title = queryTitleField.getText();
        if(StringUtils.isEmpty(title))
            title = null;
        String keyword = queryKeywordField.getText();
        if(StringUtils.isEmpty(keyword))
            keyword = null;

        NotepadParam param = new NotepadParam();
        param.setPageSize(20);
        param.setPageIndex(index == null ? 0 : index);
        param.setTitle(title);
        param.setKeyword(keyword);
        return param;
    }
    private MyFrame editNotepadFrame = null;
    private JTextPane pane = null;
    private JTextField titleField = null;
    private JTextField keywordtitleField = null;
    private JTextField createDateField = null;
    private boolean hasLoadEditFrame = false;
    private String updateId = null;
    public void newEditNotepadFrame(final String id){
        updateId = id;
        IdParam param = new IdParam();
        param.setId(id);
        NotepadParam vo = (NotepadParam)HttpConnectionHelp.httpPost(Path.httpUrl+"v1/notepad/getNotepadById",param,NotepadParam.class);
        if(!hasLoadEditFrame){
            int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - Toolkit.getDefaultToolkit().getScreenInsets(this.getGraphicsConfiguration()).bottom;
            hasLoadEditFrame = true;
            editNotepadFrame = new MyFrame();
            editNotepadFrame.setTitle("修改记事");
            editNotepadFrame.setSize(width,height);
            editNotepadFrame.setLocationRelativeTo(null);
            editNotepadFrame.setLayout(null);
            JPanel backPanel= SwingHelp.getJpanel(0,0,width,height);

            JLabel tileLable = SwingHelp.getJLabel("主题:",0,20,100,40);
            titleField = SwingHelp.getJTextField("title",70,20,800,40);
            JLabel keywordLable = SwingHelp.getJLabel("关键字:",0,70,100,40);
            keywordtitleField = SwingHelp.getJTextField("title",70,70,800,40);
            JLabel createdateLable = SwingHelp.getJLabel("创建时间:",0,120,100,40);
            createDateField = SwingHelp.getJTextField("crtDate",70,120,800,40);
            createDateField.setEnabled(false);
            pane = new JTextPane();
            pane.setContentType("text/html");

            JScrollPane scrollPane = new JScrollPane(pane);
            scrollPane.setBounds(0,200,width,height-300);

            JMenuBar bar = new JMenuBar();
            JMenu operater = new JMenu("操作");
            String[]  operaterArray = {"加粗", "倾斜", "下划线"};
            Action []  operaterAction = {new StyledEditorKit.BoldAction(), new StyledEditorKit.ItalicAction(),
                    new StyledEditorKit.UnderlineAction()};
            for (int i = 0; i < operaterArray.length;i++){
                JMenuItem tempItem = new JMenuItem(operaterArray[i]);
                tempItem.setAction(operaterAction[i]);
                tempItem.setText(operaterArray[i]);
                operater.add(tempItem);
            }
            operaterArray = null;operaterAction=null;
            bar.add(operater);
            JMenu pailie = new JMenu("排列");
            String[]  orderArray = {"向左", "居中", "向右"};
            int []  orderAction = {StyleConstants.ALIGN_LEFT,StyleConstants.ALIGN_CENTER,StyleConstants.ALIGN_RIGHT};
            for (int i = 0; i < orderArray.length;i++){
                JMenuItem tempItem = new JMenuItem(orderArray[i]);
                tempItem.setAction(new StyledEditorKit.AlignmentAction("Left Align",orderAction[i]));
                tempItem.setText(orderArray[i]);
                pailie.add(tempItem);
            }
            operaterArray = null;operaterAction=null;
            bar.add(pailie);
            JMenu menu = new JMenu("字体");
            String[] fontTypes = {"仿宋", "新宋体", "楷体", "微软正黑体", "黑体","微软雅黑","宋体"};
            for (int i = 0; i < fontTypes.length;i++)
            {
                JMenuItem nextTypeItem = new JMenuItem(fontTypes[i]);
                nextTypeItem.setAction(new StyledEditorKit.FontFamilyAction(fontTypes[i], fontTypes[i]));
                menu.add(nextTypeItem);
            }
            bar.add(menu);
            JMenu fontsize = new JMenu("大小");
            int[] fontSizes = {6, 8,10,12,14, 16, 20,24, 32,36,48,72};
            for (int i = 0; i < fontSizes.length;i++)
            {
                JMenuItem nextSizeItem = new JMenuItem(String.valueOf(fontSizes[i]));
                nextSizeItem.setAction(new StyledEditorKit.FontSizeAction(String.valueOf(fontSizes[i]), fontSizes[i]));
                fontsize.add(nextSizeItem);
            }
            bar.add(fontsize);
            bar.setBounds(0,170,200,30);
            JMenu imgMenu= new JMenu("图片");
            JMenuItem imgMenuItem = new JMenuItem("插入图片");
            imgMenuItem.setAction(new DocImageAction("图片",pane));
//        imgMenuItem.addActionListener(this);
            imgMenu.add(imgMenuItem);
            bar.add(imgMenu);

            JButton button = SwingHelp.getJButton("保存",width/2,height-90,100,50,null);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateNotepad(updateId);
                }
            });

            SwingHelp.JpanelAdd(backPanel,scrollPane,button,bar,tileLable,titleField,keywordLable,keywordtitleField,createdateLable,createDateField);
            editNotepadFrame.add(backPanel);
            editNotepadFrame.repaint();
            editNotepadFrame.setVisible(true);
        }else {
            editNotepadFrame.setVisible(true);
        }

        titleField.setText(vo.getTitle());
        keywordtitleField.setText(vo.getKeyword());
        pane.setText(vo.getContent());
        createDateField.setText(DateHelp.sdf.format(vo.getCrtDate()));
    }

    public void updateNotepad(String id){
        String title = titleField.getText();
        if(StringUtils.isEmpty(title)){
            JOptionPane.showMessageDialog(null,"主题不能为空!");
            return;
        }
        NotepadParam param = new NotepadParam();
        param.setId(id);
        param.setTitle(title);
        param.setKeyword(keywordtitleField.getText());
        param.setContent(pane.getText());
        JsonResult jsonResult =  HttpUtile.sendHttpPost(Path.httpUrl+"v1/notepad/saveOrUpdateNotepad", JSON.toJSONString(param));
        if(jsonResult.getResult().equals("000")) {
            JOptionPane.showMessageDialog(null,"修改成功!");
            editNotepadFrame.setVisible(false);
            reloadDate(getParam(null));
        }else{
            JOptionPane.showMessageDialog(null,jsonResult.getDesc());
        }
    }

    public void setPagePanel(final NotepadManagerFunction in,JTable jTable){
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

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public static void main(String[] args) {
        new NotepadManagerFunction();
    }
}
