package rgg.campusvirtualapp.vista;

public interface IVistaPaginaAsignatura {
	/**
	 * Método encargado de cambiar el titulo de la asignatura
	 * 
	 * @param nombreAsignatura
	 *            String con el nombre de la asignatura
	 */
	void setTituloAsignatura(String nombreAsignatura);

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
