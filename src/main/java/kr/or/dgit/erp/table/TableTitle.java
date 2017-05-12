package kr.or.dgit.erp.table;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import kr.or.dgit.erp.dto.Title;
import kr.or.dgit.erp.service.TitleService;

public class TableTitle extends JPanel {
	private JTable table;
	private List<Title> list;
	
	public TableTitle() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		//목록이 화면보다 많을 경우, 스크롤바 생성
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();		
		scrollPane.setViewportView(table);
		
		loadData();	// DB데이터를 Table에 넣기
	}
	
	public void loadData(){
		table.setModel(new DefaultTableModel(getRowData(), getColumnData()){
			@Override
			// 테이블 cell 내용 수정 불가
			public boolean isCellEditable(int row, int column) {
				return false;
			}			
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);	//cell 하나만 선택			
		
		CellAlign();	// cell 정렬 설정
		CellWidth();	// cell 너비 설정
	}	

	public Object[][] getRowData() {
		list = TitleService.getInstance().selectTitleAll();
		
		Object[][] datas = new Object[list.size()][];
		for(int i=0; i<datas.length; i++){
			datas[i] = list.get(i).toArray();
		}
		return datas;
	}
	
	public Object[] getColumnData() {
		return new String[] {"번호", "직책명"};
	}
	
	// 선택한 row의 해당 정보 가져오기
	public Title getSelectedObject(){
		int selectedIdx = table.getSelectedRow();	// 선택한 행의 index
		if(selectedIdx == -1) return null;
		
		String tCode = (String)table.getValueAt(selectedIdx, 0).toString().substring(1);
		return TitleService.getInstance().selectTitleByNo(new Title(Integer.parseInt(tCode)));
	}
	
	public void CellAlign() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1);
	}
	public void CellWidth() {
		tableSetWidth(100, 200);		
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

	public JTable getTable() {
		return table;
	}
	
}
