package com.demo.test;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @auther gzhen
 * @date 2023-10-27  15:17
 * @description
 */

public class Test2 {

    public static void main(String[] args) {
        try {
            /* 打开已经定义好字段以后的pdf模板 */
            PdfReader reader = new PdfReader("d:/contract_template.pdf");

            /* 将要生成的目标PDF文件名称 */
            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(
                    "d:/out1.pdf"));

            PdfContentByte under = stamp.getUnderContent(1);

            /* 使用中文字体 */
            // BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
            // BaseFont.NOT_EMBEDDED);
            BaseFont bf = BaseFont.createFont("/font/simsun.ttf",
                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            com.itextpdf.text.Font FontChinese = new com.itextpdf.text.Font(bf, 12, com.itextpdf.text.Font.NORMAL);

            /* 取出报表模板中的所有字段 */
            AcroFields form = stamp.getAcroFields();
            form.addSubstitutionFont(bf);

            /* 为字段赋值,注意字段名称是区分大小写的 */
            form.setField("xm", "裴贺先");
            form.setField("xxjszm", "26");
            form.setField("fill_3", "高二辍学");
            form.setField("email", "phx@x263.net");
            form.setField("fill_4", "用iText做报表简单吗？");
            // 可以修改值
            // Set<String> keys = form.getFields().keySet();
            // for (String name : keys) {
            // if (name == null || name.length() <= 0){
            // continue;
            // }
            //
            // AcroFields.Item item = form.getFieldItem(name);
            // String value = form.getField(name);
            // form.setFieldProperty(name, "textfont", bf/*
            // BaseFont.createFont() */, null);
            // form.setField(name, value);// reset value
            // }

            stamp.setFormFlattening(true);
            /* 必须要调用这个，否则文档不会生成的 */
            stamp.close();
            reader.close();
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
