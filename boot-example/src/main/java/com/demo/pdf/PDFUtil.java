package com.demo.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.springframework.core.io.ClassPathResource;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @auther gzhen
 * @date 2023-10-26  15:15
 * @description
 */

public class PDFUtil {

    /**
     * 读取PDF模板文件，替换指定关键字的数据
     * @param keyWordMap 需要替换的关键字数据，key表示占位符，value表示替换后的内容
     * @param pdfPath PDF模板文件的路径
     * @param destPdf 生成的目标PDF文件
     */
    public static void replaceText(Map<String, String> keyWordMap, String pdfPath, String destPdf) throws IOException {
        if (keyWordMap == null || keyWordMap.keySet().size() <= 0) {
            return;
        }
        Set<String> keyWordSet = keyWordMap.keySet();
        // 1、读取PDF模板文件
        PDDocument document = PDDocument.load(new File(pdfPath));
        // 2、创建自定义文本提取器
        KeyWordPositionStripper stripper = new KeyWordPositionStripper(new ArrayList<>(keyWordSet));
        stripper.setSortByPosition(true);
        // 注意: writeString() 方法必须执行 getText() 方法之后才会执行
        stripper.getText(document);
        // 3、获取关键字实体对象
        List<KeyWordEntity> keyWordEntityList = stripper.getKeyWordEntityList();
        // 4、替换指定关键字文本内容
        PDPageContentStream stream = new PDPageContentStream(document, document.getPage(0), PDPageContentStream.AppendMode.APPEND, true);
        // 5、加载外部字体文件,这里是直接通过File加载，如果你是SpringBoot项目，则可以通过流加载
        PDType0Font font = PDType0Font.load(document, new ClassPathResource("font/simsun.ttf").getInputStream());
        // 6、循环替换文本内容
        for (KeyWordEntity keyWord : keyWordEntityList) {
            stream.setNonStrokingColor(Color.WHITE);
            stream.addRect(keyWord.getX(), keyWord.getY(), keyWord.getWidth(), keyWord.getHeight());
            stream.fill();
            // 设置画笔颜色
            stream.setNonStrokingColor(Color.BLACK);
            // 替换关键字文本内容
            stream.beginText();
            stream.setFont(font, 14);
            stream.newLineAtOffset(keyWord.getX(), keyWord.getY());
            stream.showText(keyWordMap.get(keyWord.getKeyWord()));
            stream.endText();
        }
        // 关闭内容流
        stream.close();
        // 保存替换之后的文档
        document.save(destPdf);
        // 关闭文档
        document.close();
    }

    public static void main(String[] args) throws IOException {
        Map<String, String> keyWordMap = new HashMap<>();
        keyWordMap.put("%contractNum%", "199708292438");
        keyWordMap.put("%contractStartYear%", "2023");
//        keyWordMap.put("{{name}}", "张三");
//        keyWordMap.put("{{age}}", "25");
//        keyWordMap.put("{{sex}}", "男");
//        keyWordMap.put("{{address}}", "福建省厦门市");
        // 模拟测试
        PDFUtil.replaceText(keyWordMap, "D:\\contract_template.pdf", "D:\\new-document.pdf");
    }
}
