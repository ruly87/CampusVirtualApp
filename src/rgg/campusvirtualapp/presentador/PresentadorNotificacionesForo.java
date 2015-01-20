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
import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.modelo.DatosNotificaciones;
import rgg.campusvirtualapp.vista.IVistaNotificacionesForo;

public class PresentadorNotificacionesForo implements
		IPresentadorNotificacionesForo {
	AppMediador appMediador;
	ArrayList<DatosNotificaciones> listaNoti = null;
	private BroadcastReceiver receptorNotificaciones = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			appMediador = AppMediador.getInstance();
			IVistaNotificacionesForo vistaNotificacionesForo = appMediador
					.getVistaNotificacionesForo();
			if (intent.getAction().equals(AppMediador.AVISO_NOTIFICACIONES)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					listaNoti = (ArrayList<DatosNotificaciones>) extras
							.getSerializable(AppMediador.DATOS_NOTIFICACIONES);
					vistaNotificacionesForo.eliminarProgreso();
					vistaNotificacionesForo.setListaNotificaciones(listaNoti);
				} else {
					vistaNotificacionesForo.eliminarProgreso();
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void solicitarListaNotificaciones() {
		appMediador = AppMediador.getInstance();
		IVistaNotificacionesForo vistaNotificacionesForo = appMediador
				.getVistaNotificacionesForo();
		if (listaNoti == null) {
			vistaNotificacionesForo.mostrarProgreso();
			appMediador.getModelo().obtenerNotificacionesForo();
			appMediador.registerReceiver(receptorNotificaciones,
					new String[] { AppMediador.AVISO_NOTIFICACIONES });
		} else
			vistaNotificacionesForo.setListaNotificaciones(listaNoti);
	}

	@Override
	public void tratarItem(Object item) {
		Bundle extras = new Bundle();
		extras.putSerializable(AppMediador.DATOS_NOTIFICACIONES,
				((DatosNotificaciones) item));
		appMediador = AppMediador.getInstance();
		appMediador.launchActivity(
				appMediador.getVistaParaNotificacionesForoDetalle(),
				appMediador.getVistaNotificacionesForo(), extras);
	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaNotificacionesForo(), 1,
					null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaNotificacionesForo(), null);
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
			appMediador.getVistaNotificacionesForo().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaNotificacionesForo().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaNotificacionesForo().tratarFormato(
							Typeface.SERIF);
				} else {
					appMediador.getVistaNotificacionesForo().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}
}
