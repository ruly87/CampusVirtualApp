package rgg.campusvirtualapp.presentador;

public interface IPresentadorForoAsignaturaDetalle {
	/**
	 * Método encargado de cargar el hilo que recibe por parámetros
	 * 
	 * @param hilo
	 *            contiene la información revelante al hilo en concreto
	 */
	void tratarHilo(Object hilo);

	/**
	 * Método que recibe un item seleccionado a tratar
	 */
	void tratarItem();

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
