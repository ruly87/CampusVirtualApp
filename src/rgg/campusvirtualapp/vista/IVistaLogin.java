package rgg.campusvirtualapp.vista;

public interface IVistaLogin {
	/**
	 * M�todo que devuelve el contenido de EditText dni
	 * 
	 * @return String con el DNI almacenado en el EditText
	 */
	String getDni();

	/**
	 * M�todo que devuelve el contenido de EditText password
	 * 
	 * @return String con el
	 */
	String getPassword();

	/**
	 * Metodo que genera una alerta mostrando un mensaje
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
	 * M�todo que modifica el tipo de fuente de la vista
	 * 
	 * @param tipo
	 *            recibe el Typeface correspondiente
	 */
	void tratarFormato(Object tipo);
}
