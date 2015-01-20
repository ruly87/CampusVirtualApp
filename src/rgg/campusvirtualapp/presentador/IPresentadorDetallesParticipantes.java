package rgg.campusvirtualapp.presentador;

public interface IPresentadorDetallesParticipantes {
	/**
	 * M�todo que recibe el nombre de un participante a tratar, el presentador
	 * analiza dicha persona y solicita a la VistaDetallesParticipantes que se
	 * presente.
	 * 
	 * @param persona
	 *            contiene los datos de un participante
	 */
	void tratarPersona(Object persona);

	/**
	 * M�todo que recibe la petici�n de enviar un correo a esa persona
	 */
	void tratarItem();

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
