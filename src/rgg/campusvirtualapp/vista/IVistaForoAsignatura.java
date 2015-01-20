package rgg.campusvirtualapp.vista;

public interface IVistaForoAsignatura {
	/**
	 * Se encargar� de asignar los hilos de un foro.
	 * 
	 * @param listaForo
	 *            Objeto con la lista del foro
	 */
	void setListaForo(Object listaForo);

	/**
	 * Muestra una barra de progreso
	 */
	void mostrarProgreso();

	/**
	 * Elimina la barra de progreso
	 */
	void eliminarProgreso();

	/**
	 * M�todo que modifica el tipo de fuente de la vista
	 * 
	 * @param tipo
	 *            recibe el Typeface correspondiente
	 */
	void tratarFormato(Object tipo);
}
