package vehicle_entry_table;

public class vehicleBean {
	String MOB;
	String V_NO;
	String RID;
	String TYPE;
	String ENTRYD;
	String ENTRYT;
	String EXITD;
	String EXITT;
	
	
	public vehicleBean()
	{}
	public vehicleBean(  String mOB,String rID,String v_NO, String tYPE, String eNTRYD, String eNTRYT, String eXITD,
			String eXITT) {
		super();
		V_NO = v_NO;
		RID = rID;
		MOB = mOB;
		TYPE = tYPE;
		ENTRYD = eNTRYD;
		ENTRYT = eNTRYT;
		EXITD = eXITD;
		EXITT = eXITT;
	}
	public String getV_NO() {
		return V_NO;
	}
	public void setV_NO(String v_NO) {
		V_NO = v_NO;
	}
	public String getRID() {
		return RID;
	}
	public void setRID(String rID) {
		RID = rID;
	}
	public String getMOB() {
		return MOB;
	}
	public void setMOB(String mOB) {
		MOB = mOB;
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
