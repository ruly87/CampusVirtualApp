package rgg.campusvirtualapp.vista;

import android.view.View;

public interface IVistaGaleriaTarea {
	/**
	 * Asigna las tareas disponibles de una asignatura.
	 * 
	 * @param listaTareas
	 *            Objeto con la lista de tareas de una asignatura
	 */
	void setListTareas(Object listaTareas);

	/**
	 * Método similar al onClick donde se trata unos botones en concreto
	 * 
	 * @param v
	 *            Contiene la información a la tarea seleccionada.
	 */
	void tratarTarea(View v);

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
