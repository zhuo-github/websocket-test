package com.zhuo.test.websocket.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhuo.test.websocket.entity.RequestMessage;
import com.zhuo.test.websocket.entity.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class BroadcastCtl {
    private static final Logger logger = LoggerFactory.getLogger(BroadcastCtl.class);

    // 收到消息记数
    private AtomicInteger count = new AtomicInteger(0);

    /**
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo默认 消息将被发送到与传入消息相同的目的地(前端与后端建立websocket的连接地址)
     * 消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换
     * @param requestMessage 一个pojo是接收前端传过来的数据
     * @return
     */
    @MessageMapping("/receive")
    @SendToUser(value = "/topic/getResponse",broadcast = false)
    public ResponseMessage broadcast(RequestMessage requestMessage){
        logger.info("receive message = {}" , JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setResponseMessage(count.incrementAndGet()+"_"+requestMessage.getName());
        return responseMessage;
    }

    @RequestMapping(value="/broadcast/index")
    public String broadcastIndex(HttpServletRequest req){
        System.out.println(req.getRemoteHost());
        return "/ws-broadcast";
    }

    @RequestMapping("/login")
    public String login(){
        return "/login";
    }
}
