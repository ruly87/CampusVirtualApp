package rgg.campusvirtualapp.presentador;

public interface IPresentadorAyuda {
	/**
	 * M�todo que recibe la petici�n de los datos de la ayuda de la aplicaci�n
	 * 
	 * @param id
	 *            contiene la informaci�n del bot�n pulsado
	 */
	void solicitarAyuda(int id);

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
