package income_details;

public class incomeBean {

	String V_NO;
	String TYPE;
	String ENTRYD;
	String ENTRYT;
	String EXITD;
	String EXITT;
	String CHARGES;
	public incomeBean()
	{
	}
	
	public incomeBean(String v_NO, String tYPE, String eNTRYD, String eNTRYT, String eXITD, String eXITT,
			String cHARGES) {
		super();
		V_NO = v_NO;
		TYPE = tYPE;
		ENTRYD = eNTRYD;
		ENTRYT = eNTRYT;
		EXITD = eXITD;
		EXITT = eXITT;
		CHARGES = cHARGES;
	}

	public String getCHARGES() {
		return CHARGES;
	}

	public void setCHARGES(String cHARGES) {
		CHARGES = cHARGES;
	}

	public String getV_NO() {
		return V_NO;
	}
	public void setV_NO(String v_NO) {
		V_NO = v_NO;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getENTRYD() {
		return ENTRYD;
	}
	public void setENTRYD(String eNTRYD) {
		ENTRYD = eNTRYD;
	}
	public String getENTRYT() {
		return ENTRYT;
	}
	public void setENTRYT(String eNTRYT) {
		ENTRYT = eNTRYT;
	}
	public String getEXITD() {
		return EXITD;
	}
	public void setEXITD(String eXITD) {
		EXITD = eXITD;
	}
	public String getEXITT() {
		return EXITT;
	}
	public void setEXITT(String eXITT) {
		EXITT = eXITT;
	}
	
}
