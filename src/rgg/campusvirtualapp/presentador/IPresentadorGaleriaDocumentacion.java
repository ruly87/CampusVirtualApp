package rgg.campusvirtualapp.presentador;

public interface IPresentadorGaleriaDocumentacion {
	/**
	 * M�todo encargado en recuperar las semanas de una asignatura.
	 */
	void recuperarSemanas();

	/**
	 * M�todo que recibe una semana seleccionada a tratar
	 * 
	 * @param semana
	 *            contiene el valor de la semana
	 */
	void tratarSemana(int semana);

	/**
	 * M�todo encargado de volver a la p�gina de la asignatura
	 */
	void volverInicio();

	/**
	 * M�todo encargado de lanzar las activitys del menu
	 * 
	 * @param opcion
	 *            contiene el valor de la opci�n seleciconada en el menu
	 */
	void tratarMenu(int opcion);

	/**
	 * M�todo encargado de actualizar las preferencias (Idioma y Formato)
	 */
	void actualizaPreferencias();
}
