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

import org.apache.ibatis.exceptions.PersistenceException;

import kr.or.dgit.erp.content.ContentDepartment;
import kr.or.dgit.erp.dto.Department;
import kr.or.dgit.erp.dto.Title;
import kr.or.dgit.erp.service.DepartmentService;
import kr.or.dgit.erp.service.TitleService;
import kr.or.dgit.erp.table.TableDepartment;

public class ViewDepartment extends JFrame implements ActionListener {
	private JPanel contentPane;
	private ContentDepartment dept;
	private TableDepartment deptTable;
	private JButton btnSave;
	private JButton btnReset;
	private JPopupMenu popupMenu; // 마우스 우클릭 메뉴

	public ViewDepartment() {
		setTitle("부서 관리");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(800, 400, 300, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);

		// 컨텐트 삽입
		dept = new ContentDepartment();
		dept.setBorder(new EmptyBorder(10, 20, 10, 20));
		contentPane.add(dept);

		// 버튼 삽입
		JPanel pBtn = new JPanel();
		btnSave = new JButton("추가");
		btnSave.addActionListener(this);
		btnReset = new JButton("취소");
		btnReset.addActionListener(this);

		pBtn.add(btnSave);
		pBtn.add(btnReset);
		contentPane.add(pBtn);

		// 테이블 삽입
		deptTable = new TableDepartment();

		// 마우스 우클릭 메뉴 : 수정 / 삭제
		deptTable.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					popupMenu.show(deptTable.getTable(), e.getX(), e.getY());
					// show(활성화 될 컴포넌트, X좌표, Y좌표)
				}
				super.mouseClicked(e);
			}
		});

		contentPane.add(deptTable);
		createPopupMenu();
	}

	private void createPopupMenu() {
		popupMenu = new JPopupMenu();

		// 수정 메뉴
		JMenuItem updateData = new JMenuItem("수정");
		updateData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("수정")) {
					Department data = deptTable.getSelectedObject();
					if (data == null) {
						JOptionPane.showMessageDialog(null, "데이터를 선택하세요");
						return;
					}
					dept.setObject(data);
					btnSave.setText("수정"); // 추가 버튼이 수정으로 변경
				}
			}
		});
		popupMenu.add(updateData);

		// 삭제 메뉴
		JMenuItem deleteData = new JMenuItem("삭제");
		deleteData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Department data = deptTable.getSelectedObject();
				if (e.getActionCommand().equals("삭제")) {
					if (data == null) {
						JOptionPane.showMessageDialog(null, "데이터를 선택하세요");
						return;
					}
					// 데이터 삭제
					if (JOptionPane.showConfirmDialog(null, "삭제 하시겠습니까?") == JOptionPane.YES_OPTION) {
						DepartmentService.getInstance().deleteDepartment(data);
						dept.clear();
						deptTable.loadData();
						JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
					} else {
						JOptionPane.showMessageDialog(null, "취소 하였습니다.");
					}
				}
			}
		});
		popupMenu.add(deleteData);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReset) {
			actionPerformedBtnReset(e);
		}
		if (e.getSource() == btnSave) {
			actionPerformedBtnSave(e);
		}

	}

	private void actionPerformedBtnSave(ActionEvent e) {
		// 추가, 수정 버튼 동작
		String message = null;
		if (isValidcheck()) {
			JOptionPane.showMessageDialog(null, "공백이 존재합니다.");
			return;
		}

		if (e.getActionCommand().equals("추가")) {
			try{
				DepartmentService.getInstance().insertDepartment(dept.getObject());
			} catch (NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "숫자를 입력하세요.");
				dept.clear();
				return;
			}
			message = "추가 되었습니다.";
		} else {
			try{
				DepartmentService.getInstance().updateDepartment(dept.getObject());			
			} catch (NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "숫자를 입력하세요.");
				dept.clear();
				Department data = deptTable.getSelectedObject();
				dept.setObject(data);
				return;
			}
			message = "수정 되었습니다.";
			btnSave.setText("추가");
		}
		dept.clear();
		deptTable.loadData();
		JOptionPane.showMessageDialog(null, message);		
	}

	private void actionPerformedBtnReset(ActionEvent e) {
		dept.clear();
		btnSave.setText("추가");
	}

	private boolean isValidcheck() {
		return dept.isEmptyCheck();
	}

}
