package rgg.campusvirtualapp.modelo;

import java.util.List;

import rgg.campusvirtualapp.AppMediador;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TablaDocumentacion {
	public static final String nombreTabla = "documentacion";
	public static final String CAMPO_CODIGO = "codAsignatura";
	public static final String CAMPO_SEMANA = "semana";
	public static final String CAMPO_ARCHIVO = "archivo";
	public static final String CAMPO_NOMBRE = "nombre";
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void obtenerSemana(int codAsignatura) {
		/**
		 * Devuelve el valor de la semana.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla documentacion todas las semanas que tienen un
		// codigo de asignatura dado
		ParseQuery query = new ParseQuery(nombreTabla);

		query.whereEqualTo(CAMPO_CODIGO, codAsignatura);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				if (e == null) {
					// con los resultados obtenidos, se guarda en un vector los
					// dnis de los alumnos matriculados en esa asignatura
					int[] semanas = new int[registros.size()];// = new
																// int[registros.size()];
					int i = 0;
					for (ParseObject reg : registros) {
						for (int j = 0; j <= i; j++)
							if (!esRepetido(semanas, reg
									.getNumber(CAMPO_SEMANA).intValue()))
								semanas[i++] = reg.getNumber(CAMPO_SEMANA)
										.intValue();
					}
					int[] semana = new int[i];
					for (int j = 0; j < i; j++)
						semana[j] = semanas[j];
					Bundle extras = new Bundle();
					extras.putIntArray("semana", semana);
					appMediador
							.sendBroadcast(AppMediador.AVISO_SEMANAS, extras);

				} else {
					appMediador.sendBroadcast(AppMediador.AVISO_SEMANAS, null);
				}
			}

		});
	}

	private static boolean esRepetido(int[] semanas, int semana) {
		for (int i = 0; i < semanas.length; i++)
			if (semanas[i] == semana)
				return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	public static void descargarArchivo(int codAsignatura, int semana,
			String nombre) {
		/**
		 * Devuelve el valor del archivo.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla documentacion un archivo en concreto a descargar
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		
		query.whereEqualTo(CAMPO_CODIGO, codAsignatura);
		query.whereEqualTo(CAMPO_SEMANA, semana);
		query.whereEqualTo(CAMPO_NOMBRE, nombre);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(final List<ParseObject> registros, ParseException e) {

				if (e == null) {
					// con el resultado obtenidos, se guarda en el archivo en un
					// ParseFile
					ParseFile archivo;
					archivo = registros.get(0).getParseFile(CAMPO_ARCHIVO);

					archivo.getDataInBackground(new GetDataCallback() {
						public void done(byte[] data, ParseException e) {
							    if (e == null) {
							    	Bundle extras = new Bundle();
							    	extras.putByteArray("archivo", data);
							    	extras.putString("nombreArchivo", registros.get(0)
											.getString(CAMPO_NOMBRE));
									appMediador
											.sendBroadcast(AppMediador.AVISO_ARCHIVO, extras);
							    } else {
							    	
							    }
							  }
							});
					

				} else {
					appMediador.sendBroadcast(AppMediador.AVISO_ARCHIVO, null);
				}
			}

		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void obtenerDocumentacion(int semana, int codAsignatura) {
		/**
		 * 
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla documentacion todas los nombres de una semana que
		// tienen un codigo de asignatura dado
		ParseQuery query = new ParseQuery(nombreTabla);

		query.whereEqualTo(CAMPO_CODIGO, codAsignatura);
		query.whereEqualTo(CAMPO_SEMANA, semana);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				if (e == null) {
					// con los resultados obtenidos, se guarda en un vector los
					// dnis de los alumnos matriculados en esa asignatura
					String[] nombreArchivo = new String[registros.size()];// =
																			// new
																			// int[registros.size()];
					int i = 0;
					for (ParseObject reg : registros) {
						nombreArchivo[i++] = reg.getString(CAMPO_NOMBRE);
					}

					Bundle extras = new Bundle();
					extras.putStringArray("archivos", nombreArchivo);
					appMediador.sendBroadcast(AppMediador.AVISO_DOCUMENTACION,
							extras);

				} else {
					appMediador.sendBroadcast(AppMediador.AVISO_DOCUMENTACION,
							null);
				}
			}

		});
	}
}
