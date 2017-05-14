package kr.or.dgit.erp.dao;

import java.util.List;
import java.util.Map;

import kr.or.dgit.erp.dto.Employee;
import kr.or.dgit.erp.dto.Employee;

public interface EmployeeMapper {
	// Employee CRUD
	int insertEmployee(Employee employee);
	int deleteEmployee(Employee employee);
	int updateEmployee(Employee employee);
	
	List<Employee> selectEmployeeAll();		//전체 목록 출력
	Employee selectEmployeeByNo(Employee employee);
	
	int selectMaxNum();
}
