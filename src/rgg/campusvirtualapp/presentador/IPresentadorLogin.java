package rgg.campusvirtualapp.presentador;

public interface IPresentadorLogin {
	/**
	 * Método que recibe los datos del login y le pedirá al Modelo que los
	 * compruebe.
	 */
	void tratarDatos();

	/**
	 * Método encargado de actualizar las preferencias (Idioma y Formato)
	 */
	void actualizaPreferencias();
}
