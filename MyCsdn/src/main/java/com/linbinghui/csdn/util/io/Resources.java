package com.linbinghui.csdn.util.io;

import java.io.InputStream;

public class Resources {
    public static InputStream GetResourceAsStream(String path){
        InputStream inputStream=Resources.class.getClassLoader().getResourceAsStream(path);
        return inputStream;
    }
}
