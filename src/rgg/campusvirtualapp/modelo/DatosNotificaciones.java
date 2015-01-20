package rgg.campusvirtualapp.modelo;

import java.io.Serializable;
import java.util.Date;

public class DatosNotificaciones implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3071241034232235379L;
	private int username;
	private String autor;
	private Date horapublicacion;
	private String mensaje;
	private String asunto;

	public DatosNotificaciones(int username, String autor,
			Date horapublicacion, String mensaje, String asunto) {
		super();
		this.username = username;
		this.autor = autor;
		this.horapublicacion = horapublicacion;
		this.mensaje = mensaje;
		this.asunto = asunto;
	}

	public int getUsername() {
		return username;
	}

	public String getAutor() {
		return autor;
	}

	public Date getHorapublicacion() {
		return horapublicacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public String getAsunto() {
		return asunto;
	}

}
