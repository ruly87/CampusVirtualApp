package rgg.campusvirtualapp.presentador;

public interface IPresentadorForoAsignaturaResponderTema {
	/**
	 * M�todo que recibe un item seleccionado a tratar
	 * 
	 * @param item
	 *            contiene la informaci�n del hilo y la lista del foro
	 */
	void tratarItem(Object item);

	/**
	 * M�todo que recibe una acci�n, el presentador analiza dicha acci�n y
	 * solicita al Modelo que trate los datos.
	 */
	void responderTema();

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
