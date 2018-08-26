package com.gouge.base;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by Godden
 * Datetime : 2018/8/13 17:19.
 */
public class DocImageAction  extends StyledEditorKit.StyledTextAction{
    public DocImageAction(String nm, JTextPane panl) {
        super(nm);
        this.panl = panl;
    }

    public void actionPerformed(ActionEvent e) {
        JFileChooser f = new JFileChooser(); // 查找文件
        ImageFileFilter fileFilter = new ImageFileFilter();
        f.setFileFilter(fileFilter);
        f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        f.showOpenDialog(null);
//        ImageIcon icon = createImageIcon(f.getSelectedFile().getPath(), "a cute pig");
        HTMLEditorKit kit = (HTMLEditorKit)panl.getEditorKit();
        StyledDocument doc = getStyledDocument(panl);
        panl.setCaretPosition(doc.getLength()); // 设置插入位置
        JsonResult jsonResult =  HttpUtile.uploadFile(Path.httpUrl+"v1/upload/uploadImage",f.getSelectedFile().getPath());
        if(jsonResult.getResult().equals("000")) {
            String str = (String)jsonResult.getData();
            try {
                kit.insertHTML((HTMLDocument) panl.getDocument(),panl.getCaretPosition(),"<img  src=\""+str+"\""+">",0,0, HTML.Tag.IMG);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null,jsonResult.getDesc());
        }
//            panl.insertIcon(icon); // 插入图片
    }

    private JTextPane panl;

    /** Returns an ImageIcon, or null if the path was invalid. */
//    protected static ImageIcon createImageIcon(String path, String description) {
//        java.net.URL imgURL = DocImageAction.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(path);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }

}
