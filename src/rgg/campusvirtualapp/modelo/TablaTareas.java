package rgg.campusvirtualapp.modelo;

import java.util.ArrayList;
import java.util.List;

import rgg.campusvirtualapp.AppMediador;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TablaTareas {
	public static final String nombreTabla = "tareas";
	public static final String CAMPO_CODIGO = "codAsignatura";
	public static final String CAMPO_DETALLES = "detallesTarea";
	public static final String CAMPO_NOMBRE = "nombreTarea";

	@SuppressWarnings("unchecked")
	public static void obtenerTareas(int codAsignatura) {
		/**
		 * Devuelve una lista de tareas de una asignatura en concreto.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla tareas todas las tareas que tienen un codigo de
		// asignatura dado
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);

		query.whereEqualTo(CAMPO_CODIGO, codAsignatura);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				if (e == null) {
					// con los resultados obtenidos, se guarda en un vector los
					// nombres de las tareas de esa asignatura
					ArrayList<DatosTarea> tarea = new ArrayList<DatosTarea>(
							registros.size());
					int i = 0;
					for (ParseObject reg : registros) {
						for (int j = 0; j <= i; j++)
							tarea.add(new DatosTarea(reg
									.getNumber(CAMPO_CODIGO).intValue(), reg
									.getString(CAMPO_NOMBRE), reg
									.getString(CAMPO_DETALLES)));
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_TAREAS, tarea);
					appMediador.sendBroadcast(AppMediador.AVISO_TAREAS, extras);

				} else {
					appMediador.sendBroadcast(AppMediador.AVISO_TAREAS, null);
				}
			}

		});
	}
}
