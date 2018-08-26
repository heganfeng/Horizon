package com.gouge.base;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Godden
 * Datetime : 2018/8/9 15:14.
 */
public class ImageFileFilter extends FileFilter {
    public ImageFileFilter() {
        super();
    }

    @Override
    public String getDescription() {
        return ".jgp|.png|.gif";
    }

    @Override
    public boolean accept(File file) {
        String name = file.getName();
        return file.isDirectory() || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")
                || name.toLowerCase().endsWith(".gif");
    }
}
