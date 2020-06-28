package com.yxyk.controller;

import com.yxyk.BaseController;
import com.yxyk.bean.common.JSONResponse;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.common.VoParams;
import com.yxyk.bean.po.Navigation;
import com.yxyk.bean.vo.VoNavigation;
import com.yxyk.service.NavigationService;
import com.yxyk.utils.RoChangeUtils;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Classname NavigationController
 * @Description TODO
 * @Date 2020/6/16 11:37
 * @Created by cw
 */
@RestController
@RequestMapping(value = "/apis/navigation/")
@AllArgsConstructor
public class NavigationController extends BaseController {

    private final NavigationService navigationService;

    /**
     * 查询栏目
     *
     * @return JSONResponse
     */
    @PostMapping("findAll")
    public JSONResponse findAllNavigation() {
        String str = "{10001,10002,10003}";
        List<Navigation> navigationList = navigationService.findAllByAuthority(str);
        List<VoNavigation> navigations = RoChangeUtils.changToRoNavigation(navigationList);
        return this.success(navigations);
    }

    /**
     * 保存栏目
     *
     * @param voNavigation vo
     * @return JSONResponse
     */
    @PostMapping("save")
    public JSONResponse save(@Valid VoNavigation voNavigation) {
        navigationService.saveNavigation(voNavigation);
        return this.success(SysConst.SUCCESS);
    }

    /**
     * 删除栏目
     *
     * @param voParams id
     * @return JSONResponse
     */
    @PostMapping("delete")
    public JSONResponse deleteById(VoParams voParams) {
        navigationService.deleteById(voParams);
        return this.success(SysConst.DEL_SUCCESS);
    }

    /**
     * 通过id查询
     *
     * @param voParams vo
     * @return JSONResponse
     */
    @PostMapping("findOne")
    public JSONResponse findOne(VoParams voParams) {
       Navigation navigation=navigationService.findOne(voParams.getId());
       return this.success(navigation);
    }


//    /**
//     * 栏目上移、下移、置顶、置底
//     * 更改banner的排序位置
//     *
//     * @param id    banner 的id
//     * @param event 操作（1、上移 2、下移 3、置顶 4、置底）
//     * @return json
//     */
//    @PostMapping(value = "changeBannerIndex")
//    public JSONResponse changeBannerIndex(Long id, Integer event) throws OperationException {
//        navigationService.changeSortIndex(id, event);
//        return this.success(SysConst.SUCCESS);
//    }

}
