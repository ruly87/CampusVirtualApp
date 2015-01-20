package rgg.campusvirtualapp.presentador;

public interface IPresentadorTarea {
	/**
	 * Método que recibe una tarea seleccionada a tratar
	 * 
	 * @param tarea
	 *            contiene la información de la tarea
	 */
	void tratarTarea(Object tarea);

	/**
	 * Método que recibe una acción de subir un archivo
	 * 
	 * @param accion
	 *            contiene la información relevante al archivo
	 */
	void tratarAccion(Object accion);

	/**
	 * Método encargado de generar un evento para abrir un gestor de archivos.
	 */
	void tratarAccion();

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
