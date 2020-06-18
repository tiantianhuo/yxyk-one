package com.yxyk;

import com.alibaba.fastjson.JSONObject;
import com.yxyk.bean.common.JSONResponse;
import org.springframework.data.domain.Page;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：14:37
 */
public abstract class BaseController {
    
    /**
     * 成功
     *
     * @return JSONResponse
     */
    protected JSONResponse success() {
        return JSONResponse.Create(true, "success").setData("操作成功");
    }

    /**
     * 成功
     *
     * @param data 返回的数据
     * @return JSONResponse
     */
    protected JSONResponse success(Object data) {
        return JSONResponse.Create(true, "success").setData(data);
    }

    /**
     * 失败
     *
     * @param msg 返回的消息
     * @return JSONResponse
     */
    protected JSONResponse error(String msg) {
        return JSONResponse.Create(false, msg).setStatus(1);
    }

    /**
     * 分页返回
     *
     * @param pageNum  分页参数
     * @param pageSize 分页参数
     * @param total    分页总条数
     * @param data     数据
     * @return JSONResponse
     */
    protected JSONResponse success(Integer pageNum, Integer pageSize, Long total, Object data) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("pageNum", pageNum);
        jsonData.put("pageSize", pageSize);
        jsonData.put("total", total);
        jsonData.put("data", data);
        return this.success(jsonData);
    }

    /**
     * 分页返回
     *
     * @param page 分页对象
     * @return JSONResponse
     */
    protected JSONResponse success(Page page) {
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", page.getTotalElements());
        map.put("list", page.getContent());
        map.put("pageSize", page.getSize());
        map.put("pageNum", page.getNumber());
        return JSONResponse.Create(Boolean.TRUE, "success", map);
    }


    /**
     * 获得当前项目的同级目录
     *
     * @return String
     */
    protected String getCurrentPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!path.exists()) {
            path = new File("");
        }

        File upload = new File(path.getAbsolutePath());
        if (!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getPath() + File.separatorChar;
    }
}
