package rgg.campusvirtualapp.modelo;

import java.io.File;
import java.io.Serializable;

public class DatosTarea implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7724480222111915391L;
	private int codAsignatura;
	private String nombreTarea;
	private String detallesTarea;
	private File archivo;

	public DatosTarea(int codAsignatura, String nombreTarea,
			String detallesTarea) {
		super();
		this.codAsignatura = codAsignatura;
		this.nombreTarea = nombreTarea;
		this.detallesTarea = detallesTarea;
	}

	public DatosTarea(int codAsignatura, String nombreTarea,
			String detallesTarea, File archivo) {
		super();
		this.codAsignatura = codAsignatura;
		this.nombreTarea = nombreTarea;
		this.detallesTarea = detallesTarea;
		this.archivo = archivo;
	}

	public int getCodAsignatura() {
		return codAsignatura;
	}

	public String getNombreTarea() {
		return nombreTarea;
	}

	public String getDetallesTarea() {
		return detallesTarea;
	}

	public File getArchivo() {
		return archivo;
	}

}
