package rgg.campusvirtualapp.presentador;

public interface IPresentadorAsignaturas {
	/**
	 * Método que solicita al Modelo la lista de asignaturas y posteriormente la
	 * presenta
	 */
	void obtenerLista();

	/**
	 * Método que solicitará a la VistaAsignaturas que muestre una alerta.
	 */
	void solicitarSalir();

	/**
	 * Método cierra la aplicación y se ejecutará cuando en la VistaAsignaturas
	 * se haya confirmado la alerta
	 */
	void accionSalir();

	/**
	 * Método que recibe de la VistaAsignaturas un item seleccionado a tratar,
	 * el presentador analizará dicho item y se pasará al
	 * PresentadorPaginaAsignatura
	 * 
	 * @param item
	 *            contiene los datos de la asignatura seleccionada
	 */
	void tratarItem(Object item);

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
