package Message;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocketServer {
	
	//当前连接数
	private static int onlineCount = 0;
	private static Map<Integer, Integer>  connectionSet = new HashMap<Integer, Integer>(); 
	private Session session;
	
	@OnOpen
	public void onOpen(Session session){
		this.session = session;
		
	}
	
	@OnClose
	public void onClose(Session session){
		
	}
	
	@OnError
	public void onError(Throwable error){
		
	}
	
	@OnMessage
	public void onMessage(String message,Session session){
		
	}
}
