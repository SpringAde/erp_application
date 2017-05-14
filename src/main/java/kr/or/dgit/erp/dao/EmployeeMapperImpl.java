package kr.or.dgit.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;


import kr.or.dgit.erp.dto.Employee;

public class EmployeeMapperImpl implements EmployeeMapper {
	private String namespace="kr.or.dgit.erp.dao.EmployeeMapper.";
	private static final Log log = LogFactory.getLog(EmployeeMapperImpl.class);
	
	private SqlSession sqlSession;
	public EmployeeMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertEmployee(Employee employee) {
		log.debug("insertEmployee()");
		return sqlSession.insert(namespace+"insertEmployee", employee);
	}

	@Override
	public int deleteEmployee(Employee employee) {
		log.debug("deleteEmployee()");
		return sqlSession.delete(namespace+"deleteEmployee", employee);
	}

	@Override
	public int updateEmployee(Employee employee) {
		log.debug("updateEmployee()");
		return sqlSession.update(namespace+"updateEmployee", employee);
	}

	@Override
	public List<Employee> selectEmployeeAll() {
		log.debug("selectEmployeeAll()");
		return sqlSession.selectList(namespace+"selectEmployeeAll");
	}

	@Override
	public Employee selectEmployeeByNo(Employee employee) {
		log.debug("selectEmployeeByNo()");
		return sqlSession.selectOne(namespace+"selectEmployeeByNo", employee);
	}

	@Override
	public int selectMaxNum() {
		log.debug("selectMaxNum()");
		return sqlSession.selectOne(namespace+"selectMaxNum");
	}

}
