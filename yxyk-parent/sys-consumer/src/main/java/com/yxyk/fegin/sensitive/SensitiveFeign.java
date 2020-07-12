package com.yxyk.fegin.sensitive;


import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.vo.VoAdminSensitive;
import com.yxyk.bean.vo.VoAdminSensitiveAll;
import com.yxyk.bean.vo.VoArticle;
import com.yxyk.bean.vo.VoArticleAll;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "menu")
public interface SensitiveFeign {

    /**
     * 保存修改article
     *
     * @param vosensitive vo
     * @return JSONResponse
     */
    @PostMapping("/sensitive/saveSensitive")
    JSONResponse save(@RequestBody VoAdminSensitive vosensitive);
    /**
     * 删除
     *
     * @param id 删除文章id
     * @return JSONResponse
     */
    @PostMapping("/sensitive/delSensitive")
    JSONResponse delSensitive(@RequestParam("id") Long id);

    /**
     * 获取所有未删除文章
     *
     * @param voAdminSensitive 文章参数
     * @return JSONResponse
     */
    @PostMapping("/sensitive/findAllSensitive")
   JSONResponse findAllSensitive(@RequestBody VoAdminSensitiveAll voAdminSensitive);


    /**
     * 通过id查询
     * @param id id
     * @return JSONResponse
     */
    @PostMapping("/sensitive/findSensitiveById")
    JSONResponse findSensitiveById(@RequestParam(value = "id")Long id);
}
