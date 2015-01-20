package rgg.campusvirtualapp.presentador;

import java.util.Locale;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.modelo.DatosNotificaciones;
import rgg.campusvirtualapp.vista.IVistaNotificacionesForoDetalle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class PresentadorNotificacionesForoDetalle implements
		IPresentadorNotificacionesForoDetalle {
	DatosNotificaciones notificacion = null;

	@Override
	public void tratarItem(Object item) {
		Bundle extras = ((Intent) item).getExtras();
		if (extras != null) {
			notificacion = (DatosNotificaciones) extras
					.getSerializable(AppMediador.DATOS_NOTIFICACIONES);
		}
		if (notificacion != null) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaNotificacionesForoDetalle vistaNotificacionesForoDetalle = appMediador
					.getVistaNotificacionesForoDetalle();
			vistaNotificacionesForoDetalle.setNotificacion(
					notificacion.getMensaje(), notificacion.getAutor(),
					notificacion.getAsunto());
		}
	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaNotificacionesForoDetalle(),
					1, null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaNotificacionesForoDetalle(), null);
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
		AppMediador appMediador = AppMediador.getInstance();
		if (tipoLetra.compareTo("Normal") == 0) {
			appMediador.getVistaNotificacionesForoDetalle().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaNotificacionesForoDetalle().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaNotificacionesForoDetalle()
							.tratarFormato(Typeface.SERIF);
				} else {
					appMediador.getVistaNotificacionesForoDetalle()
							.tratarFormato(Typeface.MONOSPACE);
				}
			}
		}
	}
}
