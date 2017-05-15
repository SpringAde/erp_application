package kr.or.dgit.erp.dto;

public class Title {
	private int tCode;
	private String tName;	

	public Title() {	}
		
	public Title(String tName) {
		this.tName = tName;
	}		

	public Title(int tCode) {
		this.tCode = tCode;
	}

	public Title(int tCode, String tName) {
		this.tCode = tCode;
		this.tName = tName;
	}

	public int gettCode() {
		return tCode;
	}

	public void settCode(int tCode) {
		this.tCode = tCode;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tName == null) ? 0 : tName.hashCode());
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
		Title other = (Title) obj;
		if (tName == null) {
			if (other.tName != null)
				return false;
		} else if (!tName.equals(other.tName))	//콤보박스는 콤보박스의 내용과 같은지 내부적으로 자동 equals호출한다.
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format(tName, tCode);
	}

	public Object[] toArray() {
		return new Object[] {String.format("T%03d", tCode), tName};
	}
}
