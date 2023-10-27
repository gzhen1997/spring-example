package com.demo.test;

import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther gzhen
 * @date 2023-10-26  18:26
 * @description
 */

public class FileTemplateTest2 {
    public static void main(String[] args) throws Exception {
        export();
        System.out.println("生成完成");
    }

    public static void export(){
        try {
            String inputFileName = "D:\\contract_template.pdf";//模板
            String outputFileName = "D:\\output.pdf";//生成文件
            // pdf模板所在路径，就是网站制作好后下载的pdf模板路径
            String fileName = inputFileName;
            PdfReader reader = new PdfReader(fileName);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PdfStamper ps = new PdfStamper(reader, bos);

            // 使用中文字体
//            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            BaseFont bf = BaseFont.createFont("font/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
            fontList.add(bf);
            AcroFields fields = ps.getAcroFields();
            fields.setSubstitutionFonts(fontList);
            fillData(fields, data());

            //必须要调用这个，否则文档不会生成的
            ps.setFormFlattening(true);
            ps.close();

            //生成pdf路径存放的路径
            OutputStream fos = new FileOutputStream(outputFileName);
            fos.write(bos.toByteArray());
            fos.flush();
            fos.close();
            bos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 填充模板中的数据
     */
    public static void fillData(AcroFields fields, Map<String, String> data) {
        try {
            for (String key : data.keySet()) {
                String value = data.get(key);
                // 为字段赋值,注意字段名称是区分大小写的
                fields.setField(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 填充数据源
     * 其中data存放的key值与pdf模板中的文本域值相对应
     */
    public static Map<String, String> data() {
        Map<String, String> data = new HashMap<String, String>();
//        data.put("schoolName", "国际测试测试\r\n测试测试");
//        data.put("userName", "yvioo");
//        data.put("date", "2020/7");
        data.put("%contractNum%", "789446544631635");
        data.put("contractStartYear", "2001");
        data.put("contractStartMonth", "09");
        return data;
    }
}
