package rgg.campusvirtualapp.vista;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorForoAsignaturaResponderTema;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class VistaForoAsignaturaResponderTema extends Activity implements
		IVistaForoAsignaturaResponderTema, OnClickListener {
	private AppMediador appMediador;
	private Button inicio, enviar;
	private ProgressDialog barra;
	private IPresentadorForoAsignaturaResponderTema presentadorForoAsignaturaResponderTema;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.foroasignaturarespondertema_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaForoAsignaturaResponderTema(this);
		presentadorForoAsignaturaResponderTema = appMediador
				.getPresentadorForoAsignaturaResponderTema();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.foroasignaturarespondertema_vista);
		inicio = (Button) this.findViewById(R.id.inicio);
		inicio.setOnClickListener(this);
		enviar = (Button) this.findViewById(R.id.enviarTema);
		enviar.setOnClickListener(this);
		presentadorForoAsignaturaResponderTema.tratarItem(getIntent());
		presentadorForoAsignaturaResponderTema.actualizaPreferencias();
		invalidateOptionsMenu();
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id = item.getItemId();
		if (id == R.id.menu_configuracion) {
			presentadorForoAsignaturaResponderTema.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorForoAsignaturaResponderTema.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorForoAsignaturaResponderTema.tratarMenu(3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public String getAsunto() {
		return ((EditText) this.findViewById(R.id.ETasuntotema)).getText()
				.toString();
	}

	@Override
	public String getMensaje() {
		return ((EditText) this.findViewById(R.id.ETmensajetema)).getText()
				.toString();
	}

	@Override
	public void onClick(View v) {
		if (v.equals(inicio))
			presentadorForoAsignaturaResponderTema.volverInicio();
		if (v.equals(enviar))
			presentadorForoAsignaturaResponderTema.responderTema();
	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setCancelable(false);
		barra.setMessage(getString(R.string.mensaje_responder_tema));
		barra.show();
	}

	@Override
	public void eliminarProgreso() {
		barra.dismiss();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1)
			presentadorForoAsignaturaResponderTema.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(LinearLayout) this.findViewById(R.id.viewfororespondertema),
				tipo);
	}

}
