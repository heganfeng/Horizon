package com.gouge.main.thread;

import com.gouge.base.CmdTextArea;
import com.gouge.base.CustomCommand;
import com.gouge.base.GlobalVariable;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Godden
 * Datetime : 2018/8/15 2:56.
 */
public class ExceCmdThread implements Runnable {
    private CmdTextArea textArea;
    private String cmd;

    public  ExceCmdThread(CmdTextArea textArea,String cmd){
        this.textArea = textArea;
        this.cmd = cmd;

    }

    @Override
    public void run() {
        if(cmd.equals("cls")){
            textArea.setText("");
            textArea.restTextBuffer();
            refeshCmd();
        }else if(CustomCommand.customCommandList.contains(cmd.toLowerCase())){
            if(CustomCommand.NEXT_MUSIC.equals(cmd)){
                GlobalVariable.play.nextMusic();
            }else if(CustomCommand.UP_MUSIC.equals(cmd)){
                GlobalVariable.play.upMusic();
            }else if(CustomCommand.RANDOM_MUSIC.equals(cmd)){
                GlobalVariable.play.randMusic();
            }else if(CustomCommand.MUSIC_PLAY.equals(cmd)){
                if(!GlobalVariable.play.isPlay()){
                    GlobalVariable.play.start();
                }
            }else if(CustomCommand.MUSIC_STOP.equals(cmd)){
                if(GlobalVariable.play.isPlay()){
                    GlobalVariable.play.stop();
                }
            }else if(CustomCommand.SHOW_MUSIC.equals(cmd)){
                GlobalVariable.play.frame.setVisible(true);
            }else if(CustomCommand.HIDDEN_MUSIC.equals(cmd)){
                GlobalVariable.play.frame.setVisible(false);
            }
            refeshCmd();
        }else if(cmd.startsWith(CustomCommand.CHAT_SEND)){//聊天
            String userCode = cmd.substring(cmd.lastIndexOf(" "),cmd.length()).trim();
            String result = GlobalVariable.chatFrame.addChatByUserCode(userCode);
            if(result != null){
                refeshCmd(result);
            }
            refeshCmd();
        }else{
            Process pp = null;
            try {
                pp = Runtime.getRuntime().exec(cmd);
                BufferedReader br = new BufferedReader(new InputStreamReader(pp.getInputStream(),"gbk"));
                String str = "";
                while ((str = br.readLine())!= null) {
                    System.out.println(str);
                    refeshCmd(str);
                }
                br.close();
            } catch (IOException e) {
                if(pp != null)
                    pp.destroy();
                Process p = null;
                try{
                    String[] cmdArr = new String[5];
                    cmdArr[0] = "cmd";
                    cmdArr[1] = "/c";
                    cmdArr[2] = "start";
                    cmdArr[3] = " ";
                    cmdArr[4] = cmd;
                    p = Runtime.getRuntime().exec(cmdArr);
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String str = "";
                    while ((str = br.readLine())!= null) {
                        refeshCmd(str);
                    }
                    br.close();
                }catch (Exception e1) {
                    if(p != null)
                        p.destroy();
                    refeshCmd("error commend!");
                }
            }finally{
                refeshCmd();
            }
        }
    }

    private void refeshCmd(String str){
        textArea.append(str);
        textArea.append("\n");
//        textArea.append("-->");
        textArea.setCaretPosition(textArea.getText().length());
    }

    private void refeshCmd(){
        textArea.append("-->");
        textArea.setCaretPosition(textArea.getText().length());
    }
}
