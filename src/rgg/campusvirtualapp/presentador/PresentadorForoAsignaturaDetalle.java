package rgg.campusvirtualapp.presentador;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.modelo.DatosHilo;
import rgg.campusvirtualapp.vista.IVistaForoAsignaturaDetalle;

public class PresentadorForoAsignaturaDetalle implements
		IPresentadorForoAsignaturaDetalle {
	private AppMediador appMediador;
	DatosHilo hilo;
	int idMax;
	ArrayList<DatosHilo> listaHilo;

	@SuppressWarnings("unchecked")
	@Override
	public void tratarHilo(Object item) {
		appMediador = AppMediador.getInstance();
		IVistaForoAsignaturaDetalle vistaForoAsignaturaDetalle = appMediador
				.getVistaForoAsignaturaDetalle();
		Bundle extras = ((Intent) item).getExtras();
		if (extras != null) {
			listaHilo = (ArrayList<DatosHilo>) extras
					.getSerializable(AppMediador.DATOS_ARRAY_FORO);
			hilo = (DatosHilo) extras.getSerializable(AppMediador.DATOS_FORO);
		}
		idMax = listaHilo.size();// Almaceno el tamaño del vector que coincide
									// con el id del ultimo hilo/mensaje
									// publicado
		ArrayList<DatosHilo> sv = new ArrayList<DatosHilo>();
		sv.add(hilo);
		for (int i = listaHilo.size() - 1; i >= 0; i--) {
			if (hilo.getId() == listaHilo.get(i).getEsRespuesta()
					&& hilo.getId() != listaHilo.get(i).getId()) {
				sv.add(listaHilo.get(i));
			}
		}
		vistaForoAsignaturaDetalle.setListaHilo(sv);

	}

	@Override
	public void tratarItem() {
		Bundle extras = new Bundle();
		extras.putInt("id", idMax + 1);
		extras.putSerializable(AppMediador.DATOS_FORO, hilo);
		extras.putSerializable(AppMediador.DATOS_ARRAY_FORO, listaHilo);
		appMediador = AppMediador.getInstance();
		appMediador.launchActivity(
				appMediador.getVistaParaForoAsignaturaResponderTema(),
				appMediador.getVistaForoAsignaturaDetalle(), extras);
	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaForoAsignaturaDetalle(), 1,
					null);
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
			appMediador.getVistaForoAsignaturaDetalle().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaForoAsignaturaDetalle().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaForoAsignaturaDetalle().tratarFormato(
							Typeface.SERIF);
				} else {
					appMediador.getVistaForoAsignaturaDetalle().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}
}
