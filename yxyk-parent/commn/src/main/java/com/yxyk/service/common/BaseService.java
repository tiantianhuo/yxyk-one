package com.yxyk.service.common;


import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * author: create by ZhangHao
 * Date: 2019/7/10
 * Time: 14:56
 */
public interface BaseService<T, ID> {

    default T save(T entity) {
        return null;
    }

    default Optional<T> findById(ID id) {
        return null;
    }

}
