package rgg.campusvirtualapp.vista;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorPaginaAsignatura;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VistaPaginaAsignatura extends Activity implements
		IVistaPaginaAsignatura, OnClickListener {
	private AppMediador appMediador;
	private TextView nombreAsignatura;
	private ProgressDialog barra;
	private IPresentadorPaginaAsignatura presentadorPaginaAsignatura;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paginaasignatura_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaPaginaAsignatura(this);
		presentadorPaginaAsignatura = appMediador
				.getPresentadorPaginaAsignatura();

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.paginaasignatura_vista);
		nombreAsignatura = (TextView) this
				.findViewById(R.id.texto_pagina_asignatura);
		presentadorPaginaAsignatura.recuperarAsignatura(getIntent());
		this.findViewById(R.id.boton_documentacion).setOnClickListener(this);
		this.findViewById(R.id.boton_foroAsignatura).setOnClickListener(this);
		this.findViewById(R.id.boton_participantes).setOnClickListener(this);
		this.findViewById(R.id.boton_tareas).setOnClickListener(this);
		presentadorPaginaAsignatura.actualizaPreferencias();
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
			presentadorPaginaAsignatura.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorPaginaAsignatura.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorPaginaAsignatura.tratarMenu(3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		appMediador.getPresentadorPaginaAsignatura().tratarItem(v);

	}

	@Override
	public void setTituloAsignatura(String nombreAsignatura) {
		this.nombreAsignatura.setText(nombreAsignatura);
		this.nombreAsignatura.setGravity(0x11);
	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setCancelable(false);
		barra.setMessage(getString(R.string.mensaje_pagina_asignatura));
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
			presentadorPaginaAsignatura.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(LinearLayout) this.findViewById(R.id.viewpaginaasignatura),
				tipo);
	}

}
