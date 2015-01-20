package rgg.campusvirtualapp.presentador;

import java.util.Calendar;
import java.util.Locale;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.modelo.DatosHilo;
import rgg.campusvirtualapp.vista.IVistaForoAsignaturaNuevoTema;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class PresentadorForoAsignaturaNuevoTema implements
		IPresentadorForoAsignaturaNuevoTema {
	AppMediador appMediador;
	int id;
	private BroadcastReceiver receptorTemas = new BroadcastReceiver() {
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaForoAsignaturaNuevoTema vistaForoAsignaturaNuevoTema = appMediador
					.getVistaForoAsignaturaNuevoTema();
			if (intent.getAction().equals(AppMediador.AVISO_TEMA_ENVIADO)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					boolean respuesta = extras.getBoolean("enviado");
					vistaForoAsignaturaNuevoTema.eliminarProgreso();
					if (respuesta) {
						((Activity) appMediador.getVistaForoAsignatura())
								.finish();
						((Activity) appMediador
								.getVistaForoAsignaturaNuevoTema()).finish();
						appMediador.launchActivity(
								appMediador.getVistaParaForoAsignatura(),
								appMediador.getVistaForoAsignaturaNuevoTema(),
								null);
					}
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void tratarItem(Object item) {
		Bundle extras = ((Intent) item).getExtras();
		if (extras != null)
			id = extras.getInt("id");
	}

	@Override
	public void crearTema() {
		appMediador = AppMediador.getInstance();
		IVistaForoAsignaturaNuevoTema vistaForoAsignaturaNuevoTema = appMediador
				.getVistaForoAsignaturaNuevoTema();
		DatosHilo hiloNuevo = new DatosHilo(
				vistaForoAsignaturaNuevoTema.getAsunto(), 0, Calendar
						.getInstance().getTime(),
				vistaForoAsignaturaNuevoTema.getMensaje(), 0, id);
		appMediador.getModelo().enviarTema(hiloNuevo);
		vistaForoAsignaturaNuevoTema.mostrarProgreso();
		appMediador.registerReceiver(receptorTemas,
				new String[] { AppMediador.AVISO_TEMA_ENVIADO });
	}

	@Override
	public void volverInicio() {
		appMediador = AppMediador.getInstance();
		((Activity) appMediador.getVistaPaginaAsignatura()).finish();
		((Activity) appMediador.getVistaForoAsignatura()).finish();
		((Activity) appMediador.getVistaForoAsignaturaNuevoTema()).finish();
		appMediador.launchActivity(appMediador.getVistaParaPaginaAsignatura(),
				appMediador.getVistaForoAsignaturaNuevoTema(), null);
	}

	@Override
	public void tratarMenu(int opcion) {
		/**
		 * Método encargado de lanzar las activitys del menu
		 * 
		 */
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaForoAsignaturaNuevoTema(),
					1, null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaForoAsignaturaDetalle(), null);
			break;
		case 3:
			appMediador.launchActivity(
					appMediador.getVistaParaNotificacionesForo(),
					appMediador.getVistaParaForoAsignaturaDetalle(), null);
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
			appMediador.getVistaForoAsignaturaNuevoTema().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaForoAsignaturaNuevoTema().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaForoAsignaturaNuevoTema()
							.tratarFormato(Typeface.SERIF);
				} else {
					appMediador.getVistaForoAsignaturaNuevoTema()
							.tratarFormato(Typeface.MONOSPACE);
				}
			}
		}
	}
}
