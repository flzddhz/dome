package com.company;

import com.company.entity.EmailPreviewVo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jdk.internal.util.xml.impl.ReaderUTF8;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static boolean isEncoding(String str, String encode) {
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (isEncoding(str, encode)) { // 判断是不是GB2312
                return encode;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (isEncoding(str, encode)) { // 判断是不是ISO-8859-1
                return encode;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (isEncoding(str, encode)) { // 判断是不是UTF-8
                return encode;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (isEncoding(str, encode)) { // 判断是不是GBK
                return encode;
            }
        } catch (Exception exception3) {
        }
        return ""; // 如果都不是，说明输入的内容不属于常见的编码格式。
    }

    public static void main(String[] args) throws Exception {
//        // write your code here
//        Th th = new Th();
//        Ru ru = new Ru();
//
//        Thread t1 = new Th();
//        Thread t2 = new Ru();
////        Thread t3 = new Ru();
//
//        t1.start();
//        t2.start();
////        t3.start();

//        String a = null;
//        Object o = a;
//        Objects os = (Objects) (Object) a;
//        System.out.println(Objects.equals(o,null));
//        System.out.println(a.equals(""));
//
//        SimpleDateFormat matter = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar calendar = Calendar.getInstance();
//        System.out.println("month" + calendar.get(calendar.MONTH) );
//        System.out.println("day" + calendar.get(calendar.DATE));
//
//        System.out.println("ddd" + File.separator + "fff");
//        String createdate = "2022-04-27 08:59:00";
//        calendar.set(Calendar.YEAR, Integer.parseInt(createdate.substring(0,4)));
//        calendar.set(Calendar.MONTH, Integer.parseInt(createdate.substring(5,7))-1);
//        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(createdate.substring(8,10)));
//        int hour  = Integer.parseInt(createdate.substring(11,13));
//        if(hour>=0 && hour<8){
//            calendar.add(Calendar.DATE, -1);
//        }
//        String classes = "白班";//班次
//        if(hour<8 || hour>=20){
//            classes = "夜班";
//        }
//        String dateAccordingTo = matter.format(calendar.getTime());//生产日期
//
//        System.out.println(classes);
//        System.out.println(dateAccordingTo);

        //不同编码的空格问题
//        System.out.println(" ".hashCode());
//        System.out.println(" ".hashCode());

//        try {
//            Thread.sleep(2000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

//        System.out.println(" ".hashCode());
//        System.out.println("　".hashCode());
//        System.out.println(" ".hashCode());
//        System.out.println(" ".hashCode());
//        System.out.println("\uE640".hashCode());

        String str1 = " ";
        String str2 = " ";

        System.out.println("　".hashCode());

        System.out.println(str1.hashCode());
        System.out.println(getEncoding(str1));
        System.out.println(str2.hashCode());
        System.out.println(getEncoding(str2));
        byte[] b1 = str1.getBytes("GBK");
        //将字节数组解码，转为新的字符对象，并明确采用的编码格式
        //注意，此处必须明确指明采用哪种编码，此处采用的编码格式，要与编码时的格式一致，否则中文会乱码。
        //String s4=new String(b4,"UTF-8");
        //此处必须是采用GBK
        String s1=new String(b1,"GBK");
        System.out.println("s1="+s1);

        byte[] b2 = str2.getBytes("GBK");
        //将字节数组解码，转为新的字符对象，并明确采用的编码格式
        //注意，此处必须明确指明采用哪种编码，此处采用的编码格式，要与编码时的格式一致，否则中文会乱码。
        //此处必须是采用GBK
        String s2=new String(b2,"GBK");
        System.out.println("s2="+s2);

        byte[] b3 = "。".getBytes("GB2312");
        //将字节数组解码，转为新的字符对象，并明确采用的编码格式
        //注意，此处必须明确指明采用哪种编码，此处采用的编码格式，要与编码时的格式一致，否则中文会乱码。
        //此处必须是采用GBK
        String s3=new String(b3,"GB2312");
        System.out.println("s3="+s3);

        System.out.println(str1.equals(new String(str1.getBytes(), "GBK")));
        System.out.println(str2.equals(new String(str2.getBytes(), "GBK")));
        System.out.println("。".equals(new String("。".getBytes(), "UTF-8")));

//        System.out.println("+".hashCode());
//        System.out.println("+".hashCode());

//        OutputStream out = new FileOutputStream("aaaa");
//        out.write(null);

//        String str1 = new String("aaaaaaaaaa");
//        String re = "1";
//        String rel = "";
//        System.out.println(rel.replace(re,str1));

        //int long 最大值
//        Double d = Double.valueOf(String.valueOf(null));
//        int i = 2147483647;
//        long l = 9223372036854775807l;
//        long v = 4294967295l;
//        long b = 2147483648l;
//        System.out.println((int)b);
////        System.out.println(l);
//        System.out.println((int)l == (int)v);

//        String s = "211100619-19.1";
//        System.out.println(s.substring(s.indexOf("-")+1));

//        String ckg = "lfdsjaf*fdjslk*12323";
//        System.out.println(ckg.substring(0,ckg.indexOf("*")));
//        System.out.println(ckg.substring(ckg.indexOf("*")+1,ckg.lastIndexOf("*")));
//        System.out.println(ckg.substring(ckg.lastIndexOf("*")+1));


//        String str = "尊敬的客户您好！\\n\\n    附件出货报告为：PN\\n    出货日期：DATE\\n    请查收，谢谢！\\n\\nBest regards!";
//        System.out.println(str);
//        String str2 = str.replace("\\n","\n");
//        System.out.println(str2);


//        String num1 = "3.9";
//        int a = (int) Math.floor(Double.valueOf(num1));
//        System.out.println(a);

//        float f = 35.5f;
//        Double a = 0.0;
////        System.out.println();
//        Formatter ft = new Formatter();
//        System.out.println(a%1);
//        Double b = a%1;
//        System.out.println(a==b);
//        System.out.println(a%1==0.0?0:0.0);

//        System.out.println(System.currentTimeMillis());

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        java.util.Date date = sdf.parse("2022-12-20 12:00:00");
//        java.sql.Date createTime = new java.sql.Date(date.getTime());
//        DecimalFormat format = new DecimalFormat("0.00");
//        System.out.println(format.format((float) (Integer.parseInt("5")+Integer.parseInt("5"))/Integer.parseInt("96")*100));

//        String str = "[[12324,14232]]";
//        String a = str.replace("[","").replace("]","");
//        String[] b = a.split(",");
//        System.out.println(b[0]);
//        System.out.println(b[1]);

//        String a = "\"123\"";
//        System.out.println(a);
//        String b = StringEscapeUtils.unescapeJavaScript(a);


//        String s1="{\"MsgId\":1,\"TotalCount\":10,\"FilterCount\":8,\"SentCount\":7,\"ErrorCount\":1}";
//        System.out.println(StringEscapeUtils.unescapeJava(s1));
//        System.out.println(s1);


//        //最外层的两个参数 表单ID和data
//        String FormId = "PUR_PurchaseOrder";
//        JsonObject data = new JsonObject();
//
//        //第二层的
//        JsonArray Model = new JsonArray();
//        Map<String,Object> Model_1 = new LinkedHashMap<>();
//
//        //第三层
//        String FID = "FID";
//        JsonArray FPOOrderEntry = new JsonArray();
//        JsonObject FPOOrderEntry_1 = new JsonObject();
//        System.out.println();
//        FPOOrderEntry_1.addProperty("FEntryID","FPOOrderEntry_FEntryID");
//        FPOOrderEntry_1.addProperty("FDevSupplyDate","SCH_DATE");
//        FPOOrderEntry_1.addProperty("FDtFactoryType","PRODUCT_FACT");
//        FPOOrderEntry.add(FPOOrderEntry_1);
//
//        JsonArray FWipEntity = new JsonArray();
//        JsonObject FWipEntity_1 = new JsonObject();
//        FWipEntity_1.addProperty("FFeedBackDate","FPOOrderEntry_FEntryID");
//        FWipEntity_1.addProperty("FPOBillNo","PO_NO");
//        FWipEntity_1.addProperty("FPORow","PO_LINE_NUM");
//        FWipEntity_1.addProperty("FWipStatus","WIP_STATUS");
//        FWipEntity_1.addProperty("FLateReson","DELAY_C");
//        FWipEntity_1.addProperty("FLateDate","DELAY_SCH_DATE");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        FWipEntity_1.addProperty("FImportDatetime",sdf.format(new Date()));
//        FWipEntity_1.addProperty("FFactoryType","PRODUCT_FACT");
//        FWipEntity.add(FWipEntity_1);
////        System.out.println(FWipEntity_1);
////        System.out.println(FWipEntity);
//
//
//        Model_1.put("FID",FID);
//
//        Model_1.put("FPOOrderEntry",FPOOrderEntry);
//        Model_1.put("FWipEntity",FWipEntity);
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        Gson gson = gsonBuilder.create();
//        System.out.println(gson.toJson(Model_1));
//
//
////        Model.add(Model_1.toString().replace("\\\"","\""));
//        Model.add(gson.toJson(Model_1));
//        System.out.println(Model);
//        data.addProperty("Model",Model.toString().replace("\\\"","\""));
//
//        //构建发送到客户系统的结构
//        Map<String,Object> parMap = new LinkedHashMap<>();
//        parMap.put("FormId",FormId);
//        parMap.put("data",data.toString().replace("\\\"","\""));
//        JsonArray jParas = new JsonArray();
//        JsonObject object = new JsonObject();
//        for(String key : parMap.keySet()){
//            String value = parMap.get(key).toString();
//            System.out.println(value);
//            object.addProperty(key,value.replace("\\\"","\""));
//        }
//        String objStr = object.toString();
////        System.out.println(objStr);
////        System.out.println(objStr.replace("\\\"","\""));
//        jParas.add(objStr.replace("\\\"","\""));
////        System.out.println(jParas);
////        System.out.println(jParas.toString().replace("\\\"","\""));
//
////        JsonArray jParas = getRequestData(parMap);

//        //发件服务器地址
//        String smtpServer = "smtphz.qiye.163.com";
//        //端口
//        int port = 25;
//        //登录邮箱
//        final String userid = "sluo@suntakpcb.com";//change accordingly
//        //密码
//        final String password = "1hbl3qt..";//change accordingly
//        //邮件格式
//        String contentType = "text/html;charset=utf-8";
//        //邮件主题
//        String subject = "test: java 测试邮件";
//        //发件人
//        String from = "sluo@suntakpcb.com";
//        //收件人
//        String to = "sluo@suntakpcb.com";//some invalid address
//
//
//        Properties props = new Properties();
//
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", smtpServer);
//        props.put("mail.smtp.port", port);
//        props.put("mail.transport.protocol", "smtp");
//
//        Session mailSession = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(userid, password);
//                    }
//
//                });
//
//        MimeMessage message = new MimeMessage(mailSession);
//        message.addFrom(InternetAddress.parse(from));
//        message.setRecipients(Message.RecipientType.TO, to);
//        message.setSubject(subject);
//
//        StringBuffer body = new StringBuffer();
//
//
//        String path = "C:\\Users\\sluo\\Desktop\\test.msg";
//        EmailPreviewVo emailPreviewVo = MsgUtil.msgParseToPreview(new File(path));
//        String emailBodyPath = MsgUtil.writeHtmlFile(emailPreviewVo);
//
//        String bodyHtml = emailBodyPath;
//
//        Reader reader = new FileReader(bodyHtml);
//        int line;
//        while ((line = reader.read()) != -1) {
////            System.out.println((char)line);
//            body.append((char) line);
//        }
//        reader.close();
//
//        message.setContent(body.toString(), contentType);
//        Transport transport = mailSession.getTransport();
//        try {
//            System.out.println("Sending ....");
//            transport.connect(smtpServer, port, userid, password);
//            transport.sendMessage(message,
//                    message.getRecipients(Message.RecipientType.TO));
//            System.out.println("Sending done ...");
//        } catch (Exception e) {
//            System.err.println("Error Sending: ");
//            e.printStackTrace();
//        }
//        transport.close();
    }
}
