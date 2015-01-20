package rgg.campusvirtualapp.presentador;

public interface IPresentadorDocumentacion {
	/**
	 * M�todo que recibe un item seleccionado a tratar
	 * 
	 * @param item
	 *            contiene la semana seleccionada
	 */
	void pedirNombresArchivos(Object item);

	/**
	 * M�todo que recibe de la vista la acci�n de ver un archivo
	 * 
	 * @param accion
	 *            contiene la informaci�n revelante al documento
	 */
	void tratarAccionVer(int accion);

	/**
	 * M�todo que recibe de la vista la acci�n de descargar un archivo
	 * 
	 * @param accion
	 *            contiene la informaci�n revelante al documento
	 */
	void tratarAccionDescargar(int accion);

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
