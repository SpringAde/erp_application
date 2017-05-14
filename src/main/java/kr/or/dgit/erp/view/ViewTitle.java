package kr.or.dgit.erp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import org.apache.ibatis.exceptions.PersistenceException;

import kr.or.dgit.erp.content.ContentTitle;
import kr.or.dgit.erp.dto.Title;
import kr.or.dgit.erp.service.TitleService;
import kr.or.dgit.erp.table.TableTitle;

public class ViewTitle extends JFrame implements ActionListener {
	private JPanel contentPane;	
	private ContentTitle title;
	private TableTitle titleTable;
	private JButton btnSave;
	private JButton btnReset;
	private JPopupMenu popupMenu; //마우스 우클릭 메뉴

	public ViewTitle() {
		setTitle("직책 관리");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(1100, 400, 300, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);
		
		//컨텐트 삽입
		title = new ContentTitle();
		title.setBorder(new EmptyBorder(10, 20, 10, 20));
		contentPane.add(title);
		
		//버튼 삽입
		JPanel pBtn = new JPanel();
		btnSave = new JButton("추가");
		btnSave.addActionListener(this);
		
		btnReset = new JButton("취소");
		btnReset.addActionListener(this);
		
		pBtn.add(btnSave);
		pBtn.add(btnReset);
		contentPane.add(pBtn);
		
		//테이블 삽입
		titleTable = new TableTitle();
		
		
/*		//테이블을 가져와서 마우스 어댑터 설정하기, 선택한 행을 입력창에 띄우기
		titleTable.getTable().addMouseListener(new MouseAdapter() {	
			@Override
			public void mouseClicked(MouseEvent e) {
				title.setObject(titleTable.getSelectedObject());	//선택한 행의 정보를 가져와서, 텍스트 필드에 setObject
				super.mouseClicked(e);
			}			
		});
*/		
		
		//마우스 우클릭 메뉴 : 수정 / 삭제
		titleTable.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3){
					popupMenu.show(titleTable.getTable(), e.getX(), e.getY());
					//show(활성화 될 컴포넌트, X좌표, Y좌표)
				}
				super.mouseClicked(e);
			}			
		});

		contentPane.add(titleTable);		
		createPopupMenu();
	}
	
	private void createPopupMenu() {
		popupMenu = new JPopupMenu();
		
		//수정 메뉴
		JMenuItem updateData = new JMenuItem("수정");
		updateData.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("수정")){
					Title data = titleTable.getSelectedObject();	//return selectTitleByNo
					if(data == null){
						JOptionPane.showMessageDialog(null, "데이터를 선택하세요");
						return;
					}
					title.setObject(data);					
					btnSave.setText("수정"); //추가 버튼이 수정으로 변경					
				}
			}
		});
		popupMenu.add(updateData);
		
		//삭제 메뉴
		JMenuItem deleteData = new JMenuItem("삭제");
		deleteData.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e)  {
				Title data = titleTable.getSelectedObject();	//return selectTitleByNo
				if(e.getActionCommand().equals("삭제")){					
					if(data == null){
						JOptionPane.showMessageDialog(null, "데이터를 선택하세요");
						return;
					}
					//데이터 삭제					
					if (JOptionPane.showConfirmDialog(null, "삭제 하시겠습니까?") == JOptionPane.YES_OPTION) {
						try{
							TitleService.getInstance().deleteTitle(data);
							title.clear();
							titleTable.loadData();						
							JOptionPane.showMessageDialog(null, "삭제되었습니다.");
						} catch (Exception ex){
							JOptionPane.showMessageDialog(null, "참조하는 데이터가 있습니다.");
						}						
					} else {
						JOptionPane.showMessageDialog(null, "취소하였습니다.");							
					}
				}			
			}			
		});
		popupMenu.add(deleteData);		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReset) {
			actionPerformedBtnReset(e);
		}
		if (e.getSource() == btnSave) {
			actionPerformedBtnSave(e);
		}
	}
	protected void actionPerformedBtnSave(ActionEvent e) {
		//추가, 수정 버튼 동작
		String message = null;
		if(isValidcheck()){
			JOptionPane.showMessageDialog(null, "공백이 존재합니다.");
			return;
		}
		
		if(e.getActionCommand().equals("추가")){
			TitleService.getInstance().insertTitle(title.getObject());
			message = "추가 되었습니다.";
		}else{
			TitleService.getInstance().updateTitle(title.getObject());
			message = "수정 되었습니다.";
			btnSave.setText("추가");
		}
		title.clear();
		titleTable.loadData();		
		JOptionPane.showMessageDialog(null, message);
		
	}
	protected void actionPerformedBtnReset(ActionEvent e) {
		title.clear();
		btnSave.setText("추가");
	}
	
	private boolean isValidcheck(){
		return title.isEmptyCheck();
	}
}
