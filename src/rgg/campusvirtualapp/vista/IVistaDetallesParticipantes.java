package rgg.campusvirtualapp.vista;

public interface IVistaDetallesParticipantes {
	/**
	 * Asigna la imagen del participante
	 * 
	 * @param imagen
	 *            Imagen del participante
	 */
	void setImagen(Object imagen);

	/**
	 * Asigna los detalles del participante
	 * 
	 * @param detalles
	 *            String con los detalles del participante (Nombre y correo).
	 */
	void setDetalles(String detalles);

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
