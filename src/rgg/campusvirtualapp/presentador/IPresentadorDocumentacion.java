package rgg.campusvirtualapp.presentador;

public interface IPresentadorDocumentacion {
	/**
	 * Método que recibe un item seleccionado a tratar
	 * 
	 * @param item
	 *            contiene la semana seleccionada
	 */
	void pedirNombresArchivos(Object item);

	/**
	 * Método que recibe de la vista la acción de ver un archivo
	 * 
	 * @param accion
	 *            contiene la información revelante al documento
	 */
	void tratarAccionVer(int accion);

	/**
	 * Método que recibe de la vista la acción de descargar un archivo
	 * 
	 * @param accion
	 *            contiene la información revelante al documento
	 */
	void tratarAccionDescargar(int accion);

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
