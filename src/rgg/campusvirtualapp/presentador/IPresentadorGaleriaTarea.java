package rgg.campusvirtualapp.presentador;

public interface IPresentadorGaleriaTarea {
	/**
	 * M�todo encargado en recuperar las tareas de una asignatura
	 */
	void recuperarTareas();

	/**
	 * M�todo que recibe una tarea seleccionada
	 * 
	 * @param posicion
	 *            contiene la posici�n de la galeria de la tarea
	 */
	void tratarTarea(int posicion);

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
