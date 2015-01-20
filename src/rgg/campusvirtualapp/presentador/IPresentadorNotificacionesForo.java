package rgg.campusvirtualapp.presentador;

public interface IPresentadorNotificacionesForo {
	/**
	 * Método que recibe la petición de las notificaciones del foro de un
	 * usuario
	 */
	void solicitarListaNotificaciones();

	/**
	 * Método que recibe un item seleccionado a tratar
	 * 
	 * @param item
	 *            contiene los datos de una notificación
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
