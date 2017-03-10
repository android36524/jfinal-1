/**
 * 
 */
/**
 * @author liangjiahong
 * @date 2016年10月31日
 */
package com.xiaoan.wlt.webSocket;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSONObject;
import com.xiaoan.wlt.common.ImMsg;
import com.xiaoan.wlt.shiro.UserContext;
import com.xiaoan.wlt.utils.JsonUtil;

import javassist.tools.web.Webserver;
/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/ws/{userId}")
public class WebSocket {
    //静态变量，用来记录当前在线连接数。

    private static AtomicInteger onlineCount = new AtomicInteger(0);
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
//    protected static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();
    protected static Map<String,WebSocket> webSocketSet = new HashMap<String,WebSocket>();
    
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String userId;
    public WebSocket() {
        System.out.println(" WebSocket init~~~");
    }
    
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId,Session session){
        this.session = session;
        this.userId = userId;
        webSocketSet.put(userId, this);
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this.userId);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        //群发消息
//        {"mine":{"username":"超级管理员","avatar":"super","id":"1","mine":true,"content":"11"},"to":{"username":"robot","id":"-1","avatar":"robot","sign":"robot","name":"robot","type":"friend"}}
        JSONObject jo = JSONObject.parseObject(message);
        ImMsg im = new ImMsg();
        if(jo.getJSONObject("to").get("id").equals("-1")){
        	try {
        		im.setUsername(jo.getJSONObject("to").get("username").toString());
    			im.setAvatar("../static/img/robot.jpg");
    			im.setId(-1L+"");
    			im.setType("friend");
    			String str = Robot.send(jo.getJSONObject("mine").get("content").toString());
    			JSONObject msg = JsonUtil.strToJson(str);
    			im.setContent(msg.toJSONString());
    			im.setMine(false);
				sendMessage(im.toJsonString());
			} catch (IOException e) {
				e.printStackTrace();
			}
        }else{
        	im.setUsername(jo.getJSONObject("mine").get("username").toString());
			im.setAvatar("../upload/images/head/"+jo.getJSONObject("mine").get("avatar").toString());
			im.setId(jo.getJSONObject("mine").get("id").toString());
			im.setType("friend");
			im.setContent(jo.getJSONObject("mine").get("content").toString());
			im.setMine(false);
			System.out.println(im.toJsonString());
			try {
				sendMsgUser(jo.getJSONObject("to").get("id").toString(),im.toJsonString());
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
    }
    

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        System.out.println(error.getMessage());
//        error.printStackTrace();
        
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static int getOnlineCount() {
        return onlineCount.get();
    }

    public static void addOnlineCount() {
        onlineCount.incrementAndGet();
    }

    public static void subOnlineCount() {
        onlineCount.decrementAndGet();
    }
    
    public void sendMsgUser(String userId,String msg) throws IOException{
    	WebSocket ws = webSocketSet.get(userId);
    	if(ws != null){
    		ws.sendMessage(msg);
    	}
    }

}