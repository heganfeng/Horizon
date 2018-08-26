package com.gouge.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gouge.base.HttpConnectionHelp;
import com.gouge.base.MyMutableTreeNode;
import com.gouge.base.Path;
import com.gouge.param.main.SwingMenuVo;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

/**
 * Created by Godden
 * Datetime : 2018/8/8 10:29.
 */
public class MenuTreeFunction   implements ButtonService {
    private JFrame frame;
//    private boolean isFramLoad = false;
    @Override
    public void execute() {

            newFrame();

    }

    private MyMutableTreeNode root;
    private DefaultTreeModel treeModel;
    private JTree tree;


    private JTextField parnetIdTextField;

    public MenuTreeFunction(JTextField parnetIdTextField){
        this.parnetIdTextField = parnetIdTextField;
    }

    public void newFrame(){
        frame = new JFrame();
        frame.setSize(300, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        root = new MyMutableTreeNode("根节点");
        treeModel = new DefaultTreeModel(root);
        java.util.List<Object> list = (java.util.List<Object>) HttpConnectionHelp.httpPost(Path.httpUrl+"v1/menu/getMenus",null, java.util.List.class);
        if(null != list && list.size() > 0){
            for (Object vo : list) {
                SwingMenuVo parseVo = JSONObject.parseObject(JSON.toJSONString(vo), SwingMenuVo.class);
                MyMutableTreeNode firstNode = new MyMutableTreeNode(parseVo.getMenuName());
                firstNode.setSwingMenuVo(parseVo);
                treeModel.insertNodeInto(firstNode, root, root.getChildCount());
                if(!parseVo.getMenus().isEmpty()){
                    iterationMenus(parseVo.getMenus(),firstNode);
                }
            }
        }

        tree = new JTree(treeModel);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        DefaultTreeCellRenderer cellRenderer = (DefaultTreeCellRenderer)tree.getCellRenderer();
        cellRenderer.setTextNonSelectionColor(Color.black);
        cellRenderer.setTextSelectionColor(Color.blue);
        expandAll(tree, new TreePath(tree.getModel().getRoot()), true);
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                    MyMutableTreeNode node = (MyMutableTreeNode) path.getLastPathComponent();
                    if(node.getSwingMenuVo() != null){
                        parnetIdTextField.setText(node.getSwingMenuVo().getId());
                        frame.setVisible(false);
                    }
                }
                super.mouseClicked(e);
            }
        });
        frame.add(tree);
        frame.setVisible(true);
    }

    private void iterationMenus(java.util.List<SwingMenuVo> list, DefaultMutableTreeNode node){
        for (SwingMenuVo chirdVo : list){
            MyMutableTreeNode treeNode = new MyMutableTreeNode(chirdVo.getMenuName());
            treeNode.setSwingMenuVo(chirdVo);
            treeModel.insertNodeInto(treeNode, node, node.getChildCount());
            if(null != chirdVo.getMenus() && chirdVo.getMenus().size() > 0){
                iterationMenus(chirdVo.getMenus(),treeNode);
            }
        }
    }

    private static void expandAll(JTree tree, TreePath parent, boolean expand)
    {
        TreeNode node = (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0)
        {
            for (Enumeration e = node.children(); e.hasMoreElements();)
            {
                TreeNode n = (TreeNode) e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }
        if (expand){
            tree.expandPath(parent);
        } else{
            tree.collapsePath(parent);
        }
    }
}
