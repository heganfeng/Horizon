package com.gouge.base;

/**
 * Created by Godden
 * Datetime : 2018/8/15 2:01.
 */

import com.gouge.main.thread.ExceCmdThread;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CmdTextArea extends JTextArea implements KeyListener,
        CaretListener {

    private static final long serialVersionUID = 1L;
    private StringBuffer textBuffer = new StringBuffer();
    private int currentDot = -1;
    private boolean isAllowedInputArea = false;
    private int currentKeyCode = 0;
    private boolean isConsume = false;

    public CmdTextArea() {
        super();
    }

    public void restTextBuffer(){
        textBuffer = new StringBuffer("");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (isConsume) {
            e.consume();
            return;
        }

        // 以'enter'键结束命令输入
        if (currentKeyCode == KeyEvent.VK_ENTER) {
            String input = null;
            try{
                input = this.getText().substring(textBuffer.length(),this.getText().length() - 1);
            }catch (StringIndexOutOfBoundsException siobe){
                return;
            }
            textBuffer.append(input);
            textBuffer.append("\n");
            new Thread(new ExceCmdThread(this,input)).start();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentKeyCode = e.getKeyCode();
        isConsume = checkConsume(e) ? true : false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (isConsume) {
            e.consume();
            return;
        }
    }

    /**
     * 检查是否要使用输入事件
     *
     * @param e
     * @return
     */
    private boolean checkConsume(KeyEvent e) {
        if (!isAllowedInputArea) {
            e.consume();
            return true;
        }

        if ((currentKeyCode == KeyEvent.VK_BACK_SPACE
                || currentKeyCode == KeyEvent.VK_ENTER
                || currentKeyCode == KeyEvent.VK_UP || currentKeyCode == KeyEvent.VK_LEFT)
                && currentDot == textBuffer.length()) {
            e.consume();
            return true;
        }

        return false;
    }

    @Override
    public void append(String message) {
        super.append(message);
        textBuffer.append(message);
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        currentDot = e.getDot();
        isAllowedInputArea = currentDot < textBuffer.length() ? false : true;
    }


}
