package com.gouge.tablemodel;

/**
 * Created by Godden
 * Datetime : 2018/8/7 23:11.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gouge.base.DateHelp;
import com.gouge.base.HttpUtile;
import com.gouge.base.JsonResult;
import com.gouge.param.PageInfo;
import com.gouge.param.main.MenuAdvancedParam;
import com.gouge.param.main.SwingMenuVo;
import com.gouge.param.main.SwingMusicAdvancedParam;
import com.gouge.param.main.SwingMusicVo;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MusicPageModel extends AbstractTableModel{

    Vector rowData, columnNames;

    PreparedStatement  ps = null;
    Connection ct = null;
    ResultSet rs = null;

    private PageInfo pageInfo;

    public MusicPageModel(String url, SwingMusicAdvancedParam advancedVo){
        columnNames = new Vector<String>();
        columnNames.add("Id");
        columnNames.add("歌名");
        columnNames.add("类型");
        columnNames.add("创建时间");

        rowData = new Vector<Vector<String>>();

        try {
            JsonResult jr  = HttpUtile.sendHttpPost(url, JSON.toJSONString(advancedVo));
            setPageInfo(JSONObject.parseObject(JSON.toJSONString(jr.getPage()),PageInfo.class));//设置分页信息
            JSONArray jsonArray= JSONObject.parseArray(JSON.toJSONString(jr.getData()));
            if(jsonArray != null && jsonArray.size() > 0){
                for(int i = 0;i <jsonArray.size();i++){
                    Vector hang = new Vector<String>();
                    SwingMusicVo vo = JSONObject.parseObject(JSON.toJSONString(jsonArray.get(i)), SwingMusicVo.class);
                    hang.add(vo.getId());
                    hang.add(vo.getMusicName());
                    hang.add(vo.getMusicType());
                    hang.add( DateHelp.sdf.format(vo.getCrtDate()));
                    rowData.add(hang);
                }
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
