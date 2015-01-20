package rgg.campusvirtualapp.vista;

import java.util.ArrayList;

import rgg.campusvirtualapp.AppMediador;
import rgg.campusvirtualapp.presentador.IPresentadorGaleriaDocumentacion;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class VistaGaleriaDocumentacion extends Activity implements
		IVistaGaleriaDocumentacion, OnClickListener {
	private AppMediador appMediador;
	private LinearLayout galeriaDoc;
	private ProgressDialog barra;
	private IPresentadorGaleriaDocumentacion presentadorGaleriaDocumentacion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.galeriadocumentacion_vista);
		appMediador = AppMediador.getInstance();
		appMediador.setVistaGaleriaDocumentacion(this);
		presentadorGaleriaDocumentacion = appMediador
				.getPresentadorGaleriaDocumentacion();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		setContentView(R.layout.galeriadocumentacion_vista);
		this.findViewById(R.id.inicio).setOnClickListener(this);
		galeriaDoc = (LinearLayout) this
				.findViewById(R.id.linearGaleriaDocumentacion);
		presentadorGaleriaDocumentacion.recuperarSemanas();
		presentadorGaleriaDocumentacion.actualizaPreferencias();
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
			presentadorGaleriaDocumentacion.tratarMenu(1);
			return true;
		}
		if (id == R.id.menu_ayuda) {
			presentadorGaleriaDocumentacion.tratarMenu(2);
			return true;
		}
		if (id == R.id.menu_notificacion) {
			presentadorGaleriaDocumentacion.tratarMenu(3);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if (v.equals(this.findViewById(R.id.inicio))) {
			presentadorGaleriaDocumentacion.volverInicio();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void construirGaleria(Object galeria) {
		for (int i = 0; i < ((ArrayList<LinearLayout>) galeria).size(); i++)
			galeriaDoc.addView(((ArrayList<LinearLayout>) galeria).get(i));
	}

	@Override
	public void tratarSemana(View v) {
		presentadorGaleriaDocumentacion.tratarSemana(v.getId());
	}

	@Override
	public void mostrarProgreso() {
		barra = new ProgressDialog(this);
		barra.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		barra.setIndeterminate(true);
		barra.setMessage(getString(R.string.mensaje_gdocumentacion));
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
			presentadorGaleriaDocumentacion.actualizaPreferencias();
	}

	@Override
	public void tratarFormato(Object tipo) {
		Formato.cambiarTypeface(
				this,
				(LinearLayout) this.findViewById(R.id.viewgaleriaDocumentacion),
				tipo);
	}

}
