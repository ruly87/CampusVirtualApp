package rgg.campusvirtualapp.vista;

public interface IVistaParticipantes {
	/**
	 * Asignar los participantes de una asignatura.
	 * 
	 * @param listaPersonas
	 *            Objeto con la lista de participantes.
	 */
	void setListaPersonas(Object listaPersonas);

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
