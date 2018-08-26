package com.gouge.base;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class TaskTray {

	ActionListener listener;
	

	private SystemTray tray;
	
	/**
	 *	My Sytem Icon
	 */
	private TrayIcon trayIcon = null;
	
	public TaskTray(ActionListener listener){
		this.listener = listener;
	}
    public  void createTray() {   
        if (SystemTray.isSupported()) {
          
            tray = SystemTray.getSystemTray();

			URL imgURL = null ;
			try {
				imgURL = new URL(SystemVariable.gougeIco);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			Image image = Toolkit.getDefaultToolkit().getImage(imgURL);


            PopupMenu popup = new PopupMenu();
            MenuItem defaultItem = new MenuItem("打开");
            defaultItem.addActionListener(listener);   
            MenuItem exitItem = new MenuItem("退出");
            exitItem.addActionListener(new ActionListener() {   
                public void actionPerformed(ActionEvent e) {
					int flag = JOptionPane.showConfirmDialog(null,"你确定需要退出系统吗！",
					"信息", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
	                if(JOptionPane.YES_OPTION == flag){   
						System.exit(0);
	                }else{   
	                    return;   
	                }   
   
                }   
            });   
  
            popup.add(defaultItem);   
            popup.add(exitItem);
            trayIcon = new TrayIcon(image, "系统", popup);
			trayIcon.setImageAutoSize(true);
            trayIcon.addActionListener(listener);   
            try {   
               tray.add(trayIcon);   
            } catch (AWTException e1) {   
                e1.printStackTrace();   
            }   
        }   
    } 
    

    public void removeStstenIcon(){
    	tray.remove(trayIcon);
    }
    

    public void addStstenIcon(){
    	try {
    		System.out.println(tray.getTrayIcons().length);
    		if(tray.getTrayIcons().length < 1){
    			tray.add(trayIcon);
    		}
		} catch (AWTException e) {
			throw new RuntimeException("add System tray icon Excepiton!",e);
		}
    }
}  