package com.demo.pdf2;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.sun.imageio.plugins.common.ImageUtil;
import org.yaml.snakeyaml.error.Mark;

import java.io.FileOutputStream;
import java.util.List;

/**
 * @auther gzhen
 * @date 2023-10-27  10:13
 * @description
 */

public class PDFUtil2 {

    private static final int width = -7;//本地标记x轴调整
    // 项目放到linux 必须用服务器标记x轴；
    //private static final int width = 12;// 服务器标记x轴调整
    private static final int height = 2;// 标记y轴调整
    private static final int heightzhang = 60;// 标记y轴调整合同章

    /**
     * 功能:替换pdf指定标签内容
     *
     * @param over       PdfContentByte
     * @param result     坐标
     * @param font       字体
     * @param page       pdf页码
     * @param img        图片路劲
     * @param imgContent 模板标记内容如：{模板}
     * @return
     */
    public static PdfContentByte pdfContentByte(String fonts, PdfContentByte over, float[] result, Font font, String str, int page,
                                                String imgContent) {
        try {
            if (result != null) {
                float x = result[0];
                float y = result[1];
//                ImageUtil.createImage(img, imgContent, fonts);
//                Image image = Image.getInstance(img);
//                image.setAbsolutePosition(x - width, y - height);
//                over.addImage(image);
                // 开始写入文本
                over.beginText();
                // 设置字体和大小
                over.setFontAndSize(font.getBaseFont(), 10);
                // 设置字体的输出位置
                over.setTextMatrix(x - width, y);
                // 要输出的text
                over.showText(str);
                System.out.println(str + ":" + x + "," + y);
                over.endText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return over;
    }

    /**
     * 功能：根据标记修改pdf指定内容
     *
     * @return
     */
    public static boolean PdfSomain() {
        String fonts = "simsun.ttf";
        String filePath = "d://contract_template.pdf";
        String newpdf = "contract_out1 .pdf";
        // 创建一个pdf读入流
        PdfReader reader;
        try {
            reader = new PdfReader(filePath);
            // 根据一个pdfreader创建一个pdfStamper.用来生成新的pdf.
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(newpdf));
            BaseFont bf = BaseFont.createFont("d://simsun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); // set

            // font
            // baseFont不支持字体样式设定.但是font字体要求操作系统支持此字体会带来移植问题.
            Font font = new Font(bf, 10);
            font.setStyle(Font.BOLD);
            font.getBaseFont();
            List<float[]> result = null;
            // 页数是从1开始的
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                // 获得pdfstamper在当前页的上层打印内容.也就是说 这些内容会覆盖在原先的pdf内容之上.
                PdfContentByte over = stamper.getOverContent(i);
                //String pdfcontent = PdfPlace.pdfcontent(filePath);
                result = PdfPlace.getKeyWords(filePath, "%contractNum%", fonts);
                for (int k = 0; k < result.size(); k++) {
                    if (result.get(k)[2] == i) {
                        pdfContentByte(fonts, over, result.get(k), font, "%contractNum%", i, "199708292438");
                    }
                }
            }

            stamper.close();
        } catch (
                Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        PdfSomain();
    }
}
