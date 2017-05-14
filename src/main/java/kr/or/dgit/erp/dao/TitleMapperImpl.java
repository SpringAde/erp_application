package kr.or.dgit.erp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.erp.dto.Title;

public class TitleMapperImpl implements TitleMapper {
	
	private String namespace="kr.or.dgit.erp.dao.TitleMapper.";
	private static final Log log = LogFactory.getLog(TitleMapperImpl.class);
	
	private SqlSession sqlSession;
	public TitleMapperImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int insertTitle(Title title) {
		log.debug("insertTitle()");
		return sqlSession.insert(namespace+"insertTitle", title);
	}

	@Override
	public int deleteTitle(Title title) {
		log.debug("deleteTitle()");
		return sqlSession.delete(namespace+"deleteTitle", title);
	}

	@Override
	public int updateTitle(Title title) {
		log.debug("updateTitle()");
		return sqlSession.update(namespace+"updateTitle", title);
	}

	@Override
	public List<Title> selectTitleAll() {
		log.debug("selectTitleAll()");
		return sqlSession.selectList(namespace+"selectTitleAll");
	}

	@Override
	public Title selectTitleByNo(Title title) {
		log.debug("selectTitleByNo()");
		return sqlSession.selectOne(namespace+"selectTitleByNo", title);
	}

	@Override
	public int selectCount() {
		log.debug("selectCount()");
		return sqlSession.selectOne(namespace+"selectCount");		
	}

	@Override
	public Title selectTitleByName(Title title) {
		log.debug("selectTitleByName()");
		return sqlSession.selectOne(namespace+"selectTitleByName");	
	}

}
