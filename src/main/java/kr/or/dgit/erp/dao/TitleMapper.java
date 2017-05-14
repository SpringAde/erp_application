package kr.or.dgit.erp.dao;

import java.util.List;
import java.util.Map;

import kr.or.dgit.erp.dto.Employee;
import kr.or.dgit.erp.dto.Title;

public interface TitleMapper {
	int insertTitle(Title title);
	int deleteTitle(Title title);
	int updateTitle(Title title);
	
	List<Title> selectTitleAll();		//전체 목록 출력
	Title selectTitleByNo(Title title);
	
	int selectMaxNum();
	
	//Employee에서 보여질 직책
	Title selectTitleByName(Title title);
	
}
