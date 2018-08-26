package com.gouge.base;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Godden
 * Datetime : 2018/8/9 15:14.
 */
public class MusicFileFilter extends FileFilter {
    public MusicFileFilter() {
        super();
    }

    @Override
    public String getDescription() {
        return ".wav|.mp3";
    }

    @Override
    public boolean accept(File file) {
        String name = file.getName();
        return file.isDirectory() || name.toLowerCase().endsWith(".wav") || name.toLowerCase().endsWith(".mp3");
    }
}
