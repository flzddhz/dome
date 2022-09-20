//package com.company;
//
//import com.sun.deploy.net.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.util.EntityUtils;
//
//public class Tonke {
//
//    public static void main(String[] args) {
//        HttpClient httpclient = HttpClientBuilder.create().build();
//        String loginURL = UrlUtils.LOGINURL+UrlUtils.GRANTSERVICE+
//                "&client_id=" + UrlUtils.CLIENTID +
//                "&client_secret=" + UrlUtils.CLIENTSECRET +
//                "&username=" + UrlUtils.USERNAME +
//                "&password=" + UrlUtils.PASSWORD;
//        logger.info("=====LoginURL:"+loginURL);
//        HttpPost httpPost = new HttpPost(loginURL);
//        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
//        HttpResponse response = httpclient.execute(httpPost);
//        logger.info("=====Response:"+response);
//
//        //如果登录失败直接返回
//        int statusCode = response.getStatusLine().getStatusCode();
//        if (statusCode != HttpStatus.SC_OK) {
//            String response_string = EntityUtils.toString(response.getEntity());
//            throw new Exception("登录接口调用失败HttpPostUtils。loginPost：错误代码："+response_string);
//        }
//        //从登录返回的reponse中获得access_token
//        String getResult = EntityUtils.toString(response.getEntity());
//        logger.info("=====Result:"+getResult);
//        logger.info("-----LoginPost.loginPost successful.");
//        System.out.println("");
//        JSONObject jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
//        String loginAccessToken = jsonObject.getString("access_token");
//    }
//}
