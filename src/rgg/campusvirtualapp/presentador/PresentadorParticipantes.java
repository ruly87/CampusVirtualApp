package rgg.campusvirtualapp.presentador;

import java.util.ArrayList;
import java.util.Locale;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.modelo.DatosParticipantes;
import rgg.campusvirtualapp.vista.IVistaParticipantes;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class PresentadorParticipantes implements IPresentadorParticipantes {
	AppMediador appMediador;
	ArrayList<DatosParticipantes> listaParticipantes = null;
	private BroadcastReceiver receptorParticipantes = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaParticipantes vistaParticipantes = appMediador
					.getVistaParticipantes();
			if (intent.getAction().equals(AppMediador.AVISO_PARTICIPANTES)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					listaParticipantes = (ArrayList<DatosParticipantes>) extras
							.getSerializable(AppMediador.DATOS_ARRAY_PARTICIPANTES);
					vistaParticipantes.setListaPersonas(listaParticipantes);
					vistaParticipantes.eliminarProgreso();
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void volverInicio() {
		appMediador = AppMediador.getInstance();
		((Activity) appMediador.getVistaPaginaAsignatura()).finish();
		((Activity) appMediador.getVistaParticipantes()).finish();
		appMediador.launchActivity(appMediador.getVistaParaPaginaAsignatura(),
				appMediador.getVistaParticipantes(), null);
	}

	@Override
	public void obtenerDatos() {
		appMediador = AppMediador.getInstance();
		if (listaParticipantes == null) {
			appMediador.getModelo().pedirPersonas();
			appMediador.getVistaParticipantes().mostrarProgreso();
			appMediador.registerReceiver(receptorParticipantes,
					new String[] { AppMediador.AVISO_PARTICIPANTES });
		} else {
			appMediador.getVistaParticipantes().setListaPersonas(
					listaParticipantes);
		}
	}

	@Override
	public void solicitarDetalles(Object participante) {
		appMediador = AppMediador.getInstance();
		Bundle extras = new Bundle();
		extras.putSerializable(AppMediador.DATOS_PARTICIPANTES,
				(DatosParticipantes) participante);
		appMediador.launchActivity(
				appMediador.getVistaParaDetallesParticipantes(),
				appMediador.getVistaParticipantes(), extras);
	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaParticipantes(), 1, null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaParticipantes(), null);
			break;
		case 3:
			appMediador.launchActivity(
					appMediador.getVistaParaNotificacionesForo(),
					appMediador.getVistaParaParticipantes(), null);
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
			appMediador.getVistaParticipantes().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaParticipantes().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaParticipantes().tratarFormato(
							Typeface.SERIF);
				} else {
					appMediador.getVistaParticipantes().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}
}
