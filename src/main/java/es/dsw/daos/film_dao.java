package es.dsw.daos;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Rol_film;
import es.dsw.models.film;
import java.sql.Date;

public class film_dao {
	private boolean flagError;
	private String msgError;

	public film_dao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	public ArrayList<film> getAll(){
		
		MySqlConnection objConection = new MySqlConnection();
		ArrayList<film> objTableFilm = new ArrayList<film>();
	
		try {
			  objConection.open();
					
			  if (!objConection.isError()){
				   ResultSet Result = objConection.executeSelect("SELECT * FROM repository_film");
				   while (Result.next()) {
					   film objfilm = new film();
					   objfilm.setIdFilm(Result.getInt("IDFILM_RF"));
					   objfilm.setTittle_RF(Result.getString("TITLE_RF"));
					   objfilm.setSynopsis_RF(Result.getString("SYNOPSIS_RF"));
					   
					   Genre_Dao objGenerosDao = new Genre_Dao();
					   objfilm.setGenero(objGenerosDao.getById(Result.getInt("IDGENRE_RF")));
					   
					   
					   objfilm.setIdGenre_RF(Result.getInt("IDGENRE_RF"));
					   objfilm.setDirector_RF(Result.getString("DIRECTOR_RF"));
					   objfilm.setReparto_RF(Result.getString("REPARTO_RF"));
					   objfilm.setAnio_RF(Result.getInt("ANIO_RF"));
					   objfilm.setDatePremiere_RF(Result.getDate("DATEPREMIERE_RF"));
					   
					   Producer_Dao objProducerDao = new Producer_Dao();					
					   objfilm.setDistribuidor(objProducerDao.getbyId(Result.getInt("IDPRODUCER_RF")));
					   
					   objfilm.setIdProducer_RF(Result.getInt("IDPRODUCER_RF"));
					   //objfilm.setIdCountry_RF(Result.getInt("IDCOUNTRY_RF"));
					   
					   Country_Dao objCountryDao = new Country_Dao();
					   objfilm.setPais(objCountryDao.getById(Result.getInt("IDCOUNTRY_RF")));
					   
					   objTableFilm.add(objfilm);
				   }
				
				} else {
						this.flagError = true;
						this.msgError = "Error en getAll. El objeto clsConectionMySql informa error al abrir conexión. +Info: " + objConection.msgError();
					   }
		    } catch (Exception ex) {
					this.flagError = true;
					this.msgError = "Error en getAll. +Info: " + ex.getMessage();
			} finally {
					objConection.close();
			}
		return objTableFilm;
	
	}

public void setPelicula(film objFilm) throws ParseException {
		
		MySqlConnection objConection = new MySqlConnection();
		int idPelicula = -1;
		try {
			objConection.open();
			if(!objConection.isError()){
				String sql = "";
				if(objFilm.getDatePremiere_RF() == null) {
					sql = "INSERT INTO repository_film "
							+ "(TITLE_RF,"
							+ "SYNOPSIS_RF,"
							+ "IDGENRE_RF,"
							+ "DIRECTOR_RF,"
							+ "REPARTO_RF,"
							+ "ANIO_RF,"
							+ "DATEPREMIERE_RF,"
							+ "IDPRODUCER_RF,"
							+ "IDCOUNTRY_RF,"
							+ "S_INSERTDATE_RF,"
							+ "S_UPDATEDATE_RF,"
							+ "S_IDUSER_CF)"
							+ "VALUES "
							+ "('"+objFilm.getTittle_RF()+"',"
							+ " '"+objFilm.getSynopsis_RF()+"',"
							+ " "+objFilm.getIdGenre_RF()+","
							+ " '"+objFilm.getDirector_RF()+"',"
							+ " '"+objFilm.getReparto_RF()+"',"
							+ " "+objFilm.getAnio_RF()+","
							+ " "+  objFilm.getDatePremiere_RF()+","
							+ " "+objFilm.getIdProducer_RF()+","
							+ " "+objFilm.getIdCountry_RF()+","
							+ " CURRENT_TIMESTAMP,"
							+ " CURRENT_TIMESTAMP,"
							+ " "+objFilm.getS_iduser_CF()+")";
				}else {
					
					sql = "INSERT INTO repository_film "
							+ "(TITLE_RF,"
							+ "SYNOPSIS_RF,"
							+ "IDGENRE_RF,"
							+ "DIRECTOR_RF,"
							+ "REPARTO_RF,"
							+ "ANIO_RF,"
							+ "DATEPREMIERE_RF,"
							+ "IDPRODUCER_RF,"
							+ "IDCOUNTRY_RF,"
							+ "S_INSERTDATE_RF,"
							+ "S_UPDATEDATE_RF,"
							+ "S_IDUSER_CF)"
							+ "VALUES "
							+ "('"+objFilm.getTittle_RF()+"',"
							+ " '"+objFilm.getSynopsis_RF()+"',"
							+ " "+objFilm.getIdGenre_RF()+","
							+ " '"+objFilm.getDirector_RF()+"',"
							+ " '"+objFilm.getReparto_RF()+"',"
							+ " "+objFilm.getAnio_RF()+","
							+ " '"+  objFilm.getDatePremiere_RF() +"',"
							+ " "+objFilm.getIdProducer_RF()+","
							+ " "+objFilm.getIdCountry_RF()+","
							+ " CURRENT_TIMESTAMP,"
							+ " CURRENT_TIMESTAMP,"
							+ " "+objFilm.getS_iduser_CF()+")";
				}
				
		
						objConection.executeInsert(sql);
			
						
			}else {
				 this.flagError = true;
				 this.msgError = "Error en setArticulo. El objeto clsConectionMySql informa error al abrir conexión. +Info: " + objConection.msgError();
			}
			
		}catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en setArticulo. +Info: " + ex.getMessage();
    	} finally {
			objConection.close();
    	}
	}
	
	public void deleteById(int idPelicula) {
		
		MySqlConnection objConection = new MySqlConnection();
		
		try {
			  objConection.open();
			  if (!objConection.isError()){
				  String SQL = "DELETE FROM session_film WHERE idfilm_ssf = " + idPelicula;
				  objConection.executeUpdateOrDelete(SQL);
				  SQL = "DELETE FROM repository_film WHERE idfilm_rf = " + idPelicula;
				  objConection.executeUpdateOrDelete(SQL);
			  } else {
				    this.flagError = true;
				    this.msgError = "Error en deleteAll. El objeto clsConectionMySql informa error al abrir conexión. +Info: " + objConection.msgError();
			   }
		    } catch (Exception ex) {
			       this.flagError = true;
			       this.msgError = "Error en deleteById. +Info: " + ex.getMessage();
  	    } finally {
			       objConection.close();
  	    }
		
		
	}	
	
	
	
}