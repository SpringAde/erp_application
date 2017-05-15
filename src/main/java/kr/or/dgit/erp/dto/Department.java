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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dName == null) ? 0 : dName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (dName == null) {
			if (other.dName != null)
				return false;
		} else if (!dName.equals(other.dName))	//콤보박스는 콤보박스의 내용과 같은지 내부적으로 자동 equals호출한다.
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s(%s층)", dName, floor);
	}
	
	public Object[] toArray() {
		return new Object[] {String.format("D%03d", dCode), dName, floor};
	}
}
