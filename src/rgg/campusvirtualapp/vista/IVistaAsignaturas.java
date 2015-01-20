package rgg.campusvirtualapp.vista;

public interface IVistaAsignaturas {
	/**
	 * Se encargará de asignar las asignaturas de un usuario
	 * 
	 * @param listaAsignaturas
	 *            Objecto que recibe por parametro la lista de asignatura
	 */
	void setListaAsignaturas(Object listaAsignaturas);

	/**
	 * Muestra una alerta preguntando al usuario si desea salir de la
	 * aplicación.
	 * 
	 * @param mensaje
	 *            String que muestra en la alerta
	 */
	void crearAlertaSalir(String mensaje);

	/**
	 * Metodo que genera una alerta mostrando un mensaje
	 * 
	 * @param titulo
	 *            String con el titulo de la alerta
	 * @param mensaje
	 *            String que muestra en la alerta
	 */
	void crearAlerta(String titulo, String mensaje);

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
