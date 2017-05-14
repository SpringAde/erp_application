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

	private DepartmentService() {
	}

	public static DepartmentService getInstance() {
		return instance;
	}

	// 추가
	public int insertDepartment(Department department) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.openSession()) {
			DepartmentMapper departmentMapper = new DepartmentMapperImpl(sqlSession);
			int res = departmentMapper.insertDepartment(department);
			sqlSession.commit();
			return res;
		}
	}

	// 삭제
	public int deleteDepartment(Department department) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.openSession()) {
			DepartmentMapper departmentMapper = new DepartmentMapperImpl(sqlSession);
			int res = departmentMapper.deleteDepartment(department);
			sqlSession.commit();
			return res;
		}
	}

	// 수정
	public int updateDepartment(Department department) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.openSession()) {
			DepartmentMapper departmentMapper = new DepartmentMapperImpl(sqlSession);
			int res = departmentMapper.updateDepartment(department);
			sqlSession.commit();
			return res;
		}
	}

	// 목록 출력
	public List<Department> selectDepartmentAll() {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.openSession()) {
			DepartmentMapper departmentMapper = new DepartmentMapperImpl(sqlSession);
			return departmentMapper.selectDepartmentAll();
		}
	}

	// 검색
	public Department selectDepartmentByNo(Department department) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.openSession()) {
			DepartmentMapper departmentMapper = new DepartmentMapperImpl(sqlSession);
			return departmentMapper.selectDepartmentByNo(department);
		}
	}

	// 목록 개수
	public int selectMaxNum() {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.openSession()) {
			DepartmentMapper departmentMapper = new DepartmentMapperImpl(sqlSession);
			int res = departmentMapper.selectMaxNum();
			sqlSession.commit();
			return res;
		}
	}
	
	// 부서명
	public Department selectDepartmentByName(Department department) {
		try (SqlSession sqlSession = MybatisSqlSessionFactory.openSession()) {
			DepartmentMapper departmentMapper = new DepartmentMapperImpl(sqlSession);
			return departmentMapper.selectDepartmentByName();
		}
	}
	
	
}
