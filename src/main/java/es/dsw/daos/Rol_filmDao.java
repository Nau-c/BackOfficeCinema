package es.dsw.daos;
import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Rol_film;




public class Rol_filmDao {
	private boolean flagError;
	private String msgError;

	public Rol_filmDao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	//Método que obtiene todos los artículos. Notar que devuelve un array list de objetos de tipo artículo.
		public ArrayList<Rol_film> getAll(){
			
			MySqlConnection objConection = new MySqlConnection();
			ArrayList<Rol_film> objTableRoles = new ArrayList<Rol_film>();
			try {
				  objConection.open();
						
				  if (!objConection.isError()){
					   ResultSet Result = objConection.executeSelect("SELECT * FROM Rol_film");
					   while (Result.next()) {
						   Rol_film objArticulo = new Rol_film();
						   objArticulo.setIdRol(Result.getInt("IDROL_RF"));
						   objArticulo.setRolcode(Result.getString("ROLCODE_RF"));
						   objArticulo.setRolcode(Result.getString("ROLNAME_art"));
						   objArticulo.setRolcode(Result.getString("S_ACTIVEROW_RF"));

						   
						   objTableRoles.add(objArticulo);
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
			return objTableRoles;
		}
		
		public int setRol(Rol_film objRol){
			
			MySqlConnection objConection = new MySqlConnection();
			int IdRol = -1;		
			
			try {
					objConection.open();
					if (!objConection.isError()){					
						String SQL = "INSERT INTO rol_film "
						         + "(ROLCODE_RF,"
						         + " ROLNAME_RF,"
						         + " S_ACTIVEROW_RF,"
						         + " S_INSERTDATE_RF,"
						         + " S_UPDATEDATE_RF) "
						   + "VALUES "
						   + "('"+objRol.getRolcode()+"',"
						   + " '"+objRol.getRolname()+"',"					   
						   + " "+true+","
						   + " CURRENT_TIMESTAMP,"
						   + " CURRENT_TIMESTAMP)";
							
						ResultSet Result = objConection.executeInsert(SQL);					
						
						if ((Result != null) && (Result.next())) {
							IdRol=Result.getInt(1);						
						}
					} else {
						    this.flagError = true;
						    this.msgError = "Error en setRol. El objeto clsConectionMySql informa error al abrir conexión. +Info: " + objConection.msgError();					    
					   }
		    	} catch (Exception ex) {
					this.flagError = true;
					this.msgError = "Error en setRol. +Info: " + ex.getMessage();
		    	} finally {
					objConection.close();
		    	}
			
			objRol.setIdRol(IdRol);		
			return IdRol;
		}
		
		//Devuelve la bandera de error si se desea comprobar desde la capa de negocio si ha ocurrido algún problema.
		public boolean isFlagError() {
			return flagError;
		}

		//Método que devuelve la descripción del error.
		public String getMsgError() {
			return msgError;
		}
	
	
}
