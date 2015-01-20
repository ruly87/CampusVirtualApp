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
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.modelo.DatosAsignatura;
import rgg.campusvirtualapp.vista.IVistaAsignaturas;
import rgg.campusvirtualapp.vista.VistaAsignaturas;
import rgg.campusvirtualapp.vista.VistaLogin;

public class PresentadorAsignaturas implements IPresentadorAsignaturas {
	AppMediador appMediador;
	ArrayList<DatosAsignatura> lista = null;
	private BroadcastReceiver receptorDatos = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaAsignaturas vistaAsignaturas = appMediador
					.getVistaAsignaturas();
			vistaAsignaturas.eliminarProgreso();
			if (intent.getAction().equals(AppMediador.AVISO_ASIGNATURAS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					lista = (ArrayList<DatosAsignatura>) extras
							.getSerializable(AppMediador.DATOS_ASIGNATURAS);
					vistaAsignaturas.setListaAsignaturas(lista);
					actualizaPreferencias();
				} else {
					vistaAsignaturas.crearAlerta(((Activity) vistaAsignaturas)
							.getString(R.string.titulo_error),
							((Activity) vistaAsignaturas)
									.getString(R.string.mensaje_error));
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	public PresentadorAsignaturas() {
		appMediador = AppMediador.getInstance();
		appMediador.launchService(appMediador.getParaServicioNotificaciones(),
				null);
	}

	@Override
	public void obtenerLista() {
		appMediador = AppMediador.getInstance();
		if (lista == null) {
			appMediador.getVistaAsignaturas().mostrarProgreso();
			appMediador.getModelo().pedirLista();
			appMediador.registerReceiver(receptorDatos,
					new String[] { AppMediador.AVISO_ASIGNATURAS });
		} else {
			appMediador.getVistaAsignaturas().setListaAsignaturas(lista);
			actualizaPreferencias();
		}
	}

	@Override
	public void solicitarSalir() {
		appMediador = AppMediador.getInstance();
		appMediador.getVistaAsignaturas().crearAlertaSalir(
				((Activity) appMediador.getVistaAsignaturas())
						.getString(R.string.alerta_salir));
	}

	@Override
	public void accionSalir() {
		appMediador = AppMediador.getInstance();
		appMediador.getModelo().cerrarModelo();
		appMediador.stopService(appMediador.getParaServicioNotificaciones());
		((VistaAsignaturas) appMediador.getVistaAsignaturas()).finish();
		((VistaLogin) appMediador.getVistaLogin()).finish();
	}

	@Override
	public void tratarItem(Object item) {
		appMediador = AppMediador.getInstance();
		Bundle extras = new Bundle();
		extras.putSerializable(AppMediador.DATOS_ASIGNATURA,
				(DatosAsignatura) item);
		appMediador.launchActivity(appMediador.getVistaParaPaginaAsignatura(),
				appMediador.getVistaAsignaturas(), extras);

	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaAsignaturas(), 1, null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaAsignaturas(), null);
			break;
		case 3:
			appMediador.launchActivity(
					appMediador.getVistaParaNotificacionesForo(),
					appMediador.getVistaAsignaturas(), null);
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
			appMediador.getVistaAsignaturas().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaAsignaturas().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaAsignaturas().tratarFormato(
							Typeface.SERIF);
				} else {
					appMediador.getVistaAsignaturas().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}
}
