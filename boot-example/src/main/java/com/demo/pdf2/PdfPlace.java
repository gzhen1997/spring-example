package com.demo.pdf2;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.*;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther gzhen
 * @date 2023-10-27  10:10
 * @description
 */

public class PdfPlace {
    private static StringBuffer str = null;
    private static int i = 0;
    static List<float[]> arrays = null;
    static String sb;
    static int state = 0;
    static float x = 0;
    static float y = 0;

    /**
     * 功能：根据关键字定位坐标
     *
     * @param filePath pdf路径
     * @param KEY_WORD 关键字
     * @return
     */
    static List<float[]> getKeyWords(String filePath, final String KEY_WORD, final String fonts) {
        arrays = new ArrayList<float[]>();
        try {
            PdfReader pdfReader = new PdfReader(filePath);
            int pageNum = pdfReader.getNumberOfPages();
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
            for (i = 1; i <= pageNum; i++) {
                str = new StringBuffer();
                pdfReaderContentParser.processContent(i, new RenderListener() {
                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        // String text =
                        // textRenderInfo.getBaseline().toString();
                        String text = textRenderInfo.getText(); // 整页内容
                        if (text != null) {
                            com.itextpdf.awt.geom.Rectangle2D.Float boundingRectange = null;
                            str.append(text);
							/*if (str.toString().contains("{")){
								String[] s = str.toString().split("\\{");
								state = s.length==1?0:1;
								if(state ==0){
									state = 1;
									boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
									x = boundingRectange.x;
									y = boundingRectange.y;
								}
							}
							if (str.toString().contains("}") && state ==1){
								state = 0;
							}*/
                            if (str.toString().contains(KEY_WORD)) {
                                boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
                                //Font font = new Font("宋体", Font.PLAIN, 10);
                                Font font = Loadfont.loadFont(fonts, 11);
                                if (font == null) {
                                    return;
                                }
                                // 获取font的样式应用在str上的整个矩形
                                Rectangle2D r = font.getStringBounds(KEY_WORD,
                                        new FontRenderContext(AffineTransform.getScaleInstance(1, 1), false, false));
                                // 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
                                int width = (int) Math.round(r.getWidth());
                                float[] resu = new float[3];
                                resu[0] = boundingRectange.x - width;
                                resu[1] = boundingRectange.y;
                                resu[2] = i;
                                arrays.add(resu);
                                str = new StringBuffer();
                            }
                        }
                    }

                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void endTextBlock() {

                    }

                    @Override
                    public void beginTextBlock() {

                    }
                });
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return arrays;
    }

    static String pdfcontent(String filePath) {
        PdfReader reader;
        String pageText = null;
        try {
            reader = new PdfReader(filePath);
            PdfReaderContentParser parser = new PdfReaderContentParser(reader);
            TextExtractionStrategy strategy = new SimpleTextExtractionStrategy();
            int pageCount = reader.getNumberOfPages();
            for (int i = 1; i <= pageCount; i++) {
                TextExtractionStrategy result = parser.processContent(i, strategy);
                pageText = result.getResultantText();
                System.out.println(pageText);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pageText;
    }


}
