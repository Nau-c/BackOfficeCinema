package es.dsw.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnection {
	
	//Aunque en este ejemplo los datos de conexión están en código, siempre es recomendable que esta
	//parametrización figure en un fichero de propiedades.
	//Nombre del host
	private String host = "localhost";
	//Puerto
	private String puerto = "3306";
	//Nombre de la base de datos
	private String nameDB = "db_filmcinema";
	//Usuario de conexión
	private String usuario = "root";
	//Contraseña
	private String password = "1234";
	
	//Bandera de error
	private boolean flagError;
	//Mensaje de error
	private String msgError;
	
	//Objeto de conexión
	private Connection connection;
	

	//inicialización de parámetros por defecto
	private void _initialize() {
		this.flagError = false;
		this.msgError = "";
		this.connection = null;
	}
	
	//Inicialización de la bandera y mensaje de error
	private void _initializeError() {
		this.flagError = false;
		this.msgError = "";
	}
	
	//Constructor implícito
	public MySqlConnection()
	{
		this._initialize();
	}
	
	//Constructor que permite indicar esquema por defecto
	public MySqlConnection(String _nameDB)
	{
		this._initialize();
		this.nameDB = _nameDB;
	}
	
	//Constructor que permite conectar a cualquier base de datos MySql
	public MySqlConnection(String _host, String _puerto, String _nameDB, String _usuario, String _password)
	{
		this._initialize();
		this.host = _host;
		this.puerto = _puerto;
		this.nameDB = _nameDB;
		this.usuario = _usuario;
		this.password = _password;
	}
	

	//Método que abre la conexión y en caso de error, activa la bandera de error.
	public void open()
	{
		try
		{   this._initializeError();
			if ((this.connection == null) || ((this.connection != null) && (this.connection.isClosed())))
			{
			  Class.forName("com.mysql.cj.jdbc.Driver");
			  this.connection = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.puerto+"/"+this.nameDB, this.usuario, this.password);
			}
		}
		catch (ClassNotFoundException ex)
		{
			this.flagError = true;
			this.msgError = "Error al registrar el dricer. +Info: " + ex.getMessage();
		}
		catch (Exception ex)
		{
			this.flagError = true;
			this.msgError = "Error en Open. +Info: " + ex.getMessage();
		}
	}
	
	//Método que cierra la conexión si estaba abierta
	public void close()
	{
		try
		{   this._initializeError();
			if ((this.connection != null) && (!this.connection.isClosed()))
			   this.connection.close();
		}
		catch (SQLException ex)
		{
			this.flagError = true;
			this.msgError = ex.getMessage();
		}
		
	}
	
	//Método que retorna el resultado de cualquier consulta sql en un ResultSet
	public ResultSet executeSelect(String _sql)
	{
		try {   
			this._initializeError();
			if (this.connection != null) {
				if (!this.connection.isClosed()) {
					java.sql.Statement objStament = this.connection.createStatement();	
					ResultSet rs = objStament.executeQuery (_sql);
					return rs;
				}
				else {
					this.flagError = true;
					this.msgError = "Error en ExecuteSelect. +Info: Conexión cerrada.";
				}
			}
			else {
				this.flagError = true;
				this.msgError = "Error en ExecuteSelect. +Info: Conexión no inicializada.";
			}
		}
		catch (SQLException ex) {
			this.flagError = true;
			this.msgError = "Error en ExecuteSelect. +Info: " + ex.getMessage();
		}

		return null; 
	}
	
	//Método para ejecutar un insert. Devuelve la/s claves primarias generadas en un record set.
	public ResultSet executeInsert(String _sql) {
		
		try {   
			 this._initializeError();
			 if (this.connection != null) {
				if (!this.connection.isClosed()) {
					PreparedStatement objStament = this.connection.prepareStatement(_sql,Statement.RETURN_GENERATED_KEYS);	
					objStament.execute();
					ResultSet rs = objStament.getGeneratedKeys();
					return rs;
				}
				else {
					this.flagError = true;
					this.msgError = "Error en ExecuteQuery. +Info: Conexión cerrada.";
				}
			 }
			 else {
				this.flagError = true;
				this.msgError = "Error en ExecuteQuery. +Info: Conexión no inicializada.";
			}
		   }
		   catch (SQLException ex) {
			    this.flagError = true;
			    this.msgError = "Error en ExecuteQuery. +Info: " + ex.getMessage();
		   }
		
		return null; 
	}
	
	//Método para ejecutar un update o delete. Devuelve el número de registros afectados.
	public int executeUpdateOrDelete(String _sql) {
		int NumRows = 0;
		try {   
			 this._initializeError();
			 if (this.connection != null) {
				if (!this.connection.isClosed()) {
					PreparedStatement objStament = this.connection.prepareStatement(_sql);	
					NumRows = objStament.executeUpdate();
				}
				else {
					this.flagError = true;
					this.msgError = "Error en ExecuteQuery. +Info: Conexión cerrada.";
				}
			 }
			 else {
				this.flagError = true;
				this.msgError = "Error en ExecuteQuery. +Info: Conexión no inicializada.";
			}
		   }
		   catch (SQLException ex) {
			    this.flagError = true;
			    this.msgError = "Error en ExecuteQuery. +Info: " + ex.getMessage();
		   }
		
		return NumRows;
	}
	
	//Devuelve el valor de la bandera de error
	public boolean isError() {
		return this.flagError;
	}
	
	//Devuelve la descripción del error
	public String msgError() {
		return this.msgError;
	}

}
