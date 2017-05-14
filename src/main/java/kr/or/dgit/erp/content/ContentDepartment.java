package kr.or.dgit.erp.content;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JPanel;

import erp_application_frameWork.MyTextFieldPanel;
import kr.or.dgit.erp.dto.Department;
import kr.or.dgit.erp.service.DepartmentService;

public class ContentDepartment extends JPanel {
	private MyTextFieldPanel pDeptNo;
	private MyTextFieldPanel pDeptName;
	private MyTextFieldPanel pFloor;

	public ContentDepartment() {
		setLayout(new GridLayout(0, 1, 0, 5)); //rows, cols, hgap, vgap		
		pDeptNo = new MyTextFieldPanel();
		pDeptNo.setTitle("번호");
		add(pDeptNo);
		pDeptNo.setLayout(new GridLayout(1, 0, 20, 0));
		pDeptNo.getTextField().setEditable(false);		// 텍스트 편집 불가능하게 하기
		
		pDeptName = new MyTextFieldPanel();
		pDeptName.setTitle("부서명");
		add(pDeptName);
		pDeptName.setLayout(new GridLayout(1, 0, 20, 0));
		
		pFloor = new MyTextFieldPanel();
		pFloor.setTitle("위치");
		add(pFloor);
		pFloor.setLayout(new GridLayout(1, 0, 20, 0));	
		
		clear();	// 입력 필드 초기화, 사번은 자동부여
	}

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
	
	public MyTextFieldPanel getpNo(){		
		return pDeptNo;
	}
	
	public MyTextFieldPanel getpName(){
		return pDeptName;
	}
	
	
	public Department getObject(){		
		int dCode = Integer.parseInt(pDeptNo.getTfValue().substring(1));
		String dName = pDeptName.getTfValue();
		int floor = Integer.parseInt(pFloor.getTfValue());
		return new Department(dCode, dName, floor);
	}
	
	public void setObject(Department department){
		pDeptNo.setTfValue(String.format("D%03d",department.getdCode()));
		pDeptName.setTfValue(department.getdName());
		pFloor.setTfValue(String.valueOf(department.getFloor()));
	}
	
	public void clear(){
		pDeptNo.setTfValue(setCodeInit());	// 번호 포맷이 자동 부여
		pDeptName.setTfValue("");
		pFloor.setTfValue("");
		pDeptName.getTextField().requestFocus();
	}	
	
	public String setCodeInit(){
		String codeNumber = String.format(setCodeFormat(), DepartmentService.getInstance().selectMaxNum()+1);
		return codeNumber;
	}
	public String setCodeFormat() {return "D%03d";}
}
