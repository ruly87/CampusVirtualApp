package rgg.campusvirtualapp.modelo;

import java.io.Serializable;

import android.graphics.Bitmap;

public class DatosParticipantes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7414876837174388958L;
	private int username;
	private String nombre;
	private Bitmap foto;
	private String email;

	public DatosParticipantes(int username, String nombre, Object foto,
			String email) {
		super();
		this.username = username;
		this.nombre = nombre;
		this.foto = (Bitmap) foto;
		this.email = email;
	}

	public DatosParticipantes(int username, String nombre) {
		super();
		this.username = username;
		this.nombre = nombre;
	}

	public int getUsername() {
		return username;
	}

	public String getNombre() {
		return nombre;
	}

	public Object getFoto() {
		return foto;
	}

	public String getEmail() {
		return email;
	}
}
