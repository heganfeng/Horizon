package com.gouge.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Godden
 * Datetime : 2018/8/15 3:35.
 */
public class CustomCommand {

    public static List<String> customCommandList = new ArrayList<>();

    public static String GET_A_MOVIE = "get a movie";

    public static String ADD_A_GIRLS = "add a girls";

    public static String NEW_GIRLS = "new girls";

    public static String CHAT_SEND = "send to";

    public static String MUSIC_PLAY = "start music";

    public static String MUSIC_STOP = "stop music";

    public static String NEXT_MUSIC = "next music";

    public static String UP_MUSIC = "up music";

    public static String RANDOM_MUSIC = "random music";

    public static String SHOW_MUSIC = "show music";

    public static String HIDDEN_MUSIC = "hidden music";

    static {
//        customCommandList.add(GET_A_MOVIE);
//        customCommandList.add(ADD_A_GIRLS);
//        customCommandList.add(NEW_GIRLS);
        customCommandList.add(CHAT_SEND);
        customCommandList.add(MUSIC_PLAY);
        customCommandList.add(MUSIC_STOP);
        customCommandList.add(NEXT_MUSIC);
        customCommandList.add(UP_MUSIC);
        customCommandList.add(RANDOM_MUSIC);
        customCommandList.add(SHOW_MUSIC);
        customCommandList.add(HIDDEN_MUSIC);
    }
}
