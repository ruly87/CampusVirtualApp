package rgg.campusvirtualapp.presentador;

public interface IPresentadorForoAsignaturaResponderTema {
	/**
	 * Método que recibe un item seleccionado a tratar
	 * 
	 * @param item
	 *            contiene la información del hilo y la lista del foro
	 */
	void tratarItem(Object item);

	/**
	 * Método que recibe una acción, el presentador analiza dicha acción y
	 * solicita al Modelo que trate los datos.
	 */
	void responderTema();

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
