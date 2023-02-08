package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Genre;



public class Genre_Dao {
	
	private boolean flagError;
	private String msgError;
	
	
	public Genre_Dao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	public String getById(int idGenero) {
		String Genre = "";
	
MySqlConnection objConection = new MySqlConnection();
		
		try {
			objConection.open();
			if(!objConection.isError()){
				ResultSet Result = objConection.executeSelect("SELECT * FROM genre_film WHERE idgenre_gf = "+ idGenero);
				
				while(Result.next()) {
					Genre objGenere = new Genre();
					objGenere.setGenre_GF(Result.getString("genre_GF"));
					
					Genre = objGenere.getGenre_GF();
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
		
		return Genre;
	}

public ArrayList<Genre> getAll(){
		
		MySqlConnection objConection = new MySqlConnection();
		ArrayList<Genre> objTablaGenre = new ArrayList<Genre>();
		
		
		try {
			objConection.open();
			if(!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT * FROM genre_film");
				while(Result.next()) {
					Genre objGenre = new Genre();
					
					objGenre.setIdgenre_GF(Result.getInt("IDGENRE_GF"));
					objGenre.setGenre_GF(Result.getString("GENRE_GF"));
					
					objTablaGenre.add(objGenre);
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
		
		return objTablaGenre;
	}
	
	
	
	}
	
	
	
