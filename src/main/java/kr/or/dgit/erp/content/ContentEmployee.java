package kr.or.dgit.erp.content;

import java.awt.Component;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

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
import kr.or.dgit.erp.service.EmployeeService;
import kr.or.dgit.erp.service.TitleService;

public class ContentEmployee extends JPanel{
	private MyTextFieldPanel pNo;
	private MyTextFieldPanel pName;
	private MyComboBoxPanel<Title> pTitle;
	private MySpinnerPanel pSalary;
	private MyRadioPanel pGender;
	private MyComboBoxPanel<Department> pDept;
	private MyDateFieldPanel pDate;

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
		add(pDept);
		pDept.setLayout(new GridLayout(1, 0, 20, 0));	
		
		pDate = new MyDateFieldPanel();
		pDate.setMaskPattern("####-##-##", '#');
		pDate.setTfVAlue(String.format("%tF", new Date()));
		pDate.setTitle("입사일");
		add(pDate);
		pDate.setLayout(new GridLayout(1, 0, 20, 0));
		
		//clear();	// 입력 필드 초기화, 사번은 자동부여
	}
	
	public boolean isEmptyCheck(){
		boolean result = false;
		for(Component c : getComponents()){
			if (c instanceof MyTextFieldPanel){
				MyTextFieldPanel tfp = (MyTextFieldPanel)c;
				if(tfp.isEmptyCheck()){
					return true;
				}
			}
		}
		return result;
	}	
	
	public MyTextFieldPanel getpNo(){
		return pNo;
	}	
	public MyTextFieldPanel getpName(){
		return pName;
	}
	
	//직책을 벡터에 담아서 emp의 콤보박스에 내용 넣기
	public void setEmpTitle(Vector<Title> titles){
		pTitle.setAddItem(titles);
	}
	
	//부서를 콤보박스에 넣기
	public void setEmpDept(Vector<Department> depts){
		pDept.setAddItem(depts);
	}
	
	public Employee getObject(){		
		int eNo = Integer.parseInt(pNo.getTfValue().substring(4)); //E017001
		String eName = pName.getTfValue();
		Title title = (Title)pTitle.getSelectItem();
		int salary = (int)pSalary.getValue();
		boolean gender = pGender.getSelectedItem().equals("남")? true : false;
		Department dNo = (Department)pDept.getSelectItem();
		
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
		pNo.setTfValue(String.format("E017%03d",item.geteNo()));
		pName.setTfValue(item.geteName());
		pTitle.setSelectedItem(item.getTitle());
		pSalary.setValue(item.getSalary());
		pGender.setSelectedItem(item.isGender()+"");
		pDept.setSelectedItem(item.getdNo());
		pDate.setTfVAlue(String.format("%tF", item.getJoinDate()));
	}
	
	public void clear(){
		pNo.setTfValue(setCodeInit());	// 번호 포맷이 자동 부여
		pName.setTfValue("");
		pName.getTextField().requestFocus();
		pTitle.setSelectedIndex(0);
		pSalary.setValue(1500000);
		pGender.setSelectedIndex(0);
		pDept.setSelectedIndex(0);
		pDate.setTfVAlue(String.format("%tF", new Date()));
	}	
	
	public String setCodeInit(){
		String codeNumber = String.format(setCodeFormat(), EmployeeService.getInstance().selectCount()+1);
		return codeNumber;
	}
	public String setCodeFormat() {return "E017%03d";}
	
	
}
