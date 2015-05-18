/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.onbarcode.barcode.Code128;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import javax.swing.text.Document;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

/**
 *
 * @author rai
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        Code128 barcode=new Code128();
//        barcode.setData("123");
//        barcode.setX(2);
//        barcode.drawBarcode("/home/rai/Documents/123.jpg");
        
//        Code128Bean code128 = new Code128Bean();
//        code128.setHeight(15f);
//        code128.setModuleWidth(0.3);
//        code128.setQuietZone(10);
//        code128.doQuietZone(true);
//
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png", 300, BufferedImage.TYPE_BYTE_BINARY, false, 0);
//        code128.generateBarcode(canvas, "1234567890");
//        canvas.finish();
//
////write to png file
//        FileOutputStream fos = new FileOutputStream("barcode.png");
//        fos.write(baos.toByteArray());
//        fos.flush();
//        fos.close();
//
////write to pdf
//        Image png = Image.getInstance(baos.toByteArray());
//        png.setAbsolutePosition(400, 685);
//        png.scalePercent(25);
//
//        
//        Document document = new Document(new Rectangle(595, 842));
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("barcodes.pdf"));
//        document.open();
//        document.add(png);
//        document.close();
//
//        writer.close();
    }
}
