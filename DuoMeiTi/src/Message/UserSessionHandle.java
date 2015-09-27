package Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.Session;

import com.mysql.fabric.xmlrpc.base.Array;

import model.User;

public class UserSessionHandle {
	private final Set sessions = new HashSet<>();
	private final Set users =new HashSet<>();
	
	public void addSession(Session session){
		sessions.add(session);
	}
	public void removeSession(Session session){
		sessions.remove(session);
	}
	public List getUsers(){
		return new ArrayList<>(users);
	}
	public void addUser(User u){
		users.add(u);
	}
	public void removeUser(User u){
		users.remove(u);
	}
	private String createAddMessage(User user){
		return null;
	}
	private void sendToAllConnectionedSessions(String message){
		
	}
	private void sendToSession(Session session,String message){
		
	}
	
}
