package rgg.campusvirtualapp.modelo;

import java.io.Serializable;

public class DatosAsignatura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1007895024160714107L;
	private int codAsignatura;
	private String nombre;
	private int curso;
	private int cuatrimestre;
	private String carrera;

	public DatosAsignatura(int codAsignatura, String nombre, int curso,
			int cuatrimestre, String carrera) {
		super();
		this.codAsignatura = codAsignatura;
		this.nombre = nombre;
		this.curso = curso;
		this.cuatrimestre = cuatrimestre;
		this.carrera = carrera;
	}

	public int getCodAsignatura() {
		return codAsignatura;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCurso() {
		return curso;
	}

	public int getCuatrimestre() {
		return cuatrimestre;
	}

	public String getCarrera() {
		return carrera;
	}

}
