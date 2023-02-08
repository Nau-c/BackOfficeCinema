package es.dsw.models;

import java.util.ArrayList;

public class User_film {
	
	private int IDUSER_USF;
	private String USERNAME_USF;
	private String PASSWORD_USF;
	private String NAME_USF;
	private String FIRSTSURNAME_USF;
	private ArrayList <String> ROL;
	private String EMAIL_USF;
	
	
	
	
	public User_film(ArrayList<String> rOL) {
		super();
		ROL = rOL;
	}
	
	
	public User_film() {
	}


	public int getIDUSER_USF() {
		return IDUSER_USF;
	}
	public void setIDUSER_USF(int iDUSER_USF) {
		IDUSER_USF = iDUSER_USF;
	}
	public String getUSERNAME_USF() {
		return USERNAME_USF;
	}
	public void setUSERNAME_USF(String uSERNAME_USF) {
		USERNAME_USF = uSERNAME_USF;
	}
	public String getPASSWORD_USF() {
		return PASSWORD_USF;
	}
	public void setPASSWORD_USF(String pASSWORD_USF) {
		PASSWORD_USF = pASSWORD_USF;
	}
	public String getNAME_USF() {
		return NAME_USF;
	}
	public void setNAME_USF(String nAME_USF) {
		NAME_USF = nAME_USF;
	}
	public String getFIRSTSURNAME_USF() {
		return FIRSTSURNAME_USF;
	}
	public void setFIRSTSURNAME_USF(String fIRSTSURNAME_USF) {
		FIRSTSURNAME_USF = fIRSTSURNAME_USF;
	}
	public ArrayList<String> getROL() {
		return ROL;
	}
	public void setROL(ArrayList<String> rOL) {
		ROL = rOL;
	}
	public String getEMAIL_USF() {
		return EMAIL_USF;
	}
	public void setEMAIL_USF(String eMAIL_USF) {
		EMAIL_USF = eMAIL_USF;
	}
	
	
	
	
	

	

	
}
