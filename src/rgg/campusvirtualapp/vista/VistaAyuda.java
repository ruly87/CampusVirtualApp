package rgg.campusvirtualapp.vista;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorAyuda;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class VistaAyuda extends Activity implements IVistaAyuda,
		OnClickListener {

	private AppMediador appMediador;
	private IPresentadorAyuda presentadorAyuda;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ayuda_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaAyuda(this);
		presentadorAyuda = appMediador.getPresentadorAyuda();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		setContentView(R.layout.ayuda_vista);
		((Button) this.findViewById(R.id.ayudaLogin)).setOnClickListener(this);
		((Button) this.findViewById(R.id.ayudaDocumentacion))
				.setOnClickListener(this);
		((Button) this.findViewById(R.id.ayudaForo)).setOnClickListener(this);
		((Button) this.findViewById(R.id.ayudaTareas)).setOnClickListener(this);
		((Button) this.findViewById(R.id.ayudaParticipantes))
				.setOnClickListener(this);
		presentadorAyuda.actualizaPreferencias();
		invalidateOptionsMenu();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_ayuda, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menu_configuracion) {
			presentadorAyuda.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_acerca) {
			presentadorAyuda.tratarMenu(2);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void crearAlerta(String titulo, String ayuda) {
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle(titulo);
		alerta.setMessage(ayuda);
		alerta.setPositiveButton("OK", null);
		alerta.show();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(RelativeLayout) this.findViewById(R.id.RelativeLayout1), tipo);
		Formato.cambiarTypeface(this,
				(ScrollView) this.findViewById(R.id.ScrollView1), tipo);
	}

	@Override
	public void onClick(View v) {
		presentadorAyuda.solicitarAyuda(v.getId());

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1)
			presentadorAyuda.actualizaPreferencias();
	}
}
