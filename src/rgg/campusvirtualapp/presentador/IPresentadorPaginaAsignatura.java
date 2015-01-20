package rgg.campusvirtualapp.presentador;

public interface IPresentadorPaginaAsignatura {
	/**
	 * Método que recibe un item seleccionado a tratar
	 * 
	 * @param item
	 *            contiene la información del item seleccionado
	 */
	void tratarItem(Object item);

	/**
	 * Método encargado de recuperar los datos de una asignatura
	 * 
	 * @param asignatura
	 *            contiene el identificador de la asignatura
	 */
	void recuperarAsignatura(Object asignatura);

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
