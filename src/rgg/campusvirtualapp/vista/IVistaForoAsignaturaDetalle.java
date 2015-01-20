package rgg.campusvirtualapp.vista;

public interface IVistaForoAsignaturaDetalle {
	/**
	 * Se encargará de asignar los hilos de un foro.
	 * 
	 * @param listaForo
	 *            Objeto con la lista mensajes de un hilo
	 */
	void setListaHilo(Object listaForo);

	/**
	 * Método que modifica el tipo de fuente de la vista
	 * 
	 * @param tipo
	 *            recibe el Typeface correspondiente
	 */
	void tratarFormato(Object tipo);
}
