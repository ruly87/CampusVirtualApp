package rgg.campusvirtualapp.presentador;

public interface IPresentadorGaleriaTarea {
	/**
	 * Método encargado en recuperar las tareas de una asignatura
	 */
	void recuperarTareas();

	/**
	 * Método que recibe una tarea seleccionada
	 * 
	 * @param posicion
	 *            contiene la posición de la galeria de la tarea
	 */
	void tratarTarea(int posicion);

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
