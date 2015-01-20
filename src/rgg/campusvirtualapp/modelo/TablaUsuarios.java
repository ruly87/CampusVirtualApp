package rgg.campusvirtualapp.modelo;

import java.util.ArrayList;
import java.util.List;

import rgg.campusvirtualapp.AppMediador;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class TablaUsuarios {
	public static final String nombreTabla = "usuarios";
	public static final String CAMPO_USUARIO = "username";
	public static final String CAMPO_NOMBRE = "nombre";
	public static final String CAMPO_EMAIL = "email";
	public static final String CAMPO_FOTO = "foto";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void obtenerNombres(final int username, int codAsignatura) {
		/**
		 * Devuelve un Object que contendrá todos los nombres que contenga el
		 * parámetro que se ha pasado.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla matriculados todos los que tienen un identificador
		// de asignatura dado
		ParseQuery query = new ParseQuery(
				TablaAsignaturasMatriculadas.nombreTabla);
		query.whereEqualTo(TablaAsignaturasMatriculadas.CAMPO_CODIGO,
				codAsignatura);

		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				if (e == null) {
					// con los resultados obtenidos, se guarda en un vector los
					// dnis de los alumnos matriculados en esa asignatura
					final int[] usernames = new int[registros.size()];
					int i = 0;
					for (ParseObject reg : registros) {
						usernames[i++] = reg.getNumber(
								TablaAsignaturasMatriculadas.CAMPO_USUARIO)
								.intValue();
					}

					// busca en la tabla alumnos todos los alumnos
					ParseQuery query = new ParseQuery(nombreTabla);
					query.addAscendingOrder(CAMPO_NOMBRE);
					query.findInBackground(new FindCallback<ParseObject>() {

						@Override
						public void done(List<ParseObject> registros,
								ParseException e) {

							if (e == null) {
								// con los resultados obtenidos se buscan los
								// usernames obtenidos en la búsqueda anterior

								ArrayList<DatosParticipantes> lista = new ArrayList<DatosParticipantes>();
								for (ParseObject reg : registros) {
									if (esUsuario(usernames,
											reg.getNumber(CAMPO_USUARIO)
													.intValue())
											&& username != reg.getNumber(
													CAMPO_USUARIO).intValue()) {
										DatosParticipantes datos = new DatosParticipantes(
												reg.getNumber(CAMPO_USUARIO)
														.intValue(),
												reg.getString(CAMPO_NOMBRE));
										lista.add(datos);
									}
								}
								Bundle extras = new Bundle();
								extras.putSerializable(
										AppMediador.DATOS_ARRAY_PARTICIPANTES,
										lista);
								appMediador
										.sendBroadcast(
												AppMediador.AVISO_PARTICIPANTES,
												extras);
							} else {
								appMediador.sendBroadcast(
										AppMediador.AVISO_PARTICIPANTES, null);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void obtenerDetalles(int username) {
		/**
		 * Devuelve el valor del nombre y email.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla matriculados todos los que tienen un identificador
		// de asignatura dado

		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_USUARIO, username);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(final List<ParseObject> registros, ParseException e) {

				if (e == null) {
					// con los resultados obtenidos se buscan los usernames
					// obtenidos en la búsqueda anterior
						registros.get(0).getParseFile(CAMPO_FOTO).getDataInBackground(new GetDataCallback() {
						public void done(byte[] data, ParseException e) {
							    if (e == null) {
							    	Bitmap bmp;
									try {
										bmp = BitmapFactory.decodeByteArray(data, 0,
												registros.get(0).getParseFile(CAMPO_FOTO)
														.getData().length);
										Bundle extras = new Bundle();
										extras.putSerializable(AppMediador.DATOS_PARTICIPANTES,
												new DatosParticipantes(registros.get(0)
														.getNumber(CAMPO_USUARIO).intValue(), registros
														.get(0).getString(CAMPO_NOMBRE), bmp, registros
														.get(0).getString(CAMPO_EMAIL)));
										appMediador.sendBroadcast(
												AppMediador.AVISO_DETALLES_PARTICIPANTES, extras);
									} catch (ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									

							    } else {
							    	
							    }
							  }
							});	
				} else {
					appMediador.sendBroadcast(
							AppMediador.AVISO_DETALLES_PARTICIPANTES, null);
				}
			}

		});
	}

	public static void comprobarLogin(String username, String password) {
		/**
		 * Este método comprueba si el usuario es correcto durante el proceso de
		 * autenticación.
		 */
		ParseUser.logInInBackground(username, password, new LogInCallback() {

			@Override
			public void done(ParseUser user, ParseException e) {
				if (e == null && user != null) {
					Bundle extras = new Bundle();
					// Envía la notificación para que el presentador de login
					// sepa que ya ha terminado el inicio de sesión.
					// Si  hay error en el inicio (user==null), extras
					// almacena null. Si no hay error, extras almacena un valor
					// distinto de null.
					AppMediador.getInstance().sendBroadcast(
							AppMediador.AVISO_LOGIN, extras);
				} 
				else {
					AppMediador.getInstance().sendBroadcast(
							AppMediador.AVISO_LOGIN, null);
				}
				
			}

		});
	}

	private static boolean esUsuario(int[] usernames, int username) {
		for (int i = 0; i < usernames.length; i++)
			if (usernames[i] == username)
				return true;
		return false;
	}
}
