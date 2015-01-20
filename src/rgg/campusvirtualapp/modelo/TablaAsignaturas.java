package rgg.campusvirtualapp.modelo;

import java.util.List;

import rgg.campusvirtualapp.AppMediador;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TablaAsignaturas {
	public static final String nombreTabla = "asignaturas";
	public static final String CAMPO_CODIGO = "codAsignatura";
	public static final String CAMPO_NOMBRE = "nombreAsignatura";
	public static final String CAMPO_CURSO = "curso";
	public static final String CAMPO_CUATRIMESTRE = "cuatrimestre";
	public static final String CAMPO_CARRERA = "carrera";

	@SuppressWarnings("unchecked")
	public static void obtenerNombreAsignatura(int codAsignatura) {
		/**
		 * Devuelve el valor del nombreAsignatura.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla matriculados todos los que tienen un identificador
		// de asignatura dado
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_CODIGO, codAsignatura);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				if (e == null) {
					// Solo devolverá un unico registro ya que solo puede
					// existir una asignatura con un mismo codigo

					int i = 0;
					DatosAsignatura datos = new DatosAsignatura(registros
							.get(0).getNumber(CAMPO_CODIGO).intValue(),
							registros.get(i).getString(CAMPO_NOMBRE), registros
									.get(0).getNumber(CAMPO_CURSO).intValue(),
							registros.get(0).getNumber(CAMPO_CUATRIMESTRE)
									.intValue(), registros.get(i).getString(
									CAMPO_CARRERA));
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_ASIGNATURA, datos);
					appMediador.sendBroadcast(AppMediador.AVISO_ASIGNATURAS,
							extras);
				} else {
					appMediador.sendBroadcast(AppMediador.AVISO_ASIGNATURAS,
							null);
				}
			}

		});
	}

}
