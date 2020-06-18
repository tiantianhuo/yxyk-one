package com.yxyk.user.controller;//package com.com.yxyk.user.controller;
//
//import java.io.IOException;
//
//import com.com.yxyk.user.bean.common.JSONResponse;
//import com.com.yxyk.user.controller.common.BaseController;
//import com.com.yxyk.user.config.web.WebSocketServer;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * WebSocket服务器端推送消息示例Controller
// *
// * @author wallimn，http://wallimn.iteye.com
// *
// */
//@RestController
//@RequestMapping("/api/ws")
//public class WebSocketController extends BaseController {
//
//    @RequestMapping(value="/sendAll", method=RequestMethod.GET)
//    /**
//     * 群发消息内容
//     * @param message
//     * @return
//     */
//    JSONResponse sendAllMessage(){
//        try {
//            WebSocketServer.BroadCastInfo("ok");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return this.success("ok");
//    }
//
//}