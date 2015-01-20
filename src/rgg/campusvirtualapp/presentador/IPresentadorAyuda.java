package rgg.campusvirtualapp.presentador;

public interface IPresentadorAyuda {
	/**
	 * Método que recibe la petición de los datos de la ayuda de la aplicación
	 * 
	 * @param id
	 *            contiene la información del botón pulsado
	 */
	void solicitarAyuda(int id);

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
