package com.file;

import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;
//import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    private static int CACHE_SIZE = 8192; //缓冲区大小，设为8kb

    private static int IO_CHAR_NUM = 1000; //每次读写操作最大字符数

    /**
     * 将输入流中的内容用输出流写入
     * @param nis 输入流
     * @param nos 输出流
     */
    public static void inputToOutput(InputStream nis, OutputStream nos) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(nis, CACHE_SIZE);
            bos = new BufferedOutputStream(nos, CACHE_SIZE);
            byte[] cache = new byte[CACHE_SIZE];
            int len;
            while ((len = bis.read(cache, 0, CACHE_SIZE)) != -1) {
                bos.write(cache, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(bis, bos);
        }
    }

    /**
     * 读取文本文件返回文件内容
     * @param file
     * @return 字符串
     */
    public static String readFile(File file) {
        FileReader rd = null;
        BufferedReader brd = null;
        try {
            if (isFile(file)) {
                StringBuilder sb = new StringBuilder();
                rd = new FileReader(file);
                brd = new BufferedReader(rd, CACHE_SIZE);
                while (brd.ready()) {
                    sb.append(brd.readLine());
                }
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(brd, rd);
        }
        return null;
    }

    /**
     * 往文件中写入内容
     * @param file
     * @param content
     */
    public static void writerFile(File file,String content) {
        FileWriter wt = null;
        BufferedWriter bwt = null;
        try {
            wt = new FileWriter(file);
            bwt = new BufferedWriter(wt, CACHE_SIZE);
            int off = 0;
            int len = IO_CHAR_NUM;
            while (off < content.length()) {
                if (off + len > content.length()) {
                    len = content.length() % IO_CHAR_NUM;
                }
                bwt.write(content, off, len);
                off += len;
            }
            bwt.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(bwt, wt);
        }
    }

    /**
     * 读取文件返回字节数组
     * @param file
     * @return 字节数组
     */
    public static byte[] inputFile(File file) {
        FileInputStream is = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            if (isFile(file)) {
                is = new FileInputStream(file);
                bis = new BufferedInputStream(is, CACHE_SIZE);
                baos = new ByteArrayOutputStream();
                int cacheSize = 8192;
                byte[] cache = new byte[cacheSize];
                int len;
                while ((len = bis.read(cache, 0, cacheSize)) != -1) {
                    baos.write(cache, 0, len);
                }
                byte[] bytes = baos.toByteArray();
                return bytes;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(baos, bis, is);
        }
        return null;
    }

    /**
     * 往文件中写入内容
     * @param file
     * @param bytes
     */
    public static void outputFile(File file, byte[] bytes) {
        FileOutputStream os = null;
        BufferedOutputStream bos = null;
        try {
            if (isFile(file)) {
                os = new FileOutputStream(file);
                bos = new BufferedOutputStream(os, CACHE_SIZE);
                bos.write(bytes);
                bos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(bos, os);
        }
    }

    /**
     * 基于已有的 zip流添加压缩文件
     * @param nzos zip流
     * @param isEncrypt 是否设置密码
     * @param catalogue 压缩包内存放文件的目录，传 null则不创建
     * @param files 需要压缩的文件
     * @return
     */
    public static ZipOutputStream basedOnStreamAdd(ZipOutputStream nzos, boolean isEncrypt, String catalogue, File... files) {
        ZipOutputStream zos = null;
        FileInputStream is = null;
        BufferedInputStream bis = null;
        try {
            zos = nzos;
            ZipParameters parameters = buildZipParams(isEncrypt);
            String base = buildCatalogue(catalogue);
            for (File item : files) {
                if (!isFile(item)) continue;
                String filename = base + item.getName();
                parameters.setFileNameInZip(filename);
                zos.putNextEntry(parameters);
                is = new FileInputStream(item);
                bis = new BufferedInputStream(is, CACHE_SIZE);
                byte[] cache = new byte[CACHE_SIZE];
                int len;
                while ((len = bis.read(cache, 0, CACHE_SIZE)) != -1) {
                    zos.write(cache, 0, len);
                }
                zos.flush();
                zos.closeEntry();
                closeStream(bis, is);
            }
        } catch (Exception e) {
            closeStream(zos, bis, is);
            e.printStackTrace();
        }
        return zos;
    }

    /**
     * 创建 zip输出流
     * @param outputStream 基于这个流创建
     * @param password 压缩包的解压密码，空则不设置
     * @return
     */
    public static ZipOutputStream createZipOs(OutputStream outputStream, String password) {
        ZipOutputStream zipOutputStream = null;
        try {
            if (password != null && password.trim().length() != 0) {
                zipOutputStream = new ZipOutputStream(outputStream, null, new Zip4jConfig(StandardCharsets.UTF_8, CACHE_SIZE), new ZipModel());
            } else {
                zipOutputStream = new ZipOutputStream(outputStream, password.toCharArray(), new Zip4jConfig(StandardCharsets.UTF_8, CACHE_SIZE), new ZipModel());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zipOutputStream;
    }

    /**
     * 关闭流
     * @param streams
     */
    public static void closeStream(Closeable... streams) {
        try {
            for (Closeable stream : streams) {
                if (stream != null) {
                    stream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建 zip 包参数
     * @return
     */
    private static ZipParameters buildZipParams(boolean encrypt) {
        ZipParameters parameters = new ZipParameters();
        parameters.setEncryptFiles(encrypt); //是否开启密码保护
        parameters.setEncryptionMethod(EncryptionMethod.AES); //加密方式
        parameters.setCompressionLevel(CompressionLevel.NORMAL); //压缩级别
        return parameters;
    }

    /**
     * 构建目录
     * @param catalogue
     * @return
     */
    private static String buildCatalogue(String catalogue) {
        if (catalogue == null) {
            catalogue = "";
        } else {
            if (catalogue.lastIndexOf(File.separator) != catalogue.length() - 1) {
                catalogue += File.separator;
            }
        }
        return catalogue;
    }

    /**
     * 判断是否为文件
     * @param file
     * @return
     */
    private static boolean isFile(File file) {
        return file != null ? file.isFile() : null;
    }


}
