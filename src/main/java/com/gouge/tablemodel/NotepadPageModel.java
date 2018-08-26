package com.gouge.tablemodel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gouge.base.DateHelp;
import com.gouge.base.HttpUtile;
import com.gouge.base.JsonResult;
import com.gouge.param.PageInfo;
import com.gouge.param.main.MenuAdvancedParam;
import com.gouge.param.main.NotepadParam;
import com.gouge.param.main.SwingMenuVo;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Godden
 * Datetime : 2018/8/14 2:18.
 */
public class NotepadPageModel extends AbstractTableModel {

    Vector rowData, columnNames;

    PreparedStatement ps = null;
    Connection ct = null;
    ResultSet rs = null;

    private PageInfo pageInfo;

    public NotepadPageModel(String url,NotepadParam advancedVo){
        columnNames = new Vector<String>();
        columnNames.add("Id");
        columnNames.add("标题");
        columnNames.add("关键字");
        columnNames.add("创建时间");

        rowData = new Vector<Vector<String>>();

        try {
            JsonResult jr  = HttpUtile.sendHttpPost(url, JSON.toJSONString(advancedVo));
            setPageInfo(JSONObject.parseObject(JSON.toJSONString(jr.getPage()),PageInfo.class));//设置分页信息
            JSONArray jsonArray= JSONObject.parseArray(JSON.toJSONString(jr.getData()));
            for(int i = 0;i <jsonArray.size();i++){
                Vector hang = new Vector<String>();
                NotepadParam vo = JSONObject.parseObject(JSON.toJSONString(jsonArray.get(i)), NotepadParam.class);
                hang.add(vo.getId());
                hang.add(vo.getTitle());
                hang.add(vo.getKeyword());
                hang.add( DateHelp.sdf.format(vo.getCrtDate()));
                rowData.add(hang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(rs!=null)rs.close();
                if(ps!=null) ps.close();
                if(ct!=null) ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }
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
