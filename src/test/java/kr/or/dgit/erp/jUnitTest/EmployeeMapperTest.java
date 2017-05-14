package kr.or.dgit.erp.jUnitTest;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.erp.dto.Department;
import kr.or.dgit.erp.dto.Employee;
import kr.or.dgit.erp.dto.Title;
import kr.or.dgit.erp.service.EmployeeService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeMapperTest {	
	
	private static EmployeeService employeeService;
	
	@BeforeClass //테스트 클래스 실행 시 한번 만 실행
	public static void setUpBeforeClass() throws Exception {
		employeeService = employeeService.getInstance();
	}
	@AfterClass	//테스트 클래스 실행 시 한번 만 실행
	public static void tearDownAfterClass() throws Exception {
		employeeService = null;
	}
	
/*	@Test
	public void testAInsert() {		
		Employee employee = new Employee(17012, "황인영", new Title(3), 25000000, true, new Department(2), new Date());
		int res = employeeService.insertEmployee(employee);
		Assert.assertEquals(1, res);		// 예상값 1이 res와 같아야 테스트성공 (Updates: 1)
	}*/
	
/*	@Test
	public void testBDelete() {
		fail("Not yet implemented");
	}*/
	
/*	@Test
	public void testCUpdate() {
		fail("Not yet implemented");
	}*/
	
/*	@Test
	public void testDSelectAll() {
		fail("Not yet implemented");
	}*/
	
/*	@Test
	public void testESelectOne() {
		fail("Not yet implemented");
	}*/

}
