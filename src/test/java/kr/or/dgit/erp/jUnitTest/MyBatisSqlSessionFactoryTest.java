package kr.or.dgit.erp.jUnitTest;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.erp.util.MybatisSqlSessionFactory;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyBatisSqlSessionFactoryTest {

private static SqlSessionFactory SqlSessionFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		SqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory(); 
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SqlSessionFactory=null;
	}

	@Test
	public void testMyBatisSqlSessionFactory() {
		Assert.assertNotNull(SqlSessionFactory.openSession());
	}

}
