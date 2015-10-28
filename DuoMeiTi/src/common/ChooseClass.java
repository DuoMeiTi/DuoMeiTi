package common;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import model.DutyTime;
import model.TeachBuilding;
import model.ChooseClassSwitch;

public class ChooseClass {
	
	public ChooseClass() {
		// TODO Auto-generated constructor stub
	}
	
	public static void insertDataToDutyTimeTable(){
		Session session = model.Util.sessionFactory.openSession();
		List<TeachBuilding> building_list=session.createCriteria(TeachBuilding.class).list();
		for(TeachBuilding tb:building_list){
			for(int i=1;i<=5;i++){
				for(int j=1;j<=7;j++){
					List<DutyTime> temp=session.createCriteria(DutyTime.class)
							.add(Restrictions.eq("teachBuilding.build_id",tb.build_id))
							.add(Restrictions.eq("time",i*10+j))
							.list();

					if(temp.size()==0){
						Transaction tx=session.beginTransaction();
						DutyTime a=new DutyTime();
						a.teachBuilding=tb;
						a.time=i*10+j;
						a.numberOfDuty=4;
						a.dutyLeft=a.numberOfDuty;
						session.save(a);
						tx.commit();
					}
				}
			}
		}
		session.close();
	}
	public static void insertDataToChooseClassSwitchTable(){
		Session session = model.Util.sessionFactory.openSession();
		List<ChooseClassSwitch> s=session.createCriteria(ChooseClassSwitch.class).list();
		if(s.size()==0){
			Transaction tx=session.beginTransaction();
			ChooseClassSwitch t = new ChooseClassSwitch();
			t.open=false;
			session.save(t);
			tx.commit();
		}
		session.close();
	}
	
}
