package rgg.campusvirtualapp.presentador;

public interface IPresentadorTarea {
	/**
	 * M�todo que recibe una tarea seleccionada a tratar
	 * 
	 * @param tarea
	 *            contiene la informaci�n de la tarea
	 */
	void tratarTarea(Object tarea);

	/**
	 * M�todo que recibe una acci�n de subir un archivo
	 * 
	 * @param accion
	 *            contiene la informaci�n relevante al archivo
	 */
	void tratarAccion(Object accion);

	/**
	 * M�todo encargado de generar un evento para abrir un gestor de archivos.
	 */
	void tratarAccion();

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
