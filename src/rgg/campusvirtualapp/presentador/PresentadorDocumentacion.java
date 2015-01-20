package rgg.campusvirtualapp.presentador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.vista.IVistaDocumentacion;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.LinearLayout;

public class PresentadorDocumentacion implements IPresentadorDocumentacion {
	AppMediador appMediador;
	String[] nombreArchivos = null;
	int semana = 0;
	int estado;
	String path = "/Download/";
	private BroadcastReceiver receptorDocumentos = new BroadcastReceiver() {
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaDocumentacion vistaDocumentacion = appMediador
					.getVistaDocumentacion();
			if (intent.getAction().equals(AppMediador.AVISO_DOCUMENTACION)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					nombreArchivos = extras.getStringArray("archivos");
					ArrayList<String> datos = new ArrayList<String>();
					for (int j = 0; j < nombreArchivos.length; j++)
						datos.add(nombreArchivos[j]);
					ArrayList<LinearLayout> galeria = new ArrayList<LinearLayout>(
							datos.size() / 2);
					int i = 0;
					while (i < datos.size()) {
						if (i == (datos.size() - 1)) {
							LinearLayout ll = (LinearLayout) ((Activity) vistaDocumentacion)
									.getLayoutInflater()
									.inflate(
											R.layout.filas_impares_documentacion_archivos,
											null);
							Button boton = (Button) ll
									.findViewById(R.id.archivo1);
							boton.setText(datos.get(i));
							boton.setId(i);
							galeria.add(ll);
						} else {
							LinearLayout ll = (LinearLayout) ((Activity) vistaDocumentacion)
									.getLayoutInflater()
									.inflate(
											R.layout.filas_pares_documentacion_archivos,
											null);
							Button boton = (Button) ll
									.findViewById(R.id.archivo1);
							boton.setText(datos.get(i));
							boton.setId(i);
							i++;
							boton = (Button) ll.findViewById(R.id.archivo2);
							boton.setText(datos.get(i));
							boton.setId(i);
							galeria.add(ll);
						}
						i++;
					}
					vistaDocumentacion.setListaArchivos(galeria);
					vistaDocumentacion.eliminarProgreso();
				}
			}
			if (intent.getAction().equals(AppMediador.AVISO_ARCHIVO)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					if (estado == 1) {
						byte[] datosArchivo = extras.getByteArray("archivo");
						if (!(new File(
								Environment.getExternalStorageDirectory()
										+ path
										+ extras.getString("nombreArchivo")))
								.exists()) {
							try {
								FileOutputStream out = new FileOutputStream(
										Environment
												.getExternalStorageDirectory()
												+ path
												+ extras.getString("nombreArchivo"));
								out.write(datosArchivo);
								out.close();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						vistaDocumentacion.eliminarProgreso();
						File file = new File(
								Environment.getExternalStorageDirectory()
										+ path
										+ extras.getString("nombreArchivo"));
						appMediador.launchActivity(file);
					}
					if (estado == 2) {
						byte[] datosArchivo = extras.getByteArray("archivo");
						if (!(new File(
								Environment.getExternalStorageDirectory()
										+ path
										+ extras.getString("nombreArchivo")))
								.exists()) {
							try {
								FileOutputStream out = new FileOutputStream(
										Environment
												.getExternalStorageDirectory()
												+ path
												+ extras.getString("nombreArchivo"));
								out.write(datosArchivo);
								out.close();
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						vistaDocumentacion.eliminarProgreso();
					}

				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void pedirNombresArchivos(Object item) {
		appMediador = AppMediador.getInstance();
		IVistaDocumentacion vistaDocumentacion = appMediador
				.getVistaDocumentacion();
		Bundle extras = new Bundle();
		extras = ((Intent) item).getExtras();
		if (extras != null
				&& (nombreArchivos == null || semana != extras.getInt("semana"))) {
			semana = extras.getInt("semana");
			appMediador.getModelo().solicitarNombres(semana);
			vistaDocumentacion.mostrarProgreso(((Activity) vistaDocumentacion)
					.getString(R.string.mensaje_documentacion));
			appMediador.registerReceiver(receptorDocumentos,
					new String[] { AppMediador.AVISO_DOCUMENTACION });
		} else {
			ArrayList<String> datos = new ArrayList<String>();
			for (int j = 0; j < nombreArchivos.length; j++)
				datos.add(nombreArchivos[j]);
			ArrayList<LinearLayout> galeria = new ArrayList<LinearLayout>(
					datos.size() / 2);
			int i = 0;
			while (i < datos.size()) {
				if (i == (datos.size() - 1)) {
					LinearLayout ll = (LinearLayout) ((Activity) vistaDocumentacion)
							.getLayoutInflater()
							.inflate(
									R.layout.filas_impares_documentacion_archivos,
									null);
					Button boton = (Button) ll.findViewById(R.id.archivo1);
					boton.setText(datos.get(i));
					boton.setId(i);
					galeria.add(ll);
				} else {
					LinearLayout ll = (LinearLayout) ((Activity) vistaDocumentacion)
							.getLayoutInflater()
							.inflate(
									R.layout.filas_pares_documentacion_archivos,
									null);
					Button boton = (Button) ll.findViewById(R.id.archivo1);
					boton.setText(datos.get(i));
					boton.setId(i);
					i++;
					boton = (Button) ll.findViewById(R.id.archivo2);
					boton.setText(datos.get(i));
					boton.setId(i);
					galeria.add(ll);
				}
				i++;
			}
			vistaDocumentacion.setListaArchivos(galeria);
		}

	}

	@Override
	public void tratarAccionVer(int accion) {
		appMediador = AppMediador.getInstance();
		appMediador
				.getModelo()
				.solicitarArchivo(
						(String) ((Button) ((Activity) appMediador.getVistaDocumentacion())
								.findViewById(accion)).getText(), semana);
		IVistaDocumentacion vistaDocumentacion = appMediador
				.getVistaDocumentacion();
		vistaDocumentacion.mostrarProgreso(((Activity) vistaDocumentacion)
				.getString(R.string.mensaje_accion_documentacion));
		estado = 1;
		appMediador.registerReceiver(receptorDocumentos,
				new String[] { AppMediador.AVISO_ARCHIVO });

	}

	@Override
	public void tratarAccionDescargar(int accion) {
		appMediador = AppMediador.getInstance();
		appMediador
				.getModelo()
				.solicitarArchivo(
						(String) ((Button) ((Activity) appMediador.getVistaDocumentacion())
								.findViewById(accion)).getText(), semana);
		IVistaDocumentacion vistaDocumentacion = appMediador
				.getVistaDocumentacion();
		vistaDocumentacion.mostrarProgreso(((Activity) vistaDocumentacion)
				.getString(R.string.mensaje_accion_documentacion));
		estado = 2;
		appMediador.registerReceiver(receptorDocumentos,
				new String[] { AppMediador.AVISO_ARCHIVO });
	}

	@Override
	public void volverInicio() {
		AppMediador appMediador = AppMediador.getInstance();
		((Activity) appMediador.getVistaDocumentacion()).finish();
		((Activity) appMediador.getVistaGaleriaDocumentacion()).finish();
		((Activity) appMediador.getVistaPaginaAsignatura()).finish();
		appMediador.launchActivity(appMediador.getVistaParaPaginaAsignatura(),
				appMediador.getVistaDocumentacion(), null);

	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaDocumentacion(), 1, null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaDocumentacion(), null);
			break;
		case 3:
			appMediador.launchActivity(
					appMediador.getVistaParaNotificacionesForo(),
					appMediador.getVistaParaDocumentacion(), null);
			break;
		}
	}

	@Override
	public void actualizaPreferencias() {

		SharedPreferences ajustes = PreferenceManager
				.getDefaultSharedPreferences(AppMediador.getInstance()
						.getApplicationContext());
		String idioma = ajustes.getString("idioma", "NULL");
		String tipoLetra = ajustes.getString("tipoLetra", "NULL");
		Locale locale;
		if (idioma.compareTo("Spanish") == 0
				|| idioma.compareTo("Español") == 0)
			locale = new Locale("es_ES");
		else
			locale = new Locale("en_EN");
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		AppMediador.getInstance().getApplicationContext().getResources()
				.updateConfiguration(config, null);
		appMediador = AppMediador.getInstance();
		if (tipoLetra.compareTo("Normal") == 0) {
			appMediador.getVistaDocumentacion().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaDocumentacion().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaDocumentacion().tratarFormato(
							Typeface.SERIF);
				} else {
					appMediador.getVistaDocumentacion().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}

}
