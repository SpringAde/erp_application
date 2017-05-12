package kr.or.dgit.erp.content;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridLayout;
import erp_application_frameWork.MyTextFieldPanel;

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
		return pDeptNo;
	}
	
	public MyTextFieldPanel getpName(){
		return pDeptName;
	}
	
	
}
