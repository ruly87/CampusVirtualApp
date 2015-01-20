package rgg.campusvirtualapp.vista;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorDetallesParticipantes;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VistaDetallesParticipantes extends Activity implements
		IVistaDetallesParticipantes, OnClickListener {
	private AppMediador appMediador;
	private IPresentadorDetallesParticipantes presentadorDetallesParticipantes;
	private ProgressDialog barra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detallesparticipantes_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaDetallesParticipantes(this);
		presentadorDetallesParticipantes = appMediador
				.getPresentadorDetallesParticipantes();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.detallesparticipantes_vista);
		presentadorDetallesParticipantes.tratarPersona(getIntent());
		this.findViewById(R.id.inicio).setOnClickListener(this);
		this.findViewById(R.id.enviarCorreo).setOnClickListener(this);
		presentadorDetallesParticipantes.actualizaPreferencias();
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
			presentadorDetallesParticipantes.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorDetallesParticipantes.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorDetallesParticipantes.tratarMenu(3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void setImagen(Object imagen) {
		((ImageView) this.findViewById(R.id.imagenPerfil))
				.setImageBitmap((Bitmap) imagen);
	}

	@Override
	public void setDetalles(String detalles) {
		((TextView) this.findViewById(R.id.textoDetalles)).setText(detalles);
	}

	@Override
	public void onClick(View v) {
		if (v.equals(this.findViewById(R.id.inicio))) {
			presentadorDetallesParticipantes.volverInicio();
		}
		if (v.equals(this.findViewById(R.id.enviarCorreo))) {
			presentadorDetallesParticipantes.tratarItem();
		}
	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setMessage(getString(R.string.mensaje_detalles_participantes));
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
			presentadorDetallesParticipantes.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this, (LinearLayout) this
				.findViewById(R.id.viewdetallesparticipantes), tipo);
	}

}
