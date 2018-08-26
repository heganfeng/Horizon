package com.gouge.service;

import com.alibaba.fastjson.JSON;
import com.gouge.base.*;
import com.gouge.param.PageInfo;
import com.gouge.param.main.IdParam;
import com.gouge.param.main.MenuAdvancedParam;
import com.gouge.param.main.SwingMenuVo;
import com.gouge.param.main.UserRelationAdvancedParam;
import com.gouge.service.main.SaveOrUpdateMenuFunction;
import com.gouge.tablemodel.MenuPageModel;
import com.gouge.tablemodel.UnifiedModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Godden
 * Datetime : 2018/8/7 22:09.
 */
public class MenuFunciton extends MyFrame implements ButtonService {
    private boolean isFramLoad = false;
    private String pageUrl = Path.httpUrl+"v1/menu/getPageMenus";
    private String [] titleArr = {"Id","菜单名字","是否菜单","类名字","是否激活","创建时间"};
    private String [] fieldArr = {"id","menuName","isMenu","className","isActive","crtDate"};
    @Override
    public void execute() {
        if(!isFramLoad) {
            isFramLoad = true;
            newFrame();
        }else{
            this.setVisible(true);
        }
    }

    public MenuFunciton(){

    }
    private MenuTreeFunction menuTreeFunction;
    private JTable jt;
    private JTextField jTextField;
    private JComboBox isMenuBox;
    private JComboBox isActiveBox;
    private PageInfo pageInfo;
    private boolean isSave;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public void  newFrame(){
        this.setTitle("菜单管理");
        this.setSize(900,600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        JPanel backPanel= SwingHelp.getJpanel(0,0,900,600);
        jTextField = SwingHelp.getJTextField("menuName",50,20,200,50);
        isMenuBox = new JComboBox(new String[]{"是否菜单","0","1"});
        isMenuBox.setBounds(300,20,100,50);
        isActiveBox = new JComboBox(new String[]{"是否激活","0","1"});
        isActiveBox.setBounds(410,20,100,50);

        JButton jButton = SwingHelp.getJButton("查询",550,20,100,50,null);
        JButton inserButton = SwingHelp.getJButton("新增",650,20,100,50,null);

        MenuAdvancedParam param = getParam(null);
        UnifiedModel sm = new UnifiedModel(pageUrl,MenuAdvancedParam.class,titleArr,fieldArr, JSON.toJSONString(param));
        jt = new JTable(sm);
        jt.setOpaque(false);
        setPageInfo(sm.getPageInfo());
        setPagePanel(this,jt);
        JScrollPane jsp = new JScrollPane(jt);
        jt.getColumnModel().getColumn(2).setMaxWidth(60);
        jt.getColumnModel().getColumn(4).setMaxWidth(60);
        jsp.setBounds(0,150,900,360);
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){//实现双击
                    int row =((JTable)e.getSource()).rowAtPoint(e.getPoint()); //获得行位置
                    int  col=((JTable)e.getSource()).columnAtPoint(e.getPoint()); //获得列位置 String cellVal=(String)(tbModel.getValueAt(row,col)); //获得点击单元格数据 txtboxRow.setText((row+1)+""); txtboxCol.setText((col+1)+"");
                    String id = String.valueOf(jt.getValueAt(row,0));
                    newEditMenuFrame(false,id);
                } else
                    return;
            }
        });
        jButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                reloadDate(getParam(null));
            }
        });
        inserButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                newEditMenuFrame(true,null);
            }
        });
        this.add(isActiveBox);
        this.add(isMenuBox);
        this.add(jButton);
        this.add(inserButton);
        this.add(jTextField);
        this.add(jsp);
        this.add(backPanel);
        this.repaint();
    }

    public void reloadDate(MenuAdvancedParam param){
        UnifiedModel sm = new UnifiedModel(pageUrl,MenuAdvancedParam.class,titleArr,fieldArr, JSON.toJSONString(param));
        jt.setModel(sm);
        setPageInfo(sm.getPageInfo());
        jt.getColumnModel().getColumn(2).setMaxWidth(60);
        jt.getColumnModel().getColumn(4).setMaxWidth(60);
        jt.repaint();
    }

    public MenuAdvancedParam getParam(Integer index){
        String menuName = jTextField.getText();
        if(StringUtils.isEmpty(menuName))
            menuName = null;
        String isMenu = String.valueOf(isMenuBox.getSelectedItem());
        if(isMenu.startsWith("是否")){
            isMenu = null;
        }
        String isActive = String.valueOf(isActiveBox.getSelectedItem());
        if(isActive.startsWith("是否")){
            isActive = null;
        }
        MenuAdvancedParam param = new MenuAdvancedParam();
        param.setPageSize(20);
        param.setPageIndex(index == null ? 0 : index);
        param.setMenuName(menuName);
        param.setIsActive(isActive == null ? null :Integer.valueOf(isActive));
        param.setIsMenu(isMenu == null ? null : Integer.valueOf(isMenu));
        return param;
    }
    private MyFrame editMenuFrame = null;
    private JTextField idTextField = null;
    private JTextField parntIdTextField = null;
    private JTextField menuNameTextField = null;
    private JTextField isMenuTextField = null;
    private JTextField classNameTextField = null;
    private JTextField isActiveTextField = null;
    private JButton queryTrueButton = null;
    private JButton deleteButton = null;
    private SaveOrUpdateMenuFunction saveOrUpdateMenuFunction = null;
    private DeleteIdFunction deleteIdFunction = null;
    public void newEditMenuFrame(boolean tempIsSave,String id){
        this.isSave = tempIsSave;
        IdParam param = new IdParam();
        param.setId(id);
        SwingMenuVo vo = null;
        if(!isSave){
            vo = (SwingMenuVo)HttpConnectionHelp.httpPost(Path.httpUrl+"v1/menu/getById",param, SwingMenuVo.class);;
        }
        if(editMenuFrame == null){
            editMenuFrame = new MyFrame();
            editMenuFrame.setSize(600,600);
            editMenuFrame.setLocationRelativeTo(null);
            editMenuFrame.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editMenuFrame.setLayout(null);
            JPanel backPanel= SwingHelp.getJpanel(0,0,600,600);
            int y = 10;
            JLabel idlable = SwingHelp.getJLabel("ID:",0,y,100,50);
            idTextField = SwingHelp.getJTextField("id",110,y,300,50);
            idTextField.setText(vo == null ? "" : vo.getId());
            idTextField.setEnabled(false);
            SwingHelp.JFrameAdd(editMenuFrame,idlable,idTextField);
            y = y + 60;
            JLabel parntIdLable = SwingHelp.getJLabel("ParntId:",0,y,100,50);
            parntIdTextField = SwingHelp.getJTextField("id",110,y,300,50);
            menuTreeFunction = new MenuTreeFunction(parntIdTextField);
            queryTrueButton = SwingHelp.getJButton("...",420,y,100,50,menuTreeFunction);

            SwingHelp.JFrameAdd(editMenuFrame,parntIdLable,parntIdTextField,queryTrueButton);
            y = y + 60;
            JLabel menuNameLable = SwingHelp.getJLabel("菜单名字:",0,y,100,50);
            menuNameTextField = SwingHelp.getJTextField("menuName",110,y,300,50);
            y = y + 60;
            JLabel isMenuLable = SwingHelp.getJLabel("是否菜单:",0,y,100,50);
            isMenuTextField = SwingHelp.getJTextField("isMenu",110,y,300,50);
            y = y + 60;
            JLabel classLable = SwingHelp.getJLabel("类名字:",0,y,100,50);
            classNameTextField = SwingHelp.getJTextField("className",110,y,300,50);
            y = y + 60;
            JLabel isActiveLable = SwingHelp.getJLabel("是否激活:",0,y,100,50);
            isActiveTextField = SwingHelp.getJTextField("isActive",110,y,300,50);
            saveOrUpdateMenuFunction = new SaveOrUpdateMenuFunction(this,editMenuFrame,
                    idTextField,parntIdTextField,menuNameTextField,isMenuTextField,classNameTextField,isActiveTextField,isSave);
            JButton saveButton = SwingHelp.getJButton("保存",100,400,100,50,saveOrUpdateMenuFunction);
            deleteIdFunction = new DeleteIdFunction(id,"swing_menu",editMenuFrame,this);
            deleteButton = SwingHelp.getJButton("删除",250,400,100,50,deleteIdFunction);

            SwingHelp.JFrameAdd(editMenuFrame,menuNameLable,isMenuLable,classLable,isActiveLable,
                    menuNameTextField,isMenuTextField,classNameTextField,isActiveTextField,
                    saveButton,deleteButton);
            editMenuFrame.add(backPanel);
            editMenuFrame.repaint();
        }else {
            editMenuFrame.setVisible(true);
        }
        if(!isSave){
            saveOrUpdateMenuFunction.setIsSave(isSave);
            editMenuFrame.setTitle("修改菜单");
            deleteIdFunction.reloadId(vo.getId());
            idTextField.setText(vo.getId());
            parntIdTextField.setText(vo.getParntId());
            menuNameTextField.setText(vo.getMenuName());
            isMenuTextField.setText(String.valueOf(vo.getIsMenu()));
            classNameTextField.setText(vo.getClassName());
            isActiveTextField.setText(String.valueOf(vo.getIsActive()));
            deleteButton.setEnabled(true);
            parntIdTextField.setEnabled(false);
        }else{
            saveOrUpdateMenuFunction.setIsSave(isSave);
            editMenuFrame.setTitle("新增菜单");
            deleteIdFunction.reloadId(null);
            idTextField.setText("");
            parntIdTextField.setText("");
            menuNameTextField.setText("");
            isMenuTextField.setText("");
            classNameTextField.setText("");
            isActiveTextField.setText("");
            deleteButton.setEnabled(false);
            parntIdTextField.setEnabled(true);
        }

    }

    public void setPagePanel(final MenuFunciton in,JTable jTable){
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

    public static void main(String[] args) {
        MenuFunciton funciton = new MenuFunciton();
        funciton.newFrame();
    }
}
