package homepage;

import java.util.Collections;
import java.util.List;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import com.mysql.fabric.xmlrpc.base.Data;
import com.opensymphony.xwork2.ActionSupport;

//import model.EgFilePathSave;
import util.FileUploadBaseAction;

import model.StudentProfile;
import model.User;
import util.Const;

public class StudentAction extends FileUploadBaseAction {

	public String studentRegister() throws Exception {

		Session s = model.Util.sessionFactory.openSession();

		// StudentProfile test =
		// (StudentProfile)s.createCriteria(model.StudentProfile.class).createAlias("user",
		// "user")
		// .add(Restrictions.eq("user.id", 11)).uniqueResult();
		//
		// System.out.println(test.id);
		// System.out.println(test.studentId);
		// System.out.println(test.user.id);
		// System.out.println(test.user.fullName);
		// System.out.println(test.user.username);
		//

		s.close();
		return ActionSupport.SUCCESS;
	}

	// /*
	// * 判断学号是否重复，如果重复，返回true
	// */

	public static boolean isRepeat(Session session, String searchID) {
		Criteria q2 = session.createCriteria(StudentProfile.class).add(Restrictions.eq("studentId", searchID));
		List u2 = q2.list();

		if (u2.isEmpty()) {
			return false;
		}
		return true;
	}

	StudentProfile save_newStudentProfile;
	String save_passwordAgain;
	String save_status;

	public String studentRegisterSave() throws Exception {

		String[] studentProfileList = { "studentId", "idCard", "college", "bankCard", "user.username", "user.password",
				"user.fullName", "user.sex", "user.phoneNumber" };

		if (!save_passwordAgain.equals(save_newStudentProfile.user.password)) {
			save_status = "密码不一致";
			return SUCCESS;
		}
		Session session = model.Util.sessionFactory.openSession();
		List<String> errors = util.Util.validate(session, save_newStudentProfile);
		if (!errors.isEmpty()) {
			save_status = errors.get(0);
			session.close();
			return SUCCESS;
		}

		StudentProfile newStudentProfile = new StudentProfile();
		newStudentProfile.user = new User();
		util.Util.copyWithSpecificFields(save_newStudentProfile, newStudentProfile, studentProfileList);

		if (file != null)
			util.Util.setUserProfilePhoto(newStudentProfile.user, file, fileFileName);

		newStudentProfile.entryTime = new java.sql.Date(new java.util.Date().getTime());

		try {
			session.beginTransaction();
			session.save(newStudentProfile.user);// 因为user是外键，所以commit
													// StudentProfile之前需要先save
													// user；
			session.save(newStudentProfile);
			session.getTransaction().commit();
			save_status = "";
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			save_status = "数据库错误";
		}

		session.close();
		return ActionSupport.SUCCESS;

	}

	public StudentProfile getSave_newStudentProfile() {
		return save_newStudentProfile;
	}

	public void setSave_newStudentProfile(StudentProfile save_newStudentProfile) {
		this.save_newStudentProfile = save_newStudentProfile;
	}

	public String getSave_passwordAgain() {
		return save_passwordAgain;
	}

	public void setSave_passwordAgain(String save_passwordAgain) {
		this.save_passwordAgain = save_passwordAgain;
	}

	public String getSave_status() {
		return save_status;
	}

	public void setSave_status(String save_status) {
		this.save_status = save_status;
	}

}
