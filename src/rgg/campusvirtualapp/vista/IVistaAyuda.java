package rgg.campusvirtualapp.vista;

public interface IVistaAyuda {
	/**
	 * Muestra una alerta con la ayuda seleccionada
	 * 
	 * @param titulo
	 *            String con el titulo de la alerta
	 * @param ayuda
	 *            String con el texto de ayuda
	 */
	void crearAlerta(String titulo, String ayuda);

	/**
	 * Método que modifica el tipo de fuente de la vista
	 * 
	 * @param tipo
	 *            recibe el Typeface correspondiente
	 */
	void tratarFormato(Object tipo);
}
