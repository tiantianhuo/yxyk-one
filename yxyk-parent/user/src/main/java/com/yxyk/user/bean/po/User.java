package com.yxyk.user.bean.po;//package com.com.yxyk.user.bean.po;
//
//import com.com.yxyk.user.bean.common.IdEntity;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Entity;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//import java.time.LocalDateTime;
//
///**
// * created with IntelliJ IDEA
// *
// * @author: create by limu
// * Date: 2019/10/10
// * Time：14:10
// */
//@Setter
//@Getter
//@Entity
//@Table(name = "user")
//public class User extends IdEntity {
//
//    /**
//     * 用户名称
//     */
//    private String userName;
//
//    /**
//     * 用户密码
//     */
//    private String password;
//
//    /**
//     * 用户的salt
//     */
//    private String salt;
//
//    /**
//     * 备注
//     */
//    private String remarks;
//
//    /**
//     * 用户所属角色
//     */
//    private Long roleId;
//
//    /**
//     * 创建人id
//     */
//    private Long parentId;
//
//    /**
//     * 标注分类
//     */
//    private String tagClass;
//
//    /**
//     * 网络线索 已读 未读
//     */
//    private String networkReadState;
//
//    /**
//     * 举报线索  已读 未读
//     */
//    private String reportReadState;
//
//    /**
//     * 头像后缀
//     */
//    private String headerSuffix;
//
//    /**
//     * 检察院id
//     */
//    private Long procuratorId;
//
//    /**
//     * 角色 名称
//     */
//    @Transient
//    private String roleName;
//
//    /**
//     * 超管权限管理
//     */
//    private String pressStr;
//
//    /**
//     * 账号开始时间
//     */
//    private LocalDateTime startTime;
//
//    /**
//     * 账号开始时间
//     */
//    private LocalDateTime endTime;
//
//    /**
//     * 主页设置
//     */
//    private String homeUrl;
//
//    /**
//     * 是否是管理员用户
//     */
//    private Integer adminState;
//
//}
