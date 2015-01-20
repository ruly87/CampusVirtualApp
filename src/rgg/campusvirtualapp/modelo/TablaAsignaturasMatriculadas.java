package rgg.campusvirtualapp.modelo;

import java.util.ArrayList;
import java.util.List;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.vista.R;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TablaAsignaturasMatriculadas {
	public static final String nombreTabla = "asignaturasMatriculadas";
	public static final String CAMPO_USUARIO = "username";
	public static final String CAMPO_CODIGO = "codAsignatura";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	/**
	 * Devuelve una lista de las asignaturas matriculadas de un usuario en concreto.
	 * @param username identificador del usuario
	 */
	public static void solicitarLista(int username) {
		/**
		 * 
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla asignaturasMatriculadas todas las asignaturas que
		// tiene un usuario concreto
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_USUARIO, username);

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				if (e == null) {
					// con los resultados obtenidos, se guarda en un vector los
					// codAsignaturas de los asignaturas matriculadas de un
					// usuario
					final int[] codAsignaturas = new int[registros.size()];
					int i = 0;
					for (ParseObject reg : registros) {
						codAsignaturas[i++] = reg.getNumber(CAMPO_CODIGO)
								.intValue();
					}

					// busca en la tabla alumnos todos los alumnos
					ParseQuery query = new ParseQuery(
							TablaAsignaturas.nombreTabla);
					query.findInBackground(new FindCallback<ParseObject>() {

						@Override
						public void done(List<ParseObject> registros,
								ParseException e) {

							if (e == null) {
								// con los resultados obtenidos se buscan los
								// codAsignaturas obtenidos en la búsqueda
								// anterior
								// para almacenar la información de cada
								// asignatura
								ArrayList<DatosAsignatura> lista = new ArrayList<DatosAsignatura>();
								for (ParseObject reg : registros) {
									if (esAsignatura(
											codAsignaturas,
											reg.getNumber(
													TablaAsignaturas.CAMPO_CODIGO)
													.intValue())) {
										DatosAsignatura datos = new DatosAsignatura(
												reg.getNumber(CAMPO_CODIGO)
														.intValue(),
												reg.getString(TablaAsignaturas.CAMPO_NOMBRE),
												reg.getNumber(
														TablaAsignaturas.CAMPO_CURSO)
														.intValue(),
												reg.getNumber(
														TablaAsignaturas.CAMPO_CUATRIMESTRE)
														.intValue(),
												reg.getString(TablaAsignaturas.CAMPO_CARRERA));
										lista.add(datos);
									}
								}
								Bundle extras = new Bundle();
								extras.putString(AppMediador.TITULO,
										appMediador.getApplicationContext()
												.getString(R.string.app_name));
								extras.putSerializable(
										AppMediador.DATOS_ASIGNATURAS, lista);
								appMediador.sendBroadcast(
										AppMediador.AVISO_ASIGNATURAS, extras);
							} else {
								appMediador.sendBroadcast(
										AppMediador.AVISO_ASIGNATURAS, null);
							}
						}

					});
				} else {
					appMediador.sendBroadcast(AppMediador.AVISO_ASIGNATURAS,
							null);
				}
			}

		});
	}

	private static boolean esAsignatura(int[] codAsignaturas, int codAsignatura) {
		for (int i = 0; i < codAsignaturas.length; i++)
			if (codAsignaturas[i] == codAsignatura)
				return true;
		return false;
	}
}
