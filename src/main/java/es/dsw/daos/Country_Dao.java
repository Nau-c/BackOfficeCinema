package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Country;



public class Country_Dao {
	
	private boolean flagError;
	private String msgError;
	
	
	public Country_Dao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	
	public String getById(int idPais) {
		String Country = "";
		
		MySqlConnection objConection = new MySqlConnection();
		
		try {
			objConection.open();
			if(!objConection.isError()){
				ResultSet Result = objConection.executeSelect("SELECT * FROM country_film WHERE idcountry_cf = "+ idPais);
				
				while(Result.next()) {
					Country objCountry = new Country();
					objCountry.setCountry_CF(Result.getString("COUNTRY_CF"));
					
					Country = objCountry.getCountry_CF();
				}
			}else {
				this.flagError = true;
				this.msgError = "Error en getOnce. El objeto MySqlConnection informa error al abrir conexión. +Info: " + objConection.msgError();
			}
			
		} catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getById. +Info: " + ex.getMessage();
		} finally {
			objConection.close();
		}
		
		return Country;
	}
	
public ArrayList<Country> getAll(){
		
		MySqlConnection objConection = new MySqlConnection();
		ArrayList<Country> objTableCountry = new ArrayList<Country>();
		
		
		try {
			objConection.open();
			if(!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT * FROM country_film");
				while(Result.next()) {
					Country objCountry = new Country();
					
					objCountry.setIdcountry_CF(Result.getInt("IDCOUNTRY_CF"));
					objCountry.setCountry_CF(Result.getString("COUNTRY_CF"));
					
					objTableCountry.add(objCountry);
				}			
			}else {
				this.flagError = true;
				this.msgError = "Error en getAll. El objeto clsConectionMysql informa error al abrir conexión. +Info: " + objConection.msgError();
			}
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		}finally {
			objConection.close();
		}
		
		return objTableCountry;
	}
	
	
	
	
	
	
}
