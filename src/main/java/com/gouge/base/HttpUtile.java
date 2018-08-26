package com.gouge.base;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

/**
 * Created by Administrator on 2018/8/7.
 */
public class HttpUtile {

    public static JsonResult sendHttpPost(String httpUrl, String param){
        BufferedReader in = null;
        String result = "";
        PrintWriter out = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        try {
            URL url = new URL(httpUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("accept","*/*");
            conn.setRequestProperty("connection","Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1");
            conn.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            conn.setRequestProperty("jsessionid",SessionId.getSessionId());
            conn.setRequestProperty("platformtype","SWING");

            conn.setConnectTimeout(10*1000); //连接超时
            conn.setReadTimeout(30*1000); //读取超时
            //发送POST请求必须设置
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数
            out.print(param);
            //刷新输出流缓冲
            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                conn.disconnect();
            }
        }
        return JSONObject.parseObject(result,JsonResult.class);
    }

    public static JsonResult uploadFile(String actionUrl, String file) {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        DataOutputStream ds = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            // 统一资源
            URL url = new URL(actionUrl);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpURLConnection.setDoInput(true);
            // 设置是否向httpUrlConnection输出
            httpURLConnection.setDoOutput(true);
            // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("POST");
            // 设置字符编码连接参数
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            // 设置请求内容类型
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; charset=UTF-8; boundary=" + boundary);
            httpURLConnection.setRequestProperty("jsessionid",SessionId.getSessionId());
            httpURLConnection.setRequestProperty("platformtype","SWING");

            // 设置DataOutputStream
            ds = new DataOutputStream(httpURLConnection.getOutputStream());
//            for (int i = 0; i < uploadFilePaths.length; i++) {
            String uploadFile = file;
            String filename = uploadFile.substring(uploadFile.lastIndexOf("\\") + 1,file.length());
            String baseFileName = Base64.getEncoder().encodeToString(filename.getBytes("utf-8")).replaceAll("/","@");
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " + "name=\"file\";filename=\"" + baseFileName
                    + "\"" + end);
            ds.writeBytes(end);
            FileInputStream fStream = new FileInputStream(uploadFile);
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;
            while ((length = fStream.read(buffer)) != -1) {
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
                /* close streams */
            fStream.close();
//            }
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
            /* close streams */
            ds.flush();
            if (httpURLConnection.getResponseCode() >= 300) {
                System.out.println(httpURLConnection.getResponseMessage());
                throw new Exception(
                        "HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                tempLine = null;
                resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (ds != null) {
                try {
                    ds.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return JSONObject.parseObject(resultBuffer.toString(),JsonResult.class);
        }
    }
}
