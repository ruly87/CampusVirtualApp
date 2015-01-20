package rgg.campusvirtualapp.presentador;

import java.io.File;
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
import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.modelo.DatosTarea;
import rgg.campusvirtualapp.vista.IVistaTarea;

public class PresentadorTarea implements IPresentadorTarea {
	AppMediador appMediador;
	DatosTarea datosTarea = null;
	private BroadcastReceiver receptorTareas = new BroadcastReceiver() {
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaTarea vistaTarea = appMediador.getVistaTarea();
			if (intent.getAction().equals(AppMediador.AVISO_TAREA_SUBIDA)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					String fecha = extras.getString("fechaSubida");
					String hora = extras.getString("horaSubida");
					vistaTarea.eliminarProgreso();
					vistaTarea.crearAlerta(((Activity) vistaTarea)
							.getString(R.string.se_ha_subido)
							+ " "
							+ hora
							+ " "
							+ ((Activity) vistaTarea).getString(R.string.del)
							+ " " + fecha);
				} else {
					vistaTarea.eliminarProgreso();
					vistaTarea.crearAlerta(((Activity) vistaTarea)
							.getString(R.string.archivo_existe));
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void tratarTarea(Object tarea) {
		appMediador = AppMediador.getInstance();
		if (((Intent) tarea).getExtras() != null) {
			Bundle extras = ((Intent) tarea).getExtras();
			datosTarea = (DatosTarea) extras
					.getSerializable(AppMediador.DATOS_TAREAS);
			String detallesTarea = datosTarea.getDetallesTarea();
			IVistaTarea vistaTarea = appMediador.getVistaTarea();
			vistaTarea.setTextoDetalles(detallesTarea);
		}
	}

	@Override
	public void tratarAccion(Object accion) {
		appMediador = AppMediador.getInstance();
		IVistaTarea vistaTarea = appMediador.getVistaTarea();
		if (((File) accion) == null)
			vistaTarea.crearAlerta(((Activity) vistaTarea)
					.getString(R.string.archivo_no_seleccionado));
		else {
			DatosTarea tareaSubir = new DatosTarea(
					datosTarea.getCodAsignatura(), datosTarea.getNombreTarea(),
					datosTarea.getDetallesTarea(), ((File) accion));
			appMediador.getModelo().enviarArchivo(tareaSubir);
			vistaTarea.mostrarProgreso();
			appMediador.registerReceiver(receptorTareas,
					new String[] { AppMediador.AVISO_TAREA_SUBIDA });

		}

	}

	@Override
	public void volverInicio() {
		AppMediador appMediador = AppMediador.getInstance();
		((Activity) appMediador.getVistaTarea()).finish();
		((Activity) appMediador.getVistaGaleriaTarea()).finish();
		((Activity) appMediador.getVistaPaginaAsignatura()).finish();
		appMediador.launchActivity(appMediador.getVistaParaPaginaAsignatura(),
				appMediador.getVistaTarea(), null);

	}

	@Override
	public void tratarAccion() {
		AppMediador appMediador = AppMediador.getInstance();
		appMediador.launchActivityForResult(
				(Activity) appMediador.getVistaTarea(), "FILES/*", 2);

	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaTarea(), 1, null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaTarea(), null);
			break;
		case 3:
			appMediador.launchActivity(
					appMediador.getVistaParaNotificacionesForo(),
					appMediador.getVistaParaTarea(), null);
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
			appMediador.getVistaTarea().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaTarea().tratarFormato(Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaTarea().tratarFormato(Typeface.SERIF);
				} else {
					appMediador.getVistaTarea().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}

}
