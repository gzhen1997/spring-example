package com.demo.test;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @auther gzhen
 * @date 2023-10-27  15:59
 * @description
 */

public class WordToPdf1 {


    public static String DOC_PATH = "d://contract_template.doc";
    public static String PDF_PATH = "d://DwOpenAgree.pdf";
    public static void main(String[] args) {
        FileOutputStream os =null;
        try {
//            String s = "<License><Data><Products><Product>Aspose.Total for Java</Product><Product>Aspose.Words for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry><SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber></Data><Signature>sNLLKGMUdF0r8O1kKilWAGdgfs2BvJb/2Xp8p5iuDVfZXmhppo+d0Ran1P9TKdjV4ABwAgKXxJ3jcQTqE/2IRfqwnPf8itN8aFZlV3TJPYeD3yWE7IT55Gz6EijUpC7aKeoohTb4w2fpox58wWoF3SNp6sK6jDfiAUGEHYJ9pjU=</Signature></License>";
//            ByteArrayInputStream is = new ByteArrayInputStream(s.getBytes());
//            License license = new License();
//            license.setLicense(is);

            File file = new File(PDF_PATH); // 新建一个空白pdf文档
            os = new FileOutputStream(file);
            Document doc = new Document(DOC_PATH); // Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
