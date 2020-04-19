package customer_details_tables;

public class customerBean {
	String NAME;
	String MOB;
	String CITY;
	String ADDR;
	public customerBean()
	{
		
	}
	public customerBean(String nAME, String mOB, String cITY, String aDDR) {
		super();
		NAME = nAME;
		MOB = mOB;
		CITY = cITY;
		ADDR = aDDR;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getMOB() {
		return MOB;
	}
	public void setMOB(String mOB) {
		MOB = mOB;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getADDR() {
		return ADDR;
	}
	public void setADDR(String aDDR) {
		ADDR = aDDR;
	}

}
