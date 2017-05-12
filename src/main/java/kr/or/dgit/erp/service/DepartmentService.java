package kr.or.dgit.erp.service;

import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.erp.dao.DepartmentMapper;
import kr.or.dgit.erp.dao.DepartmentMapperImpl;
import kr.or.dgit.erp.dto.Department;
import kr.or.dgit.erp.util.MybatisSqlSessionFactory;

public class DepartmentService {
	
	private static final DepartmentService instance = new DepartmentService();
	private DepartmentService() {	}
	public static DepartmentService getInstance() {
		return instance;
	}
	
	// 부서 추가
	public int insertDepartment(Department department){		
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			DepartmentMapper departmentMapper = new DepartmentMapperImpl(sqlSession);
			int res = departmentMapper.insertDepartment(department);
			sqlSession.commit();
			JOptionPane.showMessageDialog(null, "부서가 추가되었습니다.");
			return res;
		}
	}
	
	
	
	
	
	
}	

