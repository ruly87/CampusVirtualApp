package rgg.campusvirtualapp.presentador;

import java.util.Locale;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.vista.R;
import rgg.campusvirtualapp.modelo.IModelo;
import rgg.campusvirtualapp.modelo.Modelo;
import rgg.campusvirtualapp.vista.IVistaLogin;

public class PresentadorLogin implements IPresentadorLogin {

	// El presentador tiene un receptor de notificaciones de tipo AVISO_LOGIN.
	// Esta notificación la enviará el modelo para indicar que se ha finalizado
	// el registro (ver la clase
	// TablaUsuarios).
	private IModelo modelo;
	private AppMediador appMediador;

	public PresentadorLogin() {
		modelo = new Modelo();
		AppMediador.getInstance().setModelo(modelo);
	}

	private BroadcastReceiver receptorAvisos = new BroadcastReceiver() {
		@Override
		public void onReceive(Context contexto, Intent intent) {
			AppMediador appMediador = AppMediador.getInstance();
			IVistaLogin vistaLogin = appMediador.getVistaLogin();
			if (intent.getExtras() != null) {
				// Cuando ha llegado la notificación, se lanza la ventana de
				// asignaturas
					appMediador.launchActivity(
							appMediador.getVistaParaAsignaturas(), vistaLogin,
							null);
			} else {
				vistaLogin.crearAlerta(appMediador
						.getString(R.string.error_login));

			}
			vistaLogin.eliminarProgreso();
			appMediador.unRegisterReceiver(this);

		}
	};

	@Override
	public void tratarDatos() {
		appMediador = AppMediador.getInstance();
		IVistaLogin vistaLogin = appMediador.getVistaLogin();
		String password = vistaLogin.getPassword();
		String login = vistaLogin.getDni();
		if (login.length() == 0 || password.length() == 0) {
			vistaLogin.crearAlerta(((Activity) vistaLogin)
					.getString(R.string.mensaje_alerta));

		} else {
			// Se pide al modelo que haga el login del usuario y se activa el
			// receptor de aviso para que cuando el
			// modelo finalice el acceso, se sepa qué ha ocurrido
			vistaLogin.mostrarProgreso();
			appMediador.getModelo().verificarDatos(login, password);
			// Se registra el receptor de notificaciones para que escuche la
			// notificación AVISO_LOGIN
			appMediador.registerReceiver(receptorAvisos,
					new String[] { AppMediador.AVISO_LOGIN });

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
			appMediador.getVistaLogin().tratarFormato(null);
		} else {
			if (tipoLetra.compareTo("Sans") == 0) {
				appMediador.getVistaLogin().tratarFormato(Typeface.SANS_SERIF);

			} else {
				if (tipoLetra.compareTo("Serif") == 0) {
					appMediador.getVistaLogin().tratarFormato(Typeface.SERIF);
				} else {
					appMediador.getVistaLogin().tratarFormato(
							Typeface.MONOSPACE);
				}
			}
		}
	}

}
