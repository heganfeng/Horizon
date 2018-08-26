package com.gouge.base.music;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gouge.base.GlobalVariable;
import com.gouge.base.HttpUtile;
import com.gouge.base.JsonResult;
import com.gouge.base.Path;
import com.gouge.main.thread.TipsFrameThread;
import com.gouge.param.main.SwingMusicVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.media.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Godden
 * Datetime : 2018/8/9 18:17.
 */
public class PlayerMusic implements ControllerListener {

    private  final Logger logger = LogManager.getLogger(PlayerMusic.class);

    private List<SwingMusicVo> list;
    //播放器
    public  BackMusicFrame frame;
    // 播放对象
    public static Player player;
    //播放模式
    public static String playFormat = "order";
    //当前播放歌曲坐标
    private int mp3NO = 0;
    //是否播放
    private boolean isPlay =  false;
    private boolean isClick = false;
    private Random rnd = null;
    public PlayerMusic(){
        loadMusic();
    }

    public void start(){

        try {
            if (playFormat.equals("random")) {
                if(!isClick){
                    mp3NO = rnd.nextInt(list.size());
                }
                isClick = false;
            }
            player = Manager.createPlayer(new URL(list.get(mp3NO).getAccessPath()));
//            RiffFile df = new RiffFile();
        } catch (NoPlayerException e) {
            logger.error("NoPlayerException 不能播放！", e);
            return;
        } catch (IOException e) {
            logger.error("play music file not find or exception!",e);
            return;
        }
        if (player == null) {
            logger.info("找不到音乐！");
            return;
        }
        player.addControllerListener(this);
        // 提取媒体内容
        player.prefetch();

    }

    public void reLoadMusic(){
        list.removeAll(list);
        loadMusic();
    }

    public void loadMusic(){
        JsonResult  jsonResult = HttpUtile.sendHttpPost(Path.httpUrl+"v1/music/getMusicByUserId","{}");
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(jsonResult.getData()));
        list = new ArrayList<>();
        rnd = new Random();
        if(jsonArray != null && jsonArray.size() > 0){
            StringBuffer result = new StringBuffer("加载音乐完成！共"+jsonArray.size()+"首。");
            for (int i = 0 ; i < jsonArray.size() ; i++){
                SwingMusicVo vo = JSONObject.parseObject(JSON.toJSONString(jsonArray.get(i)),SwingMusicVo.class);
                vo.setIndex(i);
                list.add(vo);
                result.append(" ").append(vo.getMusicName()).append(", ");
            }
            result.append("请欣赏！");
            new Thread(new TipsFrameThread("音乐加载",result.toString())).start();
        }
    }
    @Override
    public void controllerUpdate(ControllerEvent e) {
        // 当媒体播放结束时，循环播放
        if (e instanceof EndOfMediaEvent) {
            logger.info(list.get(mp3NO).getMusicName()+"播放结束！");
            mp3NO++;
            if (mp3NO < list.size()) {
                this.start();
            } else {
                mp3NO = 0;
                this.start();
            }
        }

        // 当提取媒体的内容结束
        if (e instanceof PrefetchCompleteEvent) {
            setPlay();
            player.start();
            isPlay = true;
            if(frame == null){
                frame =  new BackMusicFrame(list);
//                frame.setVisible(true);
                new Thread(frame).start();
            }
            frame.setTitle(list.get(mp3NO).getMusicName());
            frame.setPlayInfo(player);
        }

        // 当实例化后
        if (e instanceof RealizeCompleteEvent) {
            // pack(); //执行pack()操作
//            System.out.println("===============");
        }
    }

    public void jix() {
        isPlay = true;
        player.start();
    }

    public void stop() {
        isPlay = false;
        player.stop();
    }

    // 下一首
    public void nextMusic() {
        player.close();
        if (mp3NO >= (list.size() - 1)) {
            mp3NO = 0;
        } else {
            mp3NO++;
        }
        this.start();
    }

    // 上一首
    public void upMusic() {
        player.close();
        if (mp3NO == 0) {
            mp3NO = (list.size() - 1);
        } else {
            mp3NO--;
        }
        this.start();
    }

    public void playThis(int mp3NO){
        player.close();
        isClick = true;
        this.mp3NO = mp3NO;
        this.start();
    }

    // 随机一首
    public void randMusic() {
        player.close();
        mp3NO = rnd.nextInt(list.size());
        this.start();
    }


    public void setPlay() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsPlay()) {
                list.get(i).setIsPlay(false);
                break;
            }
        }
        list.get(mp3NO).setIsPlay(true);
    }

    public boolean isPlay() {
        return isPlay;
    }

    public void setBackMusicFrameVisible(boolean visible){
        if(frame != null) {
            frame.setVisible(visible);
        }
    }

    public static void main(String[] args) {
       GlobalVariable.play = new PlayerMusic();
        GlobalVariable.play .start();
    }
}
