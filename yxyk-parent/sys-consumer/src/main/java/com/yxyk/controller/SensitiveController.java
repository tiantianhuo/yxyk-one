package com.yxyk.controller;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.vo.VoAdminSensitive;
import com.yxyk.bean.vo.VoAdminSensitiveAll;
import com.yxyk.bean.vo.VoArticle;
import com.yxyk.bean.vo.VoArticleAll;
import com.yxyk.fegin.article.ArticleFeign;
import com.yxyk.fegin.sensitive.SensitiveFeign;
import com.yxyk.utils.Excel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname ArticleController
 * @Description TODO
 * @Date 2020/6/17 15:26
 * @Created by zk
 */
@RestController
@RequestMapping(value = "/apis/sensitive/")
@AllArgsConstructor
public class SensitiveController {

    private final SensitiveFeign sensitiveFeign;

    /**
     * 保存修改 sensitive
     *
     * @param vosensitive vo
     * @return JSONResponse
     */

    @PostMapping("saveSensitive")
    public JSONResponse save(@RequestBody VoAdminSensitive vosensitive) {
        return sensitiveFeign.save(vosensitive);
    }
    /**
     * 删除
     *
     * @param  id
     * @return JSONResponse
     */
    @PostMapping("delSensitive")
    public JSONResponse delSensitive(Long id) {
        return sensitiveFeign.delSensitive(id);
    }

    /**
     * 条件查询
     *
     * @param voAdminSensitive vo
     * @return JSONResponse
     */
    @RequestMapping("findAllSensitive")
    public JSONResponse findAllSensitive(@RequestBody VoAdminSensitiveAll voAdminSensitive) {
        return sensitiveFeign.findAllSensitive(voAdminSensitive);
    }

    /**
     * 通过id查询
     *
     *
     * @param id id
     * @return JSONResponse
     */
    @PostMapping("findSensitiveById")
    public JSONResponse findSensitiveById(@RequestParam(value = "id")Long id) {
        return sensitiveFeign.findSensitiveById(id);
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
