package model;

import org.hibernate.cfg.*;

import javax.persistence.*;






@Entity
public class StudentProfile {
	
    @OneToOne
    @JoinColumn
    public User user;
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
	public int id;

 
    @Column
    public int sex;    
    public static final String[] sexSelect = { 
    		"男",
    		"女",
    };
    
    @Column(length = 20)
    public String studentId;
    
    @Column(length = 100)
    public String profilePhotoPath;
    
    
    @Column(length = 50)
    public String idCard;
    
    @Column(length = 50)
    public String bankCard;
    
    @Column
    public int college;
    public static final String[] collegeSelect = { 
    		"数学科学学院",
    		"物理与光电工程学院",
    		"化工与环境生命学部",
    		"机械工程与材料能源学部",
    		"建设工程学部",
    		"建筑与艺术学院",
    		"运载工程与力学学部",    		
    		"电子信息与电气工程学部",
    		"外国语学院",
    		"人文与社会科学学部",
    		"管理与经济学部",
    		"创新实验学院",
    };
    
    @Column(length=20)
    public String phoneNumber;
    
    @Column
    public java.sql.Date entryTime;
    
    //负责教室
    @OneToOne
    @JoinColumn
    public Classroom classroom;
    

    
    @Column
    public int status;    
    public static final String[] statusSelect = { 
    		"在岗",
    		"离职",
    };

    
    
    @Column(length=1000)
    public String remark;
    
    
    
    
    
    
    
    
    
    
    

    


}
