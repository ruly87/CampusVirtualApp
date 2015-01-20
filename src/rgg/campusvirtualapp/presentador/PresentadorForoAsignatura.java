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
import rgg.campusvirtualapp.modelo.DatosHilo;
import rgg.campusvirtualapp.vista.IVistaForoAsignatura;

public class PresentadorForoAsignatura implements IPresentadorForoAsignatura {
	private AppMediador appMediador;
	private ArrayList<DatosHilo> listaHilos = null;
	private BroadcastReceiver receptorForo = new BroadcastReceiver() {
		@SuppressWarnings("unchecked")
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaForoAsignatura vistaForoAsignatura = appMediador
					.getVistaForoAsignatura();
			if (intent.getAction().equals(AppMediador.AVISO_FORO)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					listaHilos = (ArrayList<DatosHilo>) extras
							.getSerializable(AppMediador.DATOS_ARRAY_FORO);
					ArrayList<DatosHilo> hilos = new ArrayList<DatosHilo>();
					for (int i = 0; i < listaHilos.size(); i++) {
						if (listaHilos.get(i).getEsRespuesta() == 0) {
							hilos.add(listaHilos.get(i));
						}
					}
					vistaForoAsignatura.setListaForo(hilos);
					vistaForoAsignatura.eliminarProgreso();
				} else {
					vistaForoAsignatura.eliminarProgreso();
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void tratarItem(int item) {
		appMediador = AppMediador.getInstance();

		Bundle extras = new Bundle();
		if (item == -1) {
			if (listaHilos != null)
				extras.putInt("id", listaHilos.size() + 1);
			else
				extras.putInt("id", 1);
			appMediador.launchActivity(
					appMediador.getVistaParaForoAsignaturaNuevoTema(),
					appMediador.getVistaForoAsignatura(), extras);
		} else {
			extras.putSerializable(AppMediador.DATOS_ARRAY_FORO, listaHilos);
			extras.putSerializable(AppMediador.DATOS_FORO, listaHilos.get(item));
			appMediador.launchActivity(
					appMediador.getVistaParaForoAsignaturaDetalle(),
					appMediador.getVistaForoAsignatura(), extras);
		}
	}

	@Override
	public void obtenerListaForo() {
		appMediador = AppMediador.getInstance();
		appMediador.getModelo().obtenerListaForo();
		appMediador.getVistaForoAsignatura().mostrarProgreso();
		appMediador.registerReceiver(receptorForo,
				new String[] { AppMediador.AVISO_FORO });

	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaForoAsignatura(), 1, null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaForoAsignatura(), null);
			break;
		case 3:
			appMediador.launchActivity(
					appMediador.getVistaParaNotificacionesForo(),
					appMediador.getVistaParaForoAsignatura(), null);
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
			appMediador.getVistaForoAsignatura().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaForoAsignatura().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaForoAsignatura().tratarFormato(
							Typeface.SERIF);
				} else {
					appMediador.getVistaForoAsignatura().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}

}
