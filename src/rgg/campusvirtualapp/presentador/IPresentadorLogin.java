package rgg.campusvirtualapp.presentador;

public interface IPresentadorLogin {
	/**
	 * M�todo que recibe los datos del login y le pedir� al Modelo que los
	 * compruebe.
	 */
	void tratarDatos();

	/**
	 * M�todo encargado de actualizar las preferencias (Idioma y Formato)
	 */
	void actualizaPreferencias();
}
