package com.yxyk.service.impl;

import com.yxyk.bean.common.OperationException;
import com.yxyk.bean.common.SysConst;
import com.yxyk.bean.po.User;
import com.yxyk.bean.vo.VoUserSearch;
import com.yxyk.reportory.UserRepository;
import com.yxyk.service.UserService;
import com.yxyk.utils.DateUtils;
import com.yxyk.utils.DynamicSpecifications;
import com.yxyk.utils.PageUtils;
import com.yxyk.utils.SearchFilter;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/10/10
 * Time：14:24
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public void saveUser(User user) throws OperationException {
        Long userId = user.getId();
        Optional<User> userOptional = userRepository.findByUserNameAndDeleteState(user.getUserName(), SysConst.DeletedState.UN_DELETE_STATE.getCode());
        if (userId != null && userId != 0L) {
            User dbUser = this.findById(userId).orElseThrow(() -> new OperationException("用户不存在或者已经被删除"));
            if (!userOptional.isPresent() || Objects.equals(dbUser.getId(), userId)) {
                dbUser.setUserName(user.getUserName());
                dbUser.setRoleId(user.getRoleId());
                dbUser.setPressStr(user.getPressStr());
                dbUser.setRemarks(user.getRemarks());
                if (StringUtils.isNotBlank(user.getPassword())) {
                    user.setPassword(new SimpleHash(SysConst.SHIRO_PASSWORD_TYPE, user.getPassword(), dbUser.getSalt(), SysConst.SHIRO_PASSWORD_COUNT).toString());
                }
                userRepository.save(dbUser);
            } else {
                throw new OperationException("用户名称重复,换一个试试吧");
            }
        } else {
            if (!userOptional.isPresent()) {
                int salt = new Random().nextInt(5000);
                user.setSalt(Integer.toString(salt));
                user.setPassword(new SimpleHash(SysConst.SHIRO_PASSWORD_TYPE, user.getPassword(), Integer.toString(salt), SysConst.SHIRO_PASSWORD_COUNT).toString());
                user.setDeleteState(SysConst.DeletedState.UN_DELETE_STATE.getCode());
                user.setCreateTime(LocalDateTime.now());
                user.setAdminState(SysConst.IsAdmin.IS_ADMIN.getCode());
                userRepository.save(user);
            } else {
                throw new OperationException("用户名称重复,换一个试试吧");
            }
        }
    }

    @Override
    public Page<User> findUserPage(VoUserSearch voUserSearch) {
        Map<String, SearchFilter> filterMap = new HashMap<>();
        List<Long> provinceIdList = new ArrayList<>();
//        if (voUserSearch.getProvinceId() != null && voUserSearch.getProvinceId() != 0L) {
//            List<Long> pidList = procuratorateService.findByProvinceId(voUserSearch.getProvinceId()).stream().map(Procuratorate::getId).collect(Collectors.toList());
//            provinceIdList.addAll(pidList);
//            if (voUserSearch.getCityId() != null && voUserSearch.getCityId() != 0L) {
//                List<Long> pidListByCity = procuratorateService.findByCityId(voUserSearch.getCityId()).stream().map(Procuratorate::getId).collect(Collectors.toList());
//                provinceIdList.clear();
//                provinceIdList.addAll(pidListByCity);
//                if (voUserSearch.getCountyId() != null && voUserSearch.getCountyId() != 0L) {
//                    List<Long> pidListByCounty = procuratorateService.findByCountyId(voUserSearch.getCountyId()).stream().map(Procuratorate::getId).collect(Collectors.toList());
//                    provinceIdList.clear();
//                    provinceIdList.addAll(pidListByCounty);
//                }
//            }
//        }
//        if (provinceIdList.size() != 0) {
//            filterMap.put("procuratorId", new SearchFilter("procuratorId", SearchFilter.Operator.IN, provinceIdList));
//        } else if (voUserSearch.getProvinceId() != null && voUserSearch.getProvinceId() != 0L) {
//            filterMap.put("procuratorId", new SearchFilter("procuratorId", SearchFilter.Operator.EQ, -1L));
//        }
        if (StringUtils.isNotBlank(voUserSearch.getStartTime())) {
            filterMap.put("startTime", new SearchFilter("createTime", SearchFilter.Operator.GTE, DateUtils.parseDate(voUserSearch.getStartTime()).atTime(LocalTime.MIN)));
        }
        if (StringUtils.isNotBlank(voUserSearch.getEndTime())) {
            filterMap.put("endTime", new SearchFilter("createTime", SearchFilter.Operator.LTE, DateUtils.parseDate(voUserSearch.getEndTime()).atTime(LocalTime.MAX)));
        }
        if (StringUtils.isNotBlank(voUserSearch.getUserName())) {
            filterMap.put("userName", new SearchFilter("userName", SearchFilter.Operator.EQ, voUserSearch.getUserName()));
        }

        filterMap.put("deleteState", new SearchFilter("deleteState", SearchFilter.Operator.EQ, SysConst.DeletedState.UN_DELETE_STATE.getCode()));
        filterMap.put("adminState", new SearchFilter("adminState", SearchFilter.Operator.EQ, SysConst.IsAdmin.IS_ADMIN.getCode()));
        Specification<User> userSpecification = DynamicSpecifications.bySearchFilter(filterMap.values(), User.class);
        return userRepository.findAll(userSpecification, PageUtils.buildPageRequest(voUserSearch.getPageNum(), voUserSearch.getPageSize(), new Sort(Sort.Direction.DESC, "createTime")));
    }

    @Override
    public void deleteUser(Long id) throws OperationException {
        User user = userRepository.findByIdAndDeleteState(id, SysConst.DeletedState.UN_DELETE_STATE.getCode());
        user.setDeleteState(SysConst.DeletedState.DELETE_STATE.getCode());
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
