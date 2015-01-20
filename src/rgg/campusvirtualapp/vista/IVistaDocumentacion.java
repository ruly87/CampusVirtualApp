package rgg.campusvirtualapp.vista;

import android.view.View;

public interface IVistaDocumentacion {
	/**
	 * Asigna los archivos disponibles de una semana concreta de la asignatura.
	 * 
	 * @param listaArchivos
	 */
	void setListaArchivos(Object listaArchivos);

	/**
	 * Genera una alerta preguntando al usuario si desea ver o descargar un
	 * documento.
	 * 
	 * @param id
	 *            identificador del archivo
	 */
	void crearAlerta(int id);

	/**
	 * Método similar al onClick donde se trata unos botones en concreto
	 * 
	 * @param v
	 *            Contiene la información al archivo seleccionado.
	 */
	void tratarBoton(View v);

	/**
	 * Muestra una barra de progreso
	 * 
	 * @param mensaje
	 *            String con el mensaje a mostrar
	 */
	void mostrarProgreso(String mensaje);

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
