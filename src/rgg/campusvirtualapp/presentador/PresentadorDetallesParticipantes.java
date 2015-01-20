package rgg.campusvirtualapp.presentador;

import java.util.Locale;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.modelo.DatosParticipantes;
import rgg.campusvirtualapp.vista.IVistaDetallesParticipantes;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class PresentadorDetallesParticipantes implements
		IPresentadorDetallesParticipantes {
	DatosParticipantes participante = null;
	private BroadcastReceiver receptorDetallesParticipantes = new BroadcastReceiver() {
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaDetallesParticipantes vistaDetallesParticipantes = appMediador
					.getVistaDetallesParticipantes();
			if (intent.getAction().equals(
					AppMediador.AVISO_DETALLES_PARTICIPANTES)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					participante = (DatosParticipantes) extras
							.getSerializable(AppMediador.DATOS_PARTICIPANTES);
					vistaDetallesParticipantes.setDetalles(participante
							.getNombre()
							+ "\n\n"
							+ ((Activity) vistaDetallesParticipantes)
									.getString(R.string.correo)
							+ participante.getEmail());
					vistaDetallesParticipantes.setImagen((Bitmap) participante
							.getFoto());
					vistaDetallesParticipantes.eliminarProgreso();
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void tratarPersona(Object persona) {
		AppMediador appMediador = AppMediador.getInstance();
		IVistaDetallesParticipantes vistaDetallesParticipantes = appMediador
				.getVistaDetallesParticipantes();
		if (participante == null) {
			if (((Intent) persona).getExtras() != null) {
				Bundle extras = new Bundle();
				extras = ((Intent) persona).getExtras();
				participante = (DatosParticipantes) extras
						.getSerializable(AppMediador.DATOS_PARTICIPANTES);
				appMediador.getModelo().pedirDetalles(
						participante.getUsername());
				vistaDetallesParticipantes.mostrarProgreso();
				appMediador
						.registerReceiver(
								receptorDetallesParticipantes,
								new String[] { AppMediador.AVISO_DETALLES_PARTICIPANTES });
			}
		} else {
			if(((Intent) persona).getExtras() != null && participante.getUsername()==((DatosParticipantes)((Intent) persona).getExtras().getSerializable(AppMediador.DATOS_PARTICIPANTES)).getUsername()){
				vistaDetallesParticipantes.setDetalles(participante.getNombre()
						+ "\n\n"
						+ ((Activity) vistaDetallesParticipantes)
								.getString(R.string.correo)
						+ participante.getEmail());
				vistaDetallesParticipantes.setImagen((Bitmap) participante
						.getFoto());
			}
			else{
				if (((Intent) persona).getExtras() != null) {
					Bundle extras = new Bundle();
					extras = ((Intent) persona).getExtras();
					participante = (DatosParticipantes) extras
							.getSerializable(AppMediador.DATOS_PARTICIPANTES);
					appMediador.getModelo().pedirDetalles(
							participante.getUsername());
					vistaDetallesParticipantes.mostrarProgreso();
					appMediador
							.registerReceiver(
									receptorDetallesParticipantes,
									new String[] { AppMediador.AVISO_DETALLES_PARTICIPANTES });
				}
			}
			
		}
	}

	@Override
	public void tratarItem() {
		Uri uri = Uri.parse("mailto:" + participante.getEmail());
		AppMediador.getInstance().launchActivity(uri);
	}

	@Override
	public void volverInicio() {
		AppMediador appMediador = AppMediador.getInstance();
		((Activity) appMediador.getVistaPaginaAsignatura()).finish();
		((Activity) appMediador.getVistaParticipantes()).finish();
		((Activity) appMediador.getVistaDetallesParticipantes()).finish();
		appMediador.launchActivity(appMediador.getVistaParaPaginaAsignatura(),
				appMediador.getVistaDetallesParticipantes(), null);
	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaDetallesParticipantes(), 1,
					null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaDetallesParticipantes(), null);
			break;
		case 3:
			appMediador.launchActivity(
					appMediador.getVistaParaNotificacionesForo(),
					appMediador.getVistaParaDetallesParticipantes(), null);
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
			appMediador.getVistaDetallesParticipantes().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaDetallesParticipantes().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaDetallesParticipantes().tratarFormato(
							Typeface.SERIF);
				} else {
					appMediador.getVistaDetallesParticipantes().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}
}
