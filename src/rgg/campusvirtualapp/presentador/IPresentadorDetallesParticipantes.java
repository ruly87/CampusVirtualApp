package rgg.campusvirtualapp.presentador;

public interface IPresentadorDetallesParticipantes {
	/**
	 * Método que recibe el nombre de un participante a tratar, el presentador
	 * analiza dicha persona y solicita a la VistaDetallesParticipantes que se
	 * presente.
	 * 
	 * @param persona
	 *            contiene los datos de un participante
	 */
	void tratarPersona(Object persona);

	/**
	 * Método que recibe la petición de enviar un correo a esa persona
	 */
	void tratarItem();

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
