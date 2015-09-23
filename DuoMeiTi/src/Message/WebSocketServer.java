package Message;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/websocket")
public class WebSocketServer {
	
	@OnOpen
	public void onOpen(Session session){
		System.out.println("Client connected");
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
