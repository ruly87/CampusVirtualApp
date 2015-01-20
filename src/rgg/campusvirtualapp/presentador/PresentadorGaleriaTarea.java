package rgg.campusvirtualapp.presentador;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.LinearLayout;
import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.modelo.DatosTarea;
import rgg.campusvirtualapp.vista.IVistaGaleriaTarea;

public class PresentadorGaleriaTarea implements IPresentadorGaleriaTarea {
	AppMediador appMediador;
	ArrayList<DatosTarea> datos = null;
	private BroadcastReceiver receptorTareas = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaGaleriaTarea vistaGaleriaTarea = appMediador
					.getVistaGaleriaTarea();
			if (intent.getAction().equals(AppMediador.AVISO_TAREAS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					datos = (ArrayList<DatosTarea>) extras
							.getSerializable(AppMediador.DATOS_TAREAS);
					int i = 0;
					ArrayList<LinearLayout> galeria = new ArrayList<LinearLayout>(
							datos.size() / 2);
					while (i < datos.size()) {
						if (i == (datos.size() - 1)) {
							LinearLayout ll = (LinearLayout) ((Activity) (appMediador
									.getVistaGaleriaTarea()))
									.getLayoutInflater()
									.inflate(
											R.layout.fila_impares_galeria_tareas,
											null);
							Button boton = (Button) ll
									.findViewById(R.id.tarea1);
							boton.setText(datos.get(i).getNombreTarea());
							boton.setId(i);
							galeria.add(ll);
						} else {
							LinearLayout ll = (LinearLayout) ((Activity) (appMediador
									.getVistaGaleriaTarea()))
									.getLayoutInflater().inflate(
											R.layout.fila_pares_galeria_tareas,
											null);
							Button boton = (Button) ll
									.findViewById(R.id.tarea1);
							boton.setText(datos.get(i).getNombreTarea());
							boton.setId(i);
							i++;
							boton = (Button) ll.findViewById(R.id.tarea2);
							boton.setText(datos.get(i).getNombreTarea());
							boton.setId(i);
							galeria.add(ll);
						}
						i++;
					}
					vistaGaleriaTarea.setListTareas(galeria);
					vistaGaleriaTarea.eliminarProgreso();
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void recuperarTareas() {
		appMediador = AppMediador.getInstance();
		if (datos == null) {
			appMediador.getModelo().recuperarTareas();
			appMediador.getVistaGaleriaTarea().mostrarProgreso();
			appMediador.registerReceiver(receptorTareas,
					new String[] { AppMediador.AVISO_TAREAS });
		} else {
			int i = 0;
			ArrayList<LinearLayout> galeria = new ArrayList<LinearLayout>(
					datos.size() / 2);
			while (i < datos.size()) {
				if (i == (datos.size() - 1)) {
					LinearLayout ll = (LinearLayout) ((Activity) (appMediador
							.getVistaGaleriaTarea()))
							.getLayoutInflater()
							.inflate(R.layout.fila_impares_galeria_tareas, null);
					Button boton = (Button) ll.findViewById(R.id.tarea1);
					boton.setText(datos.get(i).getNombreTarea());
					boton.setId(i);
					galeria.add(ll);
				} else {
					LinearLayout ll = (LinearLayout) ((Activity) (appMediador
							.getVistaGaleriaTarea())).getLayoutInflater()
							.inflate(R.layout.fila_pares_galeria_tareas, null);
					Button boton = (Button) ll.findViewById(R.id.tarea1);
					boton.setText(datos.get(i).getNombreTarea());
					boton.setId(i);
					i++;
					boton = (Button) ll.findViewById(R.id.tarea2);
					boton.setText(datos.get(i).getNombreTarea());
					boton.setId(i);
					galeria.add(ll);
				}
				i++;
			}
			appMediador.getVistaGaleriaTarea().setListTareas(galeria);
		}
	}

	@Override
	public void tratarTarea(int posicion) {
		Bundle extras = new Bundle();
		extras.putSerializable(AppMediador.DATOS_TAREAS, datos.get(posicion));
		appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaTarea(),
				appMediador.getVistaGaleriaTarea(), extras);
	}

	@Override
	public void volverInicio() {
		appMediador = AppMediador.getInstance();
		((Activity) appMediador.getVistaPaginaAsignatura()).finish();
		((Activity) appMediador.getVistaGaleriaTarea()).finish();
		appMediador.launchActivity(appMediador.getVistaParaPaginaAsignatura(),
				appMediador.getVistaGaleriaTarea(), null);
	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaGaleriaTarea(), 1, null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaGaleriaTarea(), null);
			break;
		case 3:
			appMediador.launchActivity(
					appMediador.getVistaParaNotificacionesForo(),
					appMediador.getVistaParaGaleriaTarea(), null);
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
			appMediador.getVistaGaleriaTarea().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaGaleriaTarea().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaGaleriaTarea().tratarFormato(
							Typeface.SERIF);
				} else {
					appMediador.getVistaGaleriaTarea().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}
}
