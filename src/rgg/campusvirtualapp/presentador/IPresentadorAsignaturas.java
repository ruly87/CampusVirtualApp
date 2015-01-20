package rgg.campusvirtualapp.presentador;

public interface IPresentadorAsignaturas {
	/**
	 * M�todo que solicita al Modelo la lista de asignaturas y posteriormente la
	 * presenta
	 */
	void obtenerLista();

	/**
	 * M�todo que solicitar� a la VistaAsignaturas que muestre una alerta.
	 */
	void solicitarSalir();

	/**
	 * M�todo cierra la aplicaci�n y se ejecutar� cuando en la VistaAsignaturas
	 * se haya confirmado la alerta
	 */
	void accionSalir();

	/**
	 * M�todo que recibe de la VistaAsignaturas un item seleccionado a tratar,
	 * el presentador analizar� dicho item y se pasar� al
	 * PresentadorPaginaAsignatura
	 * 
	 * @param item
	 *            contiene los datos de la asignatura seleccionada
	 */
	void tratarItem(Object item);

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
