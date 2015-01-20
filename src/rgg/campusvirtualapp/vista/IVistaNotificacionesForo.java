package rgg.campusvirtualapp.vista;

public interface IVistaNotificacionesForo {
	/**
	 * Asigna la lista de notificaciones del foro recibidos
	 * 
	 * @param listaNotificaciones
	 *            Objeto con la lista de notificaciones
	 */
	void setListaNotificaciones(Object listaNotificaciones);

	/**
	 * Muestra una barra de progreso
	 */
	void mostrarProgreso();

	/**
	 * Elimina la barra de progreso
	 */
	void eliminarProgreso();

	/**
	 * Método que modifica el tipo de fuente de la vista
	 * 
	 * @param tipo
	 *            recibe el Typeface correspondiente
	 */
	void tratarFormato(Object tipo);
}
