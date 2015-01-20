package rgg.campusvirtualapp.presentador;

public interface IPresentadorNotificacionesForoDetalle {
	/**
	 * Método que recibe un item seleccionado a tratar
	 * 
	 * @param item
	 *            contiene la información de la notificación
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
