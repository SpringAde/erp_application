package kr.or.dgit.erp.dto;

public class Department {
	private int dCode;	//부서번호
	private String dName;	//부서명
	private int floor;		//위치
		
	public Department() {	}

	public Department (int dCode) {
		this.dCode = dCode;
	}	

	public Department(String dName) {
		this.dName = dName;
	}

	public Department(int dCode, String dName, int floor) {
		this.dCode = dCode;
		this.dName = dName;
		this.floor = floor;
	}

	public int getdCode() {
		return dCode;
	}

	public void setdCode(int dCode) {
		this.dCode = dCode;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s층)", dName, floor);
	}
	
	public Object[] toArray() {
		return new Object[] {String.format("D%03d", dCode), dName, floor};
	}
}
