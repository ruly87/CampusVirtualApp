package rgg.campusvirtualapp.vista;

import android.view.View;

public interface IVistaGaleriaDocumentacion {
	/**
	 * M�todo encargado de construir la galer�a de semanas
	 * 
	 * @param galeria
	 */
	void construirGaleria(Object galeria);

	/**
	 * M�todo similar al onClick donde se trata unos botones en concreto
	 * 
	 * @param v
	 *            Contiene la informaci�n a la semana seleccionada.
	 */
	void tratarSemana(View v);

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
