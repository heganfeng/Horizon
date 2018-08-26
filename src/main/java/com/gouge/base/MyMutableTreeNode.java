package com.gouge.base;

import com.gouge.param.main.SwingMenuVo;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;

/**
 * Created by Godden
 * Datetime : 2018/8/8 11:31.
 */
public class MyMutableTreeNode extends DefaultMutableTreeNode {
    public MyMutableTreeNode() {
        super();
    }

    public MyMutableTreeNode(Object userObject) {
        super(userObject);
    }

    public MyMutableTreeNode(Object userObject, boolean allowsChildren) {
        super(userObject, allowsChildren);
    }

    @Override
    public void insert(MutableTreeNode newChild, int childIndex) {
        super.insert(newChild, childIndex);
    }

    @Override
    public void remove(int childIndex) {
        super.remove(childIndex);
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        super.setParent(newParent);
    }

    @Override
    public TreeNode getParent() {
        return super.getParent();
    }

    @Override
    public TreeNode getChildAt(int index) {
        return super.getChildAt(index);
    }

    @Override
    public int getChildCount() {
        return super.getChildCount();
    }

    @Override
    public int getIndex(TreeNode aChild) {
        return super.getIndex(aChild);
    }

    @Override
    public Enumeration children() {
        return super.children();
    }

    @Override
    public void setAllowsChildren(boolean allows) {
        super.setAllowsChildren(allows);
    }

    @Override
    public boolean getAllowsChildren() {
        return super.getAllowsChildren();
    }

    @Override
    public void setUserObject(Object userObject) {
        super.setUserObject(userObject);
    }

    @Override
    public Object getUserObject() {
        return super.getUserObject();
    }

    @Override
    public void removeFromParent() {
        super.removeFromParent();
    }

    @Override
    public void remove(MutableTreeNode aChild) {
        super.remove(aChild);
    }

    @Override
    public void removeAllChildren() {
        super.removeAllChildren();
    }

    @Override
    public void add(MutableTreeNode newChild) {
        super.add(newChild);
    }

    @Override
    public boolean isNodeAncestor(TreeNode anotherNode) {
        return super.isNodeAncestor(anotherNode);
    }

    @Override
    public boolean isNodeDescendant(DefaultMutableTreeNode anotherNode) {
        return super.isNodeDescendant(anotherNode);
    }

    @Override
    public TreeNode getSharedAncestor(DefaultMutableTreeNode aNode) {
        return super.getSharedAncestor(aNode);
    }

    @Override
    public boolean isNodeRelated(DefaultMutableTreeNode aNode) {
        return super.isNodeRelated(aNode);
    }

    @Override
    public int getDepth() {
        return super.getDepth();
    }

    @Override
    public int getLevel() {
        return super.getLevel();
    }

    @Override
    public TreeNode[] getPath() {
        return super.getPath();
    }

    @Override
    protected TreeNode[] getPathToRoot(TreeNode aNode, int depth) {
        return super.getPathToRoot(aNode, depth);
    }

    @Override
    public Object[] getUserObjectPath() {
        return super.getUserObjectPath();
    }

    @Override
    public TreeNode getRoot() {
        return super.getRoot();
    }

    @Override
    public boolean isRoot() {
        return super.isRoot();
    }

    @Override
    public DefaultMutableTreeNode getNextNode() {
        return super.getNextNode();
    }

    @Override
    public DefaultMutableTreeNode getPreviousNode() {
        return super.getPreviousNode();
    }

    @Override
    public Enumeration preorderEnumeration() {
        return super.preorderEnumeration();
    }

    @Override
    public Enumeration postorderEnumeration() {
        return super.postorderEnumeration();
    }

    @Override
    public Enumeration breadthFirstEnumeration() {
        return super.breadthFirstEnumeration();
    }

    @Override
    public Enumeration depthFirstEnumeration() {
        return super.depthFirstEnumeration();
    }

    @Override
    public Enumeration pathFromAncestorEnumeration(TreeNode ancestor) {
        return super.pathFromAncestorEnumeration(ancestor);
    }

    @Override
    public boolean isNodeChild(TreeNode aNode) {
        return super.isNodeChild(aNode);
    }

    @Override
    public TreeNode getFirstChild() {
        return super.getFirstChild();
    }

    @Override
    public TreeNode getLastChild() {
        return super.getLastChild();
    }

    @Override
    public TreeNode getChildAfter(TreeNode aChild) {
        return super.getChildAfter(aChild);
    }

    @Override
    public TreeNode getChildBefore(TreeNode aChild) {
        return super.getChildBefore(aChild);
    }

    @Override
    public boolean isNodeSibling(TreeNode anotherNode) {
        return super.isNodeSibling(anotherNode);
    }

    @Override
    public int getSiblingCount() {
        return super.getSiblingCount();
    }

    @Override
    public DefaultMutableTreeNode getNextSibling() {
        return super.getNextSibling();
    }

    @Override
    public DefaultMutableTreeNode getPreviousSibling() {
        return super.getPreviousSibling();
    }

    @Override
    public boolean isLeaf() {
        return super.isLeaf();
    }

    @Override
    public DefaultMutableTreeNode getFirstLeaf() {
        return super.getFirstLeaf();
    }

    @Override
    public DefaultMutableTreeNode getLastLeaf() {
        return super.getLastLeaf();
    }

    @Override
    public DefaultMutableTreeNode getNextLeaf() {
        return super.getNextLeaf();
    }

    @Override
    public DefaultMutableTreeNode getPreviousLeaf() {
        return super.getPreviousLeaf();
    }

    @Override
    public int getLeafCount() {
        return super.getLeafCount();
    }

    private SwingMenuVo swingMenuVo;

    public SwingMenuVo getSwingMenuVo() {
        return swingMenuVo;
    }

    public void setSwingMenuVo(SwingMenuVo swingMenuVo) {
        this.swingMenuVo = swingMenuVo;
    }
}
