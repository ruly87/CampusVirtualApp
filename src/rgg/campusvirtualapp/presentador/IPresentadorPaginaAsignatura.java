package rgg.campusvirtualapp.presentador;

public interface IPresentadorPaginaAsignatura {
	/**
	 * M�todo que recibe un item seleccionado a tratar
	 * 
	 * @param item
	 *            contiene la informaci�n del item seleccionado
	 */
	void tratarItem(Object item);

	/**
	 * M�todo encargado de recuperar los datos de una asignatura
	 * 
	 * @param asignatura
	 *            contiene el identificador de la asignatura
	 */
	void recuperarAsignatura(Object asignatura);

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
