package rgg.campusvirtualapp.presentador;

public interface IPresentadorParticipantes {
	/**
	 * Método que recibe la petición de obtener el listado de personas de una
	 * asignatura
	 */
	void obtenerDatos();

	/**
	 * Método que recibe la acción de mostrar los detalles de una persona
	 * 
	 * @param participante
	 *            contiene la información de un participante
	 */
	void solicitarDetalles(Object participante);

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
