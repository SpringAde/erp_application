package kr.or.dgit.erp.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.erp.content.ContentEmployee;
import kr.or.dgit.erp.dto.Employee;
import kr.or.dgit.erp.dto.Title;
import kr.or.dgit.erp.service.EmployeeService;
import kr.or.dgit.erp.service.TitleService;
import kr.or.dgit.erp.table.TableEmployee;

public class ViewEmployee extends JFrame {
	private JPanel contentPane;	
	private JButton btnSave;
	private JButton btnReset;
	private ContentEmployee emp;
	private TableEmployee empTable;
	private JPopupMenu popupMenu; //마우스 우클릭 메뉴

	public ViewEmployee() {
		setTitle("사원 관리");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 300, 700, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		//컨텐트 삽입
		emp = new ContentEmployee();
		emp.setBorder(new EmptyBorder(10, 100, 10, 100));
		contentPane.add(emp);

		//버튼 삽입
		JPanel pBtn = new JPanel();
		btnSave = new JButton("추가");
		btnReset = new JButton("취소");
		pBtn.add(btnSave);
		pBtn.add(btnReset);
		contentPane.add(pBtn);

		//테이블 삽입
		empTable = new TableEmployee();

		//마우스 우클릭 메뉴 : 수정 / 삭제
		empTable.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3){
					popupMenu.show(empTable.getTable(), e.getX(), e.getY());
					//show(활성화 될 컴포넌트, X좌표, Y좌표)
				}
				super.mouseClicked(e);
			}			
		});

		contentPane.add(empTable);		
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
					Employee data = empTable.getSelectedObject();	//return selectTitleByNo
					if(data == null){
						JOptionPane.showMessageDialog(null, "데이터를 선택하세요");
						return;
					}
					emp.setObject(data);					
					btnSave.setText("수정"); //추가 버튼이 수정으로 변경					
				}
			}
		});
		popupMenu.add(updateData);

		//삭제 메뉴
		JMenuItem deleteData = new JMenuItem("삭제");
		deleteData.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Employee data = empTable.getSelectedObject();	//return selectTitleByNo
				if(e.getActionCommand().equals("삭제")){					
					if(data == null){
						JOptionPane.showMessageDialog(null, "데이터를 선택하세요");
						return;
					}
					//데이터 삭제					
					if (JOptionPane.showConfirmDialog(null, "삭제 하시겠습니까?") == JOptionPane.YES_OPTION) {					

						EmployeeService.getInstance().deleteEmployee(data);
						emp.clear();
						empTable.loadData();
						JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "취소 하였습니다.");							
					}
				}			
			}			
		});
		popupMenu.add(deleteData);
	}
	
	
	
	
	
	
	
	
}
