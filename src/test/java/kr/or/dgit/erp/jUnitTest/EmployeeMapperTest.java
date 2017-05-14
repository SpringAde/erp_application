package kr.or.dgit.erp.jUnitTest;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
import kr.or.dgit.erp.service.TitleService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeMapperTest {	
	
	private static EmployeeService employeeService;
	
	@BeforeClass //테스트 클래스 실행 시 한번 만 실행
	public static void setUpBeforeClass() throws Exception {
		employeeService = EmployeeService.getInstance();
	}
	@AfterClass	//테스트 클래스 실행 시 한번 만 실행
	public static void tearDownAfterClass() throws Exception {
		employeeService = null;
	}
	
	@Test
	public void testAInsert() {		
		Employee employee = new Employee(17012, "황인영", new Title(4), 2500000, false, new Department(2), new Date());
		int res = employeeService.insertEmployee(employee);
		Assert.assertEquals(1, res);		// 예상값 1이 res와 같아야 테스트성공 (Updates: 1)
	}
	

	@Test
	public void testBUpdate() {
		Employee employee = new Employee(17012, "황인영", new Title(3), 5000000, true, new Department(2), new Date());
		int res = employeeService.updateEmployee(employee);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void testCDelete() {
		Employee employee = new Employee(17012);
		int res = employeeService.deleteEmployee(employee);
		Assert.assertEquals(1, res);
	}
	
	@Test
	public void testDSelectAll() {		
		List<Employee> employees = employeeService.selectEmployeeAll();
		Assert.assertNotNull(employees);
	}
	
	@Test
	public void testESelectOne() {
		Employee employee = new Employee(17011);		
		Assert.assertNotNull(employee);
	}

}
