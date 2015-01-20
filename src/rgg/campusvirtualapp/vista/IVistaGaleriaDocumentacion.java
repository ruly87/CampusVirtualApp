package rgg.campusvirtualapp.vista;

import android.view.View;

public interface IVistaGaleriaDocumentacion {
	/**
	 * Método encargado de construir la galería de semanas
	 * 
	 * @param galeria
	 */
	void construirGaleria(Object galeria);

	/**
	 * Método similar al onClick donde se trata unos botones en concreto
	 * 
	 * @param v
	 *            Contiene la información a la semana seleccionada.
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
	 * Método que modifica el tipo de fuente de la vista
	 * 
	 * @param tipo
	 *            recibe el Typeface correspondiente
	 */
	void tratarFormato(Object tipo);
}
