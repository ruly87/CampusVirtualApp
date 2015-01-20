package rgg.campusvirtualapp.modelo;

import java.io.Serializable;
import java.util.Date;

public class DatosHilo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7365205639033000909L;
	private String asunto;
	private String autor;
	private int nrespuestas;
	private Date horapublicacion;
	private String mensaje;
	private int esRespuesta;
	private int id;
	private int codAsignatura;
	private int username;
	private int posicion;

	// Constructor hilo
	public DatosHilo(String asunto, String autor, int nrespuestas,
			Object horapublicacion, String mensaje, int esRespuesta, int id,
			int codAsignatura, int username, int posicion) {
		super();
		this.autor = autor;
		this.username = username;
		this.asunto = asunto;
		this.nrespuestas = nrespuestas;
		this.horapublicacion = (Date) horapublicacion;
		this.mensaje = mensaje;
		this.esRespuesta = esRespuesta;
		this.id = id;
		this.codAsignatura = codAsignatura;
		this.posicion = posicion;
	}

	// Nuevo tema
	public DatosHilo(String asunto, int nrespuestas, Object horapublicacion,
			String mensaje, int esRespuesta, int id) {
		super();
		this.asunto = asunto;
		this.nrespuestas = nrespuestas;
		this.horapublicacion = (Date) horapublicacion;
		this.mensaje = mensaje;
		this.esRespuesta = esRespuesta;
		this.id = id;
	}
	public DatosHilo(String asunto, String autor, int nrespuestas,
			Object horapublicacion, String mensaje, int esRespuesta, int id,
			int codAsignatura, int username) {
		super();
		this.autor = autor;
		this.username = username;
		this.asunto = asunto;
		this.nrespuestas = nrespuestas;
		this.horapublicacion = (Date) horapublicacion;
		this.mensaje = mensaje;
		this.esRespuesta = esRespuesta;
		this.id = id;
		this.codAsignatura = codAsignatura;
	}
	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getAutor() {
		return autor;
	}

	public int getNrespuestas() {
		return nrespuestas;
	}

	public Object getHorapublicacion() {
		return horapublicacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public int getEsRespuesta() {
		return esRespuesta;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodAsignatura() {
		return codAsignatura;
	}

	public void setCodAsignatura(int codAsignatura) {
		this.codAsignatura = codAsignatura;
	}

	public int getUsername() {
		return username;
	}

	public void setUsername(int username) {
		this.username = username;
	}

	public int getPosicion() {
		return posicion;
	}
}
