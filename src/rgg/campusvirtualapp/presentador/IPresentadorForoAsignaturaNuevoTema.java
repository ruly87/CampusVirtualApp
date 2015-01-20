package rgg.campusvirtualapp.presentador;

public interface IPresentadorForoAsignaturaNuevoTema {
	/**
	 * M�todo que recibe un item seleccionado a tratar
	 * 
	 * @param item
	 *            contiene el identificador del hilo a publicar
	 */
	void tratarItem(Object item);

	/**
	 * M�todo que recibe una acci�n, el presentador analiza dicha acci�n y
	 * solicita al Modelo que trate los datos.
	 */
	void crearTema();

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
