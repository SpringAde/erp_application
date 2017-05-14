package kr.or.dgit.erp.service;

import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.erp.dao.TitleMapper;
import kr.or.dgit.erp.dao.TitleMapperImpl;
import kr.or.dgit.erp.dto.Title;
import kr.or.dgit.erp.util.MybatisSqlSessionFactory;

public class TitleService {
	
	private static final TitleService instance = new TitleService();
	private TitleService() {	}
	public static TitleService getInstance() {	return instance;	}
	
	//추가
	public int insertTitle(Title title){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			TitleMapper titleMapper = new TitleMapperImpl(sqlSession);
			int res = titleMapper.insertTitle(title);
			sqlSession.commit();			
			return res;
		}
	}
	
	//삭제
	public int deleteTitle(Title title){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			TitleMapper titleMapper = new TitleMapperImpl(sqlSession);
			int res = titleMapper.deleteTitle(title);
			sqlSession.commit();			
			return res;
		}
	}
	
	//수정
	public int updateTitle(Title title){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			TitleMapper titleMapper = new TitleMapperImpl(sqlSession);
			int res = titleMapper.updateTitle(title);
			sqlSession.commit();			
			return res;
		}
	}
	
	//목록 출력
	public List<Title> selectTitleAll(){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			TitleMapper titleMapper = new TitleMapperImpl(sqlSession);
			return titleMapper.selectTitleAll();
		}
	}
	
	//검색
	public Title selectTitleByNo(Title title){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			TitleMapper titleMapper = new TitleMapperImpl(sqlSession);
			return titleMapper.selectTitleByNo(title);
		}
	}
	
	//목록 개수
	public int selectCount(){
		try(SqlSession sqlSession = MybatisSqlSessionFactory.openSession()){
			TitleMapper titleMapper = new TitleMapperImpl(sqlSession);
			int res = titleMapper.selectCount();
			sqlSession.commit();			
			return res;
		}
	}
}
