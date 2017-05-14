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
	public int deleteDepartment(Department department) {
		log.debug("deleteDepartment()");
		return sqlSession.delete(namespace+"deleteDepartment", department);
	}
	
	@Override
	public int updateDepartment(Department department) {
		log.debug("updateDepartment()");
		return sqlSession.update(namespace+"updateDepartment", department);
	}	

	@Override
	public List<Department> selectDepartmentAll() {
		log.debug("selectDepartmentAll");
		return sqlSession.selectList(namespace+"selectDepartmentAll");
	}

	@Override
	public Department selectDepartmentByNo(Department department) {
		log.debug("selectDepartmentByNo()");
		return sqlSession.selectOne(namespace+"selectDepartmentByNo", department);
	}

	@Override
	public int selectCount() {
		log.debug("selectCount()");
		return sqlSession.selectOne(namespace+"selectCount");
	}

	

}
