package kr.or.dgit.erp.dao;

import java.util.List;
import java.util.Map;

import kr.or.dgit.erp.dto.Department;
import kr.or.dgit.erp.dto.Department;

public interface DepartmentMapper {

	int insertDepartment(Department department);
	int deleteDepartment(Department department);
	int updateDepartment(Department department);
	
	List<Department> selectDepartmentAll();		//전체 목록 출력
	Department selectDepartmentByNo(Department department);
	
	int selectCount();	// 출력된 목록 개수
	Department selectDepartmentByName();
}
