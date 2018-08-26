package com.gouge.tablemodel;

/**
 * Created by Godden
 * Datetime : 2018/8/7 23:11.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gouge.base.*;
import com.gouge.param.PageInfo;
import com.gouge.param.PageParam;
import com.gouge.param.main.MenuAdvancedParam;
import com.gouge.param.main.SwingMenuVo;
import com.gouge.service.MenuFunciton;
import org.springframework.beans.BeanUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MenuPageModel extends AbstractTableModel{

    Vector rowData, columnNames;

    PreparedStatement  ps = null;
    Connection ct = null;
    ResultSet rs = null;

    private PageInfo pageInfo;

    public MenuPageModel(String url,MenuAdvancedParam advancedVo){
        columnNames = new Vector<String>();
        columnNames.add("Id");
        columnNames.add("菜单名字");
        columnNames.add("是否菜单");
        columnNames.add("类名字");
        columnNames.add("是否激活");
        columnNames.add("创建时间");

        rowData = new Vector<Vector<String>>();

        try {
            JsonResult jr  = HttpUtile.sendHttpPost(url, JSON.toJSONString(advancedVo));
            setPageInfo(JSONObject.parseObject(JSON.toJSONString(jr.getPage()),PageInfo.class));//设置分页信息
            JSONArray jsonArray= JSONObject.parseArray(JSON.toJSONString(jr.getData()));
            for(int i = 0;i <jsonArray.size();i++){
                Vector hang = new Vector<String>();
                SwingMenuVo vo = JSONObject.parseObject(JSON.toJSONString(jsonArray.get(i)), SwingMenuVo.class);
                hang.add(vo.getId());
                hang.add(vo.getMenuName());
                hang.add(vo.getIsMenu());
                hang.add(vo.getClassName());
                hang.add(vo.getIsActive());
                hang.add( DateHelp.sdf.format(vo.getCrtDate()));
                rowData.add(hang);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if(rs!=null)rs.close();
                if(ps!=null) ps.close();
                if(ct!=null) ct.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

//    public StuModel(){
//        String s = "select * from stu";
//        StuModel(s);
//
//    }



    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.columnNames.size();
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return (String) this.columnNames.get(column);
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.rowData.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return ((Vector)this.rowData.get(rowIndex)).get(columnIndex);
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

}
