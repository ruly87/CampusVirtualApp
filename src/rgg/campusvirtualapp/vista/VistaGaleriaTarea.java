package rgg.campusvirtualapp.vista;

import java.util.ArrayList;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorGaleriaTarea;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class VistaGaleriaTarea extends Activity implements IVistaGaleriaTarea,
		OnClickListener {
	private AppMediador appMediador;
	private IPresentadorGaleriaTarea presentadorGaleriaTarea;
	private LinearLayout galeriaTarea;
	private ProgressDialog barra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.galeriatareas_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaGaleriaTarea(this);
		presentadorGaleriaTarea = appMediador.getPresentadorGaleriaTarea();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		setContentView(R.layout.galeriatareas_vista);
		this.findViewById(R.id.inicio).setOnClickListener(this);
		galeriaTarea = (LinearLayout) this
				.findViewById(R.id.linearGaleriaTarea);
		presentadorGaleriaTarea.recuperarTareas();
		presentadorGaleriaTarea.actualizaPreferencias();
		invalidateOptionsMenu();
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
			presentadorGaleriaTarea.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorGaleriaTarea.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorGaleriaTarea.tratarMenu(3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setListTareas(Object listaTareas) {
		for (int i = 0; i < ((ArrayList<LinearLayout>) listaTareas).size(); i++)
			galeriaTarea
					.addView(((ArrayList<LinearLayout>) listaTareas).get(i));
	}

	@Override
	public void onClick(View v) {
		if (v.equals(this.findViewById(R.id.inicio)))
			presentadorGaleriaTarea.volverInicio();

	}

	@Override
	public void tratarTarea(View v) {
		presentadorGaleriaTarea.tratarTarea(v.getId());
	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setMessage(getString(R.string.mensaje_gtarea));
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
			presentadorGaleriaTarea.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(this,
				(LinearLayout) this.findViewById(R.id.viewgaleriatarea), tipo);
	}
}
