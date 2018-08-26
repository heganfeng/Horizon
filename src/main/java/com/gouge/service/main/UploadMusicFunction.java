package com.gouge.service.main;

import com.gouge.base.*;
import com.gouge.main.thread.UploadFileThread;
import com.gouge.service.ButtonService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Godden
 * Datetime : 2018/8/9 14:12.
 */
public class UploadMusicFunction extends MyFrame implements ButtonService,ActionListener{
    private boolean isFramLoad = false;
    private MyFrame uploadFrame;
    @Override
    public void execute() {
        if(!isFramLoad) {
            isFramLoad = true;
            newUploadFrame();
        }else{
            this.setVisible(true);
        }
    }

    public UploadMusicFunction(){

    }
    private JLabel textLable;
    private InfiniteProgressPanel glasspane;
    public void newUploadFrame(){
        uploadFrame = this;
        this.setTitle("音乐上传");
        this.setVisible(true);// 可见
        this.setSize(1000, 300);// 窗体大小
//        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// close的方式
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        JPanel backPanel= SwingHelp.getJpanel(0,0,1000,300);
        JLabel jl =SwingHelp.getJLabel("请选择：",20,20,100,50);
        JButton open =SwingHelp.getJButton("上传文件",120,20,120,40,null);
        textLable =SwingHelp.getJLabel("",250,20,600,50);
        JButton upload =SwingHelp.getJButton("上传",400,80,100,50,null);
        open.addActionListener(this);
        glasspane = new InfiniteProgressPanel();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        glasspane.setBounds(100, 100, (dimension.width) / 2, (dimension.height) / 2);
        this.setGlassPane(glasspane);
        SwingHelp.JpanelAdd(backPanel,jl,open,textLable,upload);
        SwingHelp.JFrameAdd(this,backPanel);
        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = textLable.getText();
                if(StringUtils.isEmpty(path)){
                    JOptionPane.showMessageDialog(null,"请选择文件！");
                    return;
                }
                glasspane.start();
                new Thread(new UploadFileThread(glasspane,Path.httpUrl+"v1/upload/uploadMusic",textLable,uploadFrame)).start();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            JFileChooser jfc = new JFileChooser();
            MusicFileFilter fileFilter = new MusicFileFilter();
            jfc.setFileFilter(fileFilter);
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jfc.showDialog(new JLabel(), "选择");
            /** 得到选择的文件* */
            File file2 = jfc.getSelectedFile();
            if(null != file2 && file2.isFile())
                textLable.setText(file2.getPath());
            this.repaint();
        }catch (Exception e1){
            e1.printStackTrace();
        }
    }

}
