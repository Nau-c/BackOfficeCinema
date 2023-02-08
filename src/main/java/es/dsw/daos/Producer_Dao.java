package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Producer;

public class Producer_Dao {
	
	private boolean flagError;
	private String msgError;
	
	
	public Producer_Dao() {
		this.flagError = false;
		this.msgError = "";
	}
	
	public String getbyId(int idDistribuidor) {
		String Producer = "";
		
MySqlConnection objConection = new MySqlConnection();
		
		try {
			objConection.open();
			if(!objConection.isError()){
				ResultSet Result = objConection.executeSelect("SELECT * FROM producer_film WHERE idproducer_pf = "+ idDistribuidor);
				
				while(Result.next()) {
					Producer objProducer = new Producer();
					objProducer.setProducer_PF(Result.getString("PRODUCER_PF"));
					
					Producer = objProducer.getProducer_PF();
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
		
		return Producer;
		
	}
	
public ArrayList<Producer> getAll(){
		
		MySqlConnection objConection = new MySqlConnection();
		ArrayList<Producer> objTablaProducer = new ArrayList<Producer>();
		
		
		try {
			objConection.open();
			if(!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT * FROM producer_film");
				while(Result.next()) {
					Producer objProducer = new Producer();
					
					objProducer.setIdproducer_PF(Result.getInt("IDPRODUCER_PF"));
					objProducer.setProducer_PF(Result.getString("PRODUCER_PF"));
					
					objTablaProducer.add(objProducer);
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
		
		return objTablaProducer;
	}
	
	
	
}