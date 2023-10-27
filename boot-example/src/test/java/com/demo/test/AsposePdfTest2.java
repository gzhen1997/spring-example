package com.demo.test;

import com.demo.pdf.PdfAsposeModel;
import com.itextpdf.text.Document;
import javafx.scene.text.TextBuilder;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @auther gzhen
 * @date 2023-10-27  9:53
 * @description
 */

public class AsposePdfTest2 {

    private static final String FONT = "SimSun";

    private static final float FONTSIZE = 15;

    @Test
    public void asposeTest(){
        List<PdfAsposeModel> list = new ArrayList<>();
        list.add(new PdfAsposeModel(119d,707d,1,"阿里巴巴"));
        list.add(new PdfAsposeModel(119d,619d,1,"张三"));
        list.add(new PdfAsposeModel(119d,475d,1,"奥利给"));
        String filePath = "C:/Users/Dash/Desktop/劳动合同（标准版）.pdf";
        try {
            pdfSign2(filePath,list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void pdfSign2(String pdfFilePath,List<PdfAsposeModel> list) throws IOException {
        //获取路径
//        int indexOf = pdfFilePath.lastIndexOf("/");
//        if(indexOf == -1){
//            indexOf = pdfFilePath.lastIndexOf("\\");
//        }
//        int indexOf2 = pdfFilePath.lastIndexOf(".");
//        String suffix = pdfFilePath.substring(indexOf2);
//        String url = pdfFilePath.substring(0,indexOf+1);
//        url = url + UUID.randomUUID().toString() + suffix;
//
//        //复制一份文件，因为需要生成一份新的文件，下面方法是在源文件上进行操纵的
//        FileInputStream inputStream = null;
//        FileOutputStream outputStream = null;
//        try {
//            inputStream = new FileInputStream(pdfFilePath);
//            outputStream = new FileOutputStream(url);
//            int hasRead =0;
//            while((hasRead = inputStream.read()) != -1){
//                outputStream.write(hasRead);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            inputStream.close();
//            outputStream.close();
//        }
//
//        for (PdfAsposeModel model : list) {
//            Double xCoordinate = model.getxCoordinate();
//            Double yCoordinate = model.getyCoordinate();
//            String content = model.getContent();
//            Integer pageNum = model.getPageNum();
//            if(xCoordinate == null || yCoordinate == null || content == null || "".equals(content) || pageNum == null || pageNum < 1){
//                continue;
//            }
//            //添加内容
//            Document document=new Document(url);
//            if (document!=null) {
//                Page page = document.getPages().get_Item(pageNum);
//                if (page != null) {
//                    TextParagraph paragraph = new TextParagraph();
//                    paragraph.getFormattingOptions().setWrapMode(
//                            TextFormattingOptions.WordWrapMode.ByWords);
//                    TextState textState = new TextState();
//
//                    Font pdfFont = FontRepository.findFont(FONT, true);
//                    textState.setFont(pdfFont);
//                    textState.setFontSize(FONTSIZE);
//
//                    paragraph.appendLine(content, textState);
//                    Position position = new Position(xCoordinate, yCoordinate);
//                    paragraph.setPosition(position);
//                    TextBuilder textBuilder = new TextBuilder(page);
//                    textBuilder.appendParagraph(paragraph);
//                } else {
//                    System.out.println("页面第：" + pageNum + "页不存在");
//                }
//                document.save();
//            }
//        }


    }

}
