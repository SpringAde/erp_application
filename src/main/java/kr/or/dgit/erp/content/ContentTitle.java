package kr.or.dgit.erp.content;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridLayout;
import erp_application_frameWork.MyTextFieldPanel;
import kr.or.dgit.erp.dto.Title;
import kr.or.dgit.erp.service.TitleService;

public class ContentTitle extends JPanel {
	private MyTextFieldPanel pTitleNo;
	private MyTextFieldPanel pTitleName;

	public ContentTitle() {
		setLayout(new GridLayout(0, 1, 0, 5));
		
		pTitleNo = new MyTextFieldPanel();
		pTitleNo.setTitle("번호");
		add(pTitleNo);
		pTitleNo.setLayout(new GridLayout(1, 0, 20, 0));
		pTitleNo.getTextField().setEditable(false);		// 텍스트 편집 불가능하게 하기
		
		pTitleName = new MyTextFieldPanel();
		pTitleName.setTitle("직책명");
		add(pTitleName);
		pTitleName.setLayout(new GridLayout(1, 0, 20, 0));
		
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
			
	public MyTextFieldPanel getpTitleNo() {
		return pTitleNo;
	}
	public void setpTitleNo(MyTextFieldPanel pTitleNo) {
		this.pTitleNo = pTitleNo;
	}

	public MyTextFieldPanel getpTitleName() {
		return pTitleName;
	}
	public void setpTitleName(MyTextFieldPanel pTitleName) {
		this.pTitleName = pTitleName;
	}

	public Title getObject(){		
		int tCode = Integer.parseInt(pTitleNo.getTfValue().substring(1));	//"T001"(DB에서는 1)
		String tName = pTitleName.getTfValue();
		return new Title(tCode, tName);
	}
	
	public void setObject(Title title){
		pTitleNo.setTfValue(String.format("T%03d",title.gettCode()));
		pTitleName.setTfValue(title.gettName());		
	}
	
	public void clear(){
		pTitleNo.setTfValue(setCodeInit());	// 번호 포맷이 자동 부여
		pTitleName.setTfValue("");
		pTitleName.getTextField().requestFocus();
	}	
	
	public String setCodeInit(){
		String codeNumber = String.format(setCodeFormat(), TitleService.getInstance().selectMaxNum()+1);
		return codeNumber;
	}
	public String setCodeFormat() {return "T%03d";}	
	
}
