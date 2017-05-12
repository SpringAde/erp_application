package kr.or.dgit.erp.table;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.or.dgit.erp.dto.Employee;
import kr.or.dgit.erp.dto.Title;


public class TableEmployee extends JPanel {
	private JTable table;
	
	public TableEmployee() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		//목록이 화면보다 많을 경우, 스크롤바 생성
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();		
		scrollPane.setViewportView(table);
	}
	
	public void loadData(){
		table.setModel(new DefaultTableModel(getRowData(), getColumnData()));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//cell 하나만 선택
		CellAlign();
		CellWidth();
	}	

	public Object[][] getRowData() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] getColumnData() {
		return new String[] {"번호", "사원명", "직책", "급여", "성별", "부서", "입사일"};
	}
	
	public void CellAlign() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 4, 5);
		tableCellAlignment(SwingConstants.RIGHT, 3);	//급여 오른쪽 정렬
	}

	public void CellWidth() {
		tableSetWidth(100, 100, 80, 120, 80, 120, 130);	//셀 너비		
	}
	
	public void tableCellAlignment(int align, int...idx){
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();	// 각각의 셀에 적용
		dtcr.setHorizontalAlignment(align);					// 적용된 셀에게 가운데 정렬
		
		TableColumnModel tcm = table.getColumnModel();		
		for(int i=0; i<idx.length; i++){
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);  // 첫번째 컬럼에게 이것(가운데 정렬)을 적용
		}
	}
	
	public void tableSetWidth(int...width){
		TableColumnModel tcm = table.getColumnModel();
		
		for(int i=0; i<width.length; i++){
			tcm.getColumn(i).setPreferredWidth(width[i]); 
		}		
	}

	public JTable getTable() {		//view에서 참조
		return table;
	}

	public Employee getSelectedObject() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
