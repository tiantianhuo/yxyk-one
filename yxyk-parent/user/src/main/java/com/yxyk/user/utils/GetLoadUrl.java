package com.yxyk.user.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 获取小程序临时文件下载地址
 */
@Slf4j
class GetLoadUrl {

    private static final String ASSESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7c77b15cac56acd0&secret=a12238017402bc000bf7b0263a6aa609";

    private static final String DOWNLOAD_URL = "https://api.weixin.qq.com/tcb/batchdownloadfile?access_token=";

    private static final String ENV = "litigation-dev-qb4ki";

    static String getFileUrl(String fileId) {
        String assessToken = getAssessToken();
        JSONObject jsonObject = JSON.parseObject(assessToken);
        String inputLine = null;
        HttpURLConnection httpurlconnection = null;
        try {
            URL url = new URL(DOWNLOAD_URL + jsonObject.getString("access_token"));
            httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setRequestProperty("content-type", "text/html");
            httpurlconnection.setRequestProperty("Accept-Charset", "UTF-8");
            httpurlconnection.setRequestProperty("contentType", "UTF-8");
            httpurlconnection.setRequestProperty("Charset", "UTF-8");
            httpurlconnection.setRequestMethod("POST");
            httpurlconnection.setDoOutput(true);
            httpurlconnection.setDoInput(true);
            httpurlconnection.setUseCaches(false);
            OutputStreamWriter out = new OutputStreamWriter(httpurlconnection.getOutputStream(), StandardCharsets.UTF_8);
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("env", ENV);
            hashMap.put("file_list", JSON.parseArray(fileId));
            out.write(JSON.toJSONString(hashMap));
            out.flush();
            out.close();
            inputLine = readContents(httpurlconnection);
            JSONObject parseObject = JSON.parseObject(inputLine);
            JSONArray file_list = parseObject.getJSONArray("file_list");
            ArrayList<String> urlList = new ArrayList<>();
            for (Object o : file_list) {
                urlList.add(JSON.parseObject(o.toString()).getString("download_url"));
            }
            return JSON.toJSONString(urlList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpurlconnection != null) {
                httpurlconnection.disconnect();
            }
        }
        return inputLine;
    }

    /**
     * 获取assess_token
     *
     * @return String
     */
    private static String getAssessToken() {
        String inputLine = null;
        HttpURLConnection httpurlconnection = null;
        try {
            URL url = new URL(ASSESS_TOKEN_URL);
            httpurlconnection = (HttpURLConnection) url.openConnection();
            httpurlconnection.setRequestProperty("content-type", "text/html");
            httpurlconnection.setRequestProperty("Accept-Charset", "UTF-8");
            httpurlconnection.setRequestProperty("contentType", "UTF-8");
            httpurlconnection.setRequestProperty("Charset", "UTF-8");
            httpurlconnection.setRequestMethod("POST");
            httpurlconnection.setDoOutput(true);
            httpurlconnection.setDoInput(true);
            httpurlconnection.setUseCaches(false);
            OutputStreamWriter out = new OutputStreamWriter(httpurlconnection.getOutputStream(), StandardCharsets.UTF_8);
            out.flush();
            out.close();
            inputLine = readContents(httpurlconnection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpurlconnection != null) {
                httpurlconnection.disconnect();
            }
        }
        return inputLine;
    }

    /**
     * 获取内容
     *
     * @param httpUrlConnection 请求对象
     * @return String
     */
    private static String readContents(HttpURLConnection httpUrlConnection) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder temp = new StringBuilder();
            String inputLine = in.readLine();
            while (inputLine != null) {
                temp.append(inputLine.trim());
                inputLine = in.readLine();
            }
            return temp.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
