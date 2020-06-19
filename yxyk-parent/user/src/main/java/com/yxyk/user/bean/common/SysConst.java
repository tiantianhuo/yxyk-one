package com.yxyk.user.bean.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * created with IntelliJ IDEA
 *
 * @author: create by limu
 * Date: 2019/9/27
 * Time：10:53
 */
public class SysConst {

    public final static String SPLIT_TAG = ",";

    public final static String SHIRO_USER_SESSION_NAME = "ssp_omp_user";

    public static final String COOKIE_SUFFIX = "SSP_OMP_COOKIE_ID";    //cookie前缀

    public final static Long SHIRO_SESSION_TIMEOUT = -1L;

    public final static String MODULAR_XWDT = "新闻动态";

    public static final String SHIRO_PASSWORD_TYPE = "md5";

    public static final Integer SHIRO_PASSWORD_COUNT = 1;

    public static final String SHIRO_REMBERME_COOKIE_ID = "yxyk_rememberMe";
    /**
     * 排序_属性
     */
    public static final String SORT_PROPERTIES = "createTime";


    /**
     * 举报人的类型
     */
    @Getter
    @AllArgsConstructor
    public enum ReportPersonType {
        REPORT_PERSON_TYPE_ANONYMOUS(1, "匿名"),
        REPORT_PERSON_TYPE_REALNAME(2, "实名");

        private Integer code;
        private String name;
    }

    /**
     * 线索状态
     */
    @Getter
    @AllArgsConstructor
    public enum ReportStatus {
        REPORT_STATE_UNTREATED(0, "未处理"),
        REPORT_STATE_NODISPOSAL(1, "不予处理"),
        REPORT_STATE_JOININGVERIFICATION(2, "加入核查");
        private Integer code;
        private String name;
    }

    /**
     * 邮箱状态
     */
    @Getter
    @AllArgsConstructor
    public enum EmailState {

        EMAIL_STATE_SEND(0, "未发"),
        EMAIL_STATE_UNSEND(1, "已发");

        private Integer code;
        private String name;
    }

    /**
     * 开启状态
     */
    @Getter
    @AllArgsConstructor
    public enum PushModularType {

        PUSH_MODULAR_TYPE_OPEN(0, "开启"),
        PUSH_MODULAR_TYPE_UN_OPEN(1, "关闭");

        private Integer code;
        private String name;
    }

    /**
     * 删除状态
     */
    @Getter
    @AllArgsConstructor
    public enum DeletedState {
        UN_DELETE_STATE(0, "未删除"),
        DELETE_STATE(1, "已删除");
        private int code;
        private String name;
    }

    /**
     * 用户阅读状态
     */
    @Getter
    @AllArgsConstructor
    public enum ReadState {
        ALL(0, "全部"),
        UN_READ_STATE(1, "未读"),
        READ_STATE(2, "已读");
        private int code;
        private String name;
    }

    /**
     * 消息类型
     */
    @Getter
    @AllArgsConstructor
    public enum applyState {
        SN(0, "申请信息"),
        XS(2, "新线索"),
        YQ(3, "舆情专报"),
        SF(1, "申请反馈");
        private int code;
        private String name;
    }

    /**
     * 办理状态
     */
    @Getter
    @AllArgsConstructor
    public enum transactionState {
        OK_TRANSACTION(1, "已处理"),
        NO_TRANSACTION(0, "未处理"),
        OK_INSPECT(3, "已核查"),
        NO_PROCESSING(2, "不予处理");
        private int code;
        private String name;
    }

    /**
     * 立案状态
     */
    @Getter
    @AllArgsConstructor
    public enum RegisterState {
        NO_REGISTER_STATE(0, "未立案"),
        OK_REGISTER_STATE(1, "已立案");
        private int code;
        private String name;
    }

    /**
     * 回复状态
     */
    @Getter
    @AllArgsConstructor
    public enum ReplyState {
        NO_REPLY_STATE(0, "未回复"),
        OK_REPLY_STATE(1, "已回复");
        private int code;
        private String name;
    }

    /**
     * 排序操作
     */
    @Getter
    @AllArgsConstructor
    public enum SortEvent {
        upwardEVENT(1, "上移"),
        downEVENT(2, "下移"),
        roofEVENT(3, "置顶"),
        bottomEVENT(4, "置底");
        private int code;
        private String name;
    }

    /**
     * 时间类型
     */
    @Getter
    @AllArgsConstructor
    public enum TimeType {
        CUSTOM_TIME("define", "自定义时间"),
        ALL("all", "全部"),
        TODAY("today", "今天"),
        YESTERDAY("yesterday", "昨天"),
        WEEK("week", "近7天"),
        MONTH("month", "近30天");

        private String type;
        private String name;

    }

    /**
     * 用户删除状态
     */
    @Getter
    @AllArgsConstructor
    public enum IsAdmin {
        UN_ADMIN(0, "非"),
        IS_ADMIN(1, "是");
        private int code;
        private String name;
    }

    /**
     * 公告置顶未置顶
     */
    @Getter
    @AllArgsConstructor
    public enum IsTop {
        UN_TOP(0, "未置顶"),
        IS_TOP(1, "置顶");
        private int code;
        private String name;
    }

    public static Optional<TimeType> getTimeTypeByType(String type) {
        return Arrays.stream(TimeType.values())
                .filter(replyType -> StringUtils.equals(type, replyType.getType()))
                .findFirst();
    }

    /**
     * 是否允许查看
     */
    @Getter
    @AllArgsConstructor
    public enum AllowState {
        IS_ALLOW_STATE(1, "同意"),
        UN_ALLOW_STATE(0, "不同意");
        private int code;
        private String name;
    }

    /**
     * 是否为永久
     */
    @Getter
    @AllArgsConstructor
    public enum Forever{
        //永久
        IS_FOREVER(1,"是"),
        //不永久
        NO_FOREVER(2,"否");
        private int code;
        private String name;
    }

}
