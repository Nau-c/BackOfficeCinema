package es.dsw.models;

import java.sql.Date;
import java.util.ArrayList;


/**a) Cuando el usuario haga clic en el punto de menú “Nueva película” deberá mostrarse
una ventana modal que le proporcione el formulario necesario para introducir los datos de
una nueva película. Estos datos son los siguientes:
Título*, Synopsis, Genero*, Director, Reparto, Año*, Fecha del estreno, Distribuidor, País*
Nota: Los marcados con * son datos obligatorios**/

public class film {
	private int idFilm;
	private String tittle_RF;
	private String synopsis_RF;
	private int	   idGenre_RF;
	private String genero;
	private String director_RF;
	private String reparto_RF;
	private int anio_RF;
	private Date datePremiere_RF;
	private int idProducer_RF;
	private int idCountry_RF;
	private String distribuidor;
	private String pais;
	private Date s_inserdate_RF;
	private Date s_updatedate_RF;
	private int s_iduser_CF;
	
	
	
	public film() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getIdFilm() {
		return idFilm;
	}



	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}



	public String getTittle_RF() {
		return tittle_RF;
	}



	public void setTittle_RF(String tittle_RF) {
		this.tittle_RF = tittle_RF;
	}



	public String getSynopsis_RF() {
		return synopsis_RF;
	}



	public void setSynopsis_RF(String synopsis_RF) {
		this.synopsis_RF = synopsis_RF;
	}



	public int getIdGenre_RF() {
		return idGenre_RF;
	}



	public void setIdGenre_RF(int idGenre_RF) {
		this.idGenre_RF = idGenre_RF;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	public String getDirector_RF() {
		return director_RF;
	}



	public void setDirector_RF(String director_RF) {
		this.director_RF = director_RF;
	}



	public String getReparto_RF() {
		return reparto_RF;
	}



	public void setReparto_RF(String reparto_RF) {
		this.reparto_RF = reparto_RF;
	}



	public int getAnio_RF() {
		return anio_RF;
	}



	public void setAnio_RF(int anio_RF) {
		this.anio_RF = anio_RF;
	}



	public Date getDatePremiere_RF() {
		return datePremiere_RF;
	}



	public void setDatePremiere_RF(Date datePremiere_RF) {
		this.datePremiere_RF = datePremiere_RF;
	}



	public int getIdProducer_RF() {
		return idProducer_RF;
	}



	public void setIdProducer_RF(int idProducer_RF) {
		this.idProducer_RF = idProducer_RF;
	}



	public int getIdCountry_RF() {
		return idCountry_RF;
	}



	public void setIdCountry_RF(int idCountry_RF) {
		this.idCountry_RF = idCountry_RF;
	}



	public String getDistribuidor() {
		return distribuidor;
	}



	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}



	public String getPais() {
		return pais;
	}



	public void setPais(String pais) {
		this.pais = pais;
	}



	public Date getS_inserdate_RF() {
		return s_inserdate_RF;
	}



	public void setS_inserdate_RF(Date s_inserdate_RF) {
		this.s_inserdate_RF = s_inserdate_RF;
	}



	public Date getS_updatedate_RF() {
		return s_updatedate_RF;
	}



	public void setS_updatedate_RF(Date s_updatedate_RF) {
		this.s_updatedate_RF = s_updatedate_RF;
	}



	public int getS_iduser_CF() {
		return s_iduser_CF;
	}



	public void setS_iduser_CF(int objTablaUsuarios) {
		this.s_iduser_CF = objTablaUsuarios;
	}


	

	

	
	
	
	
}