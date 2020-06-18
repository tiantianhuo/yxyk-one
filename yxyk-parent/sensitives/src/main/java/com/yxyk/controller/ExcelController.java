package com.yxyk.controller;

import com.yxyk.utils.Excel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class ExcelController {
@PostMapping("/toHtml")
String test() {
        return "excelImport";
    }
    /**
     * 描述：上传
     */
    @ResponseBody//返回json数据
    @RequestMapping(value = "/excelImport", method = RequestMethod.POST)
    public String  uploadExcel(@RequestParam MultipartFile file) {
        try{
            Excel.uploadExcel(file);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "上传成功";
    }

    /**
     * 描述：下载模板
     */
    @RequestMapping(value = "/downloadExcel")
    @ResponseBody
    public String downloadExcel(HttpServletResponse response, HttpServletRequest request) {
        try{
            Excel.downloadExcel(response,request);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "下载成功";
    }
    }