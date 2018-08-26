package com.gouge.base;

import com.gouge.service.ButtonService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Godden
 * Datetime : 2018/8/7 16:41.
 */
public class SwingHelp {

    public static JPanel getJpanel(int x,int y,int width,int height){
        JPanel panel=new JPanel();
        panel.setBounds(x,y,width,height);
        panel.setLayout(null);
//        panel.setOpaque(false);
        return panel;
    }

    public static JLabel getJLabel(String name,int x,int y,int width,int height){
        JLabel lable=new JLabel();
        lable.setText(name);
        lable.setBounds(x,y,width,height);
        return lable;
    }

    public static JTextField getJTextField(String fieldName,int x,int y,int width,int height){
        JTextField field=new JTextField();
        field.setName(fieldName);
        field.setBounds(x,y,width,height);
        return field;
    }

    public static JPasswordField getJPasswordField(String fieldName,int x,int y,int width,int height){
        JPasswordField field=new JPasswordField();
        field.setName(fieldName);
        field.setBounds(x,y,width,height);
        return field;
    }

    public static JButton getJButton(String name, int x, int y, int width, int height, final ButtonService function){
        JButton button=new JButton();
        button.setText(name);
        button.setBounds(x,y,width,height);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(function != null)
                    function.execute();
            }
        });
        return button;
    }

    public static JMenuBar getJMenuBar(int x,int y,int width,int height){
        JMenuBar mainBar=new JMenuBar();
        mainBar.setBounds(x,y,width,height);
        //mainBar.setBackground(Color.red);
        mainBar.setLayout(null);
        return  mainBar;
    }

    public static JMenu getJMenu(String menuName,int x,int y,int width,int height){
        JMenu menu=new JMenu(menuName);
        menu.setBounds(x,y,width,height);
        return  menu;
    }

    public static void JpanelAdd(JPanel jPanel,JComponent ... jComponents){
        for (JComponent jComponent : jComponents){
            jPanel.add(jComponent);
        }
    }

    public static void JFrameAdd(JFrame jFrame,JComponent ... jComponents){
        for (JComponent jComponent : jComponents){
            jFrame.add(jComponent);
        }
    }

    public static JMenuItem getJMenuItem(String name,char mask, final ButtonService function){
        JMenuItem item=new JMenuItem(name);
        item.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                function.execute();
            }
        });
//        item.setAccelerator(KeyStroke.getKeyStroke(mask, java.awt.Event.CTRL_MASK));
        return item;
    }
}
