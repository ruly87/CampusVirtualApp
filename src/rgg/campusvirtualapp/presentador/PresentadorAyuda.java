package rgg.campusvirtualapp.presentador;

import java.util.Locale;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.vista.IVistaAyuda;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.preference.PreferenceManager;

public class PresentadorAyuda implements IPresentadorAyuda {

	@Override
	public void solicitarAyuda(int item) {
		AppMediador appMediador = AppMediador.getInstance();
		IVistaAyuda vistaAyuda = appMediador.getVistaAyuda();
		switch (item) {
		case R.id.ayudaLogin:
			vistaAyuda.crearAlerta(((Activity) vistaAyuda)
					.getString(R.string.ayuda_titulo_login),
					((Activity) vistaAyuda)
							.getString(R.string.ayuda_texto_login));
			break;
		case R.id.ayudaDocumentacion:
			vistaAyuda.crearAlerta(((Activity) vistaAyuda)
					.getString(R.string.ayuda_titulo_documentacion),
					((Activity) vistaAyuda)
							.getString(R.string.ayuda_texto_documentacion));
			break;
		case R.id.ayudaForo:
			vistaAyuda.crearAlerta(((Activity) vistaAyuda)
					.getString(R.string.ayuda_titulo_foro),
					((Activity) vistaAyuda)
							.getString(R.string.ayuda_texto_foro));
			break;
		case R.id.ayudaTareas:
			vistaAyuda.crearAlerta(((Activity) vistaAyuda)
					.getString(R.string.ayuda_titulo_tarea),
					((Activity) vistaAyuda)
							.getString(R.string.ayuda_texto_tarea));
			break;
		case R.id.ayudaParticipantes:
			vistaAyuda.crearAlerta(((Activity) vistaAyuda)
					.getString(R.string.ayuda_titulo_participantes),
					((Activity) vistaAyuda)
							.getString(R.string.ayuda_texto_participantes));
			break;
		}
	}

	@Override
	public void tratarMenu(int opcion) {
		/**
		 * Método encargado de lanzar las activitys del menu
		 * 
		 */
		AppMediador appMediador = AppMediador.getInstance();
		switch (opcion) {
		case 1:
			appMediador.launchActivityForResult(
					appMediador.getParaPreferencias(),
					(Activity) appMediador.getVistaAyuda(), 1, null);
			break;
		case 2:
			appMediador
					.getVistaAyuda()
					.crearAlerta(
							((Activity) appMediador.getVistaAyuda())
									.getString(R.string.ayuda_titulo_acerca_de),
							((Activity) appMediador.getVistaAyuda())
									.getString(R.string.ayuda_texto_acerca_de));
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
			appMediador.getVistaAyuda().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaAyuda().tratarFormato(Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaAyuda().tratarFormato(Typeface.SERIF);
				} else {
					appMediador.getVistaAyuda().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}

}
