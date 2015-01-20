package rgg.campusvirtualapp.presentador;

public interface IPresentadorForoAsignatura {
	/**
	 * Método que recibe un item seleccionado a tratar
	 * 
	 * @param item
	 *            identificador del item seleccionado
	 */
	void tratarItem(int item);

	/**
	 * Método que recibe una acción, el presentador analiza dicha acción y
	 * solicita al Modelo que trate los datos
	 */
	void obtenerListaForo();

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
