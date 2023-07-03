package com.test;

import cn.hutool.core.io.FileUtil;
import com.spire.barcode.BarCodeGenerator;
import com.spire.barcode.BarCodeType;
import com.spire.barcode.BarcodeScanner;
import com.spire.barcode.BarcodeSettings;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.*;

import static java.awt.print.Printable.PAGE_EXISTS;

public class PrintBarCode {
    static Logger log = LoggerFactory.getLogger(PrintBarCode.class);


    static byte[] generateBarCode39(String msg) {
        if (StringUtils.isEmpty(msg)) {
            return null;
        }
        Code39Bean bean = new Code39Bean();
        // 精细度
        final int dpi = 256;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
        // 配置对象
        bean.setModuleWidth(moduleWidth);
        bean.setWideFactor(3);

        // 设置条码每一条的宽度
        // UnitConv 是barcode4j 提供的单位转换的实体类，用于毫米mm,像素px,英寸in,点pt之间的转换
        // 设置条形码高度和宽度
        bean.setBarHeight((double) ObjectUtils.defaultIfNull(10.00, 9.0D));

        // 设置两侧是否加空白
        bean.doQuietZone(true);

        String format = "image/png";
        ByteArrayOutputStream ous = null;
        byte[] imageByte;
        try {
            ous = new ByteArrayOutputStream();
            // 输出到流
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);
            // 生成条形码
            bean.generateBarcode(canvas, msg);
            // 结束绘制
            canvas.finish();
            imageByte = ous.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != ous) {
                    ous.close();
                }
            } catch (Exception e) {
                log.error("ByteArrayOutputStream流关闭失败，失败原因为：{{}}", e.getMessage());
            }
        }
        return imageByte;
    }


    static byte[] generateBarCode128(String msg, boolean hideText) {
        if (StringUtils.isEmpty(msg)) {
            return null;
        }
        // 如果想要其他类型的条码(CODE 39, EAN-8...)直接获取相关对象Code39Bean...等等
        Code128Bean bean = new Code128Bean();
        // 分辨率：值越大条码越长，分辨率越高。
        final int dpi = 256;
        // 设置两侧是否加空白
        bean.doQuietZone(true);
        // 设置条码每一条的宽度
        // UnitConv 是barcode4j 提供的单位转换的实体类，用于毫米mm,像素px,英寸in,点pt之间的转换
        // 设置条形码高度和宽度
        bean.setBarHeight((double) ObjectUtils.defaultIfNull(10.00, 9.0D));
        bean.setModuleWidth(UnitConv.in2mm(3.0f / dpi));
        // 设置文本位置（包括是否显示）
        if (hideText) {
            bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
        }
        // 设置图片类型
        String format = "image/png";
        ByteArrayOutputStream ous = null;
        byte[] imageByte = null;
        try {
            ous = new ByteArrayOutputStream();
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                    BufferedImage.TYPE_BYTE_BINARY, false, 0);
            // 生产条形码
            bean.generateBarcode(canvas, msg);
            // 结束
            canvas.finish();
            imageByte = ous.toByteArray();
        } catch (IOException e) {
            log.error("IOException:" + e.getMessage());
        } finally {
            try {
                if (null != ous) {
                    ous.close();
                }
            } catch (Exception e) {
                log.error("ByteArrayOutputStream流关闭失败，失败原因为：{{}}", e.getMessage());
            }
        }
        return imageByte;
    }

    public static void generateFile(String msg, String path, String barCodeType) throws IOException {
        File file = new File(path);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            log.error("未找到文件!");
        }
        byte[] barCodeBytes = null;
        if ("CODE128".equals(barCodeType)) {
            barCodeBytes = generateBarCode128(msg, false);
        } else if ("CODE39".equals(barCodeType)) {
            barCodeBytes = generateBarCode39(msg);
        }
        try {
            outputStream.write(barCodeBytes, 0, barCodeBytes.length);
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (Exception e) {
                log.error("outputStream关闭异常！");
            }
        }
    }

    static void generateBarcode(String msg, String path, BarCodeType barCodeType) throws IOException {
        //保存条码为PNG图片
        ImageIO.write(getBarcodeImage(msg, barCodeType), "png", new File(path));
    }
    static BufferedImage getBarcodeImage(String msg, BarCodeType barCodeType) {
        //创建BarcodeSettings实例
        BarcodeSettings settings = new BarcodeSettings();
        //指定条码类型 BarCodeType.Code_128
        settings.setType(barCodeType);
        //设置条码数据
        settings.setData(msg);
        //设置条码显示数据
        settings.setData2D(msg);
        //显示数据文本
        settings.setShowText(true);
        //设置数据文本显示在条码底部
        settings.setShowTextOnBottom(true);
        //设置黑白条宽度
        settings.setX(0.5f);
        //设置生成的条码图片高度
        settings.setImageHeight(70);
        //设置生成的条码图片宽度
        settings.setImageWidth(140);
        //设置边框可见
        settings.hasBorder(false);
//        settings.setBorderColor(new Color(255, 255, 255));//设置条码边框颜色
//        settings.setBorderWidth(1);//设置条码边框宽度
//        settings.setBackColor(new Color(133, 218, 152));//设置条码背景色
        //创建BarCodeGenerator实例
        BarCodeGenerator barCodeGenerator = new BarCodeGenerator(settings);
        //根据settings生成图像数据，保存至BufferedImage实例
        return barCodeGenerator.generateImage();
    }

    static String getBarcode(String path, BarCodeType barCodeType) throws Exception {
        //使用scan方法从图片中识别Code 128条形码
        String[] datas = BarcodeScanner.scan(path, barCodeType);
        return datas[0];
    }

    static void barCodePrint(BufferedImage bufferedImage, int pageWidth, int pageHeight, int showWidth, int showHeight) {
        // 通俗理解就是书、文档
        Book book = new Book();
        // 设置成竖打
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);
        // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper paper = new Paper();
        //纸张大小
        paper.setSize(pageWidth, pageHeight);
        paper.setImageableArea(0, 0, pageWidth, pageHeight);
        pf.setPaper(paper);
        // 把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append((graphics, pageFormat, pageIndex) -> {
            //将图片绘制到graphics对象中（为什么把需要打印的内容drawImage就可以实现打印自己取看值传递一引用传递的区别）
            graphics.drawImage(bufferedImage, 0, 0, showWidth, showHeight, null);
            return PAGE_EXISTS;//返回0（PAGE_EXISTS）则执行打印，返回1（NO_SUCH_PAGE）则不执行打印
        }, pf);
        // 获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();
        // 设置打印类
        job.setPageable(book);
        try {
            //可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
            job.printDialog();
            job.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        String msg = "M-TG013";
//        // 默认为A4纸张，对应像素宽和高分别为 595, 842
//        barCodePrint(getBarcodeImage(msg, BarCodeType.Code_128), 595, 842, 140, 70);
//    }


    public static void drawImage(BufferedImage fileName, int count){
        try {
            DocFlavor dof = DocFlavor.INPUT_STREAM.PNG;
            PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            pras.add(OrientationRequested.PORTRAIT);
            pras.add(new Copies(count));
            pras.add(PrintQuality.HIGH);
            DocAttributeSet das = new HashDocAttributeSet();
            das.add(new MediaPrintableArea(0, 0, 4, 6, MediaPrintableArea.INCH));

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(fileName, "png", os);

            //创建临时文件
            File file = File.createTempFile("test.png","");
            FileUtil.writeFromStream(new ByteArrayInputStream(os.toByteArray()),file);

            InputStream fin = new FileInputStream(file);
//            InputStream fin = new FileInputStream(new ByteArrayInputStream(os.toByteArray()));
            Doc doc = new SimpleDoc(fin ,dof, das);
            DocPrintJob job = ps.createPrintJob();
            job.print(doc, pras);
            fin.close();
        }
        catch (IOException ie) {
            ie.printStackTrace();
        }
        catch (PrintException pe) {
            pe.printStackTrace();
        }
    }

    public static void main(String args[]){
        drawImage(getBarcodeImage("M1A", BarCodeType.Code_128), 1);

    }
}
