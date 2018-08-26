package com.gouge.service.main;

import com.gouge.base.GlobalVariable;
import com.gouge.base.music.PlayerMusic;
import com.gouge.service.ButtonService;

/**
 * Created by Godden
 * Datetime : 2018/8/9 20:51.
 */
public class MusicPlayerFunction implements ButtonService {
    @Override
    public void execute() {
        if(!GlobalVariable.play.isPlay()){
            GlobalVariable.play.start();
            GlobalVariable.play.setBackMusicFrameVisible(true);
        }else {
            GlobalVariable.play.setBackMusicFrameVisible(true);
        }

    }
    public MusicPlayerFunction(){

    }
}
