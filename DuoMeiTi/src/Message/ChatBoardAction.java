//package Message;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.opensymphony.xwork2.ActionSupport;
//
//import org.hibernate.Session;
//import org.apache.struts2.ServletActionContext;
//import org.hibernate.Criteria;
//import org.hibernate.Query;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import model.AdminProfile;
//import model.StudentProfile;
//import model.User;
//import model.Message;
//import ognl.IteratorElementsAccessor;
//import Message.UserInfo;
//import Message.MessageInfo;
//
//public class ChatBoardAction extends ActionSupport{
//	private static final long serialVersionUID = 1L;
//	
//	//管理员列表
//	private List<UserInfo> adminList;
//	//学生列表
//	private List<UserInfo> studentList;
//	//发信人
//	private int from;
//	//接收人
//	private int to;
//	//信息内容
//	private String content;
//	//日志记录
//	private String log;
//	//当前登录用户
//	private int currentUser;
//	//未读信息列表
//	private List<MessageInfo> newMes;
//	//例示信息
//	private List<Message> historyMes;
//	//未读信息列表渲染的Html
//	private String messageListHtml;
//	//历史信息渲染的html
//	private String historyMesHtml;
//	//网友
//	private int another;
//	
//	
//	public String getHistoryMesHtml() {
//		return historyMesHtml;
//	}
//
//	public void setHistoryMesHtml(String historyMesHtml) {
//		this.historyMesHtml = historyMesHtml;
//	}
//
//	public List<Message> getHistoryMes() {
//		return historyMes;
//	}
//
//	public void setHistoryMes(List<Message> historyMes) {
//		this.historyMes = historyMes;
//	}
//
//	public int getAnother() {
//		return another;
//	}
//
//	public void setAnother(int another) {
//		this.another = another;
//	}
//
//	public String getMessageListHtml() {
//		return messageListHtml;
//	}
//
//	public void setMessageListHtml(String messageListHtml) {
//		this.messageListHtml = messageListHtml;
//	}
//
//	public List<MessageInfo> getNewMes() {
//		return newMes;
//	}
//
//	public void setNewMes(List<MessageInfo> newMes) {
//		this.newMes = newMes;
//	}
//
//	public int getCurrentUser() {
//		return currentUser;
//	}
//
//	public void setCurrentUser(int currentUser) {
//		this.currentUser = currentUser;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//
//	public String getLog() {
//		return log;
//	}
//
//	public void setLog(String log) {
//		this.log = log;
//	}
//
//	public int getFrom() {
//		return from;
//	}
//
//	public void setFrom(int from) {
//		this.from = from;
//	}
//
//	public int getTo() {
//		return to;
//	}
//
//	public void setTo(int to) {
//		this.to = to;
//	}
//
//	public String getContent() {
//		return content;
//	}
//
//	public void setContent(String content) {
//		this.content = content;
//	}
//
//	public List<UserInfo> getAdminList() {
//		return adminList;
//	}
//
//	public void setAdminList(List<UserInfo> adminList) {
//		this.adminList = adminList;
//	}
//
//	public List<UserInfo> getStudentList() {
//		return studentList;
//	}
//
//	public void setStudentList(List<UserInfo> studentList) {
//		this.studentList = studentList;
//	}
//	
//	//获取联系人列表
//	public String getUserList(){
//		Session session = model.Util.sessionFactory.openSession();
//		String hql1="select u.username,u.id from AdminProfile a,User u where a.user=u";
//		String hql2="select u.username,u.id from StudentProfile s,User u where s.user=u";
//		Query q1=session.createQuery(hql1);
//		Query q2=session.createQuery(hql2);
//		adminList=new ArrayList<UserInfo>();
//		studentList=new ArrayList<UserInfo>();
//		Iterator iter=q1.list().iterator();
//		traverseUserInfoList(iter, adminList);
//		iter=q2.list().iterator();
//		traverseUserInfoList(iter, studentList);
//		session.close();
//		return SUCCESS;
//	}
//	
//	//取出再塞进去～
//	private void traverseUserInfoList(Iterator iter,List<UserInfo> list){
//		while(iter.hasNext()){
//			Object[] temp=(Object[])iter.next();
//			String username=(String)temp[0];
//			Integer id=(Integer)temp[1];
//			list.add(new UserInfo(username,id));
//		}
//	}
//	
//	//服务器接受信息
//	public String receiveMes(){
//		System.out.println("hell");
//		try{
//			Session session = model.Util.sessionFactory.openSession();
//			Transaction tx=session.beginTransaction();
//			Message newMes =new Message();
//			newMes.content=content;
//			String selectFrom="from User u where u.id="+from;
//			String selectTo="from User u where u.id="+to;
//			
//			List<User> fromList = new ArrayList<User>();
//			fromList=session.createQuery(selectFrom).list();
//			List<User> toList= new ArrayList<User>();
//			toList=session.createQuery(selectTo).list();
//			newMes.from=fromList.get(0);
//			newMes.to=toList.get(0);
//			newMes.readornot=false;
//			
//			session.save(newMes);
//			tx.commit();
//			session.close();
//			log="发送成功";
//			return SUCCESS;
//		}
//		catch(Exception e)
//		{
//			log="发送失败";
//			return ERROR;
//		}
//	}
//	
//	//获取未读信息
//	public String getNewMessage() throws Exception{
//		try{
//			Session session = model.Util.sessionFactory.openSession();
//			String hql="From Message m where readornot=false and to="+currentUser;
//			List<Message> meslist=new ArrayList<Message>(); 
//			meslist=session.createQuery(hql).list();
//			newMes= new ArrayList<MessageInfo>();
//			for(Message tem:meslist){
//				newMes.add(new MessageInfo(tem.from.username,tem.from.id,tem.to.username,tem.to.id,tem.content));
//			}
//			session.close();
//		}
//		catch(Exception e){
//			return ERROR;
//		}
//		HttpServletRequest request = ServletActionContext.getRequest();        
//        HttpServletResponse response = ServletActionContext.getResponse();
//        messageListHtml = util.Util.getJspOutput("/jsp/base/widgets/message_list.jsp", request, response);
//		return SUCCESS;
//	}
//	
//	//聊天框获取历史信息
//	public String getMessage() throws Exception{
//		try{
//			Session session = model.Util.sessionFactory.openSession();
//			String hql="From Message m where m.from.id="+another+" or m.to.id="+another+" order by m.id";
//			historyMes=new ArrayList<Message>();
//			historyMes=session.createQuery(hql).list();
//			session.close();
//		}
//		catch(Exception e){
//			return ERROR;
//		}
//		HttpServletRequest request = ServletActionContext.getRequest();        
//        HttpServletResponse response = ServletActionContext.getResponse();
//        historyMesHtml = util.Util.getJspOutput("/jsp/base/widgets/history_message.jsp", request, response);
//        System.out.println(historyMesHtml);
//		return SUCCESS;
//	}
//}
//
//
//
//
