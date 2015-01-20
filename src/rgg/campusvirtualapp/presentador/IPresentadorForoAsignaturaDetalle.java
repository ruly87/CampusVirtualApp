package rgg.campusvirtualapp.presentador;

public interface IPresentadorForoAsignaturaDetalle {
	/**
	 * M�todo encargado de cargar el hilo que recibe por par�metros
	 * 
	 * @param hilo
	 *            contiene la informaci�n revelante al hilo en concreto
	 */
	void tratarHilo(Object hilo);

	/**
	 * M�todo que recibe un item seleccionado a tratar
	 */
	void tratarItem();

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
