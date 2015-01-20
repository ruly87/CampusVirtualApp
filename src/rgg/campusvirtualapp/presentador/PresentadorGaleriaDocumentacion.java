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
import android.widget.Button;
import android.widget.LinearLayout;
import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.vista.IVistaGaleriaDocumentacion;

public class PresentadorGaleriaDocumentacion implements
		IPresentadorGaleriaDocumentacion {
	AppMediador appMediador;
	int[] semana = null;
	private BroadcastReceiver receptorSemanas = new BroadcastReceiver() {
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaGaleriaDocumentacion vistaGaleriaDocumentacion = appMediador
					.getVistaGaleriaDocumentacion();
			if (intent.getAction().equals(AppMediador.AVISO_SEMANAS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					semana = extras.getIntArray("semana");
					ArrayList<String> datos = new ArrayList<String>();
					for (int j = 0; j < semana.length; j++)
						datos.add(((Activity) vistaGaleriaDocumentacion)
								.getString(R.string.semana) + " " + semana[j]);
					int i = 0;
					ArrayList<LinearLayout> galeria = new ArrayList<LinearLayout>(
							datos.size() / 2);
					while (i < datos.size()) {
						if (i == (datos.size() - 1)) {
							LinearLayout ll = (LinearLayout) ((Activity) (vistaGaleriaDocumentacion))
									.getLayoutInflater()
									.inflate(
											R.layout.fila_impares_galeria_documentacion,
											null);
							Button boton = (Button) ll
									.findViewById(R.id.semana1);
							boton.setText(datos.get(i));
							boton.setId(i);
							galeria.add(ll);
						} else {
							LinearLayout ll = (LinearLayout) ((Activity) (vistaGaleriaDocumentacion))
									.getLayoutInflater()
									.inflate(
											R.layout.fila_pares_galeria_documentacion,
											null);
							Button boton = (Button) ll
									.findViewById(R.id.semana1);
							boton.setText(datos.get(i));
							boton.setId(i);
							i++;
							boton = (Button) ll.findViewById(R.id.semana2);
							boton.setText(datos.get(i));
							boton.setId(i);
							galeria.add(ll);
						}
						i++;
					}
					vistaGaleriaDocumentacion.construirGaleria(galeria);
					vistaGaleriaDocumentacion.eliminarProgreso();
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void recuperarSemanas() {
		appMediador = AppMediador.getInstance();
		if (semana == null) {
			appMediador.getModelo().recuperarSemanas();
			appMediador.getVistaGaleriaDocumentacion().mostrarProgreso();
			appMediador.registerReceiver(receptorSemanas,
					new String[] { AppMediador.AVISO_SEMANAS });
		} else {
			IVistaGaleriaDocumentacion vistaGaleriaDocumentacion = appMediador
					.getVistaGaleriaDocumentacion();
			ArrayList<String> datos = new ArrayList<String>();
			for (int j = 0; j < semana.length; j++)
				datos.add(((Activity) vistaGaleriaDocumentacion)
						.getString(R.string.semana) + " " + semana[j]);
			int i = 0;
			ArrayList<LinearLayout> galeria = new ArrayList<LinearLayout>(
					datos.size() / 2);
			while (i < datos.size()) {
				if (i == (datos.size() - 1)) {
					LinearLayout ll = (LinearLayout) ((Activity) (vistaGaleriaDocumentacion))
							.getLayoutInflater()
							.inflate(
									R.layout.fila_impares_galeria_documentacion,
									null);
					Button boton = (Button) ll.findViewById(R.id.semana1);
					boton.setText(datos.get(i));
					boton.setId(i);
					galeria.add(ll);
				} else {
					LinearLayout ll = (LinearLayout) ((Activity) (vistaGaleriaDocumentacion))
							.getLayoutInflater().inflate(
									R.layout.fila_pares_galeria_documentacion,
									null);
					Button boton = (Button) ll.findViewById(R.id.semana1);
					boton.setText(datos.get(i));
					boton.setId(i);
					i++;
					boton = (Button) ll.findViewById(R.id.semana2);
					boton.setText(datos.get(i));
					boton.setId(i);
					galeria.add(ll);
				}
				i++;
			}
			vistaGaleriaDocumentacion.construirGaleria(galeria);
		}

	}

	@Override
	public void tratarSemana(int semana) {
		Bundle extras = new Bundle();
		extras.putInt("semana", (semana + 1));
		appMediador = AppMediador.getInstance();
		appMediador.launchActivity(appMediador.getVistaParaDocumentacion(),
				appMediador.getVistaGaleriaDocumentacion(), extras);
	}

	@Override
	public void volverInicio() {
		appMediador = AppMediador.getInstance();
		((Activity) appMediador.getVistaGaleriaDocumentacion()).finish();
		((Activity) appMediador.getVistaPaginaAsignatura()).finish();
		appMediador.launchActivity(appMediador.getVistaParaPaginaAsignatura(),
				appMediador.getVistaGaleriaDocumentacion(), null);
	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaGaleriaDocumentacion(), 1,
					null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaGaleriaDocumentacion(), null);
			break;
		case 3:
			appMediador.launchActivity(
					appMediador.getVistaParaNotificacionesForo(),
					appMediador.getVistaParaGaleriaDocumentacion(), null);
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
			appMediador.getVistaGaleriaDocumentacion().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaGaleriaDocumentacion().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaGaleriaDocumentacion().tratarFormato(
							Typeface.SERIF);
				} else {
					appMediador.getVistaGaleriaDocumentacion().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}

}
