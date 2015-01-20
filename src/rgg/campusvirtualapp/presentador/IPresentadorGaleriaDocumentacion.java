package rgg.campusvirtualapp.presentador;

public interface IPresentadorGaleriaDocumentacion {
	/**
	 * Método encargado en recuperar las semanas de una asignatura.
	 */
	void recuperarSemanas();

	/**
	 * Método que recibe una semana seleccionada a tratar
	 * 
	 * @param semana
	 *            contiene el valor de la semana
	 */
	void tratarSemana(int semana);

	/**
	 * Método encargado de volver a la página de la asignatura
	 */
	void volverInicio();

	/**
	 * Método encargado de lanzar las activitys del menu
	 * 
	 * @param opcion
	 *            contiene el valor de la opción seleciconada en el menu
	 */
	void tratarMenu(int opcion);

	/**
	 * Método encargado de actualizar las preferencias (Idioma y Formato)
	 */
	void actualizaPreferencias();
}
