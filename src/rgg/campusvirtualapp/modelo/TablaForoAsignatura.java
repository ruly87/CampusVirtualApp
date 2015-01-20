package rgg.campusvirtualapp.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rgg.campusvirtualapp.AppMediador;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TablaForoAsignatura {
	public static final String nombreTabla = "foroAsignatura";
	public static final String CAMPO_CODIGO = "codAsignatura";
	public static final String CAMPO_USUARIO = "username";
	public static final String CAMPO_ASUNTO = "asunto";
	public static final String CAMPO_MENSAJE = "mensaje";
	public static final String CAMPO_RESPUESTA = "esRespuesta";
	public static final String CAMPO_FECHA = "fechaEnviado";
	public static final String CAMPO_NRESPUESTAS = "numero_respuestas";
	public static final String CAMPO_ID = "idHilo";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void obtenerForo(final int codAsignatura) {
		/**
		 * Devuelve la lista de temas del foro de una asignatura en concreto.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla tareas todas las tareas que tienen un codigo de
		// asignatura dado
		ParseQuery query = new ParseQuery(
				TablaAsignaturasMatriculadas.nombreTabla);
		query.whereEqualTo(TablaAsignaturasMatriculadas.CAMPO_CODIGO,
				codAsignatura);
		query.addAscendingOrder(TablaAsignaturasMatriculadas.CAMPO_USUARIO);
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

					ParseQuery query = new ParseQuery(TablaUsuarios.nombreTabla);
					query.addAscendingOrder(TablaUsuarios.CAMPO_USUARIO);
					query.findInBackground(new FindCallback<ParseObject>() {
						@Override
						public void done(List<ParseObject> registros,
								ParseException e2) {

							if (e2 == null) {
								// con los resultados obtenidos, se guarda en un
								// vector los nombres de las tareas de esa
								// asignatura
								final String[] nombres = new String[usernames.length];
								for (ParseObject reg : registros) {
									if (esUsuario(
											usernames,
											reg.getNumber(
													TablaUsuarios.CAMPO_USUARIO)
													.intValue()) != -1) {
										nombres[esUsuario(
												usernames,
												reg.getNumber(
														TablaUsuarios.CAMPO_USUARIO)
														.intValue())] = reg
												.getString(TablaUsuarios.CAMPO_NOMBRE);
									}
								}
								ParseQuery query = new ParseQuery(nombreTabla);
								query.whereEqualTo(CAMPO_CODIGO, codAsignatura);
								query.addDescendingOrder("createdAt");
								query.findInBackground(new FindCallback<ParseObject>() {

									@Override
									public void done(
											List<ParseObject> registros,
											ParseException e3) {

										if (e3 == null && registros.size() > 0) {
											// con los resultados obtenidos, se
											// guarda en un vector los nombres
											// de las tareas de esa asignatura
											ArrayList<DatosHilo> foro = new ArrayList<DatosHilo>(
													registros.size());
											int i = 0;
											for (ParseObject reg : registros) {
												Date horapublicada = reg
														.getCreatedAt();
												if (esUsuario(
														usernames,
														reg.getNumber(
																CAMPO_USUARIO)
																.intValue()) != -1)
													foro.add(new DatosHilo(
															reg.getString(CAMPO_ASUNTO),
															nombres[esUsuario(
																	usernames,
																	reg.getNumber(
																			CAMPO_USUARIO)
																			.intValue())],
															reg.getNumber(
																	CAMPO_NRESPUESTAS)
																	.intValue(),
															horapublicada,
															reg.getString(CAMPO_MENSAJE),
															reg.getNumber(
																	CAMPO_RESPUESTA)
																	.intValue(),
															reg.getNumber(
																	CAMPO_ID)
																	.intValue(),
															codAsignatura,
															reg.getNumber(
																	CAMPO_USUARIO)
																	.intValue(),
															i++));
											}
											Bundle extras = new Bundle();
											extras.putSerializable(
													AppMediador.DATOS_ARRAY_FORO,
													foro);
											appMediador.sendBroadcast(
													AppMediador.AVISO_FORO,
													extras);

										} else {
											appMediador.sendBroadcast(
													AppMediador.AVISO_FORO,
													null);
										}
									}

								});

							} else {
								appMediador.sendBroadcast(
										AppMediador.AVISO_FORO, null);
							}
						}

					});

				} else {
					appMediador.sendBroadcast(AppMediador.AVISO_FORO, null);
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public static void publicarTema(Object tema) {
		/**
		 * Método que inserta un tema nuevo en el foro de la asignatura, por
		 * parámetro se pasa un Object que tiene todos los datos necesarios.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla foroAsignatura todos los hilos que tienen una
		// asignatura
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		final DatosHilo datos = ((DatosHilo) tema);
		query.whereEqualTo(CAMPO_CODIGO, datos.getCodAsignatura());
		query.addAscendingOrder(CAMPO_ID);
		
		Log.i("depurador","tft 1");
		
		query.findInBackground(new FindCallback<ParseObject>() {

			@SuppressWarnings("rawtypes")
			@Override
			public void done(List<ParseObject> registros, ParseException e1) {

				Log.i("depurador","tft 2"+ " "+e1);
				
				if (e1 == null) {
					for (ParseObject reg : registros) {
						if (datos.getEsRespuesta() != 0
								&& reg.getNumber(CAMPO_ID).intValue() == datos
										.getEsRespuesta()) {
							reg.put(CAMPO_NRESPUESTAS,
									reg.getNumber(CAMPO_NRESPUESTAS).intValue() + 1);
							reg.saveInBackground();
						}
					}
					if (registros.size() != 0) {
						ParseObject ultimoTema = registros.get(registros.size() - 1);
						datos.setId(ultimoTema.getNumber(CAMPO_ID).intValue() + 1);
					}
					if (0 == datos.getAsunto().compareTo(""))
						datos.setAsunto("Sin asunto");
					ParseObject po = new ParseObject(nombreTabla);
					po.put(CAMPO_USUARIO, datos.getUsername());
					po.put(CAMPO_ASUNTO, datos.getAsunto());
					po.put(CAMPO_FECHA, (Date) datos.getHorapublicacion());
					po.put(CAMPO_ID, datos.getId());
					po.put(CAMPO_MENSAJE, datos.getMensaje());
					po.put(CAMPO_NRESPUESTAS, datos.getNrespuestas());
					po.put(CAMPO_RESPUESTA, datos.getEsRespuesta());
					po.put(CAMPO_CODIGO, datos.getCodAsignatura());
					po.saveInBackground();
					// Recupero el nombre del usuario
					ParseQuery query = new ParseQuery(TablaUsuarios.nombreTabla);
					query.whereEqualTo(TablaUsuarios.CAMPO_USUARIO,
							datos.getUsername());
					query.findInBackground(new FindCallback<ParseObject>() {

						@Override
						public void done(List<ParseObject> registros,
								ParseException e1) {

							if (registros.size() == 1 && e1 == null) {
								Bundle extras = new Bundle();
								datos.setAutor(registros.get(0).getString(
										TablaUsuarios.CAMPO_NOMBRE));
								if (datos.getEsRespuesta() == 0) {
									ParseQuery query = new ParseQuery(
											TablaAsignaturasMatriculadas.nombreTabla);
									query.whereEqualTo(
											TablaAsignaturasMatriculadas.CAMPO_CODIGO,
											datos.getCodAsignatura());
									query.addAscendingOrder(TablaAsignaturasMatriculadas.CAMPO_USUARIO);
									query.findInBackground(new FindCallback<ParseObject>() {

										@Override
										public void done(
												List<ParseObject> registros,
												ParseException e) {

											if (e == null) {

												for (ParseObject reg : registros) {
													if (reg.getNumber(
															CAMPO_USUARIO)
															.intValue() != datos
															.getUsername()) {
														ParseObject po = new ParseObject(
																TablaNotificaciones.nombreTabla);
														po.put(TablaNotificaciones.CAMPO_USUARIO,
																reg.getNumber(
																		TablaAsignaturasMatriculadas.CAMPO_USUARIO)
																		.intValue());
														po.put(TablaNotificaciones.CAMPO_ASUNTO,
																datos.getAsunto());
														po.put(TablaNotificaciones.CAMPO_AUTOR,
																datos.getAutor());
														po.put(TablaNotificaciones.CAMPO_MENSAJE,
																datos.getMensaje());
														po.put(TablaNotificaciones.CAMPO_VISTA,
																false);
														po.saveInBackground();
													}
												}
											}
										}
									});
								}
								extras.putSerializable(AppMediador.DATOS_FORO,
										datos);
								extras.putBoolean("enviado", true);
								appMediador.sendBroadcast(
										AppMediador.AVISO_TEMA_ENVIADO, extras);
							} else {
								// Se ha publicado pero no ha generado las
								// notificaciones a los participantes
								Bundle extras = new Bundle();
								extras.putBoolean("enviado", true);
								appMediador.sendBroadcast(
										AppMediador.AVISO_TEMA_ENVIADO, extras);
							}
						}
					});
				} else {
					// No se ha publicado el nuevo tema.
					Bundle extras = new Bundle();
					extras.putBoolean("enviado", false);
					appMediador.sendBroadcast(AppMediador.AVISO_TEMA_ENVIADO,
							extras);
				}
			}

		});
	}

	private static int esUsuario(int[] usernames, int username) {
		for (int i = 0; i < usernames.length; i++)
			if (usernames[i] == username)
				return i;
		return -1;
	}
}
