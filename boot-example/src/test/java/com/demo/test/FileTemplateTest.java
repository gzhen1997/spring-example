package com.demo.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther gzhen
 * @date 2023-10-26  10:21
 * @description
 */

@Slf4j
public class FileTemplateTest {

    @Test
    public void pdfTest() throws IOException {
        String inFilePath = "d://contract_template.pdf";
        FileInputStream fis = new FileInputStream(inFilePath);
        HWPFDocument hwpfDocument = new HWPFDocument(fis);
        String outFilePath = "d://contract_1.pdf";
        File outFile = new File(outFilePath);
        if (!outFile.exists())
            outFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(outFile, false);
//        HashMap<String, String> varMap = new HashMap<>();
//        varMap.put("%contractNum%", "199708292438");
        Range range = hwpfDocument.getRange();
        range.replaceText("%contractNum%", "199708292438");
        hwpfDocument.write(fos);
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("d://contract_template.pdf"); // 输入的PDF文件
            File outputFile = new File("d://contract_1.pdf"); // 输出的PDF文件
            if (!outputFile.exists())
                outputFile.createNewFile();
            PDDocument document = PDDocument.load(inputFile);

            for (PDPage page : document.getPages()) {
                PDFTextStripper textStripper = new PDFTextStripper();
                String pageText = textStripper.getText(document);

                // 查找要替换的文本
                PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);
                PDType0Font font = PDType0Font.load(document, new File("D:\\simsun.ttf"));
                if (pageText.contains("%contractNum%")) {
                    pageText = pageText.replace("%contractNum%", "199708292438");
                    contentStream.setFont(font, 12); // 设置字体和字号
                    contentStream.beginText();
//                    contentStream.newLineAtOffset(50, 750); // 设置文本位置
                    contentStream.showText(pageText);
                    contentStream.endText();
                }

                contentStream.close();
            }

            document.save(outputFile);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void docToPdf() throws IOException, DocumentException {
        FileInputStream fileInputStream = new FileInputStream("d:\\最高额保证合同.docx");
        HWPFDocument hwpfDocument = new HWPFDocument(fileInputStream);
        WordExtractor we = new WordExtractor(hwpfDocument);
        String text = we.getText();
        Document pdfDoc = new Document();
        PdfWriter.getInstance(pdfDoc, new FileOutputStream("d://output.pdf"));
        pdfDoc.open();
        // 提取DOC内容并写入PDF
        Paragraph paragraph = new Paragraph(text);
        pdfDoc.add(paragraph);
        pdfDoc.close();
        fileInputStream.close();
    }

    @Test
    public void docToPdf2() throws IOException, DocumentException {
        FileInputStream fileInputStream = new FileInputStream("d:\\最高额保证合同.docx");
        HWPFDocument hwpfDocument = new HWPFDocument(fileInputStream);
        // 初始化 PDF 选项
        PdfOptions pdfOptions = PdfOptions.create();

        FileOutputStream pdfFile = new FileOutputStream("output.pdf"); // 输出的 PDF 文件路径
        hwpfDocument.write(pdfFile);
    }

    @Test
    public void docToPdf3() {
//        try {
//            // Initialize FreeMarker
//            Configuration freemarkerCfg = new Configuration(Configuration.VERSION_2_3_30);
//            freemarkerCfg.setClassForTemplateLoading(PdfConverter.class, "/templates"); // Load your FreeMarker template
//
//            // Load your Word template
//            Template template = freemarkerCfg.getTemplate("your_word_template.ftl");
//
//            // Create a Word document using Apache POI
//            XWPFDocument doc = new XWPFDocument();
//            // Populate the Word document with data using Apache POI
//
//            // Create PDF options
//            PdfOptions pdfOptions = PdfOptions.create();
//
//            // Output the Word document as HTML
//            StringWriter out = new StringWriter();
//            template.process(/* data model */, out);
//
//            // Convert the HTML to a PDF using Apache PDFBox
//            String html = out.toString();
//            PDDocument pdfDoc = new PDDocument();
//            PDPage page = new PDPage();
//            pdfDoc.addPage(page);
//            try (OutputStream os = new FileOutputStream("output.pdf")) {
//                pdfDoc.save(os);
//            }
//
//            pdfDoc.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    @Test
    public void testPdf() {
        String inputFileName = "D:\\contract_template.pdf";//模板
        String outputFileName = "D:\\output.pdf";//生成文件

        // 填充表单的数据(文本)
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("%contractNum%", "789446544631635");
        data.put("contractStartYear", "2001");
        data.put("contractStartMonth", "09");
//        data.put("address", "北京市朝阳区和平路101号");
//        data.put("phone", "13600000000");

        // 填充表单的数据(图片)
//        Map<String, String> picData = new HashMap<>();
//        picData.put("pic", "D:\\test\\dog.png");

        OutputStream os = null;
        PdfStamper ps = null;
        PdfReader reader = null;
        try {
            // 1 获取文件的输出流
            os = new FileOutputStream(new File(outputFileName));
            // 2 读取pdf模板
            reader = new PdfReader(inputFileName);
            // 3 根据表单生成一个新的pdf
            ps = new PdfStamper(reader, os);
            // 4 获取pdf表单
            AcroFields form = ps.getAcroFields();
            // 5 给表单添加中文字体
            BaseFont bf = BaseFont.createFont("font/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            form.addSubstitutionFont(bf);

            // 6遍历data赋值到form
            for (String key : data.keySet()) {
                form.setField(key, data.get(key).toString());
            }
            ps.setFormFlattening(true);

            // 7 填充图片到form
            PdfStamper stamper = ps;
//            picData.forEach((fieldName, imgSrc) -> {
//                try {
//                    List<AcroFields.FieldPosition> fieldPositions = form.getFieldPositions(fieldName);
//                    for (AcroFields.FieldPosition fieldPosition : fieldPositions) {
//                        // 通过域名获取所在页和坐标，左下角为起点
//                        int pageno = fieldPosition.page;
//                        Rectangle signrect = fieldPosition.position;
//                        float x = signrect.getLeft();
//                        float width = signrect.getWidth();
//                        float y = signrect.getBottom();
//                        // 读图片
//                        Image image = Image.getInstance(imgSrc);
//                        float img_width = image.getWidth();
//                        // 获取操作的页面
//                        PdfContentByte under = stamper.getOverContent(pageno);
//                        // 图片的大小
//                        image.scaleToFit(signrect.getWidth(), signrect.getHeight());
//                        // 添加图片
//                        image.setAbsolutePosition(x, y);
//                        under.addImage(image);
//                    }
//                } catch (BadElementException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (DocumentException e) {
//                    e.printStackTrace();
//                }
//            });

//            log.info("生成pdf成功:{}", outputFileName);
        } catch (Exception e) {
            log.error("生成pdf失败:{}", outputFileName);
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                reader.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
