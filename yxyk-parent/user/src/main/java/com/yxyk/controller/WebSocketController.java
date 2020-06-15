package com.yxyk.controller;

import java.io.IOException;

import com.yxyk.bean.common.JSONResponse;
import com.yxyk.config.web.WebSocketServer;
import com.yxyk.controller.common.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * WebSocket服务器端推送消息示例Controller
 *
 * @author wallimn，http://wallimn.iteye.com
 *
 */
@RestController
@RequestMapping("/api/ws")
public class WebSocketController extends BaseController {

    @RequestMapping(value="/sendAll", method=RequestMethod.GET)
    /**
     * 群发消息内容
     * @param message
     * @return
     */
    JSONResponse sendAllMessage(){
        try {
            WebSocketServer.BroadCastInfo("ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.success("ok");
    }

}