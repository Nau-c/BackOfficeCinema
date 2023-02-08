package es.dsw.daos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import es.dsw.connections.MySqlConnection;
import es.dsw.models.Rol_film;
import es.dsw.models.User_film;


public class User_filmDao {

	private boolean flagError;
	private String msgError;

	public User_filmDao() {
		this.flagError = false;
		this.msgError = "";
	}

	public ArrayList<User_film> getAll() {

		MySqlConnection objConection = new MySqlConnection();
		ArrayList<User_film> objTablaUsuarios = new ArrayList<User_film>();

		try {
			objConection.open();

			if (!objConection.isError()) {
				ResultSet Result = objConection.executeSelect("SELECT * FROM user_film");
				while (Result.next()) {
					User_film objUsuario = new User_film();
					objUsuario.setIDUSER_USF(Result.getInt("IDUSER_USF"));
					objUsuario.setUSERNAME_USF(Result.getString("USERNAME_USF"));
					objUsuario.setPASSWORD_USF(Result.getString("PASSWORD_USF"));
					objUsuario.setNAME_USF(Result.getString("NAME_USF"));
					objUsuario.setFIRSTSURNAME_USF(Result.getString("FIRSTSURNAME_USF"));

					ResultSet ResultRol = objConection.executeSelect(
							"SELECT ROLCODE_RF FROM user_film, rol_film, userrol_film WHERE IDUSER_USF = IDUSER_URF AND IDROL_URF = IDROL_RF AND USERNAME_USF = \""
									+ objUsuario.getUSERNAME_USF() + "\"");

					ArrayList<String> roles = new ArrayList<String>();
					while (ResultRol.next()) {
						roles.add(ResultRol.getString("ROLCODE_RF"));
					}
					objUsuario.setROL(roles);
					objTablaUsuarios.add(objUsuario);
				}

			} else {
				this.flagError = true;
				this.msgError = "Error en getAll. El objeto clsConectionMySql informa error al abrir conexión. +Info: "
						+ objConection.msgError();
			}
		} catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en getAll. +Info: " + ex.getMessage();
		} finally {
			objConection.close();
		}
		return objTablaUsuarios;
	}

	/**
	 * Método que dado un objeto de tipo Usuario inserta en base de datos un
	 * registro con sus datos en la tabla «user_film». Además actualiza las claves
	 * primarias en los atributos del objeto pasado por parámetro.
	 * 
	 * @param objUsuario La instancia de la clase Usuario cuya información se desee
	 *                   añadir en la base de datos.
	 * 
	 * @return IdUsuario Valor entero con el código identificador del nuevo usuario
	 *         que se haya generado.
	 */

	public int setUsuario(User_film objUsuario) {

		MySqlConnection objConection = new MySqlConnection();
		int IdUsuario = -1;

		try {
			objConection.open();
			if (!objConection.isError()) {
				String SQL = "INSERT INTO user_film " + "(USERNAME_USF," + " PASSWORD_USF," + " NAME_USF,"
						+ " FIRSTSURNAME_USF," + " SECOUNDSURNAME_USF," + " EMAIL_USF," + " S_ACTIVEROW_USF,"
						+ " S_INSERTDATE_USF," + " S_UPDATEDATE_USF) " + "VALUES " + "('" + objUsuario.getUSERNAME_USF()
						+ "'," + " '" + objUsuario.getPASSWORD_USF() + "'," + " '" + objUsuario.getNAME_USF() + "',"
						+ " '" + objUsuario.getFIRSTSURNAME_USF() + "'," + " " + null + "," + " '"
						+ objUsuario.getEMAIL_USF() + "'," + " " + true + "," + " CURRENT_TIMESTAMP,"
						+ " CURRENT_TIMESTAMP)";

				ResultSet Result = objConection.executeInsert(SQL);

				if ((Result != null) && (Result.next())) {
					IdUsuario = Result.getInt(1);
				}
			} else {
				this.flagError = true;
				this.msgError = "Error en setUsuario. El objeto clsConectionMySql informa error al abrir conexión. +Info: "
						+ objConection.msgError();
			}
		} catch (Exception ex) {
			this.flagError = true;
			this.msgError = "Error en setUsuario. +Info: " + ex.getMessage();
		} finally {
			objConection.close();
		}

		objUsuario.setIDUSER_USF(IdUsuario);
		return IdUsuario;
	}

// Devuelve la bandera de error si se desea comprobar desde la capa de negocio si ha ocurrido algún problema.
	public boolean isFlagError() {
		return flagError;
	}

// Método que devuelve la descripción del error.
	public String getMsgError() {
		return msgError;
	}

}
