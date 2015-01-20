package rgg.campusvirtualapp.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import rgg.campusvirtualapp.AppMediador;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class TablaTareasSubidas {
	public static final String nombreTabla = "tareasSubidas";
	public static final String CAMPO_CODIGO = "codAsignatura";
	public static final String CAMPO_NOMBRE = "nombreTarea";
	public static final String CAMPO_USUARIO = "username";
	public static final String CAMPO_ARCHIVO = "archivo";

	@SuppressWarnings("unchecked")
	public static void subirTarea(Object tarea, int username) {
		/**
		 * MÈtodo que actualiza la tarea de un usuario aÒadiendo un archivo,
		 * todos los datos necesarios se pasan por par·metro.
		 */
		final AppMediador appMediador = AppMediador.getInstance();
		// busca en la tabla tareas todas las tareas que tienen un codigo de
		// asignatura dado
		@SuppressWarnings("rawtypes")
		ParseQuery query = new ParseQuery(nombreTabla);
		final DatosTarea datos = ((DatosTarea) tarea);
		query.whereEqualTo(CAMPO_CODIGO, datos.getCodAsignatura());
		query.whereEqualTo(CAMPO_NOMBRE, datos.getNombreTarea());
		query.whereEqualTo(CAMPO_USUARIO, username);
		query.whereDoesNotExist(CAMPO_ARCHIVO);
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> registros, ParseException e1) {

				if (registros.size() > 0) {
					// con el resultado obtenido, se guarda en un array de byte
					// el archivo a subir a esta tarea
					File archivo = datos.getArchivo();

					byte[] b = new byte[(int) archivo.length()];
					try {
						@SuppressWarnings("resource")
						FileInputStream fileInputStream = new FileInputStream(
								archivo);

						Log.i("depurador", "he entrado en el try");
						fileInputStream.read(b);
					} catch (FileNotFoundException e) {
						System.out.println("File Not Found.");
						e.printStackTrace();
					} catch (IOException e2) {
						System.out.println("Error Reading The File.");
						e2.printStackTrace();
					}
					Log.i("depurador", "he llegado hasta aqui 1");
					// Obtengo la path del archivo File
					Uri uri = Uri.fromFile(archivo);
					// Elimino cualquier tilde que pueda tener el archivo en el
					// nombre. (Parse no acepta tildes)
					String nombreArchivo = borrarTildes((uri.getPathSegments())
							.get((uri.getPathSegments()).size() - 1));
					// Se crea un ParseFile con el nombre sin tildes y el byte[]
					// anteriomente creado
					ParseFile archivoASubir = new ParseFile(nombreArchivo, b);
					archivoASubir.saveInBackground(); // Guardamos el archivo
					registros.get(0).put(CAMPO_ARCHIVO, archivoASubir);
					registros.get(0).saveInBackground();
					Calendar calendario = Calendar.getInstance();
					SimpleDateFormat formatoFecha = new SimpleDateFormat(
							"dd/MM/yyyy", Locale.UK);
					SimpleDateFormat formatoHora = new SimpleDateFormat(
							"HH:mm", Locale.UK);
					StringBuilder fecha = new StringBuilder(formatoFecha
							.format(calendario.getTime()));
					StringBuilder hora = new StringBuilder(formatoHora
							.format(calendario.getTime()));
					Bundle extras = new Bundle();
					extras.putString("fechaSubida", fecha.toString());
					extras.putString("horaSubida", hora.toString());
					appMediador.sendBroadcast(AppMediador.AVISO_TAREA_SUBIDA,
							extras);
				} else {
					Log.i("depurador", "he llegado hasta aqui 1");
					appMediador.sendBroadcast(AppMediador.AVISO_TAREA_SUBIDA,
							null);
				}
			}

		});

	}

	private static String borrarTildes(String nombreArchivo) {
		// Cadena de caracteres original a sustituir.
		String original = "·‡‰ÈËÎÌÏÔÛÚˆ˙˘uÒ¡¿ƒ…»ÀÕÃœ”“÷⁄Ÿ‹—Á«";
		// Cadena de caracteres ASCII que reemplazar·n los originales.
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
		for (int i = 0; i < original.length(); i++) {
			nombreArchivo = nombreArchivo.replace(original.charAt(i),
					ascii.charAt(i));
		}
		return nombreArchivo;
	}
}
