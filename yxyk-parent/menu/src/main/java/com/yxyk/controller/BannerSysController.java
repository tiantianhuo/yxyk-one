package com.yxyk.controller;


import com.yxyk.BaseController;
import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.Banner;
import com.yxyk.bean.vo.VoBanner;
import com.yxyk.bean.vo.VoBannerSys;
import com.yxyk.service.BannerSysService;
import com.yxyk.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author ：cw
 * @date ：Created in 2019/10/12 16:48
 * @description：轮播图api接口
 * @modified By：cw
 */
@RestController
@RequestMapping("/banners")
@AllArgsConstructor
public class BannerSysController extends BaseController {

    private final BannerSysService bannerSysService;

    /**
     * 添加/修改轮播图
     *
     * @param voBanner 轮播图对象
     * @return result
     */
    @PostMapping(value = "/save")
    public JSONResponse addBanner(@RequestBody VoBanner voBanner) {
        bannerSysService.saveBanner(voBanner);
        return this.success();
    }


    /**
     * 查看轮播图
     *
     * @param id 轮播图id
     * @return json
     */
    @PostMapping(value = "/getBanner")
    public JSONResponse getBanner(Long id) {
        Optional<Banner> byId = bannerSysService.findById(id);
        return this.success(byId.orElse(null));
    }

    /**
     * 删除轮播图
     *
     * @param id 轮播图id
     * @return json
     */
    @PostMapping(value = "/deleteBannerById")
    public JSONResponse deleteBannerById(Long id) {
        try {
            Optional<Banner> byId = bannerSysService.findById(id);
            byId.ifPresent(bannerSys -> {
                bannerSys.setDeleteState(SysConst.DeletedState.DELETE_STATE.getCode());
                bannerSysService.save(bannerSys);
            });
        } catch (Exception e) {
            return this.error("删除出错");
        }
        return this.success();

    }

    /**
     * 上传轮播图
     *
     * @return Result
     */
    @PostMapping(value = "/uploadBanner")
    public JSONResponse uploadBanner() throws Exception {
        return this.success("hahahahaha");
    }

    /**
     * 轮播图上移、下移、置顶、置底
     * 更改banner的排序位置
     *
     * @param id    banner 的id
     * @param event 操作（1、上移 2、下移 3、置顶 4、置底）
     * @return json
     */
    @PostMapping(value = "changeBannerIndex")
    public JSONResponse changeBannerIndex(@RequestParam("id") Long id, @RequestParam("event") Integer event) throws OperationException {
        bannerSysService.changeSortIndex(id, event);
        return this.success();
    }

    /**
     * 表格数据
     *
     * @return Result
     */
    @PostMapping(value = "findAllBanner")
    public JSONResponse findAllBanner(@RequestBody VoBannerSys voBannerSys) {
        LocalDateTime startTime = DateUtils.parseDateTime(voBannerSys.getStartTime());
        LocalDateTime endTime = DateUtils.parseDateTime(voBannerSys.getEndTime());
        Page<Banner> all = bannerSysService.findAllBanner(voBannerSys);
        return this.success(all);
    }

    /**
     * 修改回显:通过id查询
     *
     * @param id 请求参数
     * @return Result
     */
    @PostMapping(value = "findBannerById")
    public JSONResponse findBannerById(Long id) {
        Banner bannerSys = bannerSysService.findBannerById(id);
        return this.success(bannerSys);
    }

}
