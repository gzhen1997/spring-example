package com.demo.pdf2;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

/**
 * @auther gzhen
 * @date 2023-10-27  10:08
 * @description
 */

public class Loadfont {

    public static Font loadFont(String fontFileName, float fontSize) { // 第一个参数是外部字体名，第二个是字体大小
        try {
            File file = new File(fontFileName);
            FileInputStream aixing = new FileInputStream(file);
            Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, aixing);
            Font dynamicFontPt = dynamicFont.deriveFont(fontSize);
            aixing.close();
            return dynamicFontPt;
        } catch (Exception e) {// 异常处理
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Font font = loadFont("D://simsun.ttf", 11);
        System.out.println(font.getFontName());
    }
}
