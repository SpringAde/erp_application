package kr.or.dgit.erp.content;

import java.awt.Component;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_application_frameWork.MyComboBoxPanel;
import erp_application_frameWork.MyDateFieldPanel;
import erp_application_frameWork.MyRadioPanel;
import erp_application_frameWork.MySpinnerPanel;
import erp_application_frameWork.MyTextFieldPanel;
import kr.or.dgit.erp.dto.Department;
import kr.or.dgit.erp.dto.Employee;
import kr.or.dgit.erp.dto.Title;
import kr.or.dgit.erp.service.DepartmentService;
import kr.or.dgit.erp.service.EmployeeService;
import kr.or.dgit.erp.service.TitleService;
import kr.or.dgit.erp.table.TableEmployee;

public class ContentEmployee extends JPanel{
	private MyTextFieldPanel pNo;
	private MyTextFieldPanel pName;
	private MyComboBoxPanel<Title> pTitle;
	private MySpinnerPanel pSalary;
	private MyRadioPanel pGender;
	private MyComboBoxPanel<Department> pDept;
	private MyDateFieldPanel pDate;
	private List<Title> titleData;
	private List<Department> deptData;
	
	private TableEmployee empTable; 	

	public ContentEmployee() {
		setLayout(new GridLayout(0, 1, 0, 5));		
		
		pNo = new MyTextFieldPanel();
		pNo.setTitle("번호");
		add(pNo);
		pNo.setLayout(new GridLayout(1, 0, 20, 0));
		pNo.getTextField().setEditable(false);		// 텍스트 편집 불가능하게 하기
		
		pName = new MyTextFieldPanel();
		pName.setTitle("사원명");
		add(pName);
		pName.setLayout(new GridLayout(1, 0, 20, 0));
		
		pTitle = new MyComboBoxPanel<>();
		pTitle.setTitle("직책");		
		setTitleList();		
		add(pTitle);
		pTitle.setLayout(new GridLayout(1, 0, 20, 0));
		
		pSalary = new MySpinnerPanel();
		pSalary.setValues(1500000, 1000000, 5000000, 100000);
		pSalary.setTitle("급여");
		add(pSalary);
		pSalary.setLayout(new GridLayout(1, 0, 20, 0));
		
		pGender = new MyRadioPanel();
		pGender.getpValue().setBorder(new EmptyBorder(0, 40, 0, 0));
		GridLayout gridLayout = (GridLayout) pGender.getpValue().getLayout();
		pGender.setTitle("성별");
		pGender.setAddItems("남","여");
		pGender.setSelectedItem("남");
		add(pGender);
		pGender.setLayout(new GridLayout(1, 0, 20, 0));
		
		pDept = new MyComboBoxPanel<>();
		pDept.setTitle("부서");	
		
		setDeptList();add(pDept);
		pDept.setLayout(new GridLayout(1, 0, 20, 0));	
		
		pDate = new MyDateFieldPanel();
		pDate.setMaskPattern("####-##-##", '_');
		pDate.setTfVAlue(String.format("%tF", new Date()));
		pDate.setTitle("입사일");
		add(pDate);
		pDate.setLayout(new GridLayout(1, 0, 20, 0));
		
		clear();	// 입력 필드 초기화, 사번은 자동부여
	}
		
	public MyTextFieldPanel getpNo(){
		return pNo;
	}	
	public MyTextFieldPanel getpName(){
		return pName;
	}
	
	//직책클래스의 모든 항목을 가져오기	
	public void setTitleList() {
		titleData = TitleService.getInstance().selectTitleAll();
		pTitle.setAddItem(titleData);	
	}

	//부서클래스의 모든 항목을 가져오기
	public void setDeptList() {
		deptData = DepartmentService.getInstance().selectDepartmentAll();
		pDept.setAddItem(deptData);		
	}

	//공백체크
	public boolean isEmptyCheck(){
		boolean result = false;
		for(Component c : getComponents()){
			if (c instanceof MyTextFieldPanel){
				MyTextFieldPanel tfp = (MyTextFieldPanel)c;
				if(tfp.isEmptyCheck()){		//텍스트 필드 패널에 있는 isEmptyCheck
					return true;
				}
			}
		}
		return result;
	}	
	
	
	
	public Employee getObject(){		
		int eNo = Integer.parseInt(pNo.getTfValue().substring(1)); //E017001 -> 017001
		String eName = pName.getTfValue();		
		int salary = (int)pSalary.getValue();
		boolean gender = pGender.getSelectedItem().equals("남")? true : false;	//1:0
		Title title = TitleService.getInstance().selectTitleByName(new Title((String)pTitle.getSelectItem()));
		String[] dept= (String[])pDept.getSelectItem().toString().trim().split(("("));	//개발(9층)
		Department dNo= DepartmentService.getInstance().selectDepartmentByName(new Department(dept[0]));
				
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date joinDate = null;
			try{
				joinDate = dateFormat.parse(pDate.getTfValue());
			}catch (ParseException e){
				e.printStackTrace();
			}
						// int, String, title, int, boolean, department, Date
		return new Employee(eNo, eName, title, salary, gender, dNo, joinDate);		
	}
	
	public void setObject(Employee item){
		pNo.setTfValue(String.format("E%06d",item.geteNo()));
		pName.setTfValue(item.geteName());
		pTitle.setSelectedItem(item.getTitle());
		pSalary.setValue(item.getSalary());
		if(item.isGender()){
			pGender.setSelectedIndex(0);//남
		}else{
			pGender.setSelectedIndex(1);//여
		}
		
		pDept.setSelectedItem(item.getdNo());
		pDate.setTfVAlue(String.format("%tF", item.getJoinDate()));
	}
	
	public void clear(){
		pNo.setTfValue(setCodeInit());	// 번호 포맷이 자동 부여		
		pName.setTfValue("");
		pName.getTextField().requestFocus();
		pSalary.setValue(1500000);
		pGender.setSelectedIndex(0);
		pTitle.setSelectedIndex(4);
		pDept.setSelectedIndex(0);
		pDate.setTfVAlue(String.format("%tF", new Date()));
	}	
	
	public String setCodeInit(){
		String codeNumber = String.format(setCodeFormat(), EmployeeService.getInstance().selectMaxNum()+1);
		if(codeNumber == null){
			String.format(setCodeFormat(), 17001);
		}
		return codeNumber;
	}
	public String setCodeFormat() {return "E%06d";}	
	
}
