package parking_detail_table;

public class parkingBean {
	
	String RID;
	String V_NO;
	String MOB;
	String ENTRYD;
	String ENTRYT;
	String EXITD;
	String EXITT;
	String SLOT;
	String FLOOR;
	String TYPE;
	
	public parkingBean()
	{}

	public String getRID() {
		return RID;
	}

	public void setRID(String rID) {
		RID = rID;
	}

	public String getV_NO() {
		return V_NO;
	}

	public void setV_NO(String v_NO) {
		V_NO = v_NO;
	}

	public String getMOB() {
		return MOB;
	}

	public void setMOB(String mOB) {
		MOB = mOB;
	}

	public String getENTRYD() {
		return ENTRYD;
	}

	public void setENTRYD(String eNTRYD) {
		ENTRYD = eNTRYD;
	}

	public String getENTRTT() {
		return ENTRYT;
	}

	public void setENTRTT(String eNTRTT) {
		ENTRYT = eNTRTT;
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

	public String getSLOT() {
		return SLOT;
	}

	public void setSLOT(String sLOT) {
		SLOT = sLOT;
	}

	public String getFLOOR() {
		return FLOOR;
	}

	public void setFLOOR(String fLOOR) {
		FLOOR = fLOOR;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public parkingBean(String rID, String v_NO, String mOB, String eNTRYD, String eNTRTT, String eXITD, String eXITT,
			String sLOT, String fLOOR, String tYPE) {
		super();
		RID = rID;
		V_NO = v_NO;
		MOB = mOB;
		ENTRYD = eNTRYD;
		ENTRYT = eNTRTT;
		EXITD = eXITD;
		EXITT = eXITT;
		SLOT = sLOT;
		FLOOR = fLOOR;
		TYPE = tYPE;
	}
	
	
	

}
