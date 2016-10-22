package admin.batchimport;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Classroom;
import util.FileUploadBaseAction;

public class ClassScheduleAction extends FileUploadBaseAction {

	// // 获取所有教室列表
	// public static List<Classroom> obtainClassroomList(Session s, int
	// selectTeachBuildingId)
	// {
	// return s.createCriteria(Classroom.class)
	// .add(Restrictions.eq("teachbuilding.id", selectTeachBuildingId))
	// .addOrder(Order.asc("classroom_num"))
	// .list();
	// }

	// 去除字符串中的括号部分（包括中文括号，和英文括号）以及之后的所有字符
	// 当第一次遇到左括号时就去掉左括号之后的所有字符
	public static String removeBracket(String classroomNumber) {
		StringBuilder res = new StringBuilder();
		for (char ch : classroomNumber.toCharArray()) {
			if (ch == '(' || ch == '（') {
				break;
			} else {
				res.append(ch);
			}
		}
		return res.toString().trim();
	}

	public int execute_SelectTeachBuilding = -1;
	public List<Classroom> executeClassroomList;

	public String execute() throws Exception {

		Session s = model.Util.sessionFactory.openSession();
		executeClassroomList = util.Util.obtainClassroomList(s, execute_SelectTeachBuilding);
		s.close();

		return SUCCESS;
	}

	public String upload_status = "";
	public String upload_classroomNumber;
	int upload_SelectTeachBuilding;

	public String upload() throws Exception {

		System.out.println("GGGGGGGGGGGG-----IOIOooooooo");

		if (file == null) {
			upload_status = "1没有接收到文件";
			return SUCCESS;
		}

		if (upload_SelectTeachBuilding == -1) {
			upload_status = "2请选择一个教学楼再继续上传";
			return SUCCESS;
		}

		Session s = model.Util.sessionFactory.openSession();
		String fileFileName_noBracket = removeBracket(fileFileName);
		for (Classroom classroom : util.Util.obtainClassroomList(s, upload_SelectTeachBuilding)) {
			String classroomNum_noBracket = removeBracket(classroom.getClassroom_num());
			System.out.println(fileFileName_noBracket + "---" + classroomNum_noBracket);
			if (fileFileName_noBracket.contains(classroomNum_noBracket)) {
				// String ClassroomScheduleFileName =
				// classroom.teachbuilding.build_name + "-" +
				// classroom.classroom_num;
				//
				// int lastDotIndex = fileFileName.lastIndexOf(".");
				// if(lastDotIndex != -1)
				// {
				// ClassroomScheduleFileName +=
				// fileFileName.substring(lastDotIndex);
				// }

				util.Util.saveClassroomScheduleFile(s, classroom, file, fileFileName);
				upload_classroomNumber = classroom.getClassroom_num();
				upload_status = "0";
				s.close();
				return SUCCESS;
			}
		}

		upload_status = "3未找到对应的教室";
		s.close();
		return SUCCESS;

	}

	public List<Classroom> getExecuteClassroomList() {
		return executeClassroomList;
	}

	public void setExecuteClassroomList(List<Classroom> executeClassroomList) {
		this.executeClassroomList = executeClassroomList;
	}

	public String getUpload_status() {
		return upload_status;
	}

	public void setUpload_status(String upload_status) {
		this.upload_status = upload_status;
	}

	public String getUpload_classroomNumber() {
		return upload_classroomNumber;
	}

	public void setUpload_classroomNumber(String upload_classroomNumber) {
		this.upload_classroomNumber = upload_classroomNumber;
	}

	public int getExecute_SelectTeachBuilding() {
		return execute_SelectTeachBuilding;
	}

	public void setExecute_SelectTeachBuilding(int execute_SelectTeachBuilding) {
		this.execute_SelectTeachBuilding = execute_SelectTeachBuilding;
	}

	public int getUpload_SelectTeachBuilding() {
		return upload_SelectTeachBuilding;
	}

	public void setUpload_SelectTeachBuilding(int upload_SelectTeachBuilding) {
		this.upload_SelectTeachBuilding = upload_SelectTeachBuilding;
	}

}
