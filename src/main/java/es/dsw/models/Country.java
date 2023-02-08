package es.dsw.models;

import java.util.Date;

public class Country {
	
	private int idcountry_CF;
	private String country_CF;
	private Date s_insertdate_CF;
	private Date s_updatedate_cf;
	private int s_iduser_CF;
	
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getIdcountry_CF() {
		return idcountry_CF;
	}


	public void setIdcountry_CF(int idcountry_CF) {
		this.idcountry_CF = idcountry_CF;
	}


	public String getCountry_CF() {
		return country_CF;
	}


	public void setCountry_CF(String country_CF) {
		this.country_CF = country_CF;
	}


	public Date getS_insertdate_CF() {
		return s_insertdate_CF;
	}


	public void setS_insertdate_CF(Date s_insertdate_CF) {
		this.s_insertdate_CF = s_insertdate_CF;
	}


	public Date getS_updatedate_cf() {
		return s_updatedate_cf;
	}


	public void setS_updatedate_cf(Date s_updatedate_cf) {
		this.s_updatedate_cf = s_updatedate_cf;
	}


	public int getS_iduser_CF() {
		return s_iduser_CF;
	}


	public void setS_iduser_CF(int s_iduser_CF) {
		this.s_iduser_CF = s_iduser_CF;
	}
	
	
	
	
}