package kr.or.dgit.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.erp.dto.Department;

public class DepartmentMapperImpl implements DepartmentMapper {
	
	private String namespace = "kr.or.dgit.erp.dao.DepartmentMapper.";
	private static final Log log = LogFactory.getLog(DepartmentMapper.class);
	private SqlSession sqlSession;
	
	public DepartmentMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertDepartment(Department department) {
		log.debug("insertDepartment()");
		return sqlSession.insert(namespace+"insertDepartment", department);
	}

	@Override
	public int updateDepartment(Department department) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteDepartment(Department department) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Department> selectDepartmentAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department selectDepartmentByNo(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
