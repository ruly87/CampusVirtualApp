package rgg.campusvirtualapp.presentador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.modelo.DatosHilo;
import rgg.campusvirtualapp.vista.IVistaForoAsignaturaResponderTema;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class PresentadorForoAsignaturaResponderTema implements
		IPresentadorForoAsignaturaResponderTema {
	AppMediador appMediador;
	DatosHilo hilo;
	ArrayList<DatosHilo> listaHilo;
	int idMax;
	private BroadcastReceiver receptorTemas = new BroadcastReceiver() {
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaForoAsignaturaResponderTema vistaForoAsignaturaResponderTema = appMediador
					.getVistaForoAsignaturaResponderTema();
			if (intent.getAction().equals(AppMediador.AVISO_TEMA_ENVIADO)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					boolean respuesta = extras.getBoolean("enviado");
					vistaForoAsignaturaResponderTema.eliminarProgreso();
					if (respuesta) {
						((Activity) appMediador.getVistaForoAsignaturaDetalle())
								.finish();
						((Activity) appMediador
								.getVistaForoAsignaturaResponderTema())
								.finish();
						listaHilo.add(0, (DatosHilo) extras
								.getSerializable(AppMediador.DATOS_FORO));
						extras = new Bundle();
						extras.putSerializable(AppMediador.DATOS_ARRAY_FORO,
								listaHilo);
						extras.putSerializable(AppMediador.DATOS_FORO, hilo);
						appMediador
								.launchActivity(
										appMediador
												.getVistaParaForoAsignaturaDetalle(),
										appMediador
												.getVistaForoAsignaturaResponderTema(),
										extras);
					}
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void volverInicio() {
		appMediador = AppMediador.getInstance();
		((Activity) appMediador.getVistaPaginaAsignatura()).finish();
		((Activity) appMediador.getVistaForoAsignatura()).finish();
		((Activity) appMediador.getVistaForoAsignaturaDetalle()).finish();
		((Activity) appMediador.getVistaForoAsignaturaResponderTema()).finish();
		appMediador.launchActivity(appMediador.getVistaParaPaginaAsignatura(),
				appMediador.getVistaForoAsignaturaResponderTema(), null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void tratarItem(Object item) {
		Bundle extras = ((Intent) item).getExtras();
		if (extras != null) {
			hilo = (DatosHilo) extras.getSerializable(AppMediador.DATOS_FORO);
			listaHilo = (ArrayList<DatosHilo>) extras
					.getSerializable(AppMediador.DATOS_ARRAY_FORO);
			idMax = extras.getInt("id");
		}
	}

	@Override
	public void responderTema() {
		appMediador = AppMediador.getInstance();
		IVistaForoAsignaturaResponderTema vistaForoAsignaturaResponderTema = appMediador
				.getVistaForoAsignaturaResponderTema();
		DatosHilo hiloNuevo = new DatosHilo(
				vistaForoAsignaturaResponderTema.getAsunto(), 0, Calendar
						.getInstance().getTime(),
				vistaForoAsignaturaResponderTema.getMensaje(), hilo.getId(),
				idMax);
		appMediador.getModelo().enviarTema(hiloNuevo);
		vistaForoAsignaturaResponderTema.mostrarProgreso();
		appMediador.registerReceiver(receptorTemas,
				new String[] { AppMediador.AVISO_TEMA_ENVIADO });
	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(appMediador
					.getParaPreferencias(), (Activity) appMediador
					.getVistaForoAsignaturaResponderTema(), 1, null);
			break;
		case 2:
			appMediador
					.launchActivity(appMediador.getVistaParaAyuda(),
							appMediador
									.getVistaParaForoAsignaturaResponderTema(),
							null);
			break;
		case 3:
			appMediador
					.launchActivity(appMediador
							.getVistaParaNotificacionesForo(), appMediador
							.getVistaParaForoAsignaturaResponderTema(), null);
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
			appMediador.getVistaForoAsignaturaResponderTema().tratarFormato(
					null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaForoAsignaturaResponderTema()
						.tratarFormato(Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaForoAsignaturaResponderTema()
							.tratarFormato(Typeface.SERIF);
				} else {
					appMediador.getVistaForoAsignaturaResponderTema()
							.tratarFormato(Typeface.MONOSPACE);
				}
			}
		}
	}
}
