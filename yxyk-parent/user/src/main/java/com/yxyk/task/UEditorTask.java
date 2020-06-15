//package com.yxyk.task;
//
//import com.yxyk.controller.common.BaseController;
//import com.yxyk.properties.UEditorProperties;
//import com.yxyk.utils.UnZipUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
///**
// * created with IntelliJ IDEA
// *
// * @author: create by limu
// * Date: 2019/10/22
// * Time：11:18
// */
////@Component
//public class UEditorTask extends BaseController implements CommandLineRunner {
//
//    @Autowired
//    private UEditorProperties uEditorProperties;
//
//    @Override
//    public void run(String... args) throws Exception {
//        checkJspZip();
//        UnZipUtils.unZip(new File(getCurrentPath() + "jsp.zip"), getCurrentPath());
//    }
//
//    private void checkJspZip() throws IOException {
//        String zipFilePath = "jsp.zip";
//        if (!new File(zipFilePath).exists()) {
//            org.springframework.core.io.Resource resource = new ClassPathResource(uEditorProperties.getResourceUrl());
//            int length = 2097152;
//            InputStream input = resource.getInputStream();
//            FileOutputStream out = new FileOutputStream(new File(zipFilePath));
//            byte[] buffer = new byte[length];
//            while (true) {
//                int ins = input.read(buffer);
//                if (ins == -1) {
//                    input.close();
//                    out.flush();
//                    out.close();
//                    break;
//                } else {
//                    out.write(buffer, 0, ins);
//                }
//            }
//        }
//    }
//
//}
