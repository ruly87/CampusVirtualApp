package rgg.campusvirtualapp.vista;

public interface IVistaForoAsignaturaNuevoTema {
	/**
	 * Obtiene el valor del asunto del nuevo tema.
	 * 
	 * @return String con el asunto
	 */
	String getAsunto();

	/**
	 * Obtiene el valor del mensaje del nuevo tema.
	 * 
	 * @return String con el mensaje
	 */
	String getMensaje();

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
