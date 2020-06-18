package com.yxyk.user.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * author: create by ZhangHao
 * Date: 2019/8/26
 * Time: 14:18
 */
public class PageUtils {

    /**
     * 构建PageRequest
     *
     * @param page 起始页
     * @param size 页数
     * @param sort 排序
     * @return PageRequest
     */
    public static PageRequest buildPageRequest(int page, int size, Sort sort) {
        return new PageRequest(page - 1, size, sort);
    }

    /**
     * 构建PageRequest
     *
     * @param page 起始页
     * @param size 页数
     * @return PageRequest
     */
    public static PageRequest buildPageRequest(int page, int size) {
        return new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, "createTime"));
    }

    /**
     * 构建PageRequest
     *
     * @param page      起始页
     * @param size      页数
     * @param sortField 排序参数
     * @return PageRequest
     */
    public static PageRequest buildPageRequest(int page, int size, String sortField) {

        if (StringUtils.isNotBlank(sortField)) {
            return new PageRequest(page - 1, size, new Sort(Objects.equals(sortField.split("#")[1], "Asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField.split("#")[0]));
        } else {
            return new PageRequest(page - 1, size, new Sort(Sort.Direction.DESC, "createTime"));
        }
    }
}
