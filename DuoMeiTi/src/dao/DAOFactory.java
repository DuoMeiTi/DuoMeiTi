package dao;

import java.util.concurrent.ConcurrentHashMap;

import model.CheckInRecord;
import model.StudentProfile;
import model.User;

public class DAOFactory {
	private static final ConcurrentHashMap<Class,Class<? extends BaseDaoHibernate>> factorymap = new ConcurrentHashMap<Class,Class<? extends BaseDaoHibernate>>();
	static{
		factorymap.put(StudentProfile.class, StudentProfileDao.class);
		factorymap.put(User.class,UserDao.class);
//		factorymap.put(CheckInRecord.class, CheckInRecordDao.class);
	}
	public static BaseDao getDao(Class modelClass) throws InstantiationException, IllegalAccessException
	{
		Class<? extends BaseDaoHibernate> bdclass = factorymap.get(modelClass);
		BaseDao bd = bdclass.newInstance();
		return bd;
	}
}
