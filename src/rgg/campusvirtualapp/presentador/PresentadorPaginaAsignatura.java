package rgg.campusvirtualapp.presentador;

import java.util.Locale;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.modelo.DatosAsignatura;
import rgg.campusvirtualapp.vista.IVistaPaginaAsignatura;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

public class PresentadorPaginaAsignatura implements
		IPresentadorPaginaAsignatura {
	private DatosAsignatura datosAsignatura = null;
	private BroadcastReceiver receptorDatos2 = new BroadcastReceiver() {
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaPaginaAsignatura vistaPaginaAsignaturas = appMediador
					.getVistaPaginaAsignatura();
			if (intent.getAction().equals(AppMediador.AVISO_ASIGNATURAS)) {
				Bundle extras = intent.getExtras();
				if (extras != null) {
					datosAsignatura = (DatosAsignatura) extras
							.getSerializable(AppMediador.DATOS_ASIGNATURA);
					vistaPaginaAsignaturas.setTituloAsignatura(datosAsignatura
							.getNombre());
					vistaPaginaAsignaturas.eliminarProgreso();
				}
			}
			appMediador.unRegisterReceiver(this);
		}
	};

	@Override
	public void tratarItem(Object item) {
		AppMediador appMediador;
		switch (((View) item).getId()) {
		case R.id.boton_documentacion:
			appMediador = AppMediador.getInstance();
			appMediador.launchActivity(
					appMediador.getVistaParaGaleriaDocumentacion(),
					appMediador.getVistaPaginaAsignatura(), null);
			break;
		case R.id.boton_foroAsignatura:
			appMediador = AppMediador.getInstance();
			appMediador.launchActivity(
					appMediador.getVistaParaForoAsignatura(),
					appMediador.getVistaPaginaAsignatura(), null);
			break;
		case R.id.boton_tareas:
			appMediador = AppMediador.getInstance();
			appMediador.launchActivity(appMediador.getVistaParaGaleriaTarea(),
					appMediador.getVistaPaginaAsignatura(), null);
			break;
		case R.id.boton_participantes:
			appMediador = AppMediador.getInstance();
			appMediador.launchActivity(appMediador.getVistaParaParticipantes(),
					appMediador.getVistaPaginaAsignatura(), null);
			break;
		}
	}

	@Override
	public void recuperarAsignatura(Object item) {
		AppMediador appMediador = AppMediador.getInstance();
		IVistaPaginaAsignatura vista = appMediador.getVistaPaginaAsignatura();
		if (((Intent) item).getExtras() != null) {
			Bundle extras = ((Intent) item).getExtras();
			// Si nunca he entrado en una asignatura recuperamos la página
			if (datosAsignatura == null) {
				appMediador.getModelo().setCodAsignatura(
						((DatosAsignatura) extras
								.getSerializable(AppMediador.DATOS_ASIGNATURA))
								.getCodAsignatura());
				vista.mostrarProgreso();
				appMediador.getModelo().recuperarAsignatura();
				appMediador.registerReceiver(receptorDatos2,
						new String[] { AppMediador.AVISO_ASIGNATURAS });

			} else {
				// Si ya he entrado y la asignatura es diferente entonces
				// cargamos la pagina de nuevo con la asignatura nueva
				if (datosAsignatura.getNombre().compareTo(
						((DatosAsignatura) extras
								.getSerializable(AppMediador.DATOS_ASIGNATURA))
								.getNombre()) != 0) {
					appMediador.borrarPaginaAsignatura();// Elimino todos los
															// presentadores de
															// la
															// paginaAsignatura
					appMediador
							.getModelo()
							.setCodAsignatura(
									((DatosAsignatura) extras
											.getSerializable(AppMediador.DATOS_ASIGNATURA))
											.getCodAsignatura());
					vista.mostrarProgreso();
					appMediador.getModelo().recuperarAsignatura();
					appMediador.registerReceiver(receptorDatos2,
							new String[] { AppMediador.AVISO_ASIGNATURAS });
				}
				// Sino simplemente mostramos el nombre de la asignatura porque
				// es la misma que ya estaba
				else
					vista.setTituloAsignatura(((DatosAsignatura) extras
							.getSerializable(AppMediador.DATOS_ASIGNATURA))
							.getNombre());
			}
		}
		// Si volvemos de alguna ventana que no sea de las asignaturas
		// simplemente mostraremos el titulo de la asignatura
		else if (datosAsignatura != null)
			vista.setTituloAsignatura(datosAsignatura.getNombre());

	}

	@Override
	public void tratarMenu(int opcion) {
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaPaginaAsignatura(), 1, null);
			break;
		case 2:
			appMediador.launchActivity(appMediador.getVistaParaAyuda(),
					appMediador.getVistaParaPaginaAsignatura(), null);
			break;
		case 3:
			appMediador.launchActivity(
					appMediador.getVistaParaNotificacionesForo(),
					appMediador.getVistaParaPaginaAsignatura(), null);
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
			appMediador.getVistaPaginaAsignatura().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaPaginaAsignatura().tratarFormato(
						Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaPaginaAsignatura().tratarFormato(
							Typeface.SERIF);
				} else {
					appMediador.getVistaPaginaAsignatura().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}
}
