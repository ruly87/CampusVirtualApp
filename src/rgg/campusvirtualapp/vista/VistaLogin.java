package rgg.campusvirtualapp.vista;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorLogin;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class VistaLogin extends Activity implements IVistaLogin,
		OnClickListener {

	private ProgressDialog barra;
	private AppMediador appMediador;
	private IPresentadorLogin presentadorLogin;
	private Button iniciarSesion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_vista);
		appMediador = (AppMediador) getApplication();
		appMediador.setVistaLogin(this);
		iniciarSesion = (Button) this.findViewById(R.id.iniciarSesion);
		iniciarSesion.setOnClickListener(this);
		presentadorLogin = appMediador.getPresentadorLogin();
		presentadorLogin.actualizaPreferencias();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		setContentView(R.layout.login_vista);
		presentadorLogin.actualizaPreferencias();
		invalidateOptionsMenu();
		iniciarSesion = (Button) this.findViewById(R.id.iniciarSesion);
		iniciarSesion.setOnClickListener(this);
		this.findViewById(R.id.salir).setOnClickListener(this);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1)
			presentadorLogin.actualizaPreferencias();
	}


	@Override
	public String getDni() {
		return ((EditText) this.findViewById(R.id.dni_alumno)).getText()
				.toString();
	}

	@Override
	public String getPassword() {
		return ((EditText) this.findViewById(R.id.password_alumno)).getText()
				.toString();
	}

	@Override
	public void crearAlerta(String mensaje) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.alerta));
		builder.setMessage(mensaje);
		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.create();
		builder.show();
	}

	@Override
	public void onClick(View v) {
		if (v.equals(iniciarSesion))
			appMediador.getPresentadorLogin().tratarDatos();
		if (v.equals(this.findViewById(R.id.salir))) {
			int id = android.os.Process.myPid();
			android.os.Process.killProcess(id);
		}

	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setMessage(getString(R.string.mensaje_login));
		barra.setCancelable(false);
		barra.show();
	}

	@Override
	public void eliminarProgreso() {
		barra.dismiss();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(LinearLayout) this.findViewById(R.id.LinearLayout1), tipo);
	}

}
