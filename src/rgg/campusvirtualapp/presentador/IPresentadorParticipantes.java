package rgg.campusvirtualapp.presentador;

public interface IPresentadorParticipantes {
	/**
	 * M�todo que recibe la petici�n de obtener el listado de personas de una
	 * asignatura
	 */
	void obtenerDatos();

	/**
	 * M�todo que recibe la acci�n de mostrar los detalles de una persona
	 * 
	 * @param participante
	 *            contiene la informaci�n de un participante
	 */
	void solicitarDetalles(Object participante);

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
