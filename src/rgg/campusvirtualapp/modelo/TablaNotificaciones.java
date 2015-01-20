package rgg.campusvirtualapp.modelo;

import java.util.ArrayList;
import java.util.List;

import rgg.campusvirtualapp.AppMediador;
import android.os.Bundle;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TablaNotificaciones {
	public static final String nombreTabla = "notificaciones";
	public static final String CAMPO_USUARIO = "username";
	public static final String CAMPO_AUTOR = "autor";
	public static final String CAMPO_ASUNTO = "asunto";
	public static final String CAMPO_MENSAJE = "mensaje";
	public static final String CAMPO_VISTA = "recibido";

	@SuppressWarnings("unchecked")
	public static void obtenerNotificaciones(int username) {
		/**
		 * Devuelve un Object en el que están todas las notificaciones de un
		 * usuario.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla notificaciones todas las notificaciones recibidas a
		// un usuario
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_USUARIO, username);
		query.addDescendingOrder("createdAt");
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				if (e == null) {
					// Solo devolverá un unico registro ya que solo puede
					// existir una asignatura con un mismo codigo
					ArrayList<DatosNotificaciones> datos = new ArrayList<DatosNotificaciones>(
							registros.size());
					for (ParseObject reg : registros) {
						datos.add(new DatosNotificaciones(reg.getNumber(
								CAMPO_USUARIO).intValue(), reg
								.getString(CAMPO_AUTOR), reg.getCreatedAt(),
								reg.getString(CAMPO_MENSAJE), reg
										.getString(CAMPO_ASUNTO)));
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_NOTIFICACIONES,
							datos);
					appMediador.sendBroadcast(AppMediador.AVISO_NOTIFICACIONES,
							extras);
				} else {
					appMediador.sendBroadcast(AppMediador.AVISO_NOTIFICACIONES,
							null);
				}
			}

		});
	}

	@SuppressWarnings("unchecked")
	public static void obtenerNotificacionesNuevas(int username) {
		/**
		 * Devuelve un Object en el que están todas las notificaciones de un
		 * usuario.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla notificaciones todas las notificaciones recibidas a
		// un usuario
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		query.whereEqualTo(CAMPO_USUARIO, username);
		query.whereEqualTo(CAMPO_VISTA, false);
		query.addDescendingOrder("createdAt");
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e) {

				if (registros.size() > 0 && e == null) {
					// Solo devolverá un unico registro ya que solo puede
					// existir una asignatura con un mismo codigo
					ArrayList<DatosNotificaciones> datos = new ArrayList<DatosNotificaciones>(
							registros.size());
					for (ParseObject reg : registros) {
						datos.add(new DatosNotificaciones(reg.getNumber(
								CAMPO_USUARIO).intValue(), reg
								.getString(CAMPO_AUTOR), reg.getCreatedAt(),
								reg.getString(CAMPO_MENSAJE), reg
										.getString(CAMPO_ASUNTO)));
						reg.put(CAMPO_VISTA, true);
						reg.saveInBackground();
					}
					Bundle extras = new Bundle();
					extras.putSerializable(AppMediador.DATOS_NOTIFICACIONES,
							datos);
					appMediador.sendBroadcast(
							AppMediador.AVISO_NOTIFICACIONES_NUEVAS, extras);
				} else {
					appMediador.sendBroadcast(
							AppMediador.AVISO_NOTIFICACIONES_NUEVAS, null);
				}
			}

		});
	}
}
