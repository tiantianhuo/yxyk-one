package com.yxyk.user.controller;//package com.com.yxyk.user.controller;
//
//import com.com.yxyk.user.bean.common.JSONResponse;
//import com.com.yxyk.user.bean.common.OperationException;
//import com.com.yxyk.user.bean.common.SysConst;
//import com.com.yxyk.user.bean.po.Role;
//import com.com.yxyk.user.bean.po.User;
//import com.com.yxyk.user.bean.vo.VoUser;
//import com.com.yxyk.user.bean.vo.VoUserAll;
//import com.com.yxyk.user.controller.common.BaseController;
//import com.com.yxyk.user.utils.DateUtils;
//import com.com.yxyk.user.utils.VoChangeUtils;
//import com.com.yxyk.user.service.RoleService;
//import com.com.yxyk.user.service.UserService;
//import lombok.AllArgsConstructor;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.IncorrectCredentialsException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.subject.Subject;
//import org.springframework.data.domain.Page;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * created with IntelliJ IDEA
// *
// * @author: create by limu
// * Date: 2019/10/10
// * Time：14:22
// */
//@RestController
//@RequestMapping(value = "/apis/user/")
//@AllArgsConstructor
//public class UserController extends BaseController {
//
//    private final UserService userService;
//
//    //private final ProcuratorateService procuratorateService;
//
//    private final RoleService roleService;
//
//   // private final MessageService messageService;
//
////
//    /**
//     * 保存或者修改用户
//     *
//     * @param voUser 用户参数
//     * @return JSONResponse
//     * @throws OperationException 自定义异常信息
//     */
//    @PostMapping("saveUser")
//    public JSONResponse saveUser(@Valid VoUser voUser) throws OperationException {
//        userService.saveUser(VoChangeUtils.changeToUser(voUser), this.getUser());
//        return this.success();
//    }
//
//    /**
//     * 用户登录
//     *
//     * @param userName 用户名称
//     * @param passWord 用户密码
//     * @return JSONResponse
//     */
//    @PostMapping("loginUser")
//    public JSONResponse loginUser(String userName, String passWord) {
//        try {
//            Subject subject = SecurityUtils.getSubject();
//            if (subject.isAuthenticated()) {
//                subject.logout();
//            }
//            UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord, Boolean.FALSE);
//            subject.login(token);
//            Map<String, Object> map = new HashMap<>();
//            map.put("user", getUser());
//            if (getUser().getRoleId() != 0) {
//                Role role = roleService.findByIdAndDeleteState(getUser().getRoleId());
//                map.put("perssion", role.getPermissions());
//            } else {
//                map.put("perssion", getUser().getPressStr());
//            }
//            return JSONResponse.Create(true, "success", map);
//
//        } catch (IncorrectCredentialsException e) {
//            return this.error("您输入的密码错误!");
//        } catch (AuthenticationException e) {
//            return this.error("您输入的账号不存在!");
//        } catch (Exception e) {
//            return this.error(e.getMessage());
//        }
//    }
//
//    /**
//     * 用户退出
//     *
//     * @return JSONResponse
//     */
//    @PostMapping("loginOutUser")
//    public JSONResponse logoutUser() {
//        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            subject.logout();
//        }
//        return this.success();
//    }
//
//    /**
//     * 获取本检察院下的所有的用户
//     *
//     * @param voUser 用户参数
//     * @return JSONResponse
//     */
//    @PostMapping("findAllUser")
//    public JSONResponse findAllUser(VoUserAll voUser) {
//        Long procuratorId = this.getUser().getProcuratorId();
//        LocalDateTime startTime = DateUtils.parseDateTime(voUser.getStartTime());
//        LocalDateTime endTime = DateUtils.parseDateTime(voUser.getEndTime());
//        Page<User> all = userService.findAll(startTime, endTime, voUser.getUsername(), voUser.getRoleId(), voUser.getPageNum(), voUser.getPageSize(), procuratorId);
//        return this.success(all);
//
//    }
//
//    /**
//     * 修改回显:通过id查询
//     *
//     * @param id 请求参数
//     * @return Result
//     */
//    @PostMapping(value = "findByUserId")
//    public JSONResponse findByUserId(Long id) {
//        User user = userService.findByIdAndDeleteState(id);
//        return this.success(user);
//    }
//
//    /**
//     * 删除用户
//     *
//     * @param id 删除用户id
//     * @return JSONResponse
//     */
//    @PostMapping("delUser")
//    public JSONResponse delUser(Long id) {
//        User user = userService.findByIdAndDeleteState(id);
//        if (user.getDeleteState() == SysConst.DeletedState.DELETE_STATE.getCode()) {
//            return this.error("该用户已删除");
//        }
//        userService.delUser(user);
//        return this.success();
//    }
//
//    /**
//     * 修改密码/首页设置
//     *
//     * @param password    旧密码
//     * @param newPassword 新密码
//     * @param homeUrl     首页路径
//     * @return JSONResponse
//     */
//    @PostMapping("updatePassword")
//    public JSONResponse updatePassword(String password, String newPassword, String homeUrl) throws OperationException {
//        Long userId = this.getUserId();
//        userService.updatePassword(userId, password, newPassword, homeUrl);
//        return this.success();
//    }
//
////    /**
////     * 获取头部的检察院名称
////     *
////     * @return JSONResponse
////     */
////    @PostMapping("findHeaderName")
////    public JSONResponse findHeaderName() {
////        String procuratorateName = procuratorateService.findById(getUser().getProcuratorId()).map(Procuratorate::getName).orElse(StringUtils.EMPTY);
////        return this.success(procuratorateName);
////    }
////
////    /**
////     * 保存上级查看数据权限配置
////     *
////     * @param voAllowState 表单数据
////     * @return json
////     * @throws OperationException 数据不存在或已删除
////     */
////    @PostMapping("updateAllowState")
////    @Transactional(rollbackOn = RuntimeException.class)
////    public JSONResponse updateAllowState(VoAllowState voAllowState) throws OperationException, IOException {
////        User user = getUser();
////        Procuratorate procuratorate = procuratorateService.findById(user.getProcuratorId()).orElseThrow(() -> new OperationException("数据不存在或者已删除"));
////        procuratorate.setAllowState(voAllowState.getAllowState());
////        procuratorate.setAllowStartTime(Objects.isNull(voAllowState.getStartTime()) ? null : DateUtils.parseDate(voAllowState.getStartTime()));
////        procuratorate.setAllowEndTime(Objects.isNull(voAllowState.getEndTime()) ? null : DateUtils.parseDate(voAllowState.getEndTime()));
////        procuratorate.setForever(voAllowState.getForever());
////        procuratorateService.save(procuratorate);
////        Long msgId = voAllowState.getMsgId();
////        if (msgId != null) {
////            Message message = messageService.findById(msgId).orElseThrow(() -> new OperationException("数据不存在或已删除"));
////            String content = voAllowState.getAllowState() == 0 ? SysConst.AllowState.UN_ALLOW_STATE.getName() : SysConst.AllowState.IS_ALLOW_STATE.getName();
////            messageService.feedback(message.getUserId(), getUserId(), content, getUser().getProcuratorId());
////            messageLogService.saveIsRead(msgId, getUserId());
////            WebSocketServer.BroadCastInfo("ok");
////        }
////        return this.success();
////    }
////
////    /**
////     * 上级数据查看表单回显
////     *
////     * @return json
////     */
////    @PostMapping("getAllowState")
////    public JSONResponse getAllowState() throws OperationException {
////        User user = getUser();
////        Procuratorate procuratorate = procuratorateService.findById(user.getProcuratorId()).orElseThrow(() -> new OperationException("数据不存在或者已删除"));
////        RoAllowState roAllowState = new RoAllowState(procuratorate.getAllowState(), procuratorate.getAllowStartTime(), procuratorate.getAllowEndTime(), procuratorate.getForever());
////        return this.success(roAllowState);
////    }
//
//    /**
//     * 获取当前登录用户的id
//     *
//     * @return JSONResponse
//     */
//    @PostMapping("findByLoginUserId")
//    public JSONResponse findByLoginUserId() {
//        return this.success(getUserId());
//    }
//}
