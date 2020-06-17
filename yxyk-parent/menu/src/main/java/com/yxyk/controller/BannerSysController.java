package com.yxyk.controller;


import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.Banner;
import com.yxyk.bean.vo.VoBannerSys;
import com.yxyk.controller.common.BaseController;
import com.yxyk.service.BannerSysService;
import com.yxyk.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author ：cw
 * @date ：Created in 2019/10/12 16:48
 * @description：轮播图api接口
 * @modified By：cw
 */
@RestController
@RequestMapping("banners")
@AllArgsConstructor
public class BannerSysController extends BaseController {

    private final BannerSysService bannerSysService;

    /**
     * 添加轮播图
     *
     * @param bannerSys 轮播图对象
     * @return result
     */
    @PostMapping(value = "addBanner")
    public JSONResponse addBanner(Banner bannerSys) {
        bannerSysService.save(bannerSys);
        return this.success();
    }


    /**
     * 查看轮播图
     *
     * @param id 轮播图id
     * @return json
     */
    @PostMapping(value = "getBanner")
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
    @PostMapping(value = "deleteBannerById")
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
     * @param file 文件
     * @return Result
     */
    @PostMapping(value = "uploadBanner")
    public JSONResponse uploadBanner(MultipartFile file) throws Exception {

        return this.success();
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
    public JSONResponse changeBannerIndex(Long id, Integer event) throws OperationException {
        bannerSysService.changeSortIndex(id, event);
        return this.success();
    }

    /**
     * 表格数据
     *
     * @return Result
     */
    @PostMapping(value = "findAllBanner")
    public JSONResponse findAllBanner(VoBannerSys voBannerSys) {
        Long procuratorId = this.getUser().getProcuratorId();
        LocalDateTime startTime = DateUtils.parseDateTime(voBannerSys.getStartTime());
        LocalDateTime endTime = DateUtils.parseDateTime(voBannerSys.getEndTime());
        Page<Banner> all = bannerSysService.findAllBanner(startTime, endTime, voBannerSys.getName(), voBannerSys.getPageNum(), voBannerSys.getPageSize(), procuratorId,voBannerSys.getPid());
        return this.success(all);
    }

    /**
     * 修改回显:通过id查询
     *
     * @param id 请求参数
     * @return Result
     */
    @PostMapping(value = "findBannerById")
    public JSONResponse findByUserId(Long id) {
        Banner bannerSys = bannerSysService.findBannerById(id);
        return this.success(bannerSys);
    }

}
