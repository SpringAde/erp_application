package kr.or.dgit.erp.service;

import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.erp.dao.EmployeeMapper;
import kr.or.dgit.erp.dao.EmployeeMapperImpl;
import kr.or.dgit.erp.dao.TitleMapper;
import kr.or.dgit.erp.dao.TitleMapperImpl;
import kr.or.dgit.erp.dto.Employee;
import kr.or.dgit.erp.util.MybatisSqlSessionFactory;

public class EmployeeService {
	
	private static final EmployeeService instance = new EmployeeService();
	private EmployeeService() {	}
	public static EmployeeService getInstance() {	return instance;	}
	
	//사원추가
	public int insertEmployee(Employee employee){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			EmployeeMapper employeeMapper = new EmployeeMapperImpl(sqlSession);
			int res = employeeMapper.insertEmployee(employee);
			sqlSession.commit();
			return res;
		}
	}
	
	//사원삭제
	public int deleteEmployee(Employee employee){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			EmployeeMapper employeeMapper = new EmployeeMapperImpl(sqlSession);
			int res = employeeMapper.deleteEmployee(employee);
			sqlSession.commit();
			return res;
		}
	}
	
	//사원수정
	public int updateEmployee(Employee employee){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			EmployeeMapper employeeMapper = new EmployeeMapperImpl(sqlSession);
			int res = employeeMapper.updateEmployee(employee);
			sqlSession.commit();
			return res;
		}
	}
	
	//사원 목록 출력
	public List<Employee> selectEmployeeAll(){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			EmployeeMapper employeeMapper = new EmployeeMapperImpl(sqlSession);
			return employeeMapper.selectEmployeeAll();
		}
	}
	
	//사원검색
	public Employee selectEmployeeByNo(Employee employee){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			EmployeeMapper employeeMapper = new EmployeeMapperImpl(sqlSession);
			return employeeMapper.selectEmployeeByNo(employee);
		}
	}
	
	//목록 개수
	public int selectMaxNum() {
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			EmployeeMapper employeeMapper = new EmployeeMapperImpl(sqlSession);
			int res = employeeMapper.selectMaxNum();
			sqlSession.commit();
			return res;
		}
	}
	
}
