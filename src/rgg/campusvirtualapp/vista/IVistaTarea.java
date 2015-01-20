package rgg.campusvirtualapp.vista;

public interface IVistaTarea {
	/**
	 * Añade el texto que se mostrará en el cuadro de texto de los detalles de
	 * la tarea de esta vista.
	 * 
	 * @param textoDetalle
	 *            String con los detalles de la tarea
	 */
	void setTextoDetalles(String textoDetalle);

	/**
	 * Muestra una alerta informando si se ha subido con éxito la tarea,
	 * mostrando la hora de subida.
	 * 
	 * @param mensaje
	 *            String con el mensaje a mostrar
	 */
	void crearAlerta(String mensaje);

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
