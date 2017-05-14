package kr.or.dgit.erp.appMain;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.erp.service.TitleService;
import kr.or.dgit.erp.view.ViewDepartment;
import kr.or.dgit.erp.view.ViewEmployee;
import kr.or.dgit.erp.view.ViewTitle;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ERP_Application extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JButton btnEmp;
	private JButton btnDept;
	private JButton btnTitle;
	private ViewEmployee emp;
	private ViewDepartment dept;
	private ViewTitle title;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ERP_Application erpApp = new ERP_Application();
					erpApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ERP_Application() {
		setTitle("대구아이티ERP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 20, 0));
		
		btnEmp = new JButton("사원관리");
		btnEmp.addActionListener(this);
		contentPane.add(btnEmp);
		
		btnDept = new JButton("부서관리");
		btnDept.addActionListener(this);
		contentPane.add(btnDept);
		
		btnTitle = new JButton("직책관리");
		btnTitle.addActionListener(this);
		contentPane.add(btnTitle);
		
		emp = new ViewEmployee();
		dept = new ViewDepartment();
		title = new ViewTitle();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTitle) {
			actionPerformedBtnTitle(e);
		}
		if (e.getSource() == btnDept) {
			actionPerformedBtnDept(e);
		}
		if (e.getSource() == btnEmp) {
			actionPerformedBtnEmp(e);
		}
	}
	protected void actionPerformedBtnEmp(ActionEvent e) {
		if(emp != null){			
			emp.setVisible(true);
		}		
	}
	protected void actionPerformedBtnDept(ActionEvent e) {
		if(dept != null){			
			dept.setVisible(true);
		}		
	}
	protected void actionPerformedBtnTitle(ActionEvent e) {
		if(title != null){				
			title.setVisible(true);
		}		
	}
}
