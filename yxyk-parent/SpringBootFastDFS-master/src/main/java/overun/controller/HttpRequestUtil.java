package overun.controller;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 发送httpRequest请求工具类
 *
 * @author fhx
 * @date 2019/9/2 14:49
 */
@Component
public class HttpRequestUtil {

    public static String uploadFile(String url ,MultipartFile file,String fileParamName,Map<String,String>headerParams,Map<String,String>otherParams) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            String fileName = file.getOriginalFilename();
            HttpPost httpPost = new HttpPost(url);
            //添加header
            for (Map.Entry<String, String> e : headerParams.entrySet()) {
                httpPost.addHeader(e.getKey(), e.getValue());
            }
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName("utf-8"));
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);//加上此行代码解决返回中文乱码问题
            builder.addBinaryBody(fileParamName, file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            for (Map.Entry<String, String> e : otherParams.entrySet()) {
                builder.addTextBody(e.getKey(), e.getValue());// 类似浏览器表单提交，对应input的name和value
            }
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
//    public String singleFileUpload( MultipartFile file) throws IOException{
//
//
//
//        String response = "";
//
//        InputStream ins = null;
//
//        try
//
//        {
//
//            CloseableHttpClient client = HttpClients.createDefault();
//
//            final HttpPost postMethod = new HttpPost("localhost:9280/fastdfs/filesystem/upload");
//
//            //注释掉的部分会在项目生成一个临时文件 在最后需要删除
//
//            /* File f = null;
//
//            if(file.equals("")||file.getSize()<=0)
//
//            {
//
//                file = null;
//
//            }else{
//
//            //把 MultipartFile  类型转成file类型
//
//                InputStream ins = file.getInputStream();
//
//                f=new File(file.getOriginalFilename());
//
//                inputStreamToFile(ins, f);
//
//            }
//
//            FileBody bin = new FileBody(f);
//
//            HttpEntity reqEntity = MultipartEntityBuilder.create().setCharset(Charset.forName("UTF-8")).addPart("file", bin).build();
//
//            */
//
//            ins = file.getInputStream();
//
//
//
//            HttpEntity reqEntity = MultipartEntityBuilder.create().setCharset(Charset.forName("UTF-8")).addBinaryBody("file", ins,ContentType.DEFAULT_TEXT,file.getOriginalFilename()).build();
//
//
//
//            postMethod.setEntity(reqEntity);
//
//            CloseableHttpResponse res = client.execute(postMethod);
//
////返回的结果有乱码 还未解决！！！！！
//
//            response = EntityUtils.toString(res.getEntity(),"UTF-8");
//
//
//
//            Gson gson = new GsonBuilder().create();
//
//            result = gson.fromJson(response, ResultView.class);
//
//            //删除文件
//
//            // File del = new File(f.toURI());
//
//            //del.delete();
//
//        }
//
//        catch (Exception e)
//
//        {
//
//            e.printStackTrace();
//
//        }finally {
//
//            if(ins!=null){
//
//                ins.close();
//
//            }
//
//        }
//
//        return "1";
//
//    }


//    public String imgUpload(@ApiParam(value = "上传的文件", required = true) MultipartFile uploadFile) throws Exception {
//        if (uploadFile == null) {
//            return "";
//        }
//        if (uploadFile.getSize() >= 1024 * 1024) {
//            return "";
//        }
//        String requestUrl = "localhost:9280/fastdfs/filesystem/upload";
//        Map<String, String> resultMap = new HashMap<String, String>();
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        String result = "";
//        try {
//            HttpPost httpPost = new HttpPost(requestUrl);
//            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//            builder.setCharset(java.nio.charset.Charset.forName("UTF-8"));
//            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
//            //	文件传输http请求头(multipart/form-data)
//            builder.addBinaryBody("uploadFile", uploadFile.getInputStream(), ContentType.MULTIPART_FORM_DATA,
//                    uploadFile.getOriginalFilename());// 文件流
//            //	字节传输http请求头(application/json)
//            ContentType contentType = ContentType.create("application/json", Charset.forName("UTF-8"));
//            builder.addTextBody("sig", sig, contentType);//字节流
//
//            HttpEntity entity = builder.build();
//            httpPost.setEntity(entity);
//            HttpResponse response = httpClient.execute(httpPost);
//            // 执行提交
//            HttpEntity responseEntity = response.getEntity();
//            resultMap.put("scode", String.valueOf(response.getStatusLine().getStatusCode()));
//            resultMap.put("data", "");
//            if (responseEntity != null) {
//                // 将响应内容转换为字符串
//                result = EntityUtils.toString(responseEntity, java.nio.charset.Charset.forName("UTF-8"));
//                resultMap.put("data", result);
//            }
//            JSONObject jsonObj = JSONObject.parseObject(result);
////            if (jsonObj.getString("code").equals("0")) {
////
////            } else {
////                return ResultUtils.error(jsonObj.getString("msg"), ResponseCode.UPLOAD_FILE_FAIL_EXCEPTION);
////            }
//        } catch (Exception e) {
//            resultMap.put("scode", "error");
//            resultMap.put("data", "HTTP请求出现异常: " + e.getMessage());
//            resultMap.put("msg", "HTTP请求出现异常:连接到文件服务器失败 ");
//
////            Writer w = new StringWriter();
////            e.printStackTrace(new PrintWriter(w));
////            return ResultUtils.error(resultMap.get("msg"), ResponseCode.FAIL);
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }


    public static void main(String[] args) {


    }
}
